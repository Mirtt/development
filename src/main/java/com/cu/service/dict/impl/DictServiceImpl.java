package com.cu.service.dict.impl;

import com.cu.dao.DictContentProcDao;
import com.cu.model.DictContentProc;
import com.cu.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关键字业务逻辑实现
 *
 * @authur Zyq
 * @create 2017/9/17.
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictContentProcDao dictContentProcDao;

    @Override
    public List<DictContentProc> getKey() {
        List<DictContentProc> dictList = dictContentProcDao.queryAll();
        return dictList;
    }

    @Override
    public List<DictContentProc> getKeyById(int[] idArray) {
        List<DictContentProc> dictList = dictContentProcDao.queryById(idArray);
        return dictList;
    }

    @Override
    public Map<String, String> toMap(List<DictContentProc> dictList) {
        int length = dictList.size(); //列表中关键字个数
        String[] cKeyList = new String[length]; //申告内容关键字数组
        String[] pKeyList = new String[length]; //处理过程关键字数组
        Map<String, String> keyMap = new HashMap<>();
        for (int i = 0; i < dictList.size(); i++) {
            cKeyList[i] = dictList.get(i).getContent_key();
            pKeyList[i] = dictList.get(i).getProc_key();
            cKeyList[i] = cKeyList[i].replaceAll("&", "%' AND  b.BALK_CONTENT like '%");
            cKeyList[i] = cKeyList[i].replaceAll("\\|", "%' OR  b.BALK_CONTENT like '%");
            pKeyList[i] = pKeyList[i].replaceAll("&", "%' AND  s.INTRO like '%");
            pKeyList[i] = pKeyList[i].replaceAll("\\|", "%' OR  s.INTRO like '%");
            keyMap.put(cKeyList[i], pKeyList[i]);
        }
        return keyMap;
    }
}
