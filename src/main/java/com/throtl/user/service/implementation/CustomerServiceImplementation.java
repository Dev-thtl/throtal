package com.throtl.user.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.throtl.clientModel.RSAPurchasedDataRequest;
import com.throtl.otp.OTPUtil;
import com.throtl.user.entity.MembershipPurchaseTxnData;
import com.throtl.user.entity.RefreshToken;
import com.throtl.user.entity.UserProfile;
import com.throtl.user.entity.UserTokenDetails;
import com.throtl.user.model.*;
import com.throtl.user.models.User;
import com.throtl.user.payload.response.JwtResponse;
import com.throtl.user.repository.MembershipPurchaseTxnDataRepository;
import com.throtl.user.repository.UserProfileRepository;
import com.throtl.user.repository.UserRepository;
import com.throtl.user.repository.UserTokenDetailsRepository;
import com.throtl.user.security.jwt.JwtUtils;
import com.throtl.user.security.services.UserDetailsImpl;
import com.throtl.user.service.CustomerService;
import com.throtl.user.util.CommonUtil;
import com.throtl.user.util.ResponseUtil;
import com.throtl.user.util.RestUtil;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(value ="customerService")
public class CustomerServiceImplementation implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImplementation.class);
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    UserProfileRepository userProfileRepository;



    @Autowired
    CommonUtil commonUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserTokenDetailsRepository userTokenDetailsRepository;

    @Autowired
    MembershipPurchaseTxnDataRepository membershipPurchaseTxnDataRepository;

    @Override
    public ResponseEntity<Object> validateRegisterPhoneNumberV1(VerifyRegisteredUserRequest verifyRegisteredUserRequest, Boolean isEncrypted) {

        VerifyRegisteredUserResponse verifyRegisteredUserResponse = null;

        try {
            verifyRegisteredUserResponse = new VerifyRegisteredUserResponse();
            String mobileNo = StringUtils.right(verifyRegisteredUserRequest.getMobileNumber(), 10);
            UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(mobileNo);
           // logger.info("User Profile: {}", userProfile.toString());

            Boolean flag = true;
            if(null!=userProfile) {
//                verifyRegisteredUserResponse.setRegisteredUser(true);
//                verifyRegisteredUserResponse.setFirstName(null!=userProfile.getFirstName()?userProfile.getFirstName():"");
//                verifyRegisteredUserResponse.setLastName(null!=userProfile.getLastName()?userProfile.getLastName():"");
                verifyRegisteredUserResponse.setUserOid(userProfile.getUserId());

            }else {
//                verifyRegisteredUserResponse.setRegisteredUser(false);
            }
            SendOtpDetails sendOtpDetails = new SendOtpDetails();
            sendOtpDetails.setMobileNumber(verifyRegisteredUserRequest.getMobileNumber());
            String otpMobileNumber = verifyRegisteredUserRequest.getCountryCode()+ verifyRegisteredUserRequest.getMobileNumber();
            logger.info("Send OTP Mobile Number: {}", otpMobileNumber);

            sendOtpDetails =  OTPUtil.sendOtp(otpMobileNumber, sendOtpDetails);
//            logger.info("Number----"+unregNumber.getPhoneNumber());
//            applogger.info("Response body for /V3/validate/phoneNumber" + "---"
//                    + mapper.writeValueAsString(unregNumber));
            logger.info("OTP Details : ", sendOtpDetails.toString());
            verifyRegisteredUserResponse.setSendOtpDetails(sendOtpDetails);



            JSONObject proshopCategoryResponse = new JSONObject();
            JSONObject json = new JSONObject(sendOtpDetails);
//            proshopCategoryResponse.put("response", new JSONObject());
//            proshopCategoryResponse.put("success", json.get("success"));


//           return ResponseUtil.setEncryptedResponse(json);
                return new ResponseEntity<>(verifyRegisteredUserResponse, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
//            logger.error("@@@ Exception in CustomerServiceImplementation at validateRegisterPhoneNumber", e);
        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> verifyOtp(OtpVerificationRequest otpVerificationRequest, Boolean isEncrypted) {

        OtpVerifyResponse otpVerifyResponse;
       try {
           otpVerifyResponse =  OTPUtil.verifyOtp(otpVerificationRequest);
           logger.info("OTP Validation Response : ", objectMapper.writeValueAsString(otpVerifyResponse));
           String mobileNo = StringUtils.right(otpVerificationRequest.getMobileNumber(), 10);

           PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           String encodedPassword = passwordEncoder.encode(otpVerificationRequest.getOtp());
           if(otpVerifyResponse.isValidOtp()){
//           if(true){

               UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(mobileNo);
               Boolean flag = true;

               if(null!=userProfile) {
//               otpVerifyResponse.setRegisteredUser(true);
                   otpVerifyResponse.setUserProfile(userProfile);
                   otpVerifyResponse.setMsg("Registered User");
               }else{
                   otpVerifyResponse.setMsg("User Not Registered");
               }



               Optional<User> user = userRepository.findByUsername(mobileNo);
               if(user.isPresent()) {
//                   user.setUsername(mobileNo);
                   user.get().setPassword(encodedPassword);
                   userRepository.save(user.get());
               }else{
                   User user1 = new User();
                   user1.setUsername(mobileNo);
                   user1.setPassword(encodedPassword);
                   userRepository.save(user1);

               }

//               userRepository.save(user.get());

               Authentication authentication = authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(mobileNo, otpVerificationRequest.getOtp()));

               SecurityContextHolder.getContext().setAuthentication(authentication);
               String jwt = jwtUtils.generateJwtToken(authentication);

               UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
               RefreshToken refreshToken = jwtUtils.createRefreshToken();
//               return ResponseEntity.ok(JwtResponse.builder()
//                       .accessToken(jwt)
//                       .accessTokenExpiry(new Date().toInstant())
//                       .token(refreshToken.getToken())
//                       .refreshTokenExpiry(refreshToken.getExpiryDate())
//                       .build());

             JwtResponse  token = (JwtResponse.builder()
                       .accessToken(jwt)
                       .accessTokenExpiry(new Date().toInstant())
                       .token(refreshToken.getToken())
                       .refreshTokenExpiry(refreshToken.getExpiryDate())
                       .build());

               otpVerifyResponse.setJwtResponse(token);

               UserTokenDetails userTokenDetails = new UserTokenDetails();
               userTokenDetails.setMobileNumber(mobileNo);
               userTokenDetails.setAccessToken(token.getAccessToken());
               userTokenDetails.setAccessTokenExpiry(token.getAccessTokenExpiry().toString());
               userTokenDetails.setRefreshToken(token.getToken());
//               userTokenDetails.setRefreshTokenExpiry(token.getRefreshTokenExpiry().toString());
               userTokenDetails.setOtp(otpVerificationRequest.getOtp());

               userTokenDetailsRepository.save(userTokenDetails);

           }

           return new ResponseEntity<>(otpVerifyResponse, HttpStatus.OK);
       }catch (Exception e){
           System.out.println(e);


       }

        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<Object> userRegistration(UserRegistrationRequest userRegistrationRequest, Boolean isEncrypted) {

        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        try{
            String mobileNo = StringUtils.right(userRegistrationRequest.getMobileNumber(), 10);
            UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(mobileNo);
            Boolean flag = true;
            if(null!=userProfile) {
//                userRegistrationResponse.setRegistredUser(true);
                userRegistrationResponse.setUserRegistration("Failed");
                userRegistrationResponse.setMsg("User Already Registered");

            }else {
                userProfile = new UserProfile();
//                userRegistrationResponse.setRegistredUser(false);
                userProfile.setFirstName(userRegistrationRequest.getFirstName());
                userProfile.setLastName(userRegistrationRequest.getLastName());
                userProfile.setMobileNumber(userRegistrationRequest.getMobileNumber());
                userProfile.setEmailId(userRegistrationRequest.getEmail());
                userProfile.setVehicleRegNumber(userRegistrationRequest.getRsaDetails().getVehicleRegistrationNumber());
                userProfile.setVehicleNumber(userRegistrationRequest.getRsaDetails().getVehicleNumber());
                userProfile.setVehicleModel(userRegistrationRequest.getRsaDetails().getVehicleModel());
                userProfile.setRsaEmail(userRegistrationRequest.getRsaDetails().getRsaEmail());

                userProfileRepository.save(userProfile);

                userRegistrationResponse.setUserRegistration("Success");
                userRegistrationResponse.setMsg("User Registered Successfully");
//                userRegistrationResponse.setRegistredUser(true);

            }
            return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);

        }catch (Exception e){
            
            System.out.println(e);
        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> getUserRSADetails(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted) {

        try {


            JSONObject str = RestUtil.rsaClientCall("get-user-details", userRegistrationRequest.getMobileNumber());

            return new ResponseEntity<>(str.toString(), HttpStatus.OK);
        }catch (Exception e){


        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> getRsaStateList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted) {
        try {


            JSONObject str = RestUtil.rsaClientCall("get-master-state-list", userRegistrationRequest.getMobileNumber());

            return new ResponseEntity<>(str.toString(), HttpStatus.OK);
        }catch (Exception e){


        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> getRsaMembershipList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted) {
        try {


            JSONObject str = RestUtil.rsaClientCall("get-master-rsa-membership-list", userRegistrationRequest.getMobileNumber());

            return new ResponseEntity<>(str.toString(), HttpStatus.OK);
        }catch (Exception e){


        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> getRsaBrandList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted) {
        try {


            JSONObject str = RestUtil.rsaClientCall("get-master-brand-list", userRegistrationRequest.getMobileNumber());

            return new ResponseEntity<>(str.toString(), HttpStatus.OK);
        }catch (Exception e){


        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> getRsaModelList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted) {
        try {


            JSONObject str = RestUtil.rsaClientCall("get-master-model-list", userRegistrationRequest.getBrandId());

            return new ResponseEntity<>(str.toString(), HttpStatus.OK);
        }catch (Exception e){


        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> rsaPurchase(RSAPurchasedDataRequest rsaPurchasedDataRequest, Boolean isEncrypted) {

        try {


            JSONObject str = RestUtil.rsaPurchasedClientCall("user-membership-purchase", rsaPurchasedDataRequest);

            return new ResponseEntity<>(str.toString(), HttpStatus.OK);
        }catch (Exception e){


        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<Object> saveTransactionData(TransactionData transactionData, Boolean isEncrypted) {

        ResponseUtil responseUtil = new ResponseUtil();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Format the current date
            String formattedDate = currentDate.format(formatter);

            String transactionId = "RSAMP" + transactionData.getUser_id() + formattedDate + commonUtil.generateRandomString(6);

            MembershipPurchaseTxnData membershipPurchaseTxnData = new MembershipPurchaseTxnData();

            membershipPurchaseTxnData.setTransactionId(transactionId);
            membershipPurchaseTxnData.setTransactionStatus("0");
            membershipPurchaseTxnData.setMobileNumber(transactionData.getMobile_number());
            membershipPurchaseTxnData.setMembershipName(transactionData.getMembership_name());
            membershipPurchaseTxnData.setMembershipId(transactionData.getMembership_id());
            membershipPurchaseTxnData.setMembershipAmount(transactionData.getMembership_amount());
            membershipPurchaseTxnData.setMembershipDuration(transactionData.getMembership_duration());
            membershipPurchaseTxnData.setUserId(transactionData.getUser_id());
            membershipPurchaseTxnData.setOrderId(transactionData.getOrder_id());
            membershipPurchaseTxnData.setPaymentGatewayKey(transactionData.getPayment_gateway_key());
            membershipPurchaseTxnData.setTransactionStatusDesc("Transaction Review Pending with RSA");
            membershipPurchaseTxnData.setStateId(transactionData.getState_id());
            membershipPurchaseTxnData.setCityName(transactionData.getCity_name());
            membershipPurchaseTxnData.setVehicleBrand(transactionData.getVehicle_brand());
            membershipPurchaseTxnData.setVehicleModel(transactionData.getVehicle_model());
            membershipPurchaseTxnData.setVehicleNo(transactionData.getVehicle_no());


            MembershipPurchaseTxnData saveMembershipPurchaseTxnData =  membershipPurchaseTxnDataRepository.save(membershipPurchaseTxnData);

            responseUtil.setCode(200);
            responseUtil.setMsg("Date saved Successfully");
            if(saveMembershipPurchaseTxnData.getOid() != null){
                Runnable runnable = () -> {

                    OTPUtil.sendTransactionDataEmail(transactionId, transactionData);


                };

                Thread thread = new Thread(runnable);
                thread.start();



            }
            return new ResponseEntity<>(responseUtil, HttpStatus.OK);

        }catch (Exception e){
            responseUtil.setCode(500);
            responseUtil.setMsg("Failure");

        }
        return new ResponseEntity<>(responseUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> getUserTransactionDetails(GetUserTransactionDetailsRequest getUserTransactionDetailsRequest, Boolean isEncrypted) {

        ResponseUtil responseUtil = new ResponseUtil();
        try {

            GetUserTransactionDetailsResponse getUserTransactionDetailsResponse;

            List<MembershipPurchaseTxnData> membershipPurchaseTxnDataList = membershipPurchaseTxnDataRepository
                    .getByMobileNumberAndUserId(getUserTransactionDetailsRequest.getMobile_number(),
                            getUserTransactionDetailsRequest.getUser_id());


            List<GetUserTransactionDetailsResponse> getUserTransactionDetailsResponsesList = new ArrayList<>();

            for(MembershipPurchaseTxnData membershipPurchaseTxnData : membershipPurchaseTxnDataList) {
                getUserTransactionDetailsResponse = new GetUserTransactionDetailsResponse();
                getUserTransactionDetailsResponse.setUser_id(membershipPurchaseTxnData.getUserId());
                getUserTransactionDetailsResponse.setMobile_number(membershipPurchaseTxnData.getMobileNumber());
                getUserTransactionDetailsResponse.setOrder_id(membershipPurchaseTxnData.getOrderId());
                getUserTransactionDetailsResponse.setPayment_gateway_key(membershipPurchaseTxnData.getPaymentGatewayKey());
                getUserTransactionDetailsResponse.setState_id(membershipPurchaseTxnData.getStateId());
                getUserTransactionDetailsResponse.setCity_name(membershipPurchaseTxnData.getCityName());
                getUserTransactionDetailsResponse.setVehicle_brand(membershipPurchaseTxnData.getVehicleBrand());
                getUserTransactionDetailsResponse.setVehicle_model(membershipPurchaseTxnData.getVehicleModel());
                getUserTransactionDetailsResponse.setVehicle_no(membershipPurchaseTxnData.getVehicleNo());
                getUserTransactionDetailsResponse.setMembership_id(membershipPurchaseTxnData.getMembershipId());
                getUserTransactionDetailsResponse.setMembership_amount(membershipPurchaseTxnData.getMembershipAmount());
                getUserTransactionDetailsResponse.setMembership_duration(membershipPurchaseTxnData.getMembershipDuration());
                getUserTransactionDetailsResponse.setMembership_name(membershipPurchaseTxnData.getMembershipName());
                getUserTransactionDetailsResponse.setTransaction_status(membershipPurchaseTxnData.getTransactionStatus());
                getUserTransactionDetailsResponse.setTransaction_desc(membershipPurchaseTxnData.getTransactionStatusDesc());

                getUserTransactionDetailsResponsesList.add(getUserTransactionDetailsResponse);
            }

            responseUtil.setCode(200);
            responseUtil.setMsg("Success");
            responseUtil.setDate(getUserTransactionDetailsResponsesList);

            return new ResponseEntity<>(responseUtil, HttpStatus.OK);

        }catch (Exception e){

            responseUtil.setCode(500);
            responseUtil.setMsg("Failure");
            System.out.println(e);
        }
        return new ResponseEntity<>(responseUtil, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<Object> getUserProfileDetails(ProfileDetailsRequest profileDetailsRequest, Boolean isEncrypted) {
        ResponseUtil responseUtil = new ResponseUtil();
        try{

            UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(profileDetailsRequest.getMobile_number());

            ProfileDetailsResponse profileDetailsResponse = new ProfileDetailsResponse();

            profileDetailsResponse.setUser_id(String.valueOf(userProfile.getUserId()));
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            profileDetailsResponse.setCreated_time(outputFormat.format(userProfile.getCreatedTime()));

            profileDetailsResponse.setUpdate_time(outputFormat.format(userProfile.getUpdateTime()));
            profileDetailsResponse.setRsa_id(userProfile.getRsaId());
            profileDetailsResponse.setTitle(userProfile.getTitle() != null ?userProfile.getTitle().name(): "");
            profileDetailsResponse.setFirst_name(userProfile.getFirstName());
            profileDetailsResponse.setMiddle_name(userProfile.getMiddleName());
            profileDetailsResponse.setLast_name(userProfile.getLastName());
            profileDetailsResponse.setEmail_id(userProfile.getEmailId());
            profileDetailsResponse.setCountry_code(userProfile.getCountryCode());
            profileDetailsResponse.setMobile_number(userProfile.getMobileNumber());

            RSADetails rsaDetails = new RSADetails();
            rsaDetails.setVehicleRegistrationNumber(userProfile.getVehicleRegNumber());
            rsaDetails.setVehicleNumber(userProfile.getVehicleNumber());
            rsaDetails.setVehicleModel(userProfile.getVehicleModel());
            rsaDetails.setRsaEmail(userProfile.getRsaEmail());

            profileDetailsResponse.setRsaDetails(rsaDetails);
            responseUtil.setCode(200);
            responseUtil.setMsg("Success");
            responseUtil.setDate(profileDetailsResponse);

            return new ResponseEntity<>(responseUtil, HttpStatus.OK);

        }catch (Exception e){
            responseUtil.setCode(500);
            responseUtil.setMsg("Failure");
            responseUtil.setDate(null);
            System.out.println(e);
        }
        return new ResponseEntity<>(responseUtil, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<Object> deleteUserProfile(ProfileDetailsRequest profileDetailsRequest, Boolean isEncrypted) {

        ResponseUtil responseUtil = new ResponseUtil();
        try {
            userProfileRepository.deleteUserProfile(profileDetailsRequest.getMobile_number());
            responseUtil.setCode(200);
            responseUtil.setMsg("Success");
            return new ResponseEntity<>(responseUtil, HttpStatus.OK);

        }catch (Exception e){
        responseUtil.setCode(500);
        responseUtil.setMsg("Failure");
        responseUtil.setDate(null);
        System.out.println(e);
    }
        return new ResponseEntity<>(responseUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
