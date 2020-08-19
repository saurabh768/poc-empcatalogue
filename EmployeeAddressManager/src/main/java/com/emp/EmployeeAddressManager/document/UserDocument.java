package com.emp.EmployeeAddressManager.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Transient;

@Document(collection = "User")
public class UserDocument {

    @Id
    private String userId;

    @Transient
    public static final String seqName = "UserDocumentSequence";


    @Field("email")
    private String email;

    @Field("password")
    private String password;


    @Field("userDetails")
    private UserDetailsDocument userDetailsDocument;

    public UserDocument(String userId, String email, String password, UserDetailsDocument userDetailsDocument) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userDetailsDocument = userDetailsDocument;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetailsDocument getUserDetails() {
        return userDetailsDocument;
    }

    public void setUserDetails(UserDetailsDocument userDetailsDocument) {
        this.userDetailsDocument = userDetailsDocument;
    }

    public UserDocument() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userDetails=" + userDetailsDocument +
                '}';
    }
}
