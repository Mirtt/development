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

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    public String result(Model model) {
        model.addAttribute("searchList", resultService.searchTimeList());
        return "result";
    }

    @RequestMapping(value = "/download")
    public String download(@RequestParam(value = "search_time") String search_time
            , HttpServletResponse response){
        System.out.println(search_time);
        List<Result> resultList = resultService.queryBySearchTime(search_time);
        HSSFWorkbook wb = resultService.writeResultExcel(resultList);
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        //todo 再看看下载是怎么实现的
        //try {
        //    OutputStream fos = new FileOutputStream(search_time+".xls");
        //    wb.write(fos);
        //    fos.flush();
        //    fos.close();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //String path=search_time+".xls";
        try {
            //File file = new File(path);
            //String fileName = file.getName();
            //InputStream fis = new BufferedInputStream(new FileInputStream(path));
            //byte[] buffer = new byte[fis.available()];
            //fis.read(buffer);
            //fis.close();
            wb.write(os);
            byte[] buffer = os.toByteArray();
            String fileName=search_time;
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes()));
            //response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
