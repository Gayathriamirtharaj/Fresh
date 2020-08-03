package com.example.repository;
import com.example.model.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity,Integer> {

    //static boolean existsByEmail()


    ContactEntity findByemail(String email);
    ContactEntity findByID(int ID);

    static boolean existsByEmail(String email) {
        return Boolean.parseBoolean(null);
    }

    @Override
     List<ContactEntity> findAll();
}
