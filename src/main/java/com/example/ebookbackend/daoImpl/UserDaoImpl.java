package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.constant.common.ManUserInfoForm;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ebookbackend.constant.GlobalInfo.ROLE_ADMIN;
import static com.example.ebookbackend.constant.GlobalInfo.ROLE_USER;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getOne(Long id) {
        return userRepository.getUserById(id);
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
        return userRepository.findUserById(id);
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
