package com.cu.controller;

import com.cu.model.ContentKey;
import com.cu.model.Problem;
import com.cu.model.ProcessKey;
import com.cu.model.Result;
import com.cu.service.ContentKeyService;
import com.cu.service.ProblemService;
import com.cu.service.ProcessKeyService;
import com.cu.service.ResultService;
import com.cu.util.json.DataJson;
import com.cu.util.poi.Excel;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单导出查询页面控制
 *
 * @authur Zyq
 * @create 2017/12/18.
 */
@Controller
@RequestMapping(value = "/query")
public class QueryController {

    @Autowired
    ProblemService problemService;
    @Autowired
    ContentKeyService contentKeyService;
    @Autowired
    ProcessKeyService processKeyService;
    @Autowired
    ResultService resultService;

    @RequestMapping(value = "/")
    public String query(HttpSession session, Model model) {
        if (session.getAttribute("current_user") == null){
            model.addAttribute("msg","请登录");
            return "index";
        }
        return "query";
    }

    @RequestMapping(value = "/select2ContentKey", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> select2ContentKey(@RequestParam(value = "contentKey", required = false) String content_key) {
        //返回根据故障现象分组的申告内容关键字
        List<Map> select2data = new ArrayList<>();//返回json数据
        //json数据格式如下 data=[{"text": "parent","children": [{child1},{}] }, {} ,{}....]
        List<Problem> problemList = problemService.queryAll();
        if (content_key == null || content_key.trim().length() < 1) { //如果没有输入查询则全显示
            for (Problem p : problemList) {
                Map dt = new HashMap();
                List<Map> children = new ArrayList<>();
                List<ContentKey> contentKeyList = contentKeyService.listContentKeyByProblemId(p.getProblem_id());
                for (ContentKey c : contentKeyList) {
                    Map ck = new HashMap();
                    ck.put("id", c.getContent_key_id());
                    ck.put("text", c.getContent_key());
                    ck.put("parent", p.getProblem());
                    children.add(ck);
                }
                dt.put("text", p.getProblem());
                dt.put("children", children);
                select2data.add(dt);
            }
            return select2data;
        } else {
            for (Problem p : problemList) {
                Map dt = new HashMap();
                List<Map> children = new ArrayList<>();
                List<ContentKey> contentKeyList = contentKeyService.listContentKeyByProblemId(p.getProblem_id());
                for (ContentKey c : contentKeyList) {
                    if (c.getContent_key().contains(content_key)) {
                        Map ck = new HashMap();
                        ck.put("id", c.getContent_key_id());
                        ck.put("text", c.getContent_key());
                        ck.put("parent", p.getProblem());
                        children.add(ck);
                    }
                }
                dt.put("text", p.getProblem());
                dt.put("children", children);
                select2data.add(dt);
            }
            return select2data;
        }
    }

    @RequestMapping(value = "select2ProcessKey", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> select2ProcessKey(@RequestParam(value = "contentKey", required = false) Integer[] content_key_list,
                                       @RequestParam(value = "processKey", required = false, defaultValue = "nothing") String process_key) {
        List<Map> select2data = new ArrayList<>();//返回json数据
        //json数据格式如下 data=[{"text": "parent","children": [{child1},{}] }, {} ,{}....]
        if (content_key_list == null || content_key_list.length < 1) {
            //没有申告内容关键字限制 返回所有处理过程关键字按照原因分类
            //获取所有原因分类
            List<String> reason = processKeyService.queryReason();
            //按照原因分类返回关键字
            for (String r : reason) {
                Map dt = new HashMap();
                List<Map> children = new ArrayList<>();
                List<ProcessKey> processKeyList = processKeyService.listByReason(r);
                for (ProcessKey p : processKeyList) {
                    Map ck = new HashMap();
                    if (process_key.equals("nothing") || p.getProcess_key().contains(process_key)) {
                        ck.put("id", p.getProcess_key_id());
                        ck.put("text", p.getProcess_key());
                        ck.put("parent", r);
                        children.add(ck);
                    }
                }
                dt.put("text", r);
                dt.put("children", children);
                select2data.add(dt);
            }
            return select2data;
        } else {
            List<String> reason = processKeyService.queryReason();
            List<ProcessKey> processKeyList = new ArrayList<>();
            //查询申告内容对应下的关键字
            for (int i = 0; i < content_key_list.length - 1; i++) {
                List<ProcessKey> t = processKeyService.queryAllByPriority(content_key_list[i]);
                processKeyList.addAll(t);
            }
            for (String r : reason) {
                Map dt = new HashMap();
                List<Map> children = new ArrayList<>();
                for (ProcessKey p : processKeyList) {
                    if (!p.getReason().equals(r)) {
                        continue;
                    }
                    Map ck = new HashMap();
                    if (process_key.equals("nothing") || p.getProcess_key().contains(process_key)) {
                        ck.put("id", p.getProcess_key_id());
                        ck.put("text", p.getProcess_key());
                        ck.put("parent", r);
                        children.add(ck);
                    }
                }
                dt.put("text", r);
                dt.put("children", children);
                select2data.add(dt);
            }
            return select2data;
        }
    }

    @RequestMapping(value = "key", method = RequestMethod.GET)
    @ResponseBody
    public DataJson key(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
            @RequestParam(value = "start") String start,
            @RequestParam(value = "end") String end,
            @RequestParam(value = "contentKey", required = false) String[] contentKeyList,
            @RequestParam(value = "processKey", required = false) String[] processKeyList
    ) {
        DataJson dataJson = new DataJson();
        if (!pageSize.equals("All")) {
            PageHelper.startPage(pageNum, Integer.parseInt(pageSize));
        }
        List<Result> resultList = resultService.listByCkAndPk(start, end, contentKeyList, processKeyList);
        int total = resultService.listByCkAndPk(start, end, contentKeyList, processKeyList).size();
        dataJson.setTotal(total);
        dataJson.setRows(resultList);
        return dataJson;
    }
    @RequestMapping(value = "free",method = RequestMethod.GET)
    @ResponseBody
    public DataJson free(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
            @RequestParam(value = "start") String start,
            @RequestParam(value = "end") String end,
            @RequestParam(value = "contentKey", required = false) String contentKey,
            @RequestParam(value = "processKey", required = false) String processKey
    ) {
        DataJson dataJson = new DataJson();
        if (!pageSize.equals("All")) {
            PageHelper.startPage(pageNum, Integer.parseInt(pageSize));
        }
        List<Result> resultList=resultService.listLikeCkAndPk(start,end,contentKey,processKey);
        int total =resultService.listLikeCkAndPk(start,end,contentKey,processKey).size();
        dataJson.setTotal(total);
        dataJson.setRows(resultList);
        return dataJson;
    }

    @RequestMapping(value = "report", method = RequestMethod.POST)
    public void report(@RequestParam(value = "balkNoList") String[] balkNoList, HttpServletResponse response) {
        List<Result> resultList=resultService.listResultByBalkNoList(balkNoList);
        String[] header={"序号","类型","受理单号","申告内容","填写部门","处理过程","申告内容关键字","处理过程关键字","故障现象","故障原因"}; //表头
        int[] colWidth={5,7,14,25,18,25,15,15,15,15};//列宽
        Excel excel=new Excel("result",header,colWidth);
        HSSFWorkbook wb = excel.writeToExcel(resultList);
        try {
            excel.downloadExcel(wb,response);
        } catch (IOException e) {
            System.out.println("下载问题");
            e.printStackTrace();
        }
    }
}

