package com.booking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_night", nullable = false)
    private Integer totalNight;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "property_user_id")
    private PropertyUser propertyUser;

    @Column(name = "gst_include_bill", nullable = false)
    private Double gstIncludeBill;

    public Double getGstIncludeBill() {
        return gstIncludeBill;
    }

    public void setGstIncludeBill(Double gstIncludeBill) {
        this.gstIncludeBill = gstIncludeBill;
    }

    public PropertyUser getPropertyUser() {
        return propertyUser;
    }

    public void setPropertyUser(PropertyUser propertyUser) {
        this.propertyUser = propertyUser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Integer getTotalNight() {
        return totalNight;
    }

    public void setTotalNight(Integer totalNight) {
        this.totalNight = totalNight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}