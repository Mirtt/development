package com.cu.model.stat;

/**
 * 用于统计的实例类
 *
 * @authur Zyq
 * @create 2017/11/6.
 */
public class Stat {
    private String problem;//故障现象名称
    private int problemNum;//故障现象数量
    private double problemPercent;//故障现象占比

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

    public double getProblemPercent() {
        return problemPercent;
    }

    public void setProblemPercent(double problemPercent) {
        this.problemPercent = problemPercent;
    }
}
