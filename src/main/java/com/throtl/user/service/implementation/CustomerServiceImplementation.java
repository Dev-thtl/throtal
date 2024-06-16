package com.throtl.user.service.implementation;

import com.throtl.otp.OTPUtil;
import com.throtl.user.entity.UserProfile;
import com.throtl.user.model.*;
import com.throtl.user.repository.UserProfileRepository;
import com.throtl.user.service.CustomerService;
import com.throtl.user.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service(value ="customerService")
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    UserProfileRepository userProfileRepository;



    @Autowired
    CommonUtil commonUtil;


    @Override
    public ResponseEntity<Object> validateRegisterPhoneNumberV1(VerifyRegisteredUserRequest verifyRegisteredUserRequest, Boolean isEncrypted) {

        VerifyRegisteredUserResponse verifyRegisteredUserResponse = null;

        try {
            verifyRegisteredUserResponse = new VerifyRegisteredUserResponse();
            UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(verifyRegisteredUserRequest.getMobileNumber());
            Boolean flag = true;
            if(null!=userProfile) {
                verifyRegisteredUserResponse.setRegisteredUser(true);
                verifyRegisteredUserResponse.setFirstName(null!=userProfile.getFirstName()?userProfile.getFirstName():"");
                verifyRegisteredUserResponse.setLastName(null!=userProfile.getLastName()?userProfile.getLastName():"");
                verifyRegisteredUserResponse.setUserOid(userProfile.getOid());

            }else {
                verifyRegisteredUserResponse.setRegisteredUser(false);
            }
            SendOtpDetails sendOtpDetails = new SendOtpDetails();
            sendOtpDetails.setMobileNumber(verifyRegisteredUserRequest.getMobileNumber());
            sendOtpDetails =  OTPUtil.sendOtp(sendOtpDetails);
//            logger.info("Number----"+unregNumber.getPhoneNumber());
//            applogger.info("Response body for /V3/validate/phoneNumber" + "---"
//                    + mapper.writeValueAsString(unregNumber));

            verifyRegisteredUserResponse.setSendOtpDetails(sendOtpDetails);
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


           return new ResponseEntity<>(otpVerifyResponse, HttpStatus.OK);
       }catch (Exception e){


       }

        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<Object> userRegistration(UserRegistrationRequest userRegistrationRequest, Boolean isEncrypted) {

        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        try{
            UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(userRegistrationRequest.getMobileNumber());
            Boolean flag = true;
            if(null!=userProfile) {
                userRegistrationResponse.setRegistredUser(true);
                userRegistrationResponse.setUserRegistration("Failed");
                userRegistrationResponse.setMsg("User Already Registered");

            }else {
                userProfile = new UserProfile();
                userRegistrationResponse.setRegistredUser(false);
                userProfile.setFirstName(userRegistrationRequest.getFirstName());
                userProfile.setLastName(userRegistrationRequest.getLastName());
                userProfile.setMobileNumber(userRegistrationRequest.getMobileNumber());
                userProfile.setEmailId(userRegistrationRequest.getEmail());

                userProfileRepository.save(userProfile);

                userRegistrationResponse.setUserRegistration("Success");
                userRegistrationResponse.setMsg("User Registered Successfully");
                userRegistrationResponse.setRegistredUser(true);

            }
            return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);

        }catch (Exception e){

            System.out.println(e);


        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
