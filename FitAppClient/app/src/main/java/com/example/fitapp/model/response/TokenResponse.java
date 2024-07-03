package com.example.fitapp.model.response;

public class TokenResponse {
    private String token;
    private String message;

    public TokenResponse() {
    }

    public TokenResponse(String token, String message) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
