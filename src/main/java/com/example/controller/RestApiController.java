/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.MemberAccountDAO;
import com.example.dao.OrderDAO;
import com.example.entity.MemberAccount;
import com.example.entity.Order;
import com.example.viewmodel.Something;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author buing
 */
@RestController
public class RestApiController {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private MemberAccountDAO memberAccountDAO;

    @RequestMapping(value = "/api/statistic/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> today() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp fromDay = Timestamp.valueOf(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0));
        Timestamp toDay = Timestamp.valueOf(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59));

        List<Something> countOrderList = new ArrayList<>();
        List<Something> amountIncomeList = new ArrayList<>();

        List<Order> orderList = orderDAO.getOrderListFromXToY(fromDay, toDay);
        List<MemberAccount> memberAccountList = memberAccountDAO.getSignUpListFromXToY(fromDay, toDay);

        List<Integer> days = new ArrayList<Integer>();
        int i = LocalDateTime.now().getDayOfMonth();
        int count = 0;
        while (i > 0 && count < 22) {
            days.add(i);
            --i;
            ++count;
        }
        Collections.reverse(days);
        int month = LocalDateTime.now().getMonthValue();
        int year = LocalDateTime.now().getYear();
        for (Integer day : days) {
            Timestamp dateFrom = Timestamp.valueOf(LocalDateTime.of(year, month, day, 0, 0, 0));
            Timestamp dateTo = Timestamp.valueOf(LocalDateTime.of(year, month, day, 23, 59, 59));
            List<Order> orders = orderDAO.getOrderListFromXToY(dateFrom, dateTo);
            countOrderList.add(new Something(String.valueOf(day), orders.size()));
            amountIncomeList.add(new Something(String.valueOf(day), getTotal(orders)));
        }
        LocalDateTime yesterday = LocalDateTime.now().plusDays(-1);
        Timestamp beginYesterday = Timestamp.valueOf(LocalDateTime.of(yesterday.getYear(), yesterday.getMonth(), yesterday.getDayOfMonth(), 0, 0, 0));
        Timestamp endYesterday = Timestamp.valueOf(LocalDateTime.of(yesterday.getYear(), yesterday.getMonth(), yesterday.getDayOfMonth(), 23, 59, 59));

        long tempYesterday = getTotal(orderDAO.getOrderListFromXToY(beginYesterday, endYesterday));
        long tempToday = getTotal(orderDAO.getOrderListFromXToY(fromDay, toDay));

        double increaseRate = (tempToday == 0) ? 0 : (tempToday / tempYesterday) - 1;

        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        map.put("orderCount", String.valueOf(orderList.size()));
        map.put("increaseRate", Math.round(increaseRate * 100));
        map.put("registerCount", String.valueOf(memberAccountList.size()));
        map.put("statisticSum", String.valueOf(getTotal(orderList)));
        map.put("jsonCountData", countOrderList);
        map.put("jsonIncomeData", amountIncomeList);
        return map;
    }

    @RequestMapping(value = "/api/statistic/month", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> month() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beginMonth = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0);
        Timestamp fromDay = Timestamp.valueOf(beginMonth);
        Timestamp toDay = Timestamp.valueOf(LocalDateTime.of(now.getYear(), now.getMonth().getValue() + 1, 1, 23, 59, 59).plusDays(-1));

        List<Something> countOrderList = new ArrayList<>();
        List<Something> amountIncomeList = new ArrayList<>();

        List<Order> orderList = orderDAO.getOrderListFromXToY(fromDay, toDay);
        List<MemberAccount> memberAccountList = memberAccountDAO.getSignUpListFromXToY(fromDay, toDay);

        Map<Integer, Integer> valuePairs = new HashMap<Integer, Integer>();
        valuePairs.put(1, 5);
        valuePairs.put(6, 9);
        valuePairs.put(10, 15);
        valuePairs.put(16, 19);
        valuePairs.put(20, 25);
        valuePairs.put(26, now.getDayOfMonth());

        for (Map.Entry<Integer, Integer> entry : valuePairs.entrySet()) {
            List<Order> orders = GetListOrderByMonthCondition(orderList, entry.getKey(), entry.getValue());
            countOrderList.add(new Something(entry.getKey() + "-" + entry.getValue(), orders.size()));
            amountIncomeList.add(new Something(entry.getKey() + "-" + entry.getValue(), getTotal(orders)));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        map.put("orderCount", String.valueOf(orderList.size()));
        map.put("increaseRate", "NaN");
        map.put("registerCount", String.valueOf(memberAccountList.size()));
        map.put("statisticSum", String.valueOf(getTotal(orderList)));
        map.put("jsonCountData", countOrderList);
        map.put("jsonIncomeData", amountIncomeList);
        return map;
    }

    @RequestMapping(value = "/api/statistic/year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> year() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp fromDay = Timestamp.valueOf(LocalDateTime.of(now.getYear(), 1, 1, 0, 0, 0));
        Timestamp toDay = Timestamp.valueOf(LocalDateTime.of(now.getYear(), 12, 31, 23, 59, 59));

        List<Something> countOrderList = new ArrayList<>();
        List<Something> amountIncomeList = new ArrayList<>();

        List<Order> orderList = orderDAO.getOrderListFromXToY(fromDay, toDay);
        List<MemberAccount> memberAccountList = memberAccountDAO.getSignUpListFromXToY(fromDay, toDay);

        Map<Integer, Integer> valuePairs = new HashMap<Integer, Integer>();
        valuePairs.put(1, 2);
        valuePairs.put(3, 4);
        valuePairs.put(5, 6);
        valuePairs.put(7, 8);
        valuePairs.put(9, 10);
        valuePairs.put(11, 12);

        for (Map.Entry<Integer, Integer> entry : valuePairs.entrySet()) {
            List<Order> orders = GetListOrderByYearCondition(orderList, entry.getKey(), entry.getValue());
            countOrderList.add(new Something(entry.getKey() + "-" + entry.getValue(), orders.size()));
            amountIncomeList.add(new Something(entry.getKey() + "-" + entry.getValue(), getTotal(orders)));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "success");
        map.put("orderCount", String.valueOf(orderList.size()));
        map.put("increaseRate", "NaN");
        map.put("registerCount", String.valueOf(memberAccountList.size()));
        map.put("statisticSum", String.valueOf(getTotal(orderList)));
        map.put("jsonCountData", countOrderList);
        map.put("jsonIncomeData", amountIncomeList);
        return map;
    }

    @RequestMapping(value = "/api/statistic/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> all() {
        LocalDateTime now = LocalDateTime.now();
        List<Order> orderList = orderDAO.getAll();
        List<MemberAccount> memberAccountList = memberAccountDAO.getAll();

        List<Something> countOrderList = new ArrayList<>();
        List<Something> amountIncomeList = new ArrayList<>();

        for (int i = 0; i < 12; ++i) {
            Timestamp fromDay = Timestamp.valueOf(LocalDateTime.of(now.getYear() + i, 1, 1, 0, 0, 0));
            Timestamp toDay = Timestamp.valueOf(LocalDateTime.of(now.getYear() + i, 12, 31, 23, 59, 59));

            String tomorrow = String.valueOf(now.getYear() + i);
            List<Order> temp = orderDAO.getOrderListFromXToY(fromDay, toDay);
            if (!temp.isEmpty()) {
                countOrderList.add(new Something(tomorrow, temp.size()));
                amountIncomeList.add(new Something(tomorrow, getTotal(temp)));
            } else {
                countOrderList.add(new Something(tomorrow, 0));
                amountIncomeList.add(new Something(tomorrow, 0));
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "success");
        map.put("orderCount", String.valueOf(orderList.size()));
        map.put("increaseRate", "NaN");
        map.put("registerCount", String.valueOf(memberAccountList.size()));
        map.put("statisticSum", String.valueOf(getTotal(orderList)));
        map.put("jsonCountData", countOrderList);
        map.put("jsonIncomeData", amountIncomeList);
        return map;
    }

    private List<Order> GetListOrderByMonthCondition(List<Order> orderList, int dayFrom, int dayTo) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp from = Timestamp.valueOf(LocalDateTime.of(now.getYear(), now.getMonth(), dayFrom, 0, 0, 0));
        Timestamp to = Timestamp.valueOf(LocalDateTime.of(now.getYear(), now.getMonth(), dayTo, 23, 59, 59));
        List<Order> orders = new ArrayList<>();
        for (Order item : orderList) {
            if (item.getDeliveryDate().after(from) && item.getDeliveryDate().before(to)) {
                orders.add(item);
            }
        }
        return orders;
    }

    private List<Order> GetListOrderByYearCondition(List<Order> orderList, int monthFrom, int monthTo) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp from = Timestamp.valueOf(LocalDateTime.of(now.getYear(), monthFrom, 1, 0, 0, 0));
        LocalDateTime temp = LocalDateTime.of(now.getYear(), monthTo, 1, 23, 59, 59);
        Timestamp to = Timestamp.valueOf(temp.plusMonths(1).plusDays(-1));
        List<Order> orders = new ArrayList<>();
        for (Order item : orderList) {
            if (item.getDeliveryDate().after(from) && item.getDeliveryDate().before(to)) {
                orders.add(item);
            }
        }
        return orders;
    }

    private long getTotal(List<Order> orders) {
        long res = 0;
        for (Order item : orders) {
            res += Long.parseLong(item.getTotal().toString());
        }
        return res;
    }
}
