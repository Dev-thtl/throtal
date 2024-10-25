package com.throtl.user.model;

public class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;

    private RSADetails rsaDetails;

    private UserAddress userAddress;

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RSADetails getRsaDetails() {
        return rsaDetails;
    }

    public void setRsaDetails(RSADetails rsaDetails) {
        this.rsaDetails = rsaDetails;
    }
}
