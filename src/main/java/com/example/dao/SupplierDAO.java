/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Supplier;
import com.example.mapper.SupplierMapper;
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
public class SupplierDAO implements ISupplierDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public Supplier getByID(String ID) {
        String sql = "SELECT * FROM Supplier WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{ID}, new SupplierMapper());
    }

    @Override
    public List<Supplier> getAll() {
        String sql = "SELECT * FROM Supplier";
        return jdbc.query(sql, new SupplierMapper());
    }

    @Override
    public int createSupplier(Supplier supplier) {
        String sql = "INSERT INTO Supplier (ID, SupplierName, [Address], Email, PhoneNumber, [Status]) VALUES (?,?,?,?,?,?)";
        try {
            return jdbc.update(sql, new Object[]{supplier.getID(), supplier.getSupplierName(), supplier.getAddress(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getStatus()});
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public int updateSupplier(String ID, Supplier newSupplier) {
        String sql = "UPDATE Supplier SET SupplierName = ?, [Address] = ?, Email = ?, PhoneNumber = ?, [Status] = ? WHERE ID = ?";
        try {
            return jdbc.update(sql, new Object[]{newSupplier.getSupplierName(), newSupplier.getAddress(), newSupplier.getEmail(), newSupplier.getPhoneNumber(), newSupplier.getStatus(), ID});
        } catch (DataAccessException e) {
            return 0;
        }
    }

}
