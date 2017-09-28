package com.cu.controller;

import com.cu.model.BalkBasic;
import com.cu.model.DictContentProc;
import com.cu.model.Result;
import com.cu.service.balk.BalkService;
import com.cu.service.dict.DictService;
import com.cu.service.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "/search")
    public String search(Model model) {
        List<DictContentProc> dictList = dictService.getKey();
        model.addAttribute("dictList", dictList);
        return "search";
    }

    @RequestMapping(value = "/getResult", method = RequestMethod.POST)
    public String getResult(@RequestParam("key_id") String[] ids,Model model) {
        SimpleDateFormat df= new SimpleDateFormat("yyyyMMddHHmmss");
        String search_time= df.format(new Date());
        int[] idArray = new int[ids.length];
        for (int i = 0; i < idArray.length; i++) {
            idArray[i] = Integer.parseInt(ids[i]);
        }
        List<DictContentProc> dictList = dictService.getKeyById(idArray);
        //申告内容关键字和处理过程关键字  处理后存入MAP中
        Map<String, String> keyMap = dictService.toMap(dictList);
        //接受关键字map 查询到的结果存入balkList
        List<BalkBasic> balkList = balkService.getByKey(keyMap);
        //将balkList结果提取字段存入result实例中
        List<Result> resultList=resultService.setResult(balkList,search_time);
        System.out.println(resultList.size());
        resultService.insertResult(resultList);

        model.addAttribute("search_time",search_time);

        return "result";
    }
}
