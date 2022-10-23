/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.OrderDetail;
import java.util.List;

/**
 *
 * @author buing
 */
public interface IOrderDetailDAO {
    int createOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getByOrderID(String ID);
}
