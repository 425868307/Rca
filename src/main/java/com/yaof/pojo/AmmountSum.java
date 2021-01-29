package com.yaof.pojo;

public class AmmountSum {

    private String userId = "0";    //客户id

    private String id;        //消费记录id

    private String account;    //

    private String year;    //年

    private String month;    //月

    private String day;        //日

    private double cost;    //花费

    private double income;    //收入

    private double sub;        //收入-花费  差额

    private String happenDate;    //发生日期

    private String happenTime;    //发生时间

    private String type;    //汇总数据查询 y m d

    private String desc;    //描述

    private int limit;    //查询数据条数

    public String getAccount() {
        return account;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public double getCost() {
        return cost;
    }

    public double getIncome() {
        return income;
    }

    public double getSub() {
        return sub;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setSub(double sub) {
        this.sub = sub;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHappenDate() {
        return happenDate;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
