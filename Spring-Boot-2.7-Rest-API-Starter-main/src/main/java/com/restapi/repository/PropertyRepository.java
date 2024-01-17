package com.restapi.repository;

import com.restapi.model.Property;
import com.restapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p WHERE NOT EXISTS (SELECT 1 FROM Booked b WHERE p.id = b.property.id)")
    List<Property> findAllAvailableProperties();

}
