package com.cu.service;

import com.cu.model.ProcessKey;

import java.util.List;

/**
 * 处理过程关键字
 *
 * @authur Zyq
 * @create 2017/11/17.
 */
public interface ProcessKeyService {

    List<ProcessKey> queryAllByPriority(int content_key_id);
}
