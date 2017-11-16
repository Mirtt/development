package com.cu.dao;

import com.cu.model.Problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 故障现象表数据库操作接口
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
@Repository
public interface ProblemDao {
    /**
     * 获取所有故障现象
     * @return
     */
    List<Problem> queryAll();

    /**
     * 获取所有故障现象总数
     * @return
     */
    int countAll();

    /**
     * 根据故障现象id列表查询对应的故障现象
     * @param pids
     * @return
     */
    List<Problem> queryByIdList(@Param("pids")int[] pids);

    /**
     * 根据故障现象id查询故障现象
     * @param problem_id
     * @return
     */
    Problem queryById(@Param("problem_id")int problem_id);

    /**
     * 模糊查询故障现象
     * @param problem
     * @return
     */
    List<Problem> queryLike(@Param("problem") String problem);

    /**
     * 根据id和故障现象名称同时查询
     * @param problem_id
     * @param problem
     * @return
     */
    List<Problem> queryByIdAndProblem(@Param("problem_id")int problem_id,@Param("problem") String problem);

    /**
     * 获取故障现象和申告内容关键字的对应关系
     * @return
     */
    List<Problem> queryContentKey();

    /**
     * 根据故障现象和申告内容关键字模糊查询
     * @param problem
     * @param content_key
     * @return
     */
    List<Problem> queryLikeProblemAndContentKey(@Param("problem") String problem,@Param("content_key")String content_key);

    List<Problem> queryLikeContentKey(@Param("content_key")String content_key);

    List<Problem> queryLikeProblem(@Param("problem")String problem);

    Problem queryByProblem(@Param("problem")String problem);

    void insertProblem(@Param("problem")String problem);
}
