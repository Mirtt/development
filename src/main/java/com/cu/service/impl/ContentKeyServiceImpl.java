package com.cu.service.impl;

import com.cu.dao.ContentKeyDao;
import com.cu.model.ContentKey;
import com.cu.service.ContentKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 申告内容关键字业务逻辑实现类
 *
 * @authur Zyq
 * @create 2017/10/25.
 */
@Service
public class ContentKeyServiceImpl implements ContentKeyService {
    @Autowired
    ContentKeyDao contentKeyDao;
    @Override
    public List<ContentKey> queryAllOrderByPriority() {
        return contentKeyDao.queryAllOrderByPriority();
    }

    @Override
    public List<ContentKey> queryProcessKey() {
        return contentKeyDao.queryProcessKey();
    }

    @Override
    public List<ContentKey> queryLikeContentKey(String content_key) {
        return contentKeyDao.queryLikeContentKey(content_key);
    }

    @Override
    public List<ContentKey> queryLikeProcessKey(String process_key) {
        return contentKeyDao.queryLikeProcessKey(process_key);
    }

    @Override
    public List<ContentKey> queryLikeContentKeyAndProcessKey(String content_key, String process_key) {
        return contentKeyDao.queryLikeContentKeyAndProcessKey(content_key,process_key);
    }

    @Override
    public void insertContentKey(String content_key, int content_priority, int problem_id) {
        contentKeyDao.insertContentKey(content_key,content_priority,problem_id);
    }

    @Override
    public List<ContentKey> queryByPriority( int content_priority) {
        return contentKeyDao.queryByPriority(content_priority);
    }

    @Override
    public void updateContentPriority(Map<Integer, Integer> contentKeyMap) {
        contentKeyDao.updateContentPriority(contentKeyMap);
    }

    @Override
    public ContentKey queryById(int content_key_id) {
        return contentKeyDao.queryById(content_key_id);
    }

    @Override
    public void updateContentKeyById(int content_key_id, String content_key) {
        contentKeyDao.updateContentKeyById(content_key_id,content_key);
    }

    @Override
    public List<ContentKey> listContentKeyByProblemId(int problem_id) {
        return contentKeyDao.listContentKeyByProblemId(problem_id);
    }
}
