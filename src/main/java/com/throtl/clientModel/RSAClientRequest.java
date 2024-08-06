package com.throtl.clientModel;

public class RSAClientRequest {

    private String api;
    private UserRsaDetails request_data;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public UserRsaDetails getRequest_data() {
        return request_data;
    }

    public void setRequest_data(UserRsaDetails request_data) {
        this.request_data = request_data;
    }
}
