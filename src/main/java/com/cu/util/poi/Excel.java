package com.cu.util.poi;

import com.cu.model.Result;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel工具类
 *
 * @authur Zyq
 * @create 2017/11/2.
 */
public class Excel {
    private String fileName;//文件名
    private String[] headerName;//excel表头数组
    private int colNum;//列数
    private int defaultWidth = 15;//默认列宽为15
    private int[] colWidth;//自定义列宽数组

    public Excel() {
    }

    //public Excel(String fileName) {
    //    this.fileName = fileName;
    //}
    //
    //public Excel(String fileName, int colNum) {
    //    this.fileName = fileName;
    //    this.colNum = colNum;
    //}

    public Excel(String fileName, String[] headerName, int[] colWidth) {
        this.fileName = fileName+".xls";
        this.headerName = headerName;
        this.colNum = headerName.length;
        this.colWidth = colWidth;
        if (colWidth.length == 1) {
            this.defaultWidth = colWidth[0];
        }
    }

    public HSSFWorkbook writeToExcel(List<Result> resultList) {

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
        if (colWidth.length == 1) {
            sheet.setDefaultColumnWidth(defaultWidth);
        } else {
            for (int i = 0; i < colWidth.length; i++) {
                sheet.setColumnWidth(i, 256 * colWidth[i] + 184);
            }
        }
        //设置header内容
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headerName.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style1);
            HSSFRichTextString text = new HSSFRichTextString(headerName[i]);
            cell.setCellValue(text);
        }
        //遍历resultList存入excel
        int rownum = 1;//数据行从第二行开始
        for (Result result:resultList){
            row=sheet.createRow(rownum);
            List<String> rs=new ArrayList<>(16);//将数据按照string形式存入excel
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
            for (int i=0;i<rs.size();i++){
                HSSFCell cell=row.createCell(i);
                cell.setCellStyle(style2);
                HSSFRichTextString richTextString=new HSSFRichTextString(rs.get(i));
                cell.setCellValue(richTextString);
            }
            rownum++;
        }
        return wb;
    }

    public void downloadExcel(HSSFWorkbook wb, HttpServletResponse response) throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        wb.write(os);
        byte[] buffer=os.toByteArray();
        String fileName=this.fileName;
        // 清空response
        response.reset();
        //设置字符集
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes()));
        response.addHeader("Content-Length", "" + os.size());

        OutputStream toClient = new BufferedOutputStream(
                response.getOutputStream());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }
}
