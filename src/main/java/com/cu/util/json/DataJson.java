package com.cu.util.json;

import java.util.List;

/**
 * 前端表格数据传递
 *
 * @authur Zyq
 * @create 2017/11/6.
 */
public class DataJson {
    private int total;//总行数
    private List rows;//具体数据

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
