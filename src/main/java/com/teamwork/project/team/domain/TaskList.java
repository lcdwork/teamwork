package com.teamwork.project.team.domain;

import java.util.List;

public class TaskList {
    private String time;
    private List<Task> list;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Task> getList() {
        return list;
    }

    public void setList(List<Task> list) {
        this.list = list;
    }
}
