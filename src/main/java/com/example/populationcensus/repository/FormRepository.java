package com.example.populationcensus.repository;

import com.example.populationcensus.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface FormRepository extends JpaRepository<Form, String> {
    List<Form> findByAgentIdOrderByDateCreatedDesc(Long agentId);
}
