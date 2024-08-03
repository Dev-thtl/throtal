package com.throtl.user.model;

public class RSADetails {

    private String vehicleRegistrationNumber;
    private String vehicleNumber;
    private String vehicleModel;

    private String rsaEmail;

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getRsaEmail() {
        return rsaEmail;
    }

    public void setRsaEmail(String rsaEmail) {
        this.rsaEmail = rsaEmail;
    }
}
