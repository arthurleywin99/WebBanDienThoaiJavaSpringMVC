/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.entity.ProblemContact;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buing
 */
public class ProblemContactMapper implements RowMapper<ProblemContact>{
    @Override
    public ProblemContact mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProblemContact problemContact = new ProblemContact();
        problemContact.setID(rs.getString("ID"));
        problemContact.setContent(rs.getNString("Content"));
        return problemContact;
    }
}
