package com.cu.service.impl;

import com.cu.BaseTest;
import com.cu.dao.StatDao;
import com.cu.model.stat.Stat;
import com.cu.service.StatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @authur Zyq
 * @create 2017/11/6.
 */
public class StatServiceImplTest extends BaseTest {
    @Autowired
    private StatDao statDao;
    @Autowired
    private StatService statService;
    @Test
    public void resultStat() throws Exception {
        List<Stat> stats=statService.resultStat();
        System.out.println("success");
    }

}