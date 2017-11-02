package com.cu.dao;

import com.cu.model.BalkBasic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by  Zyq
 * 2017/9/18
 * 受理单数据库操作的接口
 */
@Repository
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
     * @param content_key 申告内容关键字
     * @param proc_key 处理过程关键字
     * @return
     */
    List<BalkBasic> queryByKey(@Param("content_key") String content_key,@Param("proc_key")String proc_key);

    /**
     * 根据时间查询月度的所有受理单
     * @param month
     * @return
     */
    List<BalkBasic> queryByTime(@Param("month") int month);
}
