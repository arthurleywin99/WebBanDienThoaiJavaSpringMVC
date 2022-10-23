/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.MemberAccount;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class MemberAccountMapper implements RowMapper<MemberAccount>{

    @Override
    public MemberAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setID(rs.getString("ID"));
        memberAccount.setMemberTypeID(rs.getString("MemberTypeID"));
        memberAccount.setEmail(rs.getString("Email"));
        memberAccount.setPassword(rs.getString("Password"));
        memberAccount.setFullName(rs.getNString("FullName"));
        memberAccount.setAddress(rs.getNString("Address"));
        memberAccount.setPhoneNumber(rs.getString("PhoneNumber"));
        memberAccount.setIDQuestion(rs.getString("IDQuestion"));
        memberAccount.setAnswer(rs.getNString("Answer"));
        memberAccount.setResetPasswordCode(rs.getNString("ResetPasswordCode"));
        memberAccount.setBirthDate(rs.getTimestamp("BirthDate"));
        memberAccount.setJoinDate(rs.getTimestamp("JoinDate"));
        memberAccount.setStatus(rs.getBoolean("Status"));
        return memberAccount;
    }
    
}
