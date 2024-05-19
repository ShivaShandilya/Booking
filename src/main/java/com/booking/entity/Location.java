package com.booking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "location_nmae", nullable = false, unique = true, length = 45)
    private String locationNmae;

    public String getLocationNmae() {
        return locationNmae;
    }

    public void setLocationNmae(String locationNmae) {
        this.locationNmae = locationNmae;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}