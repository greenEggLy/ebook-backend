package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
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
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findUserByName(username);
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
        userRepository.save(user);
    }

    @Override
    public void setUnblock(Long id) {
        User user = getOne(id);
        user.setIsBlocked(false);
        userRepository.save(user);
    }

    @Override
    public void setAdmin(Long id) {
        User user = getOne(id);
        user.setIsAdmin(true);
        userRepository.save(user);
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

}
