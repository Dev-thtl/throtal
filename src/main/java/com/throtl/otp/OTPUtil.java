package com.throtl.otp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otpless.authsdk.OTPAuth;
import com.otpless.authsdk.OTPResponse;
import com.otpless.authsdk.OTPVerificationResponse;
import com.throtl.user.model.*;
import com.throtl.user.util.RestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OTPUtil {

    public static SendOtpDetails sendOtp(SendOtpDetails sendOtpDetails) {
    try
    {

        String clientId = "V60X1QKDGQHYEEGNET2L223EDBGLDQFK";
        String clientSecret = "5ul3arbte5kdjbq4gzs8q7q8boe6ze2e";
        OTPAuth otpAuth = new OTPAuth(clientId, clientSecret);
        int orderId = new Random().nextInt(900000) + 100000;
        OTPResponse otpResponse = otpAuth.sendOTP(String.valueOf(orderId), sendOtpDetails.getMobileNumber(), "neeraj.wibmo1062@gmail.com",
                "ABC1234", 60, 4, "SMS,EMAIL");

        if (otpResponse.isSuccess()) {
            System.out.println("OTP sent. orderId=> " + otpResponse.getOrderId());
            sendOtpDetails.setOrderId(otpResponse.getOrderId());
            sendOtpDetails.setOtpSent(otpResponse.isSuccess());
            sendOtpDetails.setMsg("OTP Sent Successfully");
        } else {
            System.out.println("OTP send to failed due to " + otpResponse.getErrorMessage());
            sendOtpDetails.setOtpSent(otpResponse.isSuccess());
            sendOtpDetails.setMsg(otpResponse.getErrorMessage());
        }
    } catch(
    Exception e)

    {
        // Handle exceptions
    }
    return sendOtpDetails;
}
public static OtpVerifyResponse verifyOtp(OtpVerificationRequest otpVerificationRequest){



    OtpVerifyResponse otpVerifyResponse = new OtpVerifyResponse();
    try {
            String clientId = "V60X1QKDGQHYEEGNET2L223EDBGLDQFK";
            String clientSecret = "5ul3arbte5kdjbq4gzs8q7q8boe6ze2e";
            OTPAuth otpAuth = new OTPAuth(clientId, clientSecret);
            OTPVerificationResponse otpVerificationResponse = otpAuth.verifyOTP(otpVerificationRequest.getOrderId(), otpVerificationRequest.getOtp(),
                    otpVerificationRequest.getMobileNumber(), "neeraj.wibmo1062@gmail.com");


//    if(otpVerificationResponse.getIsOTPVerified()){
            System.out.println("OTP Verification is : " + otpVerificationResponse.getIsOTPVerified());
            otpVerifyResponse.setValidOtp(otpVerificationResponse.getIsOTPVerified());
            otpVerifyResponse.setMobileNumber(otpVerificationRequest.getMobileNumber());
            otpVerifyResponse.setMsg(otpVerificationResponse.getReason());

//    }else{

//    }
        }catch (Exception e){

        }
        return otpVerifyResponse;
}

    public static String sendTransactionDataEmail(String transactionId, TransactionData transactionData) {
        try
        {

            String api_key = "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";
            // String clientSecret = "5ul3arbte5kdjbq4gzs8q7q8boe6ze2e";
//            OTPAuth otpAuth = new OTPAuth(clientId, clientSecret);
            String json = "{ \"sender\": { \"name\": \"Sender Alex\", \"email\": \"senderalex@example.com\" }, \"to\": [ { \"email\": \"testmail@example.com\", \"name\": \"John Doe\" } ], \"subject\": \"Hello world\", \"htmlContent\": \"<html><head></head><body><p>Hello,</p>This is my first transactional email sent from Brevo.</p></body></html>\" }";

            ObjectMapper mapper = new ObjectMapper();

            EmailRequest emailRequest = mapper.readValue(json, EmailRequest.class);




            emailRequest.setSubject("Transaction Details");
            emailRequest.setHtmlContent("<html><head></head><body><p>Hello,</p>This is my first transactional email sent from Brevo.</p></body></html>");
            Sender sender = new Sender();
            sender.setName("Neeraj Verma");
            sender.setEmail("neeraj.wibmo1062@gmail.com");
            emailRequest.setSender(sender);

            List<Recipient> recipientList = new ArrayList<>();
            Recipient recipient = new Recipient();
            recipient.setEmail("neeraj.techtree@gmail.com");
            recipient.setName("Neeraj Verma");
            recipientList.add(recipient);
            emailRequest.setTo(recipientList);

            String str = RestUtil.sendEmail(emailRequest);
            System.out.println(str);

        } catch(
                Exception e)

        {
            // Handle exceptions
        }
        return "";
    }



}
