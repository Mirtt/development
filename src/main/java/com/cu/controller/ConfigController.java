package com.cu.controller;


import com.cu.model.ContentKey;
import com.cu.model.Problem;
import com.cu.model.ProcessKey;
import com.cu.model.stat.Stat;
import com.cu.service.ContentKeyService;
import com.cu.service.ProblemService;
import com.cu.service.ProcessKeyService;
import com.cu.util.json.DataJson;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 配置页面控制器
 *
 * @authur Zyq
 * @create 2017/11/9.
 */
//branch
@Controller
public class ConfigController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ContentKeyService contentKeyService;
    @Autowired
    private ProcessKeyService processKeyService;

    @RequestMapping(value = "/ckConfig")
    public String ckConfig() {
        return "contentKeyConfig";
    }

    @RequestMapping(value = "/pkConfig")
    public String pkConfig() {
        return "processKeyConfig";
    }

    @RequestMapping(value = "/pkConfigTable", method = RequestMethod.GET)
    @ResponseBody
    public DataJson pkConfigTable(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") String pageSize) {
        DataJson dataJson = new DataJson();
        int total = 0;
        if (!pageSize.equals("All")){
            PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
        }
        List<ContentKey> contentKeyList = contentKeyService.queryProcessKey();
        List<ContentKey> totalList = contentKeyService.queryProcessKey();
        for (ContentKey c : totalList) {
            total += c.getProcessKeyList().size();
        }
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
                stat.setProcess_key_id(p.getProcess_key_id());
                rows.add(stat);
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
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") String pageSize) {
        DataJson dataJson = new DataJson();
        List<Stat> rows = new ArrayList<>(16);
        int total = 0;
        if (content_key != null && !content_key.equals("") && (process_key == null || process_key.equals(""))) {
            if (!pageSize.equals("All")){
                PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
            }
            List<ContentKey> contentKeyList = contentKeyService.queryLikeContentKey(content_key);
            List<ContentKey> totalList = contentKeyService.queryLikeContentKey(content_key);
            for (ContentKey c : totalList) {
                total += c.getProcessKeyList().size();
            }
            for (ContentKey c : contentKeyList) {
                List<ProcessKey> processKeyList = c.getProcessKeyList();
                for (ProcessKey p : processKeyList) {
                    Stat stat = new Stat();
                    stat.setContent_key_id(c.getContent_key_id());
                    stat.setContent_key(c.getContent_key());
                    stat.setProcess_key(p.getProcess_key());
                    stat.setProcess_priority(p.getProcess_priority());
                    stat.setReason(p.getReason());
                    stat.setProcess_key_id(p.getProcess_key_id());
                    rows.add(stat);
                }
            }
        }
        if (process_key != null && !process_key.equals("") && (content_key == null || content_key.equals(""))) {
            if (!pageSize.equals("All")){
                PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
            }
            List<ContentKey> contentKeyList = contentKeyService.queryLikeProcessKey(process_key);
            List<ContentKey> totalList = contentKeyService.queryLikeProcessKey(process_key);
            for (ContentKey c : totalList) {
                total += c.getProcessKeyList().size();
            }
            for (ContentKey c : contentKeyList) {
                List<ProcessKey> processKeyList = c.getProcessKeyList();
                for (ProcessKey p : processKeyList) {
                    Stat stat = new Stat();
                    stat.setContent_key_id(c.getContent_key_id());
                    stat.setContent_key(c.getContent_key());
                    stat.setProcess_key(p.getProcess_key());
                    stat.setProcess_priority(p.getProcess_priority());
                    stat.setReason(p.getReason());
                    stat.setProcess_key_id(p.getProcess_key_id());
                    rows.add(stat);
                }
            }
        }
        if (content_key != null && !content_key.equals("") && process_key != null && !process_key.equals("")) {
            if (!pageSize.equals("All")){
                PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
            }
            List<ContentKey> contentKeyList = contentKeyService.queryLikeContentKeyAndProcessKey(content_key, process_key);
            List<ContentKey> totalList = contentKeyService.queryLikeContentKeyAndProcessKey(content_key, process_key);
            for (ContentKey c : totalList) {
                total += c.getProcessKeyList().size();
            }
            for (ContentKey c : contentKeyList) {
                List<ProcessKey> processKeyList = c.getProcessKeyList();
                for (ProcessKey p : processKeyList) {
                    Stat stat = new Stat();
                    stat.setContent_key_id(c.getContent_key_id());
                    stat.setContent_key(c.getContent_key());
                    stat.setProcess_key(p.getProcess_key());
                    stat.setProcess_priority(p.getProcess_priority());
                    stat.setReason(p.getReason());
                    stat.setProcess_key_id(p.getProcess_key_id());
                    rows.add(stat);
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
                                        @RequestParam(defaultValue = "10") String pageSize) {
        DataJson dataJson = new DataJson();
        List<Stat> rows = new ArrayList<>(16);
        int total = 0;
        if (problem != null && !problem.equals("") && (content_key == null || content_key.equals(""))) {
            if (!pageSize.equals("All")){
                PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
            }
            List<Problem> problemList = problemService.queryLikeProblem(problem);
            List<Problem> totalList = problemService.queryLikeProblem(problem);
            for (Problem p : totalList) {
                total += p.getContentKeyList().size();
            }
            for (Problem p : problemList) {
                List<ContentKey> contentKeyList = p.getContentKeyList();
                for (ContentKey c : contentKeyList) {
                    Stat stat = new Stat();
                    stat.setProblem_id(p.getProblem_id());
                    stat.setProblem(p.getProblem());
                    stat.setContent_key(c.getContent_key());
                    stat.setContent_priority(c.getContent_priority());
                    stat.setContent_key_id(c.getContent_key_id());
                    rows.add(stat);
                }
            }
        }
        if (content_key != null && !content_key.equals("") && (problem == null || problem.equals(""))) {
            if (!pageSize.equals("All")){
                PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
            }
            List<Problem> problemList = problemService.queryLikeContentKey(content_key);
            List<Problem> totalList = problemService.queryLikeContentKey(content_key);
            for (Problem p : totalList) {
                total += p.getContentKeyList().size();
            }
            for (Problem p : problemList) {
                List<ContentKey> contentKeyList = p.getContentKeyList();
                for (ContentKey c : contentKeyList) {
                    Stat stat = new Stat();
                    stat.setProblem_id(p.getProblem_id());
                    stat.setProblem(p.getProblem());
                    stat.setContent_key(c.getContent_key());
                    stat.setContent_priority(c.getContent_priority());
                    stat.setContent_key_id(c.getContent_key_id());
                    rows.add(stat);
                }
            }
        }
        if (problem != null && !problem.equals("") && content_key != null && !content_key.equals("")) {
            if (!pageSize.equals("All")){
                PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
            }
            List<Problem> problemList = problemService.queryLikeProblemAndContentKey(problem, content_key);
            List<Problem> totalList = problemService.queryLikeProblemAndContentKey(problem, content_key);
            for (Problem p : totalList) {
                total += p.getContentKeyList().size();
            }
            for (Problem p : problemList) {
                List<ContentKey> contentKeyList = p.getContentKeyList();
                for (ContentKey c : contentKeyList) {
                    Stat stat = new Stat();
                    stat.setProblem_id(p.getProblem_id());
                    stat.setProblem(p.getProblem());
                    stat.setContent_key(c.getContent_key());
                    stat.setContent_priority(c.getContent_priority());
                    stat.setContent_key_id(c.getContent_key_id());
                    rows.add(stat);
                }
            }
        }
        Collections.sort(rows);
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }

    @RequestMapping(value = "/ckConfigTable", method = RequestMethod.GET)
    @ResponseBody
    public DataJson ckConfigTable(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10")String pageSize) {
        DataJson dataJson = new DataJson();
        int total = 0;
        if (!pageSize.equals("All")){
            PageHelper.startPage(pageNum,Integer.parseInt(pageSize));
        }
        List<Problem> problemList = problemService.queryContentKey();
        List<Problem> totalList = problemService.queryContentKey();
        for (Problem p : totalList) {
            total += p.getContentKeyList().size();
        }
        List<Stat> rows = new ArrayList<>(16);
        for (Problem p : problemList) {
            List<ContentKey> contentKeyList = p.getContentKeyList();
            for (ContentKey c : contentKeyList) {
                Stat stat = new Stat();
                stat.setProblem_id(p.getProblem_id());
                stat.setProblem(p.getProblem());
                stat.setContent_key(c.getContent_key());
                stat.setContent_priority(c.getContent_priority());
                stat.setContent_key_id(c.getContent_key_id());
                rows.add(stat);
            }
        }
        Collections.sort(rows);
        dataJson.setTotal(total);
        dataJson.setRows(rows);
        return dataJson;
    }

    @RequestMapping(value = "/addContentKey", method = RequestMethod.POST)
    public String addContentKey(@RequestParam(value = "problem") String problem,
                                @RequestParam(required = false, value = "content_key") String content_key,
                                @RequestParam(required = false, value = "content_priority") String content_priority,
                                Model model) {
        Problem p = problemService.queryByProblem(problem);
        //先判断是否存在关键字和优先级参数 如果不存在则添加故障现象
        if ((content_key == null || content_key.trim().equals("")) && (content_priority == null || content_priority.trim().equals(""))) {
            if (p == null) {
                //没有对应故障现象 创建新的故障现象
                problemService.insertProblem(problem);
                model.addAttribute("msg", "添加成功");
                return "search";
            } else {
                model.addAttribute("msg", "故障现象已存在");
                return "search";
            }
        }
        //存在关键字和优先级参数 则添加故障现象----关键字----优先级的对应关系
        if (content_key != null && !content_key.trim().equals("") && content_priority != null && !content_priority.trim().equals("")) {
            content_key = content_key.trim();
            content_priority = content_priority.trim();
            //判断是否存在该优先级 若存在则插入到该优先级其余优先级向后加一，不存在则直接添加
            int cp = Integer.parseInt(content_priority);
            List<ContentKey> contentKeyList = contentKeyService.queryByPriority(cp);
            if (contentKeyList.size() != 0 && contentKeyList.get(0).getContent_priority() == cp) {
                Map<Integer, Integer> contentKeyMap = new HashMap<>(16);// id--priority 对应关系
                for (ContentKey c : contentKeyList) {
                    c.setContent_priority(c.getContent_priority() + 1);
                    contentKeyMap.put(c.getContent_key_id(), c.getContent_priority());
                }
                contentKeyService.updateContentPriority(contentKeyMap);
            }
            if (p == null) {
                //没有对应故障现象 创建新的故障现象并且建立关系
                problemService.insertProblem(problem);
                Problem tempP = problemService.queryByProblem(problem);
                contentKeyService.insertContentKey(content_key, Integer.parseInt(content_priority), tempP.getProblem_id());
                model.addAttribute("msg", "添加成功");
                return "contentKeyConfig";
            } else {
                contentKeyService.insertContentKey(content_key, Integer.parseInt(content_priority), p.getProblem_id());
                model.addAttribute("msg", "添加成功");
                return "contentKeyConfig";
            }
        } else {
            model.addAttribute("msg", "请重新填写关键字和故障现象");
            return "contentKeyConfig";
        }
    }


    @RequestMapping(value = "/addProcessKey", method = RequestMethod.POST)
    public String addProcessKey(@RequestParam(value = "content_key_id") int cid,
                                @RequestParam(value = "process_key") String process_key,
                                @RequestParam(value = "process_priority") int process_priority,
                                @RequestParam(value = "reason", required = false) String reason,
                                Model model) {
        //确认cid是否存在对应的申告内容关键字并且确认输入的优先级是否已经存在
        ContentKey contentKey = contentKeyService.queryById(cid);
        if (contentKey == null) {
            model.addAttribute("msg", "请输入正确的申告内容关键字ID");
            return "processKeyConfig";
        }
        List<ProcessKey> processKeyList = processKeyService.queryByPriority(process_priority, cid);
        if (processKeyList.size() != 0 && processKeyList.get(0).getProcess_priority() == process_priority) {
            Map<Integer, Integer> updateMap = new HashMap<>(16);//需要更新的行
            for (int i = 0; i < processKeyList.size(); i++) {
                ProcessKey p = processKeyList.get(i);
                updateMap.put(p.getProcess_key_id(), p.getProcess_priority() + 1);
            }
            if (!updateMap.isEmpty()) {
                processKeyService.updateProcessKey(updateMap);//更新优先级
            }
        }
        processKeyService.insertProcessKey(cid, process_key, process_priority, reason);
        model.addAttribute("msg", "添加成功");
        return "processKeyConfig";
    }

    @RequestMapping(value = "/editContentKey", method = RequestMethod.POST)
    @ResponseBody
    public DataJson editContentKey(@RequestParam(value = "cid") int cid,
                                   @RequestParam(value = "ck") String ck) {
        DataJson d = new DataJson();
        contentKeyService.updateContentKeyById(cid, ck);
        d.setStatus("success");
        return d;
    }

    @RequestMapping(value = "/editProcessKey", method = RequestMethod.POST)
    @ResponseBody
    public DataJson editProcessKey(ProcessKey processKey) {
        DataJson d = new DataJson();
        processKeyService.updateProcessKeyById(processKey.getProcess_key_id(), processKey.getProcess_key());
        d.setStatus("success");
        return d;
    }

    /**
     * 2017.12.05 yjz 更新处理过程关键字的内容申告关键字id
     *
     * @param processKey
     * @return
     */
    @RequestMapping(value = "//editCIdOfProcessKey", method = RequestMethod.POST)
    @ResponseBody
    public DataJson editCIdOfProcessKey(ProcessKey processKey) {
        DataJson d = new DataJson();
        processKeyService.updateContentIdById(processKey.getProcess_key_id(), processKey.getContent_key_id());
        d.setStatus("success");
        return d;
    }
}
