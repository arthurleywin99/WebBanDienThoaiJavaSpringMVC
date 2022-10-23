/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.MemberAccount;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author buing
 */
public interface IMemberAccountDAO {
    List<MemberAccount> signInByEmail(String email, String password);
    List<MemberAccount> signInByPhone(String phone, String password);
    boolean isEmailExist(String email);
    boolean isPhoneNumberExist(String phoneNumber);
    int signUp(MemberAccount newAccount);
    List<MemberAccount> getSignUpListFromXToY(Timestamp from, Timestamp to);
    List<MemberAccount> getAll();
}
