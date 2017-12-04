package com.congp.timekeeping.data;

/**
 * Created by congp on 11/25/2017.
 */

public class Shift {
    int id;
    String sDate;
    String sName;
    String sInTime;
    String sOutTime;
    double sTotalTime;
    String sNote;
    String sMonth;


    public String getsMonth() {
        return sMonth;
    }

    public void setsMonth(String sMonth) {
        this.sMonth = sMonth;
    }

    public Shift(String sDate, String sName, String sInTime, String sOutTime, double sTotalTime, String sNote,String sMonth) {
        this.sDate = sDate;
        this.sName = sName;
        this.sInTime = sInTime;
        this.sOutTime = sOutTime;
        this.sTotalTime = sTotalTime;
        this.sNote = sNote;
        this.sMonth= sMonth;
    }

    public Shift() {

    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsInTime() {
        return sInTime;
    }

    public void setsInTime(String sInTime) {
        this.sInTime = sInTime;
    }

    public String getsOutTime() {
        return sOutTime;
    }

    public void setsOutTime(String sOutTime) {
        this.sOutTime = sOutTime;
    }

    public double getsTotalTime() {
        return sTotalTime;
    }

    public void setsTotalTime(double sTotalTime) {
        this.sTotalTime = sTotalTime;
    }

    public String getsNote() {
        return sNote;
    }

    public void setsNote(String sNote) {
        this.sNote = sNote;
    }
}
