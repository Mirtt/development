package com.cu.dao;

import com.cu.model.ProcessKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理过程关键字表数据库操作接口
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
@Repository
public interface ProcessKeyDao {
    /**
     * 根据申告内容关键字id按照优先级获取对应处理过程关键字
     * @return
     */
    List<ProcessKey> queryAllByPriority(@Param("content_key_id")int content_key_id);
}
