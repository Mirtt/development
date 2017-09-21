package com.cu.dao;

import com.cu.model.DictContentProc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by  Zyq
 * 2017/9/16
 * 字典表的数据库操作类
 */
@Component
public interface DictContentProcDao {
    /**
     * 查询出所有的申告内容和处理过程关键字
     * @return
     */
    List<DictContentProc> queryAll();

    /**
     * 根据一批id查询出关键字列表
     */
    List<DictContentProc> queryById(@Param("idArray")int[] id);
}
