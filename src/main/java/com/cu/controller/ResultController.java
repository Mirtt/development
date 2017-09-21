package com.cu.controller;

import com.cu.model.BalkBasic;
import com.cu.service.balk.BalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private BalkService balkService;

    @RequestMapping(value = "/result")
    public String result(Model model){
        BalkBasic balkBasic = balkService.getContentProc("201701339202");
        model.addAttribute("balkBasic",balkBasic);
        return "result";
    }
}
