//package com.example.eventhub.dto.response;
//
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Builder;
//import lombok.Data;
//
//
//@Builder
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class LoginResponse {
//    private String token;
//    private String response;
//
//    public static LoginResponse withToken(String token) {
//        LoginResponse loginresponse = new LoginResponse();
//        loginresponse.setToken(token);
//        return loginresponse;
//    }
//
//    public static LoginResponse withResponse(String response) {
//        LoginResponse loginresponse = new LoginResponse();
//        loginresponse.setResponse(response);
//        return loginresponse;
//    }
//}
