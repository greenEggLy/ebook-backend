package com.example.ebookbackend.controller;

import com.example.ebookbackend.constant.common.AvatarInfoForm;
import com.example.ebookbackend.constant.common.ManUserInfoForm;
import com.example.ebookbackend.constant.common.UserInfoForm;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.service.UserService;
import com.example.ebookbackend.utils.Msg;
import com.example.ebookbackend.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User findUser(@PathVariable("id") Long user_id) {
        return userService.findOne(user_id);
    }


    @PostMapping(value = "/uinfo")
    public Msg modUser(@RequestBody UserInfoForm user_info) {
        try {
            userService.modUser_uinfo(user_info);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/info/get/{id}")
    public ManUserInfoForm getUserInfo(@PathVariable("id") Long user_id) {
        return userService.get_user_info(user_id);
    }

    @GetMapping(value = "/info/all")
    public Iterable<ManUserInfoForm> getAllUsers() {
        return userService.get_all_info();
    }

    @PostMapping(value = "/info/mod")
    public void modUserInfo(@RequestBody ManUserInfoForm infoForm) {
        userService.modUserInfo(infoForm);
    }

    @PostMapping(value = "/avatar")
    public void modUserAvatar(@RequestBody AvatarInfoForm form) {
        userService.modUserAvatar(form);
    }
}
