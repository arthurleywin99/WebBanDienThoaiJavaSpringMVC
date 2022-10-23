/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.SupplierDAO;
import com.example.entity.AdminConfig;
import com.example.entity.Supplier;
import java.util.List;
import java.util.UUID;
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
public class AdminSupplierController {
    @Autowired
    private SupplierDAO supplierDAO;
    
    @RequestMapping("/adminsupplier/index")
    public String adminSupplierIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        List<Supplier> supplierList = supplierDAO.getAll();
        model.addAttribute("supplierList", supplierList);
        return "admin/supplier/index";
    }
    
    @RequestMapping("/adminsupplier/create/index")
    public String createSupplierIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        return "admin/supplier/create";
    }
    
    @RequestMapping(value = "/adminsupplier/create", method = RequestMethod.POST)
    public String createSupplier(HttpServletRequest request, Model model) {
        String SupplierName = request.getParameter("SupplierName");
        String Address = request.getParameter("Address");
        String Email = request.getParameter("Email");
        String PhoneNumber = request.getParameter("PhoneNumber");
        
        Supplier supplier = new Supplier();
        supplier.setID(UUID.randomUUID().toString());
        supplier.setSupplierName(SupplierName);
        supplier.setAddress(Address);
        supplier.setEmail(Email);
        supplier.setPhoneNumber(PhoneNumber);
        supplier.setStatus(Boolean.TRUE);
        
        int created = supplierDAO.createSupplier(supplier);
        if (created == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            return "admin/supplier/create";
        }
        return "redirect:/adminsupplier/index";
    }
    
    @RequestMapping("/adminsupplier/update/index")
    public String updateSupplierIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String ID = request.getParameter("supplierid");
        Supplier supplier = supplierDAO.getByID(ID);
        model.addAttribute("supplier", supplier);
        return "admin/supplier/update";
    }
    
    @RequestMapping(value = "/adminsupplier/update", method = RequestMethod.POST)
    public String updateSupplier(HttpServletRequest request, Model model) {
        String ID = request.getParameter("SupplierID");
        String SupplierName = request.getParameter("SupplierName");
        String Address = request.getParameter("Address");
        String Email = request.getParameter("Email");
        String PhoneNumber = request.getParameter("PhoneNumber");
        
        Supplier supplier = new Supplier();
        supplier.setID(ID);
        supplier.setSupplierName(SupplierName);
        supplier.setAddress(Address);
        supplier.setEmail(Email);
        supplier.setPhoneNumber(PhoneNumber);
        supplier.setStatus(Boolean.TRUE);
        
        int updated = supplierDAO.updateSupplier(ID, supplier);
        if (updated == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            return "admin/supplier/update";
        }
        return "redirect:/adminsupplier/index";
    }
}
