package com.example.ebookbackend.dao;

import com.example.ebookbackend.constant.common.ManUserInfoForm;
import com.example.ebookbackend.entity.User;

import java.util.List;

public interface UserDao {
    User getOne(Long id);

    List<ManUserInfoForm> getAll();

    User findUserById(Long id);

    User findUserByName(String username);

    User findUserByEmail(String email);

    void addUser(String name, String about, String avatar);

    void delUser(Long id);

    void modName(Long id, String name);

    void modAvatar(Long id, String avatar);

    void modAbout(Long id, String about);

    void modRole(Long id, Boolean role);
}
