/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.AccountType;
import com.example.mapper.AccountTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class AccountTypeDAO implements IAccountTypeDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public AccountType getByID(String ID) {
        String sql = "SELECT * FROM AccountType WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{ID}, new AccountTypeMapper());
    }
}
