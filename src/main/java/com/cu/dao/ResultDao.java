package com.cu.dao;

import com.cu.model.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作结果表的接口
 * Created by  Zyq
 * 2017/9/18
 * 结果表
 */
@Repository
public interface ResultDao {
    /**
     * 给结果表中插入结果数据
     * @param resultList
     */
    void insertResult(@Param("resultList") List<Result> resultList);

    /**
     * 将结果对应的申告内容关键字和申告内容关键字对应的故障现象存入result中
     * @param content_key
     */
    //todo
    void updateContentKeyAndProblem(@Param("content_key")String content_key,@Param("problem")String problem,@Param("balk_no_list")List<String> balk_no_list);

    /**
     * 将结果对应的处理过程关键字和对应的故障原因存入result中
     * @param proc_key
     * @param reason
     * @param balk_no_list
     */
    void updateProcessKeyAndReason(@Param("proc_key")String proc_key,@Param("reason")String reason,@Param("balk_no_list")List<String> balk_no_list);

    /**
     * 显示所有查询时间 用做批号显示
     * @return
     */
    List<String> searchTimeList();

    /**
     * 根据批号查询对应的结果集合
     * @param search_time
     * @return
     */
    List<Result> queryBySearchTime(@Param("search_time") String search_time);

    /**
     * 根据故障现象查询对应的结果集
     * @param problems
     * @return
     */
    List<Result> queryByProblem(@Param("problems")String[] problems);

    /**
     * 查询含有申告内容关键字的结果
     * @param content_key
     * @return
     */
    List<Result> queryByContentKey(@Param("content_key")String content_key);

    /**
     * 根据受理单号列表和处理过程关键字搜索对应的行
     * @param balk_no_list
     * @param proc_key
     * @return
     */
    List<Result> queryByProcessKey(@Param("balk_no_list")List<String> balk_no_list, @Param("proc_key")String proc_key);
}
