package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.User;

import java.util.List;

public interface UserDao {
    User getOne(Long id);

    List<User> getAll();

    void addUser(String name, String about, String avatar);

    void delUser(Long id);

    void setBlock(Long id);

    void setUnblock(Long id);

    void setAdmin(Long id);

    void modName(Long id, String name);

    void modAvatar(Long id, String avatar);

    void modAbout(Long id, String about);
}
