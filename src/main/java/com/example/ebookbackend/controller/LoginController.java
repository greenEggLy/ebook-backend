package com.example.ebookbackend.controller;

import com.example.ebookbackend.constant.GlobalInfo;
import com.example.ebookbackend.constant.common.LoginForm;
import com.example.ebookbackend.constant.common.SignUpForm;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;
import com.example.ebookbackend.service.LoginService;
import com.example.ebookbackend.utils.Msg;
import com.example.ebookbackend.utils.MsgCode;
import com.example.ebookbackend.utils.MsgUtil;
import com.example.ebookbackend.utils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.regex.Pattern;

@RestController
public class LoginController {
    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping("/login")
    @CrossOrigin(value = "http://localhost:3000")
    public Msg login(@RequestBody LoginForm loginForm) {
        LoginService loginService_ = applicationContext.getBean(LoginService.class);
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        UserAuth userAuth = loginService_.checkUser(username, password);
        if (userAuth != null) {
            if (userAuth.getIsBlocked()) {
                return MsgUtil.makeMsg(MsgCode.BLOCK_USER_ERROR);
            }
            JSONObject obj = new JSONObject();
            User user = userAuth.getUser();
            obj.put(GlobalInfo.USER_ID, user.getId());
            obj.put(GlobalInfo.USERNAME, user.getName());
            obj.put(GlobalInfo.USER_TYPE, user.getIs_admin() ? GlobalInfo.ADMIN_NUMBER : GlobalInfo.USER_NUMBER);
            SessionUtil.setSession(obj);
            JSONObject response = new JSONObject();
            response.put("username", user.getName());
            response.put("id", user.getId());
            response.put("isBlocked", userAuth.getIsBlocked());
            response.put("userType", user.getIs_admin() ? GlobalInfo.ADMIN_NUMBER : GlobalInfo.USER_NUMBER);
            loginService_.startTiming();
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, response);
        } else {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

    @RequestMapping("/logout")
    @CrossOrigin(value = "http://localhost:3000")
    public Msg logout() {
        LoginService loginService = applicationContext.getBean(LoginService.class);
        long duration = loginService.stopTiming();
        Boolean status = SessionUtil.removeSession();
        if (status && duration != 0) {
//            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
            long day = duration / (24 * 60 * 60 * 1000);
            long hour = (duration / (60 * 60 * 1000) - day * 24);
            long min = ((duration / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (duration / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            String msg = day + "天" + hour + "小时" + min + "分" + s + "秒";
            return MsgUtil.makeMsg(MsgCode.SUCCESS, msg);
        } else if (status) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, "");
        }
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
    }

    @RequestMapping("/session/check")
    @CrossOrigin(value = "http://localhost:3000")
    public Msg checkSession() {
        JSONObject auth = SessionUtil.getAuth();
        if (auth == null) {
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        } else {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, auth);
        }
    }

    @RequestMapping("/signup")
    @CrossOrigin(value = "http://localhost:3000")
    public Msg checkSignup(@RequestBody SignUpForm form) {
        LoginService loginService_ = applicationContext.getBean(LoginService.class);
        String username = form.getUsername();
        String password = form.getPassword();
        String email = form.getEmail();
        Pattern pattern = Pattern.compile(GlobalInfo.EMAIL_REGEX);
        if (!pattern.matcher(email).matches()) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, MsgUtil.EMAIL_FORMAT_ERROR_MSG);
        }
        try {
            loginService_.checkSignUpUser(username, email, password);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, e.getMessage());
        }
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG);
    }
}
