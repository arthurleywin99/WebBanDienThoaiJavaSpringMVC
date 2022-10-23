/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.AccountType;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class AccountTypeMapper implements RowMapper<AccountType>{

    @Override
    public AccountType mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountType accountType = new AccountType();
        accountType.setID(rs.getString("ID"));
        accountType.setUserTypeName(rs.getNString("UserTypeName"));
        accountType.setDiscount(rs.getBigDecimal("Discount"));
        accountType.setStatus(rs.getBoolean("Status"));
        return accountType;
    }
}
