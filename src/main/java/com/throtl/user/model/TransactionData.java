package com.throtl.user.model;

public class TransactionData {

    private String user_id;
    private String mobile_number;
    private String order_id;
    private String payment_gateway_key;
    private String state_id;
    private String city_name;
    private String vehicle_brand;
    private String vehicle_model;
    private String vehicle_no;
    private String membership_name;
    private String membership_id;
    private String membership_amount;
    private String membership_duration;


    public String getMembership_name() {
        return membership_name;
    }

    public void setMembership_name(String membership_name) {
        this.membership_name = membership_name;
    }

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    public String getMembership_amount() {
        return membership_amount;
    }

    public void setMembership_amount(String membership_amount) {
        this.membership_amount = membership_amount;
    }

    public String getMembership_duration() {
        return membership_duration;
    }

    public void setMembership_duration(String membership_duration) {
        this.membership_duration = membership_duration;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_gateway_key() {
        return payment_gateway_key;
    }

    public void setPayment_gateway_key(String payment_gateway_key) {
        this.payment_gateway_key = payment_gateway_key;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }
}
