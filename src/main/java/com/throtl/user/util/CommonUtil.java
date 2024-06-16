package com.throtl.user.util;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
@Component
public class CommonUtil {


    private String strTimeStamp = "timestamp";
    private String strMessage = "message";

    public Map<String, Object> getInternalServerError() {

        Map<String, Object> json = new LinkedHashMap<>();

        try {
            json.put(strTimeStamp, new Date());
            json.put(strMessage, "failure");
        } catch (Exception e) {
//            logger.error("", e);
        }
        return json;
    }
}
