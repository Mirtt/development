package com.cu.service;

import com.cu.model.BalkBasic;
import com.cu.model.Result;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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

    /**
     * 查询出的结果数据导入结果表
     * @param resultList
     */
    void insertResult(List<Result>resultList);

    /**
     * 查询出结果表中所有批号
     * @return
     */
    List<String> searchTimeList();

    /**
     * 根据批号查询对应的结果集合
     * @param search_time
     * @return
     */
    List<Result> queryBySearchTime(String search_time);

    /**
     * 下载结果表
     * @param resultList
     */
    HSSFWorkbook writeResultExcel(List<Result>resultList);
}
