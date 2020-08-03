package com.example.repository;
import com.example.model.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(Optional<ContactEntity> username);

    UserEntity findByUsername(String username);





    UserEntity findByLastName(String lastName);

    boolean existsByUsername(String username);
}
