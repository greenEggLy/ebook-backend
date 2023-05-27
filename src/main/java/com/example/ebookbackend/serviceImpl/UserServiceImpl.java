package com.example.ebookbackend.serviceImpl;

import com.example.ebookbackend.constant.forms.AvatarInfoForm;
import com.example.ebookbackend.constant.forms.ManUserInfoForm;
import com.example.ebookbackend.constant.forms.UserInfoForm;
import com.example.ebookbackend.dao.UserAuthDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;
import com.example.ebookbackend.repository.UserAuthRepository;
import com.example.ebookbackend.repository.UserRepository;
import com.example.ebookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ebookbackend.constant.GlobalInfo.DEFAULT_AVATAR;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthDao userAuthDao;
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public User findOne(Long user_id) {
        return userDao.findUserById(user_id);
    }

    @Override
    public User findByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public ManUserInfoForm get_user_info(Long user_id) {
        User user = userDao.findUserById(user_id);
        if (user == null) return null;
        UserAuth userAuth = userAuthDao.getUserAuthByUser(user);
        if (userAuth == null) return null;
        return ManUserInfoForm.builder()
                .id(user.getId())
                .username(user.getName())
                .is_admin(user.getIs_admin())
                .is_blocked(userAuth.getIsBlocked())
                .email(user.getEmail())
                .build();
    }

    @Override
    public List<ManUserInfoForm> get_all_info() {
        return userDao.getAll();
    }

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
    public Integer checkSignUpUser(String username, String email, String password) {
        User user = userDao.findUserByName(username);
        if (user != null) {
            return 1; // has username
        }
        user = userDao.findUserByEmail(email);
        if (user != null) {
            return 2; // has email
        }
        // create user
        user = User.builder()
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
        return 0; // success
    }

    @Override
    public void modUser_uinfo(UserInfoForm user_info) {
        User user = userDao.findUserById(user_info.getId());
        if (user != null) {
            user.setName(user_info.getUsername());
            user.setAbout(user_info.getAbout());
            user.setEmail(user_info.getEmail());
            userRepository.save(user);
        }
    }

    @Override
    public void modUserInfo(ManUserInfoForm infoForm) {
        User user = userDao.findUserById(infoForm.getId());
        if (user != null) {
            user.setName(infoForm.getUsername());
            user.setEmail(infoForm.getEmail());
            user.setIs_admin(infoForm.getIs_admin());
            userRepository.save(user);
        }
        UserAuth userAuth = userAuthDao.getUserAuthByUser(user);
        if (userAuth != null) {
            userAuth.setIsBlocked(infoForm.getIs_blocked());
            userAuthRepository.save(userAuth);
        }
    }

    @Override
    public void modUserAvatar(AvatarInfoForm form) {
        User user = userDao.findUserById(form.getId());
        if (user != null) {
            user.setAvatar(form.getAvatar());
            userRepository.save(user);
            System.out.println("ok");
        }
    }

}
