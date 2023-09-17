package com.example.ebookbackend.service.serviceImpl;

import com.example.ebookbackend.dao.UserAuthDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;
import com.example.ebookbackend.repository.UserAuthRepository;
import com.example.ebookbackend.repository.UserRepository;
import com.example.ebookbackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.ebookbackend.constant.GlobalInfo.DEFAULT_AVATAR;

@Service
@Scope("session")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthDao userAuthDao;

    Date loginTime = new Date();


    @Override
    public UserAuth checkUser(String username, String password) {
        User user = userDao.findUserByName(username);
        UserAuth userAuth = userAuthDao.getUserAuthByUser(user);
        if (userAuth == null) {
            return null;
        }
        if (userAuth.getPassword().equals(password)) {
            return userAuth;
        }
        return null;
    }


    @Override
    public void startTiming() {
        loginTime = new Date();
    }

    @Override
    public long stopTiming() {
        Date now = new Date();
        return now.getTime() - this.loginTime.getTime();
    }

    @Override
    public void checkSignUpUser(String username, String email, String password) throws Exception {
        // create user
        try {
            User user = User.builder()
                    .name(username)
                    .email(email)
                    .avatar(DEFAULT_AVATAR)
                    .is_admin(false)
                    .build();
            userRepository.save(user);
            UserAuth userAuth = UserAuth.builder()
                    .user(user)
                    .password(password)
                    .isBlocked(false)
                    .build();
            userAuthRepository.save(userAuth);
        } catch (Exception e) {
            throw new Exception("用户名或邮箱已存在");
        }
    }
}
