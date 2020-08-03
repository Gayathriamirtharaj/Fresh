package com.example.repository;
import com.example.model.AgentEntity;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<AgentEntity,Integer>
{
    Boolean existsByContacts(String contacts);
    AgentEntity findById(int id);
    List<AgentEntity>findAll();
}
