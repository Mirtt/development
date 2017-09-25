package com.cu.dao;

import com.cu.model.BalkBasic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by  Zyq
 * 2017/9/18
 * 受理单数据库操作
 */
@Component
public interface BalkBasicDao {
    /**
     * 根据受理单号查询出该受理单下的申告内容和处理过程内容
     *
     * @param balk_no 受理单号
     * @return
     */
    BalkBasic queryByBalkNo(@Param("balk_no") String balk_no);

    /**
     * 根据申告内容关键字和处理过程关键字列表查询对应的受理单的单号
     *
     * @param keyMap 关键字字典
     * @return
     */
    List<BalkBasic> queryByKey(@Param("keyMap") Map<String,String> keyMap);
}
