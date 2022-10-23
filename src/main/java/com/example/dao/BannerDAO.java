/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Banner;
import com.example.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class BannerDAO implements IBannerDAO{

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public Banner getByName(String key) {
        String sql = "SELECT * FROM Banner WHERE BannerName = ?";
        return jdbc.queryForObject(sql, new Object[]{key}, new BannerMapper());
    }
}
