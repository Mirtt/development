package com.cu.dao;

import com.cu.BaseTest;
import com.cu.model.ContentKey;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

;

/**
 * @authur Zyq
 * @create 2017/10/25.
 */
public class ContentKeyDaoTest extends BaseTest {
    @Autowired
    private ContentKeyDao contentKeyDao;

    @Test
    public  void testQueryAllOrderByPriority(){
        List<ContentKey> contentKeyList = contentKeyDao.queryAllOrderByPriority();
        System.out.println(contentKeyList.size());
        for (int i =0;i<contentKeyList.size();i++){
            System.out.print(contentKeyList.get(i).getContent_priority());
            System.out.print("<-------->");
            System.out.print(contentKeyList.get(i).getContent_key());
            System.out.print("\n");
        }
    }
}
