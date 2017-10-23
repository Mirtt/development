package com.cu.service.impl;

import com.cu.dao.ProblemDao;
import com.cu.model.Problem;
import com.cu.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 故障现象业务逻辑实现类
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemDao problemDao;

    @Override
    public List<Problem> queryAll() {
        return problemDao.queryAll();
    }
}
