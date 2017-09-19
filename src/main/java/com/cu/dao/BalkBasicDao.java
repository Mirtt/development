package com.cu.dao;

import com.cu.model.BalkBasic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by  Zyq
 * 2017/9/18
 * 受理单数据库操作
 */
@Component
public interface BalkBasicDao {
    /**
     * 根据受理单号查询出该受理单下的申告内容和处理过程内容
     * @param balk_no
     * @return
     */
    BalkBasic queryByBalkNo(@Param("balk_no")String balk_no);
}
