package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth getUserAuthByUser(User user);

    void deleteByUser(User user);

}
