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
public class BalkBasicDaoTest extends BaseTest {
    @Autowired
    private BalkBasicDao balkBasicDao;
    @Autowired
    private ResultService resultService;
    @Test
    public void queryByTime() throws Exception {
        List<BalkBasic> balkBasicList = balkBasicDao.queryByTime("2017-06-17 00:00:00","2017-06-17 23:59:59");
        System.out.println(balkBasicList.size());
        List<Result> resultList=resultService.setResult(balkBasicList);
        System.out.println(resultList.size());
    }

}