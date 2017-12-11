package com.cu.dao;

import com.cu.model.ProcessKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 查找比指定优先级小的所有processKey
     * @param process_priority
     * @return
     */
    List<ProcessKey> queryByPriority(@Param("process_priority")int process_priority,@Param("content_key_id")int content_key_id);

    /**
     * 后移processkey的优先级
     * @param process_key_map
     */
    void  updateProcessKey(@Param("process_key_map")Map process_key_map);

    /**
     * 插入processkey
     * @param content_key_id
     * @param process_key
     * @param process_priority
     * @param reason
     */
    void  insertProcessKey(@Param("content_key_id")int content_key_id, @Param("process_key")String process_key, @Param("process_priority")int process_priority,@Param("reason")String reason);

    /**
     * 根据id更新processkey
     * @param process_key_id
     * @param process_key
     */
    void updateProcessKeyById(@Param("process_key_id")int process_key_id,@Param("process_key")String process_key);

    /**
     * 2017.12.05 yjz 根据process_key_id更新content_key_id字段
     * @param process_key_id
     * @param content_key_id
     */
    void updateContentIdById(@Param("process_key_id")int process_key_id,@Param("content_key_id")int content_key_id);
}
