package com.cu.service;

import com.cu.model.BalkBasic;

import java.util.List;
import java.util.Map;

/**
 * Created by  Zyq
 * 2017/9/19
 * 处理工单的接口
 */
public interface BalkService {
    /**
     * 根据受理单号查询对应的申告内容和处理过程内容
     * @param balk_no
     * @return
     */
    BalkBasic getContentProc(String balk_no);

    /**
     * 根据关键字在申告内容中查询含有关键字的受理单
     * @param keyMap
     * @return
     */
    List<BalkBasic> getByKey(Map<String,String> keyMap);
}
