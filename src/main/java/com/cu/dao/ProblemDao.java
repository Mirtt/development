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
     * 根据故障现象id查询对应的故障现象
     * @param pids
     * @return
     */
    List<Problem> queryById(@Param("pids")int[] pids);
}
