package com.cu.service.impl;

import com.cu.dao.BalkBasicDao;
import com.cu.model.BalkBasic;
import com.cu.service.BalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

;

/**
 * @authur Zyq
 * @create 2017/9/19.
 */
@Service
public class BalkServiceImpl implements BalkService{

    @Autowired
    private BalkBasicDao balkBasicDao;


    @Override
    public BalkBasic getContentProc(String balk_no) {
        BalkBasic balkBasic = balkBasicDao.queryByBalkNo(balk_no);
        return balkBasic;
    }

    @Override
    public List<BalkBasic> getByKey(Map<String,String> keyMap) {
        List<BalkBasic> balkList= new ArrayList<>();
        //为查询结果添加查询的关键字
        for(Map.Entry<String,String> key : keyMap.entrySet()){
            List<BalkBasic> balk=balkBasicDao.queryByKey(key.getKey(),key.getValue());
            for (int i=0;i<balk.size();i++){
                balk.get(i).setContent_key(key.getKey().replaceAll("%.+%",""));
                balk.get(i).setProc_key(key.getValue().replaceAll("%.+%",""));
            }
            balkList.addAll(balk);
        }
        return balkList;
    }
}
