/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class OrderDetailMapper implements RowMapper<OrderDetail>{
    @Override
    public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderID(rs.getString("OrderID"));
        orderDetail.setProductID(rs.getString("ProductID"));
        orderDetail.setPriceNow(rs.getBigDecimal("PriceNow").toBigInteger());
        orderDetail.setQuantity(rs.getInt("Quantity"));
        orderDetail.setContent(rs.getNString("Content"));
        orderDetail.setRatingStar(rs.getInt("RatingStar"));
        return orderDetail;
    }
}
