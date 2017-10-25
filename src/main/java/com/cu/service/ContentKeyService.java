package com.cu.service;

import com.cu.model.ContentKey;

import java.util.List;

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
}
