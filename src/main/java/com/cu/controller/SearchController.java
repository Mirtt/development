package com.cu.controller;

import com.cu.model.DictContentProc;
import com.cu.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/search")
    public String search(Model model){
        List<DictContentProc> dictList=dictService.getKey();
        model.addAttribute("dictList",dictList);
        return "search";
    }
}
