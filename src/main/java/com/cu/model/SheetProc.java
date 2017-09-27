package com.cu.model;

/**
 * 工单处理过程表
 *
 * @authur Zyq
 * @create 2017/9/18.
 */
public class SheetProc {
    private String write_dept_name; //填写部门
    private String type_id; //操作类型
    private String write_user_name; //填写人
    private String write_time; //填写时间
    private String intro; //处理过程内容

    public String getWrite_dept_name() {
        return write_dept_name;
    }

    public void setWrite_dept_name(String write_dept_name) {
        this.write_dept_name = write_dept_name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
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
}
