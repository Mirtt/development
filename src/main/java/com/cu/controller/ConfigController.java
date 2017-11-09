package com.cu.controller;


import com.cu.model.ContentKey;
import com.cu.model.Problem;
import com.cu.model.stat.Stat;
import com.cu.service.ProblemService;
import com.cu.util.json.DataJson;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置页面控制器
 *
 * @authur Zyq
 * @create 2017/11/9.
 */
@Controller
public class ConfigController {
    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/config",method = RequestMethod.GET)
    public String config(){
        return "config";
    }

    @RequestMapping(value = "/configTable",method = RequestMethod.GET)
    @ResponseBody
    public DataJson configTable(@RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        DataJson dataJson=new DataJson();
        PageHelper.startPage(pageNum,pageSize);
        List<Problem> problemList=problemService.queryContentKey();
        int total=0;
        List<Stat> rows=new ArrayList<>(16);
        for (Problem p:problemList){
            List<ContentKey> contentKeyList=p.getContentKeyList();
            for (ContentKey c:contentKeyList){
                Stat stat=new Stat();
                stat.setProblem_id(p.getProblem_id());
                stat.setProblem(p.getProblem());
                stat.setContent_key(c.getContent_key());
                stat.setContent_priority(c.getContent_priority());
                rows.add(stat);
                total+=1;
            }
        }
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }
}
