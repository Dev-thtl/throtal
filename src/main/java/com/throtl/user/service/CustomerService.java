package com.throtl.user.service;

import com.throtl.user.model.OtpVerificationRequest;
import com.throtl.user.model.UserRegistrationRequest;
import com.throtl.user.model.VerifyRegisteredUserRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerService {


    ResponseEntity<Object> validateRegisterPhoneNumberV1(VerifyRegisteredUserRequest verifyRegisteredUserRequest, Boolean isEncrypted);

    ResponseEntity<Object> verifyOtp(OtpVerificationRequest otpVerificationRequest, Boolean isEncrypted);

    ResponseEntity<Object> userRegistration(UserRegistrationRequest userRegistrationRequest, Boolean isEncrypted);
}
