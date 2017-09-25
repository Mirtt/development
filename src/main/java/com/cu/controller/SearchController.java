package com.cu.controller;

import com.cu.model.BalkBasic;
import com.cu.model.DictContentProc;
import com.cu.service.balk.BalkService;
import com.cu.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分析页控制
 *
 * @authur Zyq
 * @create 2017/9/17.
 */
@Controller
@RequestMapping("")
public class SearchController {
    @Autowired
    private DictService dictService;
    @Autowired
    private BalkService balkService;

    @RequestMapping(value = "/search")
    public String search(Model model) {
        List<DictContentProc> dictList = dictService.getKey();
        model.addAttribute("dictList", dictList);
        return "search";
    }

    @RequestMapping(value = "/getResult", method = RequestMethod.POST)
    public String getResult(@RequestParam("key_id") String[] ids) {
        int[] idArray = new int[ids.length];
        for (int i = 0; i < idArray.length; i++) {
            idArray[i] = Integer.parseInt(ids[i]);
        }
        List<DictContentProc> dictList = dictService.getKeyById(idArray);
        String[] cKeyList = new String[ids.length]; //申告内容关键字数组
        String[] pKeyList = new String[ids.length]; //处理过程关键字数组
        Map<String, String> keyMap = new HashMap<>();
        for (int i = 0; i < dictList.size(); i++) {
            cKeyList[i] = dictList.get(i).getContent_key();
            pKeyList[i] = dictList.get(i).getProc_key();
            cKeyList[i] = cKeyList[i].replaceAll("&", "%' AN D  b.BALK_CONTENT like '%");
            cKeyList[i] = cKeyList[i].replaceAll("\\|", "%' OR  b.BALK_CONTENT like '%");
            pKeyList[i] = pKeyList[i].replaceAll("&", "%' AND  s.INTRO like '%");
            pKeyList[i] = pKeyList[i].replaceAll("\\|", "%' OR  s.INTRO like '%");
            keyMap.put(cKeyList[i],pKeyList[i]);
            //System.out.println(cKeyList[i]);
        }
        List<BalkBasic> balkList = balkService.getByKey(keyMap); //接受查询结果存到balklist
        System.out.println(balkList.size());

        return null;
    }
}
