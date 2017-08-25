package com.cu.service.user.impl;

import com.cu.dao.UserDao;
import com.cu.model.User;
import com.cu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger= LoggerFactory.getLogger(this.getClass());//业务操作日志

    @Autowired
    private UserDao userDao;

    public User getUser(String account, String password) {
        User user=userDao.queryUser(account,password);
        if (user!=null){
            return user;
        }
        return null;
    }
}
