package com.example.ebookbackend.controller;

import com.example.ebookbackend.constant.forms.ManUserInfoForm;
import com.example.ebookbackend.constant.forms.UserInfoForm;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/get/{id}")
    @CrossOrigin(value = "http://localhost:3000")
    public User findUser(@PathVariable("id") Long user_id) {
        return userService.findOne(user_id);
    }


    @RequestMapping(value = "/user/uinfo/mod")
    @CrossOrigin(value = "http://localhost:3000")
    public void modUser(@RequestBody UserInfoForm user_info) {
        userService.modUser_uinfo(user_info);
    }

    @RequestMapping(value = "/user/info/get/{id}")
    @CrossOrigin(value = "http://localhost:3000")
    public ManUserInfoForm getUserInfo(@PathVariable("id") Long user_id) {
        return userService.get_user_info(user_id);
    }

    @RequestMapping(value = "/user/info/get/all")
    @CrossOrigin(value = "http://localhost:3000")
    public Iterable<ManUserInfoForm> getAllUsers() {
        return userService.get_all_info();
    }

    @RequestMapping(value = "/user/info/mod")
    @CrossOrigin(value = "http://localhost:3000")
    public void modUserInfo(@RequestBody ManUserInfoForm infoForm) {
        userService.modUserInfo(infoForm);
    }

}
