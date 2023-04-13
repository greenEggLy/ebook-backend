package com.example.ebookbackend.utils;

public enum MsgCode {
    SUCCESS(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG),
    ERROR(MsgUtil.ERROR, MsgUtil.ERROR_MSG),
    LOGIN_USER_ERROR(MsgUtil.LOGIN_USER_ERROR, MsgUtil.LOGIN_USER_ERROR_MSG),
    NOT_LOGGED_IN_ERROR(MsgUtil.NOT_LOGGED_IN_ERROR, MsgUtil.NOT_LOGGED_IN_ERROR_MSG),
    BLOCK_USER_ERROR(MsgUtil.BLOCK_USER_ERROR, MsgUtil.BLOCK_USER_ERROR_MSG),
    SIGN_UP_USERNAME_ERROR(MsgUtil.SIGN_UP_USERNAME_ERROR, MsgUtil.SIGN_UP_USERNAME_ERROR_MSG),
    SIGN_UP_EMAIL_EXIST_ERROR(MsgUtil.SIGN_UP_EMAIL_EXIST_ERROR, MsgUtil.SIGN_UP_EMAIL_EXIT_ERROR_MSG);

    private final int status;
    private final String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    private MsgCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
