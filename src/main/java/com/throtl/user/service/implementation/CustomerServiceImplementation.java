package com.throtl.user.service.implementation;

import com.throtl.otp.OTPUtil;
import com.throtl.user.entity.RefreshToken;
import com.throtl.user.entity.UserProfile;
import com.throtl.user.entity.UserTokenDetails;
import com.throtl.user.model.*;
import com.throtl.user.models.User;
import com.throtl.user.payload.response.JwtResponse;
import com.throtl.user.repository.UserProfileRepository;
import com.throtl.user.repository.UserRepository;
import com.throtl.user.repository.UserTokenDetailsRepository;
import com.throtl.user.security.jwt.JwtUtils;
import com.throtl.user.security.services.UserDetailsImpl;
import com.throtl.user.service.CustomerService;
import com.throtl.user.util.CommonUtil;
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

import java.util.Date;
import java.util.Optional;

@Service(value ="customerService")
public class CustomerServiceImplementation implements CustomerService {

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

    @Override
    public ResponseEntity<Object> validateRegisterPhoneNumberV1(VerifyRegisteredUserRequest verifyRegisteredUserRequest, Boolean isEncrypted) {

        VerifyRegisteredUserResponse verifyRegisteredUserResponse = null;

        try {
            verifyRegisteredUserResponse = new VerifyRegisteredUserResponse();
            String mobileNo = StringUtils.right(verifyRegisteredUserRequest.getMobileNumber(), 10);
            UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(mobileNo);
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
            sendOtpDetails.setMobileNumber(verifyRegisteredUserRequest.getCountryCode()+ verifyRegisteredUserRequest.getMobileNumber());
            sendOtpDetails =  OTPUtil.sendOtp(sendOtpDetails);
//            logger.info("Number----"+unregNumber.getPhoneNumber());
//            applogger.info("Response body for /V3/validate/phoneNumber" + "---"
//                    + mapper.writeValueAsString(unregNumber));

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
               }else{
                   user.get().setUsername(mobileNo);
                   user.get().setPassword(encodedPassword);

               }

               userRepository.save(user.get());

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

        String str = RestUtil.rsaClientCall();


        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
