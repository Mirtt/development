package com.cu.service.result;

import com.cu.model.BalkBasic;
import com.cu.model.Result;

import java.util.List;

/**
 * 结果表的业务逻辑
 *
 * @authur Zyq
 * @create 2017/9/27.
 */
public interface ResultService {
    /**
     * 通过查询到的balkList将有用字段存入Result中
     * @param balkList
     * @return
     */
    List<Result> setResult(List<BalkBasic> balkList,String search_time);
}
