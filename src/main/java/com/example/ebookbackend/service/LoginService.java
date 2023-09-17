package com.example.ebookbackend.service;


import com.example.ebookbackend.entity.UserAuth;

public interface LoginService {

    void startTiming();

    long stopTiming();

    void checkSignUpUser(String username, String email, String password) throws Exception;

    UserAuth checkUser(String username, String password);
}
