/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.MemberAccount;
import com.example.mapper.MemberAccountMapper;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class MemberAccountDAO implements IMemberAccountDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<MemberAccount> signInByEmail(String email, String password) {
        String sql = "SELECT * FROM MemberAccount WHERE Email = ? AND Password = ?";
        return jdbc.query(sql, new Object[]{email, password}, new MemberAccountMapper());
    }

    @Override
    public List<MemberAccount> signInByPhone(String phone, String password) {
        String sql = "SELECT * FROM MemberAccount WHERE PhoneNumber = ? AND Password = ?";
        return jdbc.query(sql, new Object[]{phone, password}, new MemberAccountMapper());
    }

    @Override
    public boolean isEmailExist(String email) {
        String sql = "SELECT * FROM MemberAccount WHERE Email = ?";
        List<MemberAccount> list = jdbc.query(sql, new Object[]{email}, new MemberAccountMapper());
        return !list.isEmpty();
    }

    @Override
    public boolean isPhoneNumberExist(String phoneNumber) {
        String sql = "SELECT * FROM MemberAccount WHERE PhoneNumber = ?";
        List<MemberAccount> list = jdbc.query(sql, new Object[]{phoneNumber}, new MemberAccountMapper());
        return !list.isEmpty();
    }

    @Override
    public int signUp(MemberAccount entity) {
        String sql = "INSERT INTO MemberAccount (ID, MemberTypeID, Email, Password, FullName, Address, PhoneNumber, IDQuestion, Answer, ResetPasswordCode, BirthDate, JoinDate, Status) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            int result = jdbc.update(sql, new Object[]{entity.getID(), entity.getMemberTypeID(), entity.getEmail(), entity.getPassword(),
                entity.getFullName(), entity.getAddress(), entity.getPhoneNumber(), entity.getIDQuestion(), entity.getAnswer(),
                entity.getResetPasswordCode(), entity.getBirthDate(), entity.getJoinDate(), entity.getStatus()});
            return result;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<MemberAccount> getSignUpListFromXToY(Timestamp from, Timestamp to) {
        String sql = "SELECT * FROM MemberAccount WHERE JoinDate >= ? AND JoinDate <= ?";
        return jdbc.query(sql, new Object[]{from, to}, new MemberAccountMapper());
    }
    
    @Override
    public List<MemberAccount> getAll() {
        String sql = "SELECT * FROM MemberAccount";
        return jdbc.query(sql, new MemberAccountMapper());
    }
}
