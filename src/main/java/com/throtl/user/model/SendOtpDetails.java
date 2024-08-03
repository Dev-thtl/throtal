package com.throtl.user.model;

public class SendOtpDetails {

    private String otp;
    private String orderId;
    private boolean isOtpSent;
    private String mobileNumber;
    private String msg;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isOtpSent() {
        return isOtpSent;
    }

    public void setOtpSent(boolean otpSent) {
        isOtpSent = otpSent;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
