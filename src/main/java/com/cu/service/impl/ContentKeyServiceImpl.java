package com.cu.service.impl;

import com.cu.dao.ContentKeyDao;
import com.cu.model.ContentKey;
import com.cu.service.ContentKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
