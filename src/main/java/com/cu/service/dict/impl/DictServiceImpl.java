package com.cu.service.dict.impl;

import com.cu.dao.DictContentProcDao;
import com.cu.model.DictContentProc;
import com.cu.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关键字业务逻辑实现
 *
 * @authur Zyq
 * @create 2017/9/17.
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictContentProcDao dictContentProcDao;

    @Override
    public List<DictContentProc> getKey() {
        List<DictContentProc> dictList=dictContentProcDao.queryAll();
        return dictList;
    }

    @Override
    public List<DictContentProc> getKeyById(int[] idArray) {
        List<DictContentProc> dictList=dictContentProcDao.queryById(idArray);
        return dictList;
    }
}
