package com.throtl.user.model;

public class OtpVerifyResponse {

    private String MobileNumber;
    private boolean isValidOtp;

    private String msg;

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
}
