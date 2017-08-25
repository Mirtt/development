package com.cu.service.user;

import com.cu.model.User;

/**
 * Created by  Mirt
 * 2017/8/25
 * 关于用户操作的业务逻辑层（暂时只有用户登录逻辑）
 */
public interface UserService {
    /**
     * 通过账户，密码获取用户
     * @param account
     * @param password
     * @return
     */
    User getUser(String account, String password);
}
