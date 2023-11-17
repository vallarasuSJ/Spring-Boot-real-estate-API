package com.restapi.repository;

import com.restapi.model.Booked;
import com.restapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedRepository extends JpaRepository<Booked, Integer> {

}
