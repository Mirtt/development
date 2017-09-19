package com.cu.service.balk;

import com.cu.model.BalkBasic;

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
}
