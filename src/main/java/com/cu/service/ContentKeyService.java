package com.cu.service;

import com.cu.model.ContentKey;

import java.util.List;
import java.util.Map;

/**
 * 申告内容关键字业务逻辑接口
 *
 * @authur Zyq
 * @create 2017/10/25.
 */
public interface ContentKeyService {
    /**
     * 查询所有申告内容关键字并按优先级排序
     * @return
     */
    List<ContentKey> queryAllOrderByPriority();

    List<ContentKey> queryProcessKey();

    List<ContentKey> queryLikeContentKey(String content_key);

    List<ContentKey> queryLikeProcessKey(String process_key);

    List<ContentKey> queryLikeContentKeyAndProcessKey(String content_key,String process_key);

    void insertContentKey(String content_key,int content_priority,int problem_id);

    List<ContentKey> queryByPriority(int content_priority);

    void updateContentPriority(Map<Integer,Integer> contentKeyMap);

    ContentKey queryById(int content_key_id);

    void updateContentKeyById(int content_key_id,String content_key);

    List<ContentKey> listContentKeyByProblemId(int problem_id);
}
