package com.throtl.user.model;

public class ProfileDetailsResponse {

    private String user_id;
    private String created_time;
    private String update_time;
    private String rsa_id;
    private String title;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String email_id;
    private String country_code;
    private String mobile_number;

    private RSADetails rsaDetails;

    private UserAddress userAddress;

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public RSADetails getRsaDetails() {
        return rsaDetails;
    }

    public void setRsaDetails(RSADetails rsaDetails) {
        this.rsaDetails = rsaDetails;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getRsa_id() {
        return rsa_id;
    }

    public void setRsa_id(String rsa_id) {
        this.rsa_id = rsa_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
