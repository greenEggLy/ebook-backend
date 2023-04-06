package com.example.ebookbackend.controller;

import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser")
    @CrossOrigin
    public User findUser(Long user_id) {
        return userService.findOne(user_id);
    }

    @RequestMapping(value = "/getUserByName")
    @CrossOrigin
    public User findUserByName(String username) {
        return userService.findByName(username);
    }

    @RequestMapping(value = "/modUserName")
    @CrossOrigin
    public void modUserName(Long user_id, String username) {
        userService.modUserName(user_id, username);
    }

    @RequestMapping(value = "/modUserAbout")
    @CrossOrigin
    public void modUserAbout(Long user_id, String about) {
        userService.modUserAbout(user_id, about);
    }
}
