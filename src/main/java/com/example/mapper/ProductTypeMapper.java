/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.ProductType;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class ProductTypeMapper implements RowMapper<ProductType> {

    @Override
    public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductType productType = new ProductType();
        productType.setID(rs.getString("ID"));
        productType.setProductTypeName(rs.getNString("ProductTypeName"));
        productType.setIconURL(rs.getNString("IconURL"));
        productType.setStatus(rs.getBoolean("Status"));
        return productType;
    }
}
