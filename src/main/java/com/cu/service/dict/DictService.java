package com.cu.service.dict;

import com.cu.model.DictContentProc;

import java.util.List;
import java.util.Map;

/**
 * 字典业务逻辑接口
 *
 * @authur Zyq
 * @create 2017/9/17.
 */
public interface DictService {
    /**
     * 查询出所有申告内容和处理过程的关键字
     *
     * @return
     */
    List<DictContentProc> getKey();

    /**
     * 根据前端传入的id数组查询出所有对应的数据行实例
     *
     * @param idArray
     * @return
     */
    List<DictContentProc> getKeyById(int[] idArray);

    /**
     * 将查询出的结果列表转换成map格式
     * @param dictList
     * @return
     */
    Map<String,String> toMap(List<DictContentProc> dictList);
}
