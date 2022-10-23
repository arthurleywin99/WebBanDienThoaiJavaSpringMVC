/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.WebInfo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class WebInfoMapper implements RowMapper<WebInfo>{
    @Override
    public WebInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        WebInfo webInfo = new WebInfo();
        webInfo.setID(resultSet.getString("ID"));
        webInfo.setKeyword(resultSet.getString("Keyword"));
        webInfo.setValue(resultSet.getString("Value"));
        webInfo.setStatus(resultSet.getBoolean("Status"));
        return webInfo;
    }
}
