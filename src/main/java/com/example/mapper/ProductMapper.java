/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setID(rs.getString("ID"));
        product.setSupplierID(rs.getString("SupplierID"));
        product.setProductTypeID(rs.getString("ProductTypeID"));
        product.setBrandID(rs.getString("BrandID"));
        product.setProductName(rs.getNString("ProductName"));
        product.setPrice(rs.getBigDecimal("Price").toBigInteger());
        product.setDiscount(rs.getBigDecimal("Discount").toBigInteger());
        product.setUpdateDate(rs.getTimestamp("UpdateDate"));
        product.setConfig(rs.getNString("Config"));
        product.setDescribe(rs.getNString("Describe"));
        product.setImageURL(rs.getString("ImageURL"));
        product.setQuantityInStock(rs.getInt("QuantityInStock"));
        product.setStatus(rs.getBoolean("Status"));
        return product;
    }

}
