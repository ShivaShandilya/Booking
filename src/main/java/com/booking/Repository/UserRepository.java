package com.booking.Repository;

import com.booking.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<PropertyUser, Long> {


    Optional<PropertyUser> findByUsername(String username);
}