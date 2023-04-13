package com.example.ebookbackend.utils;

import net.sf.json.JSONObject;

public class MsgUtil {

    // log in error code
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int LOGIN_USER_ERROR = -100;
    public static final int NOT_LOGGED_IN_ERROR = -101;
    public static final int BLOCK_USER_ERROR = -102;

    // sign up error code
    public static final int SIGN_UP_USERNAME_ERROR = -200;
    public static final int SIGN_UP_EMAIL_EXIST_ERROR = -201;


    // log in error message
    public static final String SUCCESS_MSG = "成功！";
    public static final String LOGIN_SUCCESS_MSG = "登录成功！";
    public static final String LOGOUT_SUCCESS_MSG = "登出成功！";
    public static final String LOGOUT_ERR_MSG = "登出异常！";
    public static final String ERROR_MSG = "错误！";
    public static final String LOGIN_USER_ERROR_MSG = "用户名或密码错误，请重新输入！";
    public static final String NOT_LOGGED_IN_ERROR_MSG = "登录失效，请重新登录！";
    public static final String BLOCK_USER_ERROR_MSG = "用户已被封禁！";

    // sign up error message
    public static final String SIGN_UP_USERNAME_ERROR_MSG = "用户名已存在！";
    public static final String SIGN_UP_EMAIL_EXIT_ERROR_MSG = "邮箱已存在！";


    public static Msg makeMsg(MsgCode code, JSONObject data) {
        return new Msg(code, data);
    }

    public static Msg makeMsg(MsgCode code, String msg, JSONObject data) {
        return new Msg(code, msg, data);
    }

    public static Msg makeMsg(MsgCode code) {
        return new Msg(code);
    }

    public static Msg makeMsg(MsgCode code, String msg) {
        return new Msg(code, msg);
    }

    public static Msg makeMsg(int status, String msg, JSONObject data) {
        return new Msg(status, msg, data);
    }

    public static Msg makeMsg(int status, String msg) {
        return new Msg(status, msg);
    }
}
