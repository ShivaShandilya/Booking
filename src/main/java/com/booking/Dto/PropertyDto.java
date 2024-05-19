package com.booking.Dto;

public class PropertyDto {

    private int bedroom;
    private int guest;
    private int nightlyPrice;
    private String propertyName;
    private String country;
    private String location;

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public int getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(int nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
