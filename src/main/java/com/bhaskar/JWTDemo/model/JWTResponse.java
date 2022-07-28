package com.bhaskar.JWTDemo.model;

public class JWTResponse {
    private String jwtToken;

    public JWTResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
