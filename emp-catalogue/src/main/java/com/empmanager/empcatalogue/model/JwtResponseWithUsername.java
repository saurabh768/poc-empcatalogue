package com.empmanager.empcatalogue.model;

import java.io.Serializable;

public class JwtResponseWithUsername implements Serializable {

    private  String User_id;
    private String Token;

    public JwtResponseWithUsername() {
    }

    public JwtResponseWithUsername(String token, String user_id) {
        User_id = user_id;
        Token = token;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUser_id() {
        return User_id;
    }

    public String getToken() {
        return Token;
    }

}

