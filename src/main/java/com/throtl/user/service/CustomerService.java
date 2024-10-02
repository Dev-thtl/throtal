package com.throtl.user.service;

import com.throtl.clientModel.RSAPurchasedDataRequest;
import com.throtl.user.model.*;
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
ResponseEntity<Object> saveTransactionData(TransactionData transactionData, Boolean isEncrypted);
ResponseEntity<Object> getUserTransactionDetails(GetUserTransactionDetailsRequest getUserTransactionDetailsRequest, Boolean isEncrypted);
ResponseEntity<Object> getUserProfileDetails(ProfileDetailsRequest profileDetailsRequest, Boolean isEncrypted);

}
