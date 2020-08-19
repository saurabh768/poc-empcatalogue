package com.emp.EmployeeAddressManager.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Transient;

@Document(collection = "address")
public class AddressDocument {


    @Transient
    public static String seqName="AddressDocumentSequence";

    @Field("address_id")
    @JsonProperty("address_id")
    private Integer address_id;

    @Field("city")
    private String city;

    @Field("state")
    private String state;

    @Field("zipcode")
    @JsonProperty("zipcode")
    private int zipCode;

    @Field("country")
    private String country;

    @Field("house_number")
    private String houseNumber;

    @Field("street")
    private String street ;

    @Field("landmark")
    private String landmark;

    public AddressDocument() {
    }

    public AddressDocument(int address_id, String city, String state, int zipCode, String country, String houseNumber, String street, String landmark) {
        this.address_id = address_id;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.houseNumber = houseNumber;
        this.street = street;
        this.landmark = landmark;
    }

    public int getAddressId() {
        return address_id;
    }

    public void setAddressId(int addressId) {
        this.address_id = address_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Override
    public String toString() {
        return "AddressDocument{" +
                "address_id=" + address_id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", street='" + street + '\'' +
                ", landmark='" + landmark + '\'' +
                '}';
    }
}

