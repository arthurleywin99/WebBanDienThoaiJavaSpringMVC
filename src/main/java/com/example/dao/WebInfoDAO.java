/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.WebInfo;
import com.example.mapper.WebInfoMapper;

/**
 *
 * @author buing
 */
@Repository
public class WebInfoDAO implements IWebInfoDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public WebInfo getWebInfo(String key) {
        String sql = "SELECT * FROM WebInfo WHERE Keyword = ?";
        return jdbc.queryForObject(sql, new Object[]{key}, new WebInfoMapper());
    }
}
