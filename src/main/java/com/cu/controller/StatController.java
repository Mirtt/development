package com.cu.controller;

import com.cu.model.stat.Stat;
import com.cu.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计分析页面控制器
 *
 * @authur Zyq
 * @create 2017/11/6.
 */
@Controller
public class StatController {
    @Autowired
    private StatService statService;
    @RequestMapping(value = "/stat",method = RequestMethod.GET)
    public String stat(){
        return "stat";
    }
    @RequestMapping(value = "/testChart",method = RequestMethod.GET)
    @ResponseBody
    public Map getChart(){
        Map Data = new HashMap<>(16);
        List<Stat> statList=statService.resultStat();
        int length=statList.size();//设定返回数组长度
        String[] problems=new String[length];
        Integer[] problemNum=new Integer[length];
        Map[] problemMapArray=new HashMap[length];
        for (int i =0;i<statList.size();i++){
            problems[i]=statList.get(i).getProblem();
            problemNum[i]=statList.get(i).getProblemNum();
            //为饼图构造必要的json数据格式 [{name: percent:},{}]
            Map problemMap=new HashMap<>(1);
            problemMap.put("name",problems[i]);
            problemMap.put("value",problemNum[i]);
            problemMapArray[i]=problemMap;
        }
        Data.put("problems",problems);
        Data.put("problemNum",problemNum);
        Data.put("problemMapArray",problemMapArray);

        return Data;
    }
    @RequestMapping(value = "/testDrill",method = RequestMethod.GET)
    @ResponseBody
    public Map drillChart(@RequestParam(value = "problem",required = true)String problem){
        System.out.println(problem);
        return new HashMap(1);
    }
}
