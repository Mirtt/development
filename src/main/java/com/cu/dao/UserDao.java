package com.cu.dao;

import com.cu.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by  Mirt
 * 2017/8/25
 * 用户相关的数据操作的接口（暂时只做登录使用）
 */
@Repository
public interface UserDao {
    /**
     * 通过账户名，密码 查询用户用于登录
     *
     * @param user_name 账户名
     * @param password  密码
     * @return
     */
    User queryUser(@Param("user_name") String user_name, @Param("password") String password);
}
