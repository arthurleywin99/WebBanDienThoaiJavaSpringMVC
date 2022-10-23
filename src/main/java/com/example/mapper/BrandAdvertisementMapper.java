/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.BrandAdvertisement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class BrandAdvertisementMapper implements RowMapper<BrandAdvertisement> {

    @Override
    public BrandAdvertisement mapRow(ResultSet rs, int rowNum) throws SQLException {
        BrandAdvertisement ba = new BrandAdvertisement();
        ba.setID(rs.getString("ID"));
        ba.setBrandAdName(rs.getNString("BrandAdName"));
        ba.setImageURL(rs.getNString("ImageURL"));
        ba.setURLTo(rs.getString("URLTo"));
        ba.setStatus(rs.getBoolean("Status"));
        return ba;
    }
}
