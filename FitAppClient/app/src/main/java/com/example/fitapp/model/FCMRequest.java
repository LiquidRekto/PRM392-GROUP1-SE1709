package com.example.fitapp.model;

import com.google.gson.annotations.SerializedName;

public class FCMRequest {
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("token")
    private String token;

    public FCMRequest() {
    }

    public FCMRequest(String title, String body, String token) {
        this.title = title;
        this.body = body;
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FCMRequest{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
