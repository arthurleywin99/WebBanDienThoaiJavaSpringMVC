/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.entity.AdminConfig;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author buing
 */
@Controller
public class AdminStatisticController {

    @RequestMapping("/adminstatistic/index")
    public String adminStatiscticIndex(HttpServletRequest request) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        return "admin/statistic/index";
    }
}
