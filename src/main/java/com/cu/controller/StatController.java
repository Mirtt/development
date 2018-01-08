package com.cu.controller;

import com.cu.model.Problem;
import com.cu.model.stat.Stat;
import com.cu.service.ProblemService;
import com.cu.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

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
    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/stat", method = RequestMethod.GET)
    public String stat() {
        return "stat";
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    @ResponseBody
    public Map getChart(@RequestParam(value = "date") String date
    ) {
        if (date.equals("")) {
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);//查询的记录是上一月的
            date = Integer.toString(y) + "-" + Integer.toString(m);
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

    @RequestMapping(value = "/barChart", method = RequestMethod.GET)
    @ResponseBody
    public Map geBarChart(@RequestParam(value = "date") String date
    ) {
        if (date.equals("")) {
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);//查询的记录是上一月的
            date = Integer.toString(y) + "-" + Integer.toString(m);
        }
        Map Data = new HashMap<>(16);
        List<Stat> statList = statService.resultStatByType(date);
        List<Problem> problemList = problemService.queryAll();
        Problem other = new Problem();
        other.setProblem("其他");
        problemList.add(other);
        other = null;
        int length = statList.size();//设定返回数组长度
        String[] problems = new String[problemList.size()];
        Integer[] problemNum = new Integer[length];
        Map[] normalProblemMapArray = new HashMap[problemList.size()];

        Map[] vipProblemMapArray = new HashMap[problemList.size()];


        for (int i = 0; i < problemList.size(); i++) {
            problems[i] = problemList.get(i).getProblem();
            for (int j = 0; j < length; j++) {
                if (statList.get(j).getProblem().equals(problems[i])) {
                    problemNum[j] = statList.get(j).getProblemNum();
                    Map problemMap = new HashMap<>(1);
                    problemMap.put("name", problems[i]);
                    problemMap.put("value", problemNum[j]);
                    if ("VIP受理单".equals(statList.get(j).getBalkType())) {
                        vipProblemMapArray[i] = problemMap;
                    }
                    if ("普通受理单".equals(statList.get(j).getBalkType())) {
                        normalProblemMapArray[i] = problemMap;
                    }
                }
            }
            if (vipProblemMapArray[i] == null) {
                Map problemMap = new HashMap<>(1);
                problemMap.put("name", problems[i]);
                problemMap.put("value", 0);
                vipProblemMapArray[i] = problemMap;
            }
            if (normalProblemMapArray[i] == null) {
                Map problemMap = new HashMap<>(1);
                problemMap.put("name", problems[i]);
                problemMap.put("value", 0);
                normalProblemMapArray[i] = problemMap;
            }
        }
        Data.put("problems", problems);
        Data.put("problemNum", problemNum);
        Data.put("normalProblemMapArray", normalProblemMapArray);
        Data.put("vipProblemMapArray", vipProblemMapArray);

        return Data;
    }

    @RequestMapping(value = "/barDrill", method = RequestMethod.GET)
    @ResponseBody
    public Map barDrillChart(@RequestParam(value = "problem") String problem,
                             @RequestParam(value = "date") String date) throws UnsupportedEncodingException {
        if (date.equals("")) {
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);//查询的记录是上一月的
            date = Integer.toString(y) + "-" + Integer.toString(m);
        }
        String problemTemp = URLDecoder.decode(problem, "utf-8");
        Map Data = new HashMap<>(16);
        List<Stat> statList = statService.resultReasonByType(problemTemp, date);
        if (statList.size() < 1) {
            return null;
        }
        int length = statList.size();
        Set<String> reasonSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            reasonSet.add(statList.get(i).getReason());
        }
        String[] reasons = new String[reasonSet.size()];
        reasonSet.toArray(reasons);
        Integer[] reasonNum = new Integer[length];
        Map[] normalReasonMapArray = new Map[reasons.length];
        Map[] vipReasonMapArray = new Map[reasons.length];
        for (int i = 0; i < reasons.length; i++) {
            for (int j = 0; j < length; j++) {
                if (statList.get(j).getReason().equals(reasons[i])){
                    reasonNum[j] = statList.get(j).getReasonNum();
                    Map reasonMap = new HashMap(1);
                    reasonMap.put("name", reasons[i]);
                    reasonMap.put("value", reasonNum[j]);
                    if ("VIP受理单".equals(statList.get(j).getBalkType())){
                        vipReasonMapArray[i]=reasonMap;
                    }
                    if ("普通受理单".equals(statList.get(j).getBalkType())){
                        normalReasonMapArray[i]=reasonMap;
                    }
                }
            }
            if (vipReasonMapArray[i]==null){
                Map reasonMap = new HashMap(1);
                reasonMap.put("name", reasons[i]);
                reasonMap.put("value", 0);
                vipReasonMapArray[i]=reasonMap;
            }
            if (normalReasonMapArray[i]==null){
                Map reasonMap = new HashMap(1);
                reasonMap.put("name", reasons[i]);
                reasonMap.put("value", 0);
                normalReasonMapArray[i]=reasonMap;
            }
        }

        Data.put("reasons", reasons);
        Data.put("reasonNum", reasonNum);
        Data.put("vipReasonMapArray", vipReasonMapArray);
        Data.put("normalReasonMapArray", normalReasonMapArray);
        return Data;
    }

    @RequestMapping(value = "/drill", method = RequestMethod.GET)
    @ResponseBody
    public Map drillChart(@RequestParam(value = "problem") String problem,
                          @RequestParam(value = "date") String date) throws UnsupportedEncodingException {
        if (date.equals("")) {
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);//查询的记录是上一月的
            date = Integer.toString(y) + "-" + Integer.toString(m);
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
