package com.throtl.clientModel;

public class RSAPurchaseClientRequest {

    private String api;
    private RSAPurchasedDataRequest request_data;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public RSAPurchasedDataRequest getRequest_data() {
        return request_data;
    }

    public void setRequest_data(RSAPurchasedDataRequest request_data) {
        this.request_data = request_data;
    }
}
