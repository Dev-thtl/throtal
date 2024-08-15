package com.throtl.clientModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserRsaDetails {

    private String mobile_no;

    @JsonIgnore
    private int brand_id;

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }
}
