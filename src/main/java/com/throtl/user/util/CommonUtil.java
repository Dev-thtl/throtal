package com.throtl.user.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

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


    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    // Method to generate a random alphanumeric string
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
