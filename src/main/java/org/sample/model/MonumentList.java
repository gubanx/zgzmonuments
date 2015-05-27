package org.sample.model;

import java.io.Serializable;
import java.util.List;

public class MonumentList implements Serializable {

    private int totalCount;
    private int start;
    private int rows;
    private List<Monument> result;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Monument> getResult() {
        return result;
    }

    public void setResult(List<Monument> result) {
        this.result = result;
    }
}
