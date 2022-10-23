/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Product;
import com.example.entity.ProductType;
import com.example.mapper.ProductMapper;
import com.example.mapper.ProductTypeMapper;
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
public class ProductTypeDAO implements IProductTypeDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    public ProductTypeDAO() {

    }

    @Override
    public List<Product> getByTypeName(String key) {
        String sql = "SELECT A.ID, SupplierID, ProductTypeID, BrandID, ProductName, Price, Discount, UpdateDate, Config, Describe, ImageURL, QuantityInStock, A.[Status] "
                + "FROM Product AS A INNER JOIN ProductType AS B "
                + "ON A.ProductTypeID = B.ID "
                + "WHERE B.ProductTypeName LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + key + "%"}, new ProductMapper());
    }

    @Override
    public List<ProductType> getAll() {
        String sql = "SELECT * FROM ProductType";
        return jdbc.query(sql, new ProductTypeMapper());
    }

    @Override
    public ProductType getByID(String ID) {
        String sql = "SELECT * FROM ProductType WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{ID}, new ProductTypeMapper());
    }

    @Override
    public int createProductType(ProductType productType) {
        String sql = "INSERT INTO ProductType (ID, ProductTypeName, IconURL, Status) VALUES (?,?,?,?)";
        try {
            return jdbc.update(sql, new Object[]{productType.getID(), productType.getProductTypeName(), productType.getIconURL(), productType.getStatus()});
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public int updateProductType(String ID, ProductType newProductType) {
        String sql = "UPDATE ProductType SET ProductTypeName = ?, IconURL = ?, [Status] = ? WHERE ID = ?";
        try {
            return jdbc.update(sql, new Object[]{newProductType.getProductTypeName(), newProductType.getIconURL(), newProductType.getStatus(), ID});
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public void disableProductType(Boolean Status, String ID) {
        String sql = "UPDATE ProductType SET [Status] = ? WHERE ID = ?";
        jdbc.update(sql, new Object[]{Status, ID});
    }
}
