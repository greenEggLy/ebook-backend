package com.example.ebookbackend.service;

import com.example.ebookbackend.entity.User;

public interface UserService {
    User findOne(Long user_id);

    User findByName(String username);

    void modUserName(Long user_id, String username);

    void modUserAbout(Long user_id, String about);

}
