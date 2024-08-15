package com.throtl.user.service;

import com.throtl.clientModel.RSAPurchasedDataRequest;
import com.throtl.user.model.OtpVerificationRequest;
import com.throtl.user.model.UserRSADetailsRequest;
import com.throtl.user.model.UserRegistrationRequest;
import com.throtl.user.model.VerifyRegisteredUserRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerService {


    ResponseEntity<Object> validateRegisterPhoneNumberV1(VerifyRegisteredUserRequest verifyRegisteredUserRequest, Boolean isEncrypted);

    ResponseEntity<Object> verifyOtp(OtpVerificationRequest otpVerificationRequest, Boolean isEncrypted);

    ResponseEntity<Object> userRegistration(UserRegistrationRequest userRegistrationRequest, Boolean isEncrypted);

    ResponseEntity<Object> getUserRSADetails(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted);

    ResponseEntity<Object> getRsaStateList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted);
 ResponseEntity<Object> getRsaMembershipList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted);
ResponseEntity<Object> getRsaBrandList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted);
ResponseEntity<Object> getRsaModelList(UserRSADetailsRequest userRegistrationRequest, Boolean isEncrypted);
ResponseEntity<Object> rsaPurchase(RSAPurchasedDataRequest rsaPurchasedDataRequest, Boolean isEncrypted);

}
