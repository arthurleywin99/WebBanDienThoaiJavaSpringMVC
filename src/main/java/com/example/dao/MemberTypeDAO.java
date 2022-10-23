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
public class MemberTypeDAO implements IMemberTypeDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public AccountType getByName(String userTypeName) {
        String sql = "SELECT * FROM AccountType WHERE UserTypeName = ?";
        return jdbc.queryForObject(sql, new Object[]{userTypeName}, new AccountTypeMapper());
    }

}
