package com.throtl.user.model;

public class UserRegistrationResponse {

    private boolean isRegistredUser;
    private String userRegistration;
    private String msg;


    public boolean isRegistredUser() {
        return isRegistredUser;
    }

    public void setRegistredUser(boolean registredUser) {
        isRegistredUser = registredUser;
    }

    public String getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(String userRegistration) {
        this.userRegistration = userRegistration;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
