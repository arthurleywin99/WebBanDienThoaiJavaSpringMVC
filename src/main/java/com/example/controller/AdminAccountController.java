/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.AdminConfigDAO;
import com.example.dao.WebInfoDAO;
import com.example.entity.AdminConfig;
import com.example.entity.WebInfo;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author buing
 */
@Controller
public class AdminAccountController {

    @Autowired
    private WebInfoDAO webInfoDAO;
    @Autowired
    private AdminConfigDAO adminConfigDAO;

    @RequestMapping("/adminaccount/signin/index")
    public String signinIndex(Model model) {
        sendData(model);
        return "admin/account/signin";
    }

    @RequestMapping(value = "/adminaccount/signin", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model) {
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        List<AdminConfig> signInByEmail = adminConfigDAO.signInWithEmail(Username, MD5Hash(Password));
        List<AdminConfig> signInByPhone = adminConfigDAO.signInWithPhone(Username, MD5Hash(Password));
        if (!signInByEmail.isEmpty()) {
            request.getSession().setAttribute("AdminAccount", signInByEmail.get(0));
            return "redirect:/adminstatistic/index";
        } else if (!signInByPhone.isEmpty()) {
            request.getSession().setAttribute("AdminAccount", signInByPhone.get(0));
            return "redirect:/adminstatistic/index";
        } else {
            sendData(model);
            model.addAttribute("LoginError", "Sai tài khoản hoặc mật khẩu");
            return "admin/account/signin";
        }
    }

    private void sendData(Model model) {
        WebInfo headerLogo = webInfoDAO.getWebInfo("headerLogo");
        model.addAttribute("headerLogo", headerLogo.getValue());
    }

    private String MD5Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
