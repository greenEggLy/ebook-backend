package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;

public interface UserAuthDao {

    UserAuth getUserAuthByUsername(String username);

    UserAuth getUserAuthByUser(User user);

    Boolean addUser(String username, String password, Integer role);

    void delUserAuth(String username);

    void modBlocked(Long user_id, Boolean blocked);

    void modPassword(String username, String password);

//    void modRole(String username, Integer role);

//    void modUsername(String username, String newUsername);
}
