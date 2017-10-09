package com.cu.controller;

import com.cu.model.Result;
import com.cu.service.balk.BalkService;
import com.cu.service.result.ResultService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "/result")
    public String result(Model model){
        model.addAttribute("searchList", resultService.searchTimeList());
        return "result";
    }

    @RequestMapping(value = "/download")
    public String download(@RequestParam(value = "search_time")String search_time){
        System.out.println(search_time);
        List<Result> resultList=resultService.queryBySearchTime(search_time);
        HSSFWorkbook wb= resultService.writeResultExcel(resultList);
        //todo 需要添加下载方法，弹窗下载
        return null;
    }
}
