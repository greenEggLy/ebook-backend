package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);

    User findUserById(Long id);

    User findUserByName(String name);
}
