package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.UserIcon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserIconRepository extends MongoRepository<UserIcon, Integer> {
}
