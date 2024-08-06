package com.throtl.user.util;

import com.google.gson.Gson;
import com.throtl.clientModel.RSAClientRequest;
import com.throtl.clientModel.UserRsaDetails;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RestUtil {


public static String rsaClientCall() {

    Gson gson = new Gson();
    RSAClientRequest rsaClientRequest = new RSAClientRequest();

    rsaClientRequest.setApi("get-user-details");
    UserRsaDetails userRsaDetails = new UserRsaDetails();
    userRsaDetails.setMobile_no("8898141483");
    rsaClientRequest.setRequest_data(userRsaDetails);

    String requestBody = gson.toJson(rsaClientRequest).toString();

    HttpEntity<?> httpEntity = null;
    httpEntity = new HttpEntity<>(gson.toJson(rsaClientRequest).toString());
    RestTemplate restTemplate = null;
    ResponseEntity<?> response = null;
    restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(1000)).build();
    response = restTemplate.postForEntity("https://landmarkrsa.in/api/v1/throtl", httpEntity, Object.class);

    JSONObject jsonObject = null;
    Map<String, String> map11 = new LinkedHashMap<>();
//    conceptStatus = new ConceptStatusBean();
    if (response != null && response.getStatusCode().value() == 200) {
        map11 = (Map<String, String>) response.getBody();
        jsonObject = new JSONObject(map11);

    }

    return null;
//    HashMap<String, Integer> map = new HashMap<>();


//    map.put("custNo", 123);
//    String requestBody = EncryptLibGolf.getEncryptedData(gson.toJson(map).toString(), s);
//    HashMap<String, String> map1 = new HashMap<>();
//			map1.put("data", requestBody);
//    ResponseEntity<?> response = null;
//    RestTemplate restTemplate = null;
//    HttpEntity<?> httpEntity = null;
//    HttpHeaders headers = new HttpHeaders();
//			headers.set("Content-Type", "application/json");
//			headers.set("Authorization", tokValue);
//			headers.set("ivKey", s);
//			headers.set("DEVICE_ID", deviceId);
//			headers.set("ApiSource",apiSource);
//    httpEntity = new HttpEntity<>(gson.toJson(map1).toString(), headers);
//    restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(conceptTimeout)).build();
//    response = restTemplate.postForEntity(memberConceptUrl + "/Searchv2", httpEntity, Object.class);

}
}
