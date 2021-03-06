package com.cu.service;

import com.cu.model.Problem;

import java.util.List;

/**
 * 故障现象业务逻辑接口
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
public interface ProblemService {
    /**
     * 获取所有故障现象
     * @return
     */
    List<Problem> queryAll();

    /**
     * 获取故障现象总数
     * @return
     */
    int countAll();

    /**
     * 根据故障现象id获取对应故障现象
     * @param pids
     * @return
     */
    List<Problem> queryByIdList(int[] pids);

    Problem queryById(int problem_id);

    List<Problem> queryLike(String problem);

    List<Problem> queryContentKey();

    List<Problem> queryByIdAndProblem(int problem_id,String problem);

    List<Problem> queryLikeProblemAndContentKey(String problem,String content_key);

    List<Problem> queryLikeContentKey(String content_key);

    List<Problem> queryLikeProblem(String problem);

    Problem queryByProblem(String problem);

    void insertProblem(String problem);
}
