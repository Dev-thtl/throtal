package com.throtl.user.rsaModel;

import java.util.List;

public class Data {

    private String mobile_no;
    private List<Policy> policy_list;
    private String id;
    private String fullname;
    private String full_address;
    private String email_addr;

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public List<Policy> getPolicy_list() {
        return policy_list;
    }

    public void setPolicy_list(List<Policy> policy_list) {
        this.policy_list = policy_list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr;
    }
}
