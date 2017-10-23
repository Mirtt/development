package com.cu.service;

import com.cu.model.User;

/**
 * Created by  Mirt
 * 2017/8/25
 * 关于用户操作的业务逻辑层（暂时只有用户登录逻辑）
 */
public interface UserService {
    /**
     * 通过账户，密码获取用户
     * @param user_name
     * @param password
     * @return
     */
    User getUser(String user_name, String password);
}
