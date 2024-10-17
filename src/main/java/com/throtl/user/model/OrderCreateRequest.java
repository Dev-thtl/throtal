package com.throtl.user.model;

public class OrderCreateRequest {

    private String user_id;
    private String order_summary;
    private String blood_group;
    private String quantity;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_summary() {
        return order_summary;
    }

    public void setOrder_summary(String order_summary) {
        this.order_summary = order_summary;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
