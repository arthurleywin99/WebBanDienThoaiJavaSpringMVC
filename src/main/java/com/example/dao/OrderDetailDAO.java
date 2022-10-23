/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.OrderDetail;
import com.example.mapper.OrderDetailMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class OrderDetailDAO implements IOrderDetailDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public int createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetail (OrderID, ProductID, PriceNow, Quantity, Content, RatingStar) VALUES (?,?,?,?,?,?)";
        try {
            int createOrderDetail = jdbc.update(sql, new Object[]{orderDetail.getOrderID(), orderDetail.getProductID(), orderDetail.getPriceNow(), orderDetail.getQuantity(), null, null});
            return createOrderDetail;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<OrderDetail> getByOrderID(String ID) {
        String sql = "SELECT OrderID, ProductID, PriceNow, Quantity, [Content], RatingStar FROM [Order] AS A INNER JOIN OrderDetail AS B ON A.ID = B.OrderID WHERE B.OrderID = ?";
        return jdbc.query(sql, new Object[]{ID}, new OrderDetailMapper());
    }
}
