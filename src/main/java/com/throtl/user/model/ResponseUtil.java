package com.throtl.user.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<Object> setEncryptedResponse(JSONObject json) throws JSONException {
        JSONObject response = new JSONObject();
        json.put("timestamp", System.currentTimeMillis());
        response.put("Data", (json.toString()));
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

}
