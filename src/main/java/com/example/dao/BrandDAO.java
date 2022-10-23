/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Brand;
import com.example.entity.Product;
import com.example.mapper.BrandMapper;
import com.example.mapper.ProductMapper;
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
public class BrandDAO implements IBrandDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Product> getByBrandName(String key) {
        String sql = "SELECT A.ID, SupplierID, ProductTypeID, BrandID, ProductName, Price, Discount, UpdateDate, Config, A.Describe, ImageURL, QuantityInStock, A.[Status] "
                + "FROM Product AS A INNER JOIN Brand AS B "
                + "ON A.BrandID = B.ID "
                + "WHERE B.BrandName LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + key + "%"}, new ProductMapper());
    }

    @Override
    public Brand getByID(String ID) {
        String sql = "SELECT * FROM Brand WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{ID}, new BrandMapper());
    }

    @Override
    public List<Brand> getAll() {
        String sql = "SELECT * FROM Brand";
        return jdbc.query(sql, new BrandMapper());
    }

    @Override
    public int createBrand(Brand brand) {
        String sql = "INSERT INTO Brand (ID, BrandName, LogoURL, Describe, [Status]) VALUES (?,?,?,?,?)";
        try {
            return jdbc.update(sql, new Object[]{brand.getID(), brand.getBrandName(), brand.getLogoURL(), brand.getDescribe(), brand.getStatus()});
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public int updateBrand(String ID, Brand brand) {
        String sql = "UPDATE Brand SET BrandName = ?, LogoURL = ?, Describe = ?, [Status] = ? WHERE ID = ?";
        try {
            return jdbc.update(sql, new Object[]{brand.getBrandName(), brand.getLogoURL(), brand.getDescribe(), brand.getStatus(), ID});
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
