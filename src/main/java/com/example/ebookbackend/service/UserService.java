package com.example.ebookbackend.service;

import com.example.ebookbackend.constant.forms.AvatarInfoForm;
import com.example.ebookbackend.constant.forms.ManUserInfoForm;
import com.example.ebookbackend.constant.forms.UserInfoForm;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;

import java.util.List;

public interface UserService {
    User findOne(Long user_id);

    User findByName(String username);

    ManUserInfoForm get_user_info(Long user_id);

    List<ManUserInfoForm> get_all_info();

    UserAuth checkUser(String username, String password);

    void checkSignUpUser(String username, String email, String password) throws Exception;

    void modUser_uinfo(UserInfoForm user_info) throws Exception;

    void modUserInfo(ManUserInfoForm infoForm);

    void modUserAvatar(AvatarInfoForm form);
}
