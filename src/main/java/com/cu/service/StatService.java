package com.cu.service;

import com.cu.model.stat.Stat;

import java.util.List;

;

/**
 * @authur Zyq
 * @create 2017/11/6.
 */
public interface StatService {

    List<Stat> resultStat(String date);

    List<Stat> resultReason(String problem,String date);
}
