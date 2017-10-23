package com.cu.dao;

import com.cu.model.Problem;
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
}
