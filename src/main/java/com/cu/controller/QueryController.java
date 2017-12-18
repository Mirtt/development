package com.cu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 工单导出查询页面控制
 *
 * @authur Zyq
 * @create 2017/12/18.
 */
@Controller
public class QueryController {

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String query(){
        return "query";
    }
}

