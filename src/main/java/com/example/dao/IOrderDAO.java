/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Order;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author buing
 */
public interface IOrderDAO {
    int createOrder(Order order);
    List<Order> getOrderListFromXToY(Timestamp fromDay, Timestamp toDay);
    List<Order> getAll();
    void confirmOrder(String ID);
    void cancelOrder(String ID);
    Order getByID(String ID);
    List<Order> getByMemberID(String ID);
}
