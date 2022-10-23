/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.QAndA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class QAndADAO implements IQAndADAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public int sendQAndA(QAndA entity) {
        String sql = "INSERT INTO QAndA (ID, Email, Fullname, PhoneNumber, Title, Content, IDProblem, Status) VALUES (?,?,?,?,?,?,?,?)";
        try {
            int result = jdbc.update(sql, new Object[]{entity.getID(), entity.getEmail(), entity.getFullname(), entity.getPhoneNumber(),
                entity.getTitle(), entity.getContent(), entity.getIDProblem(), entity.getStatus()});
            return result;
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
