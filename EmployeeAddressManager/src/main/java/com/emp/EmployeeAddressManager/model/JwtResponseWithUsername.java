package com.emp.EmployeeAddressManager.model;

import java.io.Serializable;

public class JwtResponseWithUsername implements Serializable {

    private  String User_id;
    private String Token;

    public JwtResponseWithUsername( String token,String user_id) {
        User_id = user_id;
        Token = token;
    }

    public String getUser_id() {
        return User_id;
    }

    public String getToken() {
        return Token;
    }
}
