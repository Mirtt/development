package com.cu.dao;

import com.cu.model.ContentKey;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 获取所有申告内容关键字和对应的处理过程关键字
     * @return
     */
    List<ContentKey> queryProcessKey();

    /**
     * 根据申告内容关键字模糊查询
     * @param content_key
     * @return
     */
    List<ContentKey> queryLikeContentKey(@Param("content_key") String content_key);

    /**
     * 根据处理过程关键字模糊查询
     * @param process_key
     * @return
     */
    List<ContentKey> queryLikeProcessKey(@Param("process_key")String process_key);

    /**
     * 根据双关键字模糊查询
     * @param content_key
     * @param process_key
     * @return
     */
    List<ContentKey> queryLikeContentKeyAndProcessKey(@Param("content_key") String content_key,@Param("process_key")String process_key);
}
