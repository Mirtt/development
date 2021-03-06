package com.cu.model;

/**
 * 用户类，关联数据库中user表所有字段
 *
 * @authur 99689
 * @create 2017/8/21.
 */
public class User {

    private int id; //user id
    private String user_name; // 用户名
    private String password; //密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
