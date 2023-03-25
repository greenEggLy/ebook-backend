package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.repository.UserRepository;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private UserRepository userRepository;

    @Override
    public User getOne(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
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
    public void setBlock(Long id) {
        User user = getOne(id);
        user.setIsBlocked(true);
    }

    @Override
    public void setUnblock(Long id) {
        User user = getOne(id);
        user.setIsBlocked(false);
    }

    @Override
    public void setAdmin(Long id) {
        User user = getOne(id);
        user.setIsAdmin(true);
    }

    @Override
    public void modName(Long id, String name) {
        User user = getOne(id);
        user.setName(name);
    }

    @Override
    public void modAvatar(Long id, String avatar) {
        User user = getOne(id);
        user.setAvatar(avatar);
    }

    @Override
    public void modAbout(Long id, String about) {
        User user = getOne(id);
        user.setAbout(about);
    }

}
