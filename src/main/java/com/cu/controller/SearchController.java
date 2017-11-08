package com.cu.controller;

import com.cu.model.Problem;
import com.cu.model.Result;
import com.cu.service.BalkService;
import com.cu.service.DictService;
import com.cu.service.ProblemService;
import com.cu.service.ResultService;
import com.cu.util.json.DataJson;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    @RequestMapping(value = "/initTable",method = RequestMethod.GET)
    @ResponseBody
    public DataJson initTable(@RequestParam(required = true,defaultValue = "1")Integer pageNum,
            @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Problem> problemList = problemService.queryAll();
        PageInfo<Problem> p = new PageInfo<>(problemList);
        DataJson dataJson=new DataJson();
        int total=problemService.countAll();
        dataJson.setTotal(total);//todo 获取所有problem的数目
        dataJson.setRows(problemList);
        return dataJson;
    }
    @RequestMapping(value = "/searchTable",method = RequestMethod.GET)
    @ResponseBody
    public DataJson searchTable(@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "10")Integer pageSize,
                                @RequestParam(required = false,value = "problem_id")String problem_id,@RequestParam(required = false,value = "problem")String problem,Model model){
        System.out.println(problem_id+"\n"+problem);
        int total=0;
        List<Problem> rows=new ArrayList<>(16);
        DataJson dataJson=new DataJson();
        if (problem_id != null && !problem_id.equals("")){
            int pid=Integer.parseInt(problem_id);
            Problem p=problemService.queryById(pid);
            if (p!=null){
                total+=1;
                rows.add(p);
            }
        }
        if (problem!=null && !problem.equals("")){
            List<Problem> problemList=problemService.queryLike(problem);
            if (problemList!=null){
                total+=problemList.size();
                rows.addAll(problemList);
            }
        }
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }

    @RequestMapping(value = "/report",method = RequestMethod.POST)
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
            int[] colWidth={5,7,14,25,18,25,15,15,15,15};//列宽
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

    @RequestMapping(value="/deleteProblem",method = RequestMethod.POST)
    public void deleteProblem(@RequestParam(value = "problem_id",required = true)int[] ids){
        //todo delete method
    }
}
