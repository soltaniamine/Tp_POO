package com.example.back;

import java.io.Serializable;

public class Creneau implements Serializable {
    private String StartTime ;
    private String endTime ;
    private boolean isFree ;

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Creneau(String startTime , String endTime , boolean isFree) {
        this.StartTime = startTime ;
        this.endTime = endTime ;
        this.isFree = isFree ;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
}
