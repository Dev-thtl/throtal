package com.throtl.otp;

import com.otpless.authsdk.OTPAuth;
import com.otpless.authsdk.OTPResponse;
import com.otpless.authsdk.OTPVerificationResponse;
import com.throtl.user.model.OtpVerificationRequest;
import com.throtl.user.model.OtpVerifyResponse;
import com.throtl.user.model.SendOtpDetails;

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

}
