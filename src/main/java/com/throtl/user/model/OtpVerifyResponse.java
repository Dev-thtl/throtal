package com.throtl.user.model;

import com.throtl.user.entity.UserProfile;
import com.throtl.user.payload.response.JwtResponse;

public class OtpVerifyResponse {

    private String MobileNumber;
    private boolean isValidOtp;

    private String msg;

//    private boolean registeredUser;

//    private UserProfile userProfile;

    private JwtResponse jwtResponse;


    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public boolean isValidOtp() {
        return isValidOtp;
    }

    public void setValidOtp(boolean validOtp) {
        isValidOtp = validOtp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public boolean isRegisteredUser() {
//        return registeredUser;
//    }
//
//    public void setRegisteredUser(boolean registeredUser) {
//        this.registeredUser = registeredUser;
//    }

//    public UserProfile getUserProfile() {
//        return userProfile;
//    }
//
//    public void setUserProfile(UserProfile userProfile) {
//        this.userProfile = userProfile;
//    }
}
