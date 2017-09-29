package com.cu.service.result.impl;

import com.cu.dao.ResultDao;
import com.cu.model.BalkBasic;
import com.cu.model.Result;
import com.cu.model.SheetProc;
import com.cu.service.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 结果表业务逻辑实现类
 *
 * @authur Zyq
 * @create 2017/9/27.
 */
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultDao resultDao;

    @Override
    public List<Result> setResult(List<BalkBasic> balkList, String search_time) {
        List<Result> resultList = new ArrayList<>();
        for (int i = 0; i < balkList.size(); i++) {
            BalkBasic balkBasic = balkList.get(i);
            List<SheetProc> sheetProcList = balkBasic.getSheetProcList();
            for (int j = 0; j < sheetProcList.size(); j++) {
                Result result = new Result();
                //将查询结果存入实例中
                result.setUser_id(0);//todo: 缺少动态获取userid的session方法，待添加
                result.setSearch_time(search_time);//查询时间
                result.setType("受理单");//todo 还未知受理单类型如何查询，待添加
                result.setBalk_no(balkBasic.getBalk_no()); //受理单号
                result.setBalk_content(balkBasic.getBalk_content()); //申告内容
                result.setContent_key(balkBasic.getContent_key()); //申告内容关键字
                result.setProc_key(balkBasic.getProc_key()); //处理过程关键字
                result.setWrite_dept_name("网管中心.交换中心"); //填写部门
                result.setIntro(sheetProcList.get(j).getIntro());
                result.setOperation_type(sheetProcList.get(j).getType_id()); //操作类型
                result.setWrite_user_name(sheetProcList.get(j).getWrite_user_name());//填写人
                result.setWrite_time(sheetProcList.get(j).getWrite_time());//填写时间
                resultList.add(result);
            }
        }
        return resultList;
    }

    @Override
    public void insertResult(List<Result> resultList) {
        resultDao.insertResult(resultList);
    }

    @Override
    public void downloadResultExcel(List<Result> resultList) {

    }
}
