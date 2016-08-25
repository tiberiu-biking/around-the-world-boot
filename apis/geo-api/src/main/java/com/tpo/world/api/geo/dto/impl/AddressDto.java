package com.tpo.world.api.geo.dto.impl;


public class AddressDto implements IAddressDto {

    private String formattedAddress;

    private String country;

    private String city;

    public AddressDto(String aFormattedAddress) {
        setFormattedAddress(aFormattedAddress);
    }

    @Override
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String aFormattedAddress) {
        formattedAddress = aFormattedAddress;
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String aCountry) {
        country = aCountry;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getShortAddress() {
        return getCity() + ", " + getCountry();
    }

}
