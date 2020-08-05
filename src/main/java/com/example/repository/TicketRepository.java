package com.example.repository;
import com.example.model.TicketEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {

    @Override
    boolean existsById(Integer id);

    @Override
    TicketEntity getOne(Integer integer);

    TicketEntity findByContacts(String contacts);

    @Override
    List<TicketEntity> findAll();

    //@Override
    List<TicketEntity> findByassigneeId(Integer assigneeId);

}
