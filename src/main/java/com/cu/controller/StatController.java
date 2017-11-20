package com.cu.controller;

import com.cu.model.stat.Stat;
import com.cu.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
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

    @RequestMapping(value = "/stat", method = RequestMethod.GET)
    public String stat() {
        return "stat";
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    @ResponseBody
    public Map getChart(@RequestParam(value = "date") String date
                        ) {
        if (date.equals("")){
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);//查询的记录是上一月的
            date=Integer.toString(y)+"-"+Integer.toString(m);
        }
        Map Data = new HashMap<>(16);
        List<Stat> statList = statService.resultStat(date);
        int length = statList.size();//设定返回数组长度
        String[] problems = new String[length];
        Integer[] problemNum = new Integer[length];
        Map[] problemMapArray = new HashMap[length];
        for (int i = 0; i < length; i++) {
            problems[i] = statList.get(i).getProblem();
            problemNum[i] = statList.get(i).getProblemNum();
            //为饼图构造必要的json数据格式 [{name: percent:},{}]
            Map problemMap = new HashMap<>(1);
            problemMap.put("name", problems[i]);
            problemMap.put("value", problemNum[i]);
            problemMapArray[i] = problemMap;
        }
        Data.put("problems", problems);
        Data.put("problemNum", problemNum);
        Data.put("problemMapArray", problemMapArray);

        return Data;
    }

    @RequestMapping(value = "/drill", method = RequestMethod.GET)
    @ResponseBody
    public Map drillChart(@RequestParam(value = "problem") String problem,
                          @RequestParam(value = "date")String date) throws UnsupportedEncodingException {
        if (date.equals("")){
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);//查询的记录是上一月的
            date=Integer.toString(y)+"-"+Integer.toString(m);
        }
        String problemTemp = URLDecoder.decode(problem, "utf-8");
        Map Data = new HashMap<>(16);
        List<Stat> statList = statService.resultReason(problemTemp, date);
        int length = statList.size();
        String[] reasons = new String[length];
        Integer[] reasonNum = new Integer[length];
        Map[] reasonMapArray = new Map[length];
        for (int i = 0; i < length; i++) {
            reasons[i] = statList.get(i).getReason();
            reasonNum[i] = statList.get(i).getReasonNum();
            Map reasonMap = new HashMap(1);
            reasonMap.put("name", reasons[i]);
            reasonMap.put("value", reasonNum[i]);
            reasonMapArray[i] = reasonMap;
        }
        Data.put("reasons", reasons);
        Data.put("reasonNum", reasonNum);
        Data.put("reasonMapArray", reasonMapArray);
        return Data;
    }
}
