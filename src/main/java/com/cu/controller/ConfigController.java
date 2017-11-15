package com.cu.controller;


import com.cu.model.ContentKey;
import com.cu.model.Problem;
import com.cu.model.ProcessKey;
import com.cu.model.stat.Stat;
import com.cu.service.ContentKeyService;
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
    @Autowired
    private ContentKeyService contentKeyService;

    @RequestMapping(value = "/ckConfig", method = RequestMethod.GET)
    public String ckConfig() {
        return "contentKeyConfig";
    }

    @RequestMapping(value = "/pkConfig", method = RequestMethod.GET)
    public String pkConfig() {
        return "processKeyConfig";
    }

    @RequestMapping(value = "/pkConfigTable", method = RequestMethod.GET)
    @ResponseBody
    public DataJson pkConfigTable(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        DataJson dataJson = new DataJson();
        PageHelper.startPage(pageNum, pageSize);
        List<ContentKey> contentKeyList = contentKeyService.queryProcessKey();
        int total = 0;
        List<Stat> rows = new ArrayList<>(16);
        for (ContentKey c : contentKeyList) {
            List<ProcessKey> processKeyList = c.getProcessKeyList();
            for (ProcessKey p : processKeyList) {
                Stat stat = new Stat();
                stat.setContent_key_id(c.getContent_key_id());
                stat.setContent_key(c.getContent_key());
                stat.setProcess_key(p.getProcess_key());
                stat.setProcess_priority(p.getProcess_priority());
                stat.setReason(p.getReason());
                rows.add(stat);
                total += 1;
            }
        }
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }

    @RequestMapping(value = "/pkConfigSearchTable", method = RequestMethod.GET)
    @ResponseBody
    public DataJson pkConfigSearchTable(@RequestParam(required = false, value = "process_key") String process_key,
                                        @RequestParam(required = false, value = "content_key") String content_key,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        DataJson dataJson = new DataJson();
        List<Stat> rows = new ArrayList<>(16);
        int total = 0;
        if (content_key != null && !content_key.equals("") && (process_key == null || process_key.equals(""))) {
            PageHelper.startPage(pageNum,pageSize);
            List<ContentKey> contentKeyList = contentKeyService.queryLikeContentKey(content_key);
            for (ContentKey c : contentKeyList) {
                List<ProcessKey> processKeyList = c.getProcessKeyList();
                for (ProcessKey p : processKeyList) {
                    Stat stat = new Stat();
                    stat.setContent_key_id(c.getContent_key_id());
                    stat.setContent_key(c.getContent_key());
                    stat.setProcess_key(p.getProcess_key());
                    stat.setProcess_priority(p.getProcess_priority());
                    stat.setReason(p.getReason());
                    rows.add(stat);
                    total += 1;
                }
            }
        }
        if (process_key != null && !process_key.equals("") && (content_key == null || content_key.equals(""))) {
            PageHelper.startPage(pageNum,pageSize);
            List<ContentKey> contentKeyList = contentKeyService.queryLikeProcessKey(process_key);
            for (ContentKey c : contentKeyList) {
                List<ProcessKey> processKeyList = c.getProcessKeyList();
                for (ProcessKey p : processKeyList) {
                    Stat stat = new Stat();
                    stat.setContent_key_id(c.getContent_key_id());
                    stat.setContent_key(c.getContent_key());
                    stat.setProcess_key(p.getProcess_key());
                    stat.setProcess_priority(p.getProcess_priority());
                    stat.setReason(p.getReason());
                    rows.add(stat);
                    total += 1;
                }
            }
        }
        if (content_key != null && !content_key.equals("") && process_key != null && !process_key.equals("")) {
            PageHelper.startPage(pageNum,pageSize);
            List<ContentKey> contentKeyList = contentKeyService.queryLikeContentKeyAndProcessKey(content_key, process_key);
            for (ContentKey c : contentKeyList) {
                List<ProcessKey> processKeyList = c.getProcessKeyList();
                for (ProcessKey p : processKeyList) {
                    Stat stat = new Stat();
                    stat.setContent_key_id(c.getContent_key_id());
                    stat.setContent_key(c.getContent_key());
                    stat.setProcess_key(p.getProcess_key());
                    stat.setProcess_priority(p.getProcess_priority());
                    stat.setReason(p.getReason());
                    rows.add(stat);
                    total += 1;
                }
            }
        }
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }

    @RequestMapping(value = "/ckConfigSearchTable", method = RequestMethod.GET)
    @ResponseBody
    public DataJson ckConfigSearchTable(@RequestParam(required = false, value = "problem") String problem,
                                        @RequestParam(required = false, value = "content_key") String content_key,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        DataJson dataJson = new DataJson();
        List<Stat> rows = new ArrayList<>(16);
        int total = 0;
        if (problem != null && !problem.equals("") && (content_key == null || content_key.equals(""))) {
            PageHelper.startPage(pageNum, pageSize);
            List<Problem> problemList = problemService.queryLikeProblem(problem);
            for (Problem p : problemList) {
                List<ContentKey> contentKeyList = p.getContentKeyList();
                for (ContentKey c : contentKeyList) {
                    Stat stat = new Stat();
                    stat.setProblem_id(p.getProblem_id());
                    stat.setProblem(p.getProblem());
                    stat.setContent_key(c.getContent_key());
                    stat.setContent_priority(c.getContent_priority());
                    rows.add(stat);
                    total += 1;
                }
            }
        }
        if (content_key != null && !content_key.equals("") && (problem == null || problem.equals(""))) {
            PageHelper.startPage(pageNum, pageSize);
            List<Problem> problemList = problemService.queryLikeContentKey(content_key);
            for (Problem p : problemList) {
                List<ContentKey> contentKeyList = p.getContentKeyList();
                for (ContentKey c : contentKeyList) {
                    Stat stat = new Stat();
                    stat.setProblem_id(p.getProblem_id());
                    stat.setProblem(p.getProblem());
                    stat.setContent_key(c.getContent_key());
                    stat.setContent_priority(c.getContent_priority());
                    rows.add(stat);
                    total += 1;
                }
            }
        }
        if (problem != null && !problem.equals("") && content_key != null && !content_key.equals("")) {
            PageHelper.startPage(pageNum, pageSize);
            List<Problem> problemList = problemService.queryLikeProblemAndContentKey(problem, content_key);
            for (Problem p : problemList) {
                List<ContentKey> contentKeyList = p.getContentKeyList();
                for (ContentKey c : contentKeyList) {
                    Stat stat = new Stat();
                    stat.setProblem_id(p.getProblem_id());
                    stat.setProblem(p.getProblem());
                    stat.setContent_key(c.getContent_key());
                    stat.setContent_priority(c.getContent_priority());
                    rows.add(stat);
                    total += 1;
                }
            }
        }
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }

    @RequestMapping(value = "/ckConfigTable", method = RequestMethod.GET)
    @ResponseBody
    public DataJson ckConfigTable(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        DataJson dataJson = new DataJson();
        PageHelper.startPage(pageNum, pageSize);
        List<Problem> problemList = problemService.queryContentKey();
        int total = 0;
        List<Stat> rows = new ArrayList<>(16);
        for (Problem p : problemList) {
            List<ContentKey> contentKeyList = p.getContentKeyList();
            for (ContentKey c : contentKeyList) {
                Stat stat = new Stat();
                stat.setProblem_id(p.getProblem_id());
                stat.setProblem(p.getProblem());
                stat.setContent_key(c.getContent_key());
                stat.setContent_priority(c.getContent_priority());
                rows.add(stat);
                total += 1;
            }
        }
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }

    @RequestMapping(value = "/addContentKey",method = RequestMethod.POST)
    public String addContentKey(@RequestParam(value = "problem")String problem,
                              @RequestParam(required = false,value = "content_key")String content_key,
                              @RequestParam(required = false,value = "content_priority")String content_priority){
        return "contentKeyConfig";
    }
}
