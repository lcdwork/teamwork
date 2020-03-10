package com.teamwork.framework.web.domain;

import java.util.Date;
import java.util.List;

public class GanttTreeList {

    private Date startDate;

    private Date endDate;

    private List<GanttTree> list;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<GanttTree> getList() {
        return list;
    }

    public void setList(List<GanttTree> list) {
        this.list = list;
    }
}
