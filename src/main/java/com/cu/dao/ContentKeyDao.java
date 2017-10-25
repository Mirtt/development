package com.cu.dao;

import com.cu.model.ContentKey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 申告内容关键字表数据库操作接口
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
@Repository
public interface ContentKeyDao {
    /**
     * 获取所有申告内容关键字并且按照优先级排序
     * @return
     */
    List<ContentKey> queryAllOrderByPriority();
}
