package com.throtl.user.util;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseUtil {

    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object date;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
