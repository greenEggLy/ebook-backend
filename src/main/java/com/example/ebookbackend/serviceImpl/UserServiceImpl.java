package com.example.ebookbackend.serviceImpl;

import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findOne(Long user_id) {
        return userDao.findUserById(user_id);
    }

    @Override
    public User findByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public void modUserName(Long user_id, String username) {
        userDao.modName(user_id, username);
    }

    @Override
    public void modUserAbout(Long user_id, String about) {
        userDao.modAbout(user_id, about);
    }
}
