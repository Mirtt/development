package com.cu.model;

/**
 * 用户类，关联数据库中user表所有字段
 *
 * @authur 99689
 * @create 2017/8/21.
 */
public class User {

    private int id; //user id
    private String name; // 用户名

    private String account; //账户名 用于登录

    private String password; //密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
