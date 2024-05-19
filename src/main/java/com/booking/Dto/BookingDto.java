package com.booking.Dto;

public class BookingDto {

private long id;
private String guestName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getTotalNight() {
        return totalNight;
    }

    public void setTotalNight(int totalNight) {
        this.totalNight = totalNight;
    }

    private int totalNight;

}
