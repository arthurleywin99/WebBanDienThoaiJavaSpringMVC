/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.Banner;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author buing
 */
public class BannerMapper implements RowMapper<Banner>{
    @Override
    public Banner mapRow(ResultSet resultSet, int i) throws SQLException {
        Banner banner = new Banner();
        banner.setID(resultSet.getString("ID"));
        banner.setBannerName(resultSet.getNString("BannerName"));
        banner.setImageURL(resultSet.getString("ImageURL"));
        banner.setLinkTo(resultSet.getString("LinkTo"));
        banner.setStatus(resultSet.getBoolean("Status"));
        return banner;
    }
}
