/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.AccountType;

/**
 *
 * @author buing
 */
public interface IMemberTypeDAO {
    AccountType getByName(String userTypeName);
}
