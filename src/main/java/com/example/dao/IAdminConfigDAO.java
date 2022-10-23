/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.AdminConfig;
import java.util.List;

/**
 *
 * @author buing
 */
public interface IAdminConfigDAO {
    List<AdminConfig> signInWithEmail(String Email, String Password);
    List<AdminConfig> signInWithPhone(String PhoneNumber, String Password);
}
