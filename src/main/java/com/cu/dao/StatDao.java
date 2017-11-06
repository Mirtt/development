package com.cu.dao;

import com.cu.model.stat.Stat;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 统计结果查询
 *
 * @authur Zyq
 * @create 2017/11/6.
 */
@Repository
public interface StatDao {
    /**
     * 统计结果存入实例
     * @return
     */
    List<Stat> resultStat();
}
