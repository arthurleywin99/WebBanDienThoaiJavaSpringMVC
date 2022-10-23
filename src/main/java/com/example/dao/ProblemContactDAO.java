/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.ProblemContact;
import com.example.mapper.ProblemContactMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class ProblemContactDAO implements IProblemContactDAO {
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<ProblemContact> getAlls() {
        String sql = "SELECT * FROM ProblemContact";
        return jdbc.query(sql, new ProblemContactMapper());
    }
}
