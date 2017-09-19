package com.cu.model;

import java.util.List;

/**
 * 受理单实体类，用于向结果表中存入数据
 *
 * @authur Zyq
 * @create 2017/9/18.
 */
public class BalkBasic {
    private String balk_no; //受理单号
    private String balk_content; //申告内容
    private List<SheetProc> sheetProcList; //受理单对应多张工单


    public String getBalk_no() {
        return balk_no;
    }

    public void setBalk_no(String balk_no) {
        this.balk_no = balk_no;
    }

    public String getBalk_content() {
        return balk_content;
    }

    public void setBalk_content(String balk_content) {
        this.balk_content = balk_content;
    }

    public List<SheetProc> getSheetProcList() {
        return sheetProcList;
    }

    public void setSheetProcList(List<SheetProc> sheetProcList) {
        this.sheetProcList = sheetProcList;
    }
}
