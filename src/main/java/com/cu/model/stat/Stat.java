package com.cu.model.stat;

/**
 * 用于统计的实例类
 * 2017 11 9 可能这个类叫dto？
 * 用来向前端传送数据的类
 *
 * @authur Zyq
 * @create 2017/11/6.
 */
public class Stat {
    private String problem;//故障现象名称
    private int problemNum;//故障现象数量
    private String reason;//故障原因名称
    private int reasonNum;//故障原因数量

    private int problem_id;//故障现象id
    private String content_key;//申告内容关键字
    private int content_priority;//申告内容优先级


    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(int problemNum) {
        this.problemNum = problemNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getReasonNum() {
        return reasonNum;
    }

    public void setReasonNum(int reasonNum) {
        this.reasonNum = reasonNum;
    }

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

    public String getContent_key() {
        return content_key;
    }

    public void setContent_key(String content_key) {
        this.content_key = content_key;
    }

    public int getContent_priority() {
        return content_priority;
    }

    public void setContent_priority(int content_priority) {
        this.content_priority = content_priority;
    }
}
