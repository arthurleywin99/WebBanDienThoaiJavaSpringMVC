/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.AdminConfig;
import com.example.mapper.AdminConfigMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class AdminConfigDAO implements IAdminConfigDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<AdminConfig> signInWithEmail(String Email, String Password) {
        String sql = "SELECT * FROM AdminConfig WHERE AdEmail = ? AND AdPassword = ?";
        return jdbc.query(sql, new Object[]{Email, Password}, new AdminConfigMapper());
    }

    @Override
    public List<AdminConfig> signInWithPhone(String PhoneNumber, String Password) {
        String sql = "SELECT * FROM AdminConfig WHERE AdPhoneNumber = ? AND AdPassword = ?";
        return jdbc.query(sql, new Object[]{PhoneNumber, Password}, new AdminConfigMapper());
    }
}
