package com.emp.EmployeeAddressManager.document;


import com.emp.EmployeeAddressManager.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "user_details")
public class UserDetailsDocument {

    private String firstName;

    private String lastName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dob;

    private String gender;

    private String mobile;

    @JsonProperty("address")
    private List<Address> addressDocument;


    public UserDetailsDocument(String firstName, String lastName, Date dob, String gender, String mobile, List<Address> addressDocument) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.mobile = mobile;
        this.addressDocument = addressDocument;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Address> getAddressDocument() {
        return addressDocument;
    }

    public void setAddressDocument(List<Address> addressDocument) {
        this.addressDocument = addressDocument;
    }


    @Override
    public String toString() {
        return "UserDetailsDocument{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                ", addressDocument=" + addressDocument +
                '}';
    }
}

