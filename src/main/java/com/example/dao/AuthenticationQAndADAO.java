/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.AuthenticationQAndA;
import com.example.mapper.AuthenticationQAndAMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class AuthenticationQAndADAO implements IAuthenticationQAndADAO {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<AuthenticationQAndA> getAll() {
        String sql = "SELECT * FROM AuthenticationQAndA";
        return jdbc.query(sql, new AuthenticationQAndAMapper());
    }
}
