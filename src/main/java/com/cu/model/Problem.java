package com.cu.model;

import java.util.List;

/**
 * 故障现象表
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
public class Problem {
    private int problem_id; //故障现象id
    private String problem; //故障现象
    private List<ContentKey> contentKeyList; //故障现象和申告内容关键字一对多关系

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public List<ContentKey> getContentKeyList() {
        return contentKeyList;
    }

    public void setContentKeyList(List<ContentKey> contentKeyList) {
        this.contentKeyList = contentKeyList;
    }

}
