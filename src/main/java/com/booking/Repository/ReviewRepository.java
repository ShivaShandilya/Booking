package com.booking.Repository;

import com.booking.entity.Property;
import com.booking.entity.PropertyUser;
import com.booking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.property=:property and r.propertyUser=:user")
    public Review findReviewByUser(@Param("property") Property property, @Param("user") PropertyUser user);

}