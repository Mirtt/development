package com.cu.service.impl;

import com.cu.dao.ProcessKeyDao;
import com.cu.model.ProcessKey;
import com.cu.service.ProcessKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 处理过程关键字业务逻辑
 *
 * @authur Zyq
 * @create 2017/11/17.
 */
@Service
public class ProcessKeyServiceImpl implements ProcessKeyService {
    @Autowired
    private ProcessKeyDao processKeyDao;
    @Override
    public List<ProcessKey> queryAllByPriority(int content_key_id) {
        return processKeyDao.queryAllByPriority(content_key_id);
    }

    @Override
    public void insertProcessKey(int content_key_id, String process_key, int process_priority, String reason) {
        processKeyDao.insertProcessKey(content_key_id,process_key,process_priority,reason);
    }

    @Override
    public void updateProcessKey(Map process_key_map) {
        processKeyDao.updateProcessKey(process_key_map);
    }
}
