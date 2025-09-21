package com.gregoryleblond.giftcardrestapi.dto;

public class UserLoginDto {
    private String token;
    private String message;

    public UserLoginDto(String message, String token) {
        this.token = token;
        this.message = message;
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
