/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.Brand;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class BrandMapper implements RowMapper<Brand> {

    @Override
    public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
        Brand brand = new Brand();
        brand.setID(rs.getString("ID"));
        brand.setBrandName(rs.getNString("BrandName"));
        brand.setLogoURL(rs.getString("LogoURL"));
        brand.setDescribe(rs.getNString("Describe"));
        brand.setStatus(rs.getBoolean("Status"));
        return brand;
    }
}
