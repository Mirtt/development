package com.cu.model;

import java.util.List;

/**
 * 申告内容关键字表（包含优先级和对应关系）
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
public class ContentKey {
    private int content_key_id; //申告内容关键字id
    private String content_key; //申告内容关键字
    private int priority; //优先级
    private int problem_id; //故障现象和申告内容关键字关联id
    private List<ProcessKey> processKeyList; //申告内容关键字与处理过程关键字一对多关系

    public int getContent_key_id() {
        return content_key_id;
    }

    public void setContent_key_id(int content_key_id) {
        this.content_key_id = content_key_id;
    }

    public String getContent_key() {
        return content_key;
    }

    public void setContent_key(String content_key) {
        this.content_key = content_key;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }
}
