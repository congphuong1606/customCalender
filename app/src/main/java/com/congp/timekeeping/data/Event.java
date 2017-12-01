package com.congp.timekeeping.data;

/**
 * Created by congp on 11/24/2017.
 */

public class Event {
    String id;//ngày+tháng+năm
    int day;
    int month;
    int year;

    public Event(String id, int day, int month, int year) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
