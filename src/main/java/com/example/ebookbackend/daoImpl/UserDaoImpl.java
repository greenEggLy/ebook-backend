package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.constant.common.ManUserInfoForm;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserIcon;
import com.example.ebookbackend.repository.UserIconRepository;
import com.example.ebookbackend.repository.UserRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.ebookbackend.constant.GlobalInfo.ROLE_ADMIN;
import static com.example.ebookbackend.constant.GlobalInfo.ROLE_USER;

@Repository
public class UserDaoImpl implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserIconRepository userIconRepository;

    @Override
    public User getOne(Long id) {
        User user = userRepository.getUserById(id);
        Integer _id = id.intValue();
        Optional<UserIcon> userIcon = userIconRepository.findById(_id);
        logger.info("find user icon from mongodb");
        userIcon.ifPresent(icon -> user.setAvatar(icon.getIcon()));
        return user;
    }

    @Override
    public List<ManUserInfoForm> getAll() {
        val allUsers = userRepository.findAll();
        val allUsersInfo = new ArrayList<ManUserInfoForm>();
        allUsers.forEach(user -> {
            val info = ManUserInfoForm.builder()
                    .id(user.getId())
                    .username(user.getName())
                    .email(user.getEmail())
                    .is_admin(user.getIs_admin())
                    .is_blocked(user.getUserAuth().getIsBlocked())
                    .build();
            allUsersInfo.add(info);
        });
        return allUsersInfo;
    }

    @Override
    public User findUserById(Long id) {
        User user = userRepository.findUserById(id);
        Optional<UserIcon> userIcon = userIconRepository.findById(id.intValue());
        if (userIcon.isPresent()) {
            logger.info("find user icon from mongodb: {}: {}", userIcon.get().getId(), userIcon.get().getIcon());
            user.setAvatar(userIcon.get().getIcon());
        }
        return user;
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findUserByName(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void addUser(String name, String about, String avatar) {
        User user = User.builder()
                .name(name)
                .about(about)
                .avatar(avatar).build();
        userRepository.save(user);
    }

    @Override
    public void delUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void modName(Long id, String name) {
        User user = getOne(id);
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    public void modAvatar(Long id, String avatar) {
        User user = getOne(id);
        user.setAvatar(avatar);
        userRepository.save(user);
    }

    @Override
    public void modAbout(Long id, String about) {
        User user = getOne(id);
        user.setAbout(about);
        userRepository.save(user);
    }

    @Override
    public void modRole(Long id, Boolean role) {
        User user = getOne(id);
        if (Objects.equals(role, ROLE_USER)) {
            user.setIs_admin(false);
        } else if (Objects.equals(role, ROLE_ADMIN)) {
            user.setIs_admin(true);
        }
    }

}
