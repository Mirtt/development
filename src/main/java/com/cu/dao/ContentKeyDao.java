package com.cu.dao;

import com.cu.model.ContentKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 插入新的申告内容关键字
     * @param content_key
     * @param content_priority
     * @param problem_id
     */
    void insertContentKey(@Param("content_key")String content_key,@Param("content_priority")int content_priority, @Param("problem_id")int problem_id);

    /**
     * 查询故障现象下比指定优先级小的所有数据
     * @return
     */
    List<ContentKey> queryByPriority(@Param("content_priority")int content_priority);

    void updateContentPriority(@Param("content_key_map")Map<Integer,Integer> contentKeyMap);

    ContentKey queryById(@Param("content_key_id")int content_key_id);

    void updateContentKeyById(@Param("content_key_id")int content_key_id,@Param("content_key")String content_key);

    List<ContentKey> listContentKeyByProblemId(@Param("problem_id")int problem_id);
}
