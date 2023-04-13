package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.UserAuthDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.entity.UserAuth;
import com.example.ebookbackend.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserDao userDao;

    @Override
    public UserAuth getUserAuthByUsername(String username) {
        User user = userDao.findUserByName(username);
        return userAuthRepository.getUserAuthByUser(user);
    }

    @Override
    public UserAuth getUserAuthByUser(User user) {
        return userAuthRepository.getUserAuthByUser(user);
    }

    @Override
    public Boolean addUser(String username, String password, Integer role) {
        User user = userDao.findUserByName(username);
        UserAuth userAuth = userAuthRepository.getUserAuthByUser(user);
        if (userAuth != null) {
            return false;
        } else {
            userAuth = UserAuth.builder()
                    .user(user)
                    .password(password)
                    .isBlocked(false)
                    .build();
            userAuthRepository.save(userAuth);
            return true;
        }
    }

    @Override
    public void delUserAuth(String username) {
        User user = userDao.findUserByName(username);
        userAuthRepository.deleteByUser(user);
    }

    @Override
    public void modBlocked(Long user_id, Boolean blocked) {
        User user = userDao.findUserById(user_id);
        UserAuth userAuth = userAuthRepository.getUserAuthByUser(user);
        userAuth.setIsBlocked(blocked);
        userAuthRepository.save(userAuth);
    }

    @Override
    public void modPassword(String username, String password) {
        User user = userDao.findUserByName(username);
        UserAuth userAuth = userAuthRepository.getUserAuthByUser(user);
        userAuth.setPassword(password);
        userAuthRepository.save(userAuth);
    }


}
