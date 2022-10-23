/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.AdminConfig;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class AdminConfigMapper implements RowMapper<AdminConfig>{

    @Override
    public AdminConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
        AdminConfig adminConfig = new AdminConfig();
        adminConfig.setID(rs.getString("ID"));
        adminConfig.setAdEmail(rs.getString("AdEmail"));
        adminConfig.setAdPhoneNumber(rs.getString("AdPhoneNumber"));
        adminConfig.setAdPassword(rs.getString("AdPassword"));
        adminConfig.setName(rs.getNString("Name"));
        adminConfig.setStatus(rs.getBoolean("Status"));
        return adminConfig;
    }
    
}
