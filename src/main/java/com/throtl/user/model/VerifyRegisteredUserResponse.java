package com.throtl.user.model;

import com.throtl.core.CoreConstant;

public class VerifyRegisteredUserResponse {

    private String firstName= CoreConstant.EMPTY;
    private String lastName=CoreConstant.EMPTY;
//    private Boolean isRegisteredUser;
    private Long userOid;

    private SendOtpDetails sendOtpDetails;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Boolean getRegisteredUser() {
//        return isRegisteredUser;
//    }
//
//    public void setRegisteredUser(Boolean registeredUser) {
//        isRegisteredUser = registeredUser;
//    }

    public Long getUserOid() {
        return userOid;
    }

    public void setUserOid(Long userOid) {
        this.userOid = userOid;
    }

    public SendOtpDetails getSendOtpDetails() {
        return sendOtpDetails;
    }

    public void setSendOtpDetails(SendOtpDetails sendOtpDetails) {
        this.sendOtpDetails = sendOtpDetails;
    }
}
