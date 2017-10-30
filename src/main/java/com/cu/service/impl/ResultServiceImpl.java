package com.cu.service.impl;

import com.cu.dao.ContentKeyDao;
import com.cu.dao.ProblemDao;
import com.cu.dao.ProcessKeyDao;
import com.cu.dao.ResultDao;
import com.cu.model.*;
import com.cu.service.ResultService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 结果表业务逻辑实现类
 *
 * @authur Zyq
 * @create 2017/9/27.
 */
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultDao resultDao;
    @Autowired
    private ContentKeyDao contentKeyDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private ProcessKeyDao processKeyDao;

    @Override
    public List<Result> setResult(List<BalkBasic> balkList) {
        List<Result> resultList = new ArrayList<>(16);

        for (int i = 0; i < balkList.size(); i++) {
            BalkBasic balkBasic = balkList.get(i);
            Result result = new Result();
            StringBuffer introTemp = new StringBuffer();//用于整合一个受理单下所有工单的处理过程
            result.setUser_id(0);//todo: 缺少动态获取userid的session方法，待添加
            result.setType("受理单");//todo 还未知受理单类型如何查询，待添加
            result.setBalk_no(balkBasic.getBalk_no()); //受理单号
            result.setBalk_content(balkBasic.getBalk_content()); //申告内容
            result.setWrite_dept_name("网管中心.交换中心"); //填写部门
            for (int j = 0; j < balkBasic.getSheetProcList().size(); j++) {
                SheetProc sheetProc = balkBasic.getSheetProcList().get(j);
                introTemp.append(sheetProc.getIntro());
                introTemp.append("   ");
            }
            String intro = introTemp.toString();
            result.setIntro(intro);
            resultList.add(result);
        }
        return resultList;
    }

    @Override
    public List<Result> setResult(List<BalkBasic> balkList, String search_time) {
        List<Result> resultList = new ArrayList<>(16);
        for (int i = 0; i < balkList.size(); i++) {
            BalkBasic balkBasic = balkList.get(i);
            List<SheetProc> sheetProcList = balkBasic.getSheetProcList();
            for (int j = 0; j < sheetProcList.size(); j++) {
                Result result = new Result();
                //将查询结果存入实例中
                result.setUser_id(0);//todo: 缺少动态获取userid的session方法，待添加
                result.setSearch_time(search_time);//查询时间
                result.setType("受理单");//todo 还未知受理单类型如何查询，待添加
                result.setBalk_no(balkBasic.getBalk_no()); //受理单号
                result.setBalk_content(balkBasic.getBalk_content()); //申告内容
                result.setContent_key(balkBasic.getContent_key()); //申告内容关键字
                result.setProc_key(balkBasic.getProc_key()); //处理过程关键字
                result.setWrite_dept_name("网管中心.交换中心"); //填写部门
                result.setIntro(sheetProcList.get(j).getIntro());
                resultList.add(result);
            }
        }
        return resultList;
    }

    @Override
    public void insertResult(List<Result> resultList) {
        resultDao.insertResult(resultList);
    }

    @Override
    public List<String> searchTimeList() {
        return resultDao.searchTimeList();
    }

    @Override
    public List<Result> queryBySearchTime(String search_time) {
        return resultDao.queryBySearchTime(search_time);
    }

    @Override
    public List<Result> queryByProblem(String[] problems) {
        return resultDao.queryByProblem(problems);
    }

    @Override
    public List<Result> queryByContentKey(String content_key) {
        return resultDao.queryByContentKey(content_key);
    }

    @Override
    public HSSFWorkbook writeResultExcel(List<Result> resultList) {
        String[] header = {"序号", "类型", "受理单号", "申告内容", "填写部门", "填写内容", "申告内容关键字", "处理过程关键字", "故障现象", "故障原因"};
        //创建workbook （excel）
        HSSFWorkbook wb = new HSSFWorkbook();
        //首先创建字体样式
        HSSFFont font1 = wb.createFont();//创建header字体样式
        font1.setFontName("微软雅黑");//使用宋体
        font1.setFontHeightInPoints((short) 10);//字体大小
        font1.setBold(true);//加粗
        HSSFFont font2 = wb.createFont();//创建数据行字体样式
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 10);

        //设置header单元格样式
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setFont(font1);//将字体注入
        //style1.setWrapText(true);// 自动换行
        style1.setAlignment(HorizontalAlignment.CENTER);//居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);// 水平居中
        style1.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);// 设置单元格的背景颜色（亮黄色）
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style1.setBorderTop(BorderStyle.THIN);// 边框的大小
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        //设置数据单元格样式
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        //style2.setWrapText(true);//自动换行
        style2.setAlignment(HorizontalAlignment.LEFT);//居左
        style2.setVerticalAlignment(VerticalAlignment.CENTER);//水平居中
        style2.setFillForegroundColor(IndexedColors.WHITE.index);//背景色（白色）
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderTop(BorderStyle.THIN);// 边框的大小
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);

        //创建excel的sheet
        HSSFSheet sheet = wb.createSheet();
        //设置列的默认宽度
        sheet.setDefaultColumnWidth(15);
        //设置header内容
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style1);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
        }

        //遍历resultList存入excel
        int rownum = 1;//数据行从第二行开始
        for (Result result : resultList) {
            row = sheet.createRow(rownum);
            List<String> rs = new ArrayList<>();
            rs.add(String.valueOf(rownum));//序号
            rs.add(result.getType());//类型
            rs.add(result.getBalk_no());//受理单号
            rs.add(result.getBalk_content());//申告内容
            rs.add(result.getWrite_dept_name());//填写部门
            rs.add(result.getIntro());//填写内容（处理过程）
            rs.add(result.getContent_key());//申告内容关键字
            rs.add(result.getProc_key());//处理过程关键字
            rs.add(result.getProblem());//故障现象
            rs.add(result.getReason());//故障原因
            for (int i = 0; i < rs.size(); i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                HSSFRichTextString richTextString = new HSSFRichTextString(rs.get(i));
                cell.setCellValue(richTextString);
            }
            rownum++;
        }
        return wb;
    }

    @Override
    public void tagResult() {
        //1.给resultList打申告内容关键字和故障现象的标签
        List<ContentKey> contentKeyList = contentKeyDao.queryAllOrderByPriority();
        for (int i = 0; i < contentKeyList.size(); i++) { //为result从高到低的优先级打标签
            String contentKey = contentKeyList.get(i).getContent_key();
            //contentKeyTemp:经过处理后的申告内容关键字
            String contentKeyTemp = contentKey.replaceAll("&", "%' )AND( balk_content LIKE '%");
            contentKeyTemp = contentKeyTemp.replaceAll("或", "%' OR  balk_content LIKE '%");
            List<Result> resultList = resultDao.queryByContentKey(contentKeyTemp); //查找含有contentkey的受理单
            //problemId:故障现象id 用于标记故障现象
            int problemId = contentKeyList.get(i).getProblem_id();
            Problem problem = problemDao.queryById(problemId); //根据contentKey对应的problemid 查找对应的故障现象
            //contentkeyid：用于查询对应的处理过程
            List<String> balkNoListForContent=new ArrayList<>(16);
            if (resultList == null || resultList.size() == 0) {
                continue;
            }
            for (Result result : resultList) {
                //为contentkey和problem字段为空的行打上标签
                if ((result.getContent_key() == null || result.getContent_key().equals("")) && (result.getProblem() == null || result.getProblem().equals(""))) {
                    //result.setContent_key(contentKey);
                    balkNoListForContent.add( result.getBalk_no());
                } else {
                    continue;
                }
            }
            if (balkNoListForContent != null && balkNoListForContent.size() != 0) {
                resultDao.updateContentKeyAndProblem(contentKey, problem.getProblem(), balkNoListForContent);
            }else{
                continue;
            }
            //2.给resultList打处理过程关键字和故障原因标签
            int contentKeyId = contentKeyList.get(i).getContent_key_id();
            List<ProcessKey> processKeyList = processKeyDao.queryAllByPriority(contentKeyId);
            for (int j = 0; j < processKeyList.size(); j++) {
                String reason = processKeyList.get(j).getReason();
                String processKey = processKeyList.get(j).getProcess_key();
                String processKeyTemp = processKey.replaceAll("&", "%' )AND( intro LIKE '%");//todo need to change
                processKeyTemp = processKeyTemp.replaceAll("或", "%' OR  intro LIKE '%");
                List<Result> resultList1 = resultDao.queryByProcessKey(balkNoListForContent, processKeyTemp);
                List<String>balkNoListForProcess = new ArrayList<>(16);
                if (resultList1 == null || resultList1.size() == 0) {
                    continue;
                }
                for (Result result : resultList1) {
                    if ((result.getProc_key() == null || result.getProc_key().equals("")) && (result.getReason() == null || result.getReason().equals(""))) {
                        balkNoListForProcess.add(result.getBalk_no());
                    } else {
                        continue;
                    }
                }
                if (balkNoListForProcess != null && balkNoListForProcess.size() != 0) {
                    resultDao.updateProcessKeyAndReason(processKey, reason, balkNoListForProcess);
                }
            }
        }
        //todo null-->other
    }
}
