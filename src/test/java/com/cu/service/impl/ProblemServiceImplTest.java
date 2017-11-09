package com.cu.service.impl;

import com.cu.BaseTest;
import com.cu.model.Problem;
import com.cu.service.ProblemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @authur Zyq
 * @create 2017/11/9.
 */
public class ProblemServiceImplTest extends BaseTest {
    @Autowired
    private ProblemService problemService;
    @Test
    public void queryContentKey() throws Exception {
        List<Problem> problemList=problemService.queryContentKey();
        System.out.println(problemList.size());
    }

}