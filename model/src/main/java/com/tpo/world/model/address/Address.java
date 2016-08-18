package com.tpo.world.model.address;

public class Address {

    private String formattedAddress;

    private String country;

    private String city;

    public Address(String aFormattedAddress) {
        setFormattedAddress(aFormattedAddress);
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String aFormattedAddress) {
        formattedAddress = aFormattedAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String aCountry) {
        country = aCountry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShortAddress() {
        return getCity() + ", " + getCountry();
    }

}
