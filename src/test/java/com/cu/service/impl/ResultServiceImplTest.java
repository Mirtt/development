package com.cu.service.impl;

import com.cu.BaseTest;
import com.cu.service.ResultService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ${DESCRIPTION}
 *
 * @authur Zyq
 * @create 2017/10/27.
 */
public class ResultServiceImplTest extends BaseTest {

    @Autowired
    private ResultService resultService;
    @Test
    public void tagResult() throws Exception {
        resultService.tagResult();
        System.out.println("success");
    }
}