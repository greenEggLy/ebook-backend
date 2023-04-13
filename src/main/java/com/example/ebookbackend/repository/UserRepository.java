package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);

    User findUserById(Long id);

    User findUserByName(String name);

    User findUserByEmail(String email);
}
