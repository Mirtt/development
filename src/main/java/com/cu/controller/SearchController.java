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

import java.util.List;

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
        String[] cKeyList = new String[ids.length];
        for (int i = 0; i < dictList.size(); i++) {
            cKeyList[i] = dictList.get(i).getContent_key();
            //System.out.println(cKeyList[i]);
        }
        List<BalkBasic> balkList= balkService.getByKey(cKeyList);
        System.out.println(balkList.size());

        return null;
    }
}
