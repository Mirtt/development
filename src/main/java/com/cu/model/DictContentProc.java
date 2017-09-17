package com.cu.model;

/**
 * 关键字字典表
 *
 * @authur Zyq
 * @create 2017/9/16.
 */
public class DictContentProc {
    private int id; //id
    private String content_key; //申告内容关键字
    private String proc_key; //处理过程关键字

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
