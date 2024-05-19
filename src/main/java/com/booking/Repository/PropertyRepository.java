package com.booking.Repository;

import com.booking.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {


    @Query("SELECT p FROM Property p JOIN Location l ON p.location=l.id JOIN Country c ON p.country =c.id WHERE l.locationNmae = :locationName OR c.countryName = :locationName")
    List<Property> findByLocation(@Param("locationName") String locationName);
}
