package com.cu.dao;

import com.cu.model.stat.Stat;
import org.apache.ibatis.annotations.Param;
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
     * 统计结果存入实例（统计故障现象）
     * @return
     */
    List<Stat> resultStat();

    /**
     * 统计故障现象下的故障原因
     * @param problem
     * @return
     */
    List<Stat> resultReason(@Param("problem")String problem);
}
