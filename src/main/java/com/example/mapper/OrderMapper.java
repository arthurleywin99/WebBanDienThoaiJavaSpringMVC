/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.Order;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class OrderMapper implements RowMapper<Order>{

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setID(rs.getString("ID"));
        order.setMemberID(rs.getString("MemberID"));
        order.setOrderDate(rs.getTimestamp("OrderDate"));
        order.setOrderStatus(rs.getNString("OrderStatus"));
        order.setDeliveryDate(rs.getTimestamp("DeliveryDate"));
        order.setIsPaid(rs.getBoolean("IsPaid"));
        order.setDiscount(rs.getBigDecimal("Discount").toBigInteger());
        order.setTotal(rs.getBigDecimal("Total").toBigInteger());
        order.setOrderPhone(rs.getString("OrderPhone"));
        order.setOrderEmail(rs.getString("OrderEmail"));
        order.setOrderName(rs.getNString("OrderName"));
        order.setOrderAddress(rs.getNString("OrderAddress"));
        order.setTransferID(rs.getString("TransferID"));
        order.setNote(rs.getNString("Note"));
        return order;
    }
}
