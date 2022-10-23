/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.viewmodel;

import java.util.List;

/**
 *
 * @author buing
 */
public class ResponseObject {

    private String message, orderCount, registerCount, statisticSum;
    private List<Something> jsonCountData, jsonIncomeData;
    private double increaseRate;

    public ResponseObject() {
    }

    public ResponseObject(String message, String orderCount, String registerCount, String statisticSum, double increaseRate) {
        this.message = message;
        this.orderCount = orderCount;
        this.registerCount = registerCount;
        this.statisticSum = statisticSum;
        this.increaseRate = increaseRate;
    }

    public ResponseObject(String message, String orderCount, String registerCount, String statisticSum, List<Something> jsonCountData, List<Something> jsonIncomeData, double increaseRate) {
        this.message = message;
        this.orderCount = orderCount;
        this.registerCount = registerCount;
        this.statisticSum = statisticSum;
        this.jsonCountData = jsonCountData;
        this.jsonIncomeData = jsonIncomeData;
        this.increaseRate = increaseRate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(String registerCount) {
        this.registerCount = registerCount;
    }

    public String getStatisticSum() {
        return statisticSum;
    }

    public void setStatisticSum(String statisticSum) {
        this.statisticSum = statisticSum;
    }

    public List<Something> getJsonCountData() {
        return jsonCountData;
    }

    public void setJsonCountData(List<Something> jsonCountData) {
        this.jsonCountData = jsonCountData;
    }

    public List<Something> getJsonIncomeData() {
        return jsonIncomeData;
    }

    public void setJsonIncomeData(List<Something> jsonIncomeData) {
        this.jsonIncomeData = jsonIncomeData;
    }

    public double getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(double increaseRate) {
        this.increaseRate = increaseRate;
    }

}
