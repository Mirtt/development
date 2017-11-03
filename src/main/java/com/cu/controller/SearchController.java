package com.cu.controller;

import com.cu.model.BalkBasic;
import com.cu.model.DictContentProc;
import com.cu.model.Problem;
import com.cu.model.Result;
import com.cu.service.BalkService;
import com.cu.service.DictService;
import com.cu.service.ProblemService;
import com.cu.service.ResultService;
import com.cu.util.poi.Excel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private ProblemService problemService;
    @Autowired
    private BalkService balkService;
    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "/search")
    public String search(@RequestParam(required = true,defaultValue = "1")Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,1);
        List<Problem> problemList = problemService.queryAll();
        PageInfo<Problem> p = new PageInfo<>(problemList);
        model.addAttribute("pList",problemList);
        model.addAttribute("page",p);
        return "search";
    }

    @RequestMapping(value = "/getResult", method = RequestMethod.POST)
    public String getResult(@RequestParam(value = "key_id", required = false) String[] ids, Model model) {
        if (ids != null && ids.length != 0) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String search_time = df.format(new Date()); //设置查询时间
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
            List<Result> resultList = resultService.setResult(balkList, search_time);
            System.out.println(resultList.size());
            //查询结果存入结果表中
            if (resultList.size() != 0){
                resultService.insertResult(resultList);
            }else{
                model.addAttribute("msg","所查询的关键字无对应工单");
                return "forward:/search";
            }
            model.addAttribute("searchList", resultService.searchTimeList());
            return "result";
        }

        model.addAttribute("msg", "请选择查询关键字");

        return "forward:/search";
    }

    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    public String downloadExcel(@RequestParam(value = "key_id", required = false) String[] ids,Model model,HttpServletResponse response){
        if (ids != null && ids.length != 0){
            int[] idArray = new int[ids.length];
            for (int i = 0; i < idArray.length; i++) {
                idArray[i] = Integer.parseInt(ids[i]);
            }
            //获取故障现象
            List<Problem>  problemList=problemService.queryByIdList(idArray);
            //故障现象的名称存入list
            String[] problems=new String[problemList.size()];
            for (int i=0;i<problemList.size();i++){
                problems[i]=problemList.get(i).getProblem();
            }
            //根据故障现象名称查询结果表中对应的结果
            List<Result> resultList=resultService.queryByProblem(problems);
            if (resultList == null ||resultList.size() == 0){
                model.addAttribute("msg","所查询的故障现象无对应工单");
                return "forward:/search";
            }
            //todo 将结果导出成excel
            String[] header={"序号","类型","受理单号","申告内容","填写部门","处理过程","申告内容关键字","处理过程关键字","故障现象","故障原因"}; //表头
            int[] colWidth={15};
            Excel excel=new Excel("result",header,colWidth);
            HSSFWorkbook wb = excel.writeToExcel(resultList);
            try {
                excel.downloadExcel(wb,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", "请选择查询关键字");

        return "forward:/search";
    }
}
