/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class SupplierMapper implements RowMapper<Supplier>{

    @Override
    public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setID(rs.getString("ID"));
        supplier.setSupplierName(rs.getNString("SupplierName"));
        supplier.setAddress(rs.getNString("Address"));
        supplier.setEmail(rs.getString("Email"));
        supplier.setPhoneNumber(rs.getString("PhoneNumber"));
        supplier.setStatus(rs.getBoolean("Status"));
        return supplier;
    }
    
}
