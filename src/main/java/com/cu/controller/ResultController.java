package com.cu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 结果页控制
 *
 * @authur Zyq
 * @create 2017/9/17.
 */
@Controller
@RequestMapping("")
public class ResultController {
    @RequestMapping(value = "/result")
    public String result(){
        return "result";
    }
}
