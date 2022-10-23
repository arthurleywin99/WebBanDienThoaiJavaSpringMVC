/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.BrandAdvertisement;
import com.example.mapper.BrandAdvertisementMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class BrandAdvertisementDAO implements IBrandAdvertisementDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<BrandAdvertisement> getAll() {
        String sql = "SELECT * FROM BrandAdvertisement";
        return jdbc.query(sql, new BrandAdvertisementMapper());
    }
}
