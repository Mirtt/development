package com.cu.dao;

import com.cu.BaseTest;
import com.cu.model.BalkBasic;
import com.cu.model.Result;
import com.cu.service.ResultService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @authur Zyq
 * @create 2017/10/26.
 */
public class ResultDaoTest extends BaseTest {
    @Autowired
    private BalkBasicDao balkBasicDao;
    @Autowired
    private  ResultDao resultDao;
    @Autowired
    private ResultService resultService;
    @Test
    public void insertResult() throws Exception {
        List<BalkBasic> balkBasicList=balkBasicDao.queryByTime("2017-06-17 00:00:00","2017-06-26 00:00:00");
        List<Result> resultList=resultService.setResult(balkBasicList);
        resultDao.insertResult(resultList);
    }

}