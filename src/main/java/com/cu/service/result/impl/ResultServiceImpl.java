package com.cu.service.result.impl;

import com.cu.model.BalkBasic;
import com.cu.model.Result;
import com.cu.model.SheetProc;
import com.cu.service.result.ResultService;
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
    @Override
    public List<Result> setResult(List<BalkBasic> balkList, String search_time) {
        List<Result> resultList = new ArrayList<>();
        for (int i = 0; i < balkList.size(); i++) {
            Result result = new Result();
            StringBuffer proc = new StringBuffer(); //存储处理过程
            StringBuffer operation = new StringBuffer();//存储操作类型
            StringBuffer write_user_name = new StringBuffer();//存储操作人名
            StringBuffer write_time = new StringBuffer();//存储操作时间
            BalkBasic balkBasic = balkList.get(i);
            List<SheetProc> sheetProcList = balkBasic.getSheetProcList();
            //将查询结果存入实例中
            result.setUser_id(0);//todo: 缺少动态获取userid的session方法，待添加
            result.setSearch_time(search_time);//查询时间
            result.setType("受理单");//todo 还未知受理单类型如何查询，待添加
            result.setBalk_no(balkBasic.getBalk_no()); //受理单号
            result.setBalk_content(balkBasic.getBalk_content()); //申告内容
            result.setContent_key(balkBasic.getContent_key()); //申告内容关键字
            result.setProc_key(balkBasic.getProc_key()); //处理过程关键字
            result.setWrite_dept_name("网管中心.交换中心"); //填写部门
            for (int j = 0; j < sheetProcList.size(); j++) {
                proc.append(sheetProcList.get(j).getIntro());
                proc.append(" ");
                operation.append(sheetProcList.get(j).getType_id());
                operation.append(",");
                write_user_name.append(sheetProcList.get(j).getWrite_user_name());
                write_user_name.append(" ");
                write_time.append(sheetProcList.get(j).getWrite_time());
                write_time.append(" ");
            }
            result.setIntro(proc.toString()); //处理过程
            result.setOperation_type(operation.toString()); //操作类型
            result.setWrite_user_name(write_user_name.toString());//填写人
            result.setWrite_time(write_time.toString());//填写时间
            resultList.add(result);
        }
        return resultList;
    }
}
