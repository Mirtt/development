package com.cu.service.impl;

import com.cu.dao.StatDao;
import com.cu.model.stat.Stat;
import com.cu.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

;

/**
 * @authur Zyq
 * @create 2017/11/6.
 */
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    private StatDao statDao;

    //private int total;//存储所有故障分类数量总和
    @Override
    public List<Stat> resultStat() {
        int total=0;
        List<Stat> statList= statDao.resultStat();
        for (Stat s:statList){
            total+=s.getProblemNum();
        }
        for (Stat s:statList){
            s.setProblemPercent((double) s.getProblemNum()/total);
        }
        return statList;
    }

}
