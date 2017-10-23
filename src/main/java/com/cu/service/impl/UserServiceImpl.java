package com.cu.service.impl;

import com.cu.dao.UserDao;
import com.cu.model.User;
import com.cu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑实现类，实现接口的逻辑
 * Spring 中的service
 *
 * @authur 99689
 * @create 2017/8/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String user_name, String password) {
        User user = userDao.queryUser(user_name, password);
        if (user != null) {
            return user;
        }
        return null;
    }
}
