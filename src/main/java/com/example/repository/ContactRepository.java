package com.example.repository;
import com.example.model.ContactEntity;
import com.example.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity,Integer> {

    ContactEntity findByemail(String email);
    ContactEntity findByID(int ID);
     //boolean existsByContact(String contacts);
     @Override
     List<ContactEntity> findAll();
}
