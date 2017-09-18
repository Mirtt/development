package com.cu.model;

import java.sql.Timestamp;

/**
 * 结果表实体类
 *
 * @authur Zyq
 * @create 2017/9/18.
 */
public class Result {
    private int user_id; //用户id
    private Timestamp search_time; //结果导出时间
    private String type; //受理单工单类型
    private String balk_no; //受理单号
    private String balk_content; //申告内容
    private String write_dept_name; //填写部门
    private String operation_type; //操作类型，类型ID派发 签收 退单 驳回 回复完成 填写处理过程
    private String write_user_name; //填写人
    private String write_time; //填写时间
    private String intro; //处理过程内容
    private String content_key; //申告内容关键字
    private String proc_key; //处理过程关键字

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getSearch_time() {
        return search_time;
    }

    public void setSearch_time(Timestamp search_time) {
        this.search_time = search_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalk_no() {
        return balk_no;
    }

    public void setBalk_no(String balk_no) {
        this.balk_no = balk_no;
    }

    public String getBalk_content() {
        return balk_content;
    }

    public void setBalk_content(String balk_content) {
        this.balk_content = balk_content;
    }

    public String getWrite_dept_name() {
        return write_dept_name;
    }

    public void setWrite_dept_name(String write_dept_name) {
        this.write_dept_name = write_dept_name;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public String getWrite_user_name() {
        return write_user_name;
    }

    public void setWrite_user_name(String write_user_name) {
        this.write_user_name = write_user_name;
    }

    public String getWrite_time() {
        return write_time;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent_key() {
        return content_key;
    }

    public void setContent_key(String content_key) {
        this.content_key = content_key;
    }

    public String getProc_key() {
        return proc_key;
    }

    public void setProc_key(String proc_key) {
        this.proc_key = proc_key;
    }
}
