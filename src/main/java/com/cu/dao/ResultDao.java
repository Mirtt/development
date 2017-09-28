package com.cu.dao;

import com.cu.model.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作结果表的接口
 * Created by  Zyq
 * 2017/9/18
 * 结果表
 */
@Repository
public interface ResultDao {
    /**
     * 给结果表中插入结果数据
     * @param resultList
     */
    void insertResult(@Param("resultList") List<Result> resultList);
}
