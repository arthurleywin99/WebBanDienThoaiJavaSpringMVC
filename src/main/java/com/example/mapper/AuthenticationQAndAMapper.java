/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.AuthenticationQAndA;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class AuthenticationQAndAMapper implements RowMapper<AuthenticationQAndA>{

    @Override
    public AuthenticationQAndA mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthenticationQAndA qAndA = new AuthenticationQAndA();
        qAndA.setID(rs.getString("ID"));
        qAndA.setQuestion(rs.getNString("Question"));
        return qAndA;
    }
    
}
