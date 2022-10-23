/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Order;
import com.example.mapper.OrderMapper;
import java.sql.Timestamp;
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
public class OrderDAO implements IOrderDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public int createOrder(Order order) {
        String sql = "INSERT INTO [Order] (ID, MemberID, OrderDate, OrderStatus, DeliveryDate, IsPaid, Discount, Total, OrderPhone, OrderEmail, OrderName, OrderAddress, TransferID, Note) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            int createOrder = jdbc.update(sql, new Object[]{order.getID(), order.getMemberID(), order.getOrderDate(),
                order.getOrderStatus(), order.getDeliveryDate(), order.getIsPaid(), order.getDiscount(), order.getTotal(),
                order.getOrderPhone(), order.getOrderEmail(), order.getOrderName(), order.getOrderAddress(),
                order.getTransferID(), order.getNote()});
            return createOrder;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<Order> getOrderListFromXToY(Timestamp fromDay, Timestamp toDay) {
        String sql = "SELECT * FROM [Order] WHERE DeliveryDate >= ? AND DeliveryDate <= ?";
        return jdbc.query(sql, new Object[]{fromDay, toDay}, new OrderMapper());
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM [Order]";
        return jdbc.query(sql, new OrderMapper());
    }

    @Override
    public void confirmOrder(String ID) {
        String sql = "UPDATE [Order] SET OrderStatus = ? WHERE ID = ?";
        jdbc.update(sql, new Object[]{"Đang Giao", ID});
    }

    @Override
    public void cancelOrder(String ID) {
        String sql = "UPDATE [Order] SET OrderStatus = ? WHERE ID = ?";
        jdbc.update(sql, new Object[]{"Đã Huỷ", ID});
    }

    @Override
    public Order getByID(String ID) {
        String sql = "SELECT * FROM [Order] WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{ID}, new OrderMapper());
    }

    @Override
    public List<Order> getByMemberID(String ID) {
        String sql = "SELECT * FROM [Order] WHERE MemberID = ?";
        return jdbc.query(sql, new Object[]{ID}, new OrderMapper());
    }
}
