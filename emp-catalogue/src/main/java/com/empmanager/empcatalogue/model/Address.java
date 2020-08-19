package com.empmanager.empcatalogue.model;

public class Address {
    public static String seqName="AddressDocumentSequence";

    private int addressId;

    private String city;

    private String state;

    private int zipCode;

    private String country;

    private String houseNumber;
    private String street ;

    private String landmark;




    public Address(int addressId, String city, String state, int zipCode, String country, String houseNumber, String street, String landmark) {
        this.addressId = addressId;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.houseNumber = houseNumber;
        this.street = street;
        this.landmark = landmark;
    }
    public static String getSeqName() {
        return seqName;
    }
    public static void setSeqName(String seqName) {
        Address.seqName = seqName;
    }
    public int getAddressId() {
        return addressId;
    }
    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

    public Address() {
    }
    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
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
