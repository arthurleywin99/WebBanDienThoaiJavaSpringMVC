/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.QAndA;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class QAndAMapper implements RowMapper<QAndA> {

    @Override
    public QAndA mapRow(ResultSet rs, int rowNum) throws SQLException {
        QAndA qAndA = new QAndA();
        qAndA.setID(rs.getString("ID"));
        qAndA.setEmail(rs.getString("Email"));
        qAndA.setFullname(rs.getNString("Fullname"));
        qAndA.setPhoneNumber(rs.getString("PhoneNumber"));
        qAndA.setTitle(rs.getNString("Title"));
        qAndA.setContent(rs.getNString("Content"));
        qAndA.setIDProblem(rs.getString("IDProblem"));
        qAndA.setStatus(rs.getBoolean("Status"));
        return qAndA;
    }
}
