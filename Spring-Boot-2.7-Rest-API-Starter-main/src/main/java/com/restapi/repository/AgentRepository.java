package com.restapi.repository;

import com.restapi.model.Agent;
import com.restapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query("Select c from Agent c where c.appUser.id=:agentId")
    Optional<Agent> findByUserId(Long agentId);
}
