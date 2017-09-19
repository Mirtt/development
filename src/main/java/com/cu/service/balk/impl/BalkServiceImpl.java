package com.cu.service.balk.impl;

import com.cu.dao.BalkBasicDao;
import com.cu.model.BalkBasic;
import com.cu.service.balk.BalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
