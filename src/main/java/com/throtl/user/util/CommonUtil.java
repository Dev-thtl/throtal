package com.throtl.user.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


//    public static ResponseEntity<Object> setEncryptedResponse(JSONObject json) throws JSONException {
//        JSONObject response = new JSONObject();
//        json.put("timestamp", System.currentTimeMillis());
//        response.put(CoreConstants.DATA, CansCrypt.encrypt(json.toString()));
//        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
//    }
}
