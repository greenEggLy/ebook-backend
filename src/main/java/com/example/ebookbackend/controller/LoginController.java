package com.example.ebookbackend.controller;

import com.example.ebookbackend.constant.GlobalInfo;
import com.example.ebookbackend.constant.forms.LoginForm;
import com.example.ebookbackend.constant.forms.SignUpForm;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;
import com.example.ebookbackend.service.UserService;
import com.example.ebookbackend.utils.Msg;
import com.example.ebookbackend.utils.MsgCode;
import com.example.ebookbackend.utils.MsgUtil;
import com.example.ebookbackend.utils.SessionUtil;
import lombok.val;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @CrossOrigin(value = "http://localhost:3000")
    public Msg login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        UserAuth userAuth = userService.checkUser(username, password);
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

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, response);
        } else {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

    @RequestMapping("/logout")
    @CrossOrigin(value = "http://localhost:3000")
    public Msg logout() {
        Boolean status = SessionUtil.removeSession();
        if (status) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
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
        String username = form.getUsername();
        String password = form.getPassword();
        String email = form.getEmail();
        assert username != null && password != null && email != null;
        val status = userService.checkSignUpUser(username, email, password);
        if (status == 0)
            return MsgUtil.makeMsg(MsgCode.SUCCESS);
        else if (status == 1)
            return MsgUtil.makeMsg(MsgCode.SIGN_UP_USERNAME_ERROR);
        else
            return MsgUtil.makeMsg(MsgCode.SIGN_UP_EMAIL_EXIST_ERROR);
    }
}
