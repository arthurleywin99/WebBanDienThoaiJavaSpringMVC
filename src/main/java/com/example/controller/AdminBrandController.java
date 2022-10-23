/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.BrandDAO;
import com.example.entity.AdminConfig;
import com.example.entity.Brand;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author buing
 */
@Controller
public class AdminBrandController {
    @Autowired
    private BrandDAO brandDAO;
    
    @RequestMapping("/adminbrand/index")
    public String adminBrandIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        List<Brand> brandList = brandDAO.getAll();
        model.addAttribute("brandList", brandList);
        return "admin/brand/index";
    }
    
    @RequestMapping("/adminbrand/create/index")
    public String createBrandIndex() {
        return "admin/brand/create";
    }
    
    @Autowired
    ServletContext context;
    @RequestMapping(value = "/adminbrand/create", method = RequestMethod.POST)
    public String createBrand(HttpServletRequest request, Model model, @RequestParam("LogoURL") MultipartFile file) throws IOException {
        String BrandName = request.getParameter("BrandName");
        String Describe = request.getParameter("Describe");
        
        Brand brand = new Brand();
        brand.setID(UUID.randomUUID().toString());
        brand.setBrandName(BrandName);
        brand.setDescribe(Describe);
        brand.setStatus(Boolean.TRUE);
        
        if (file.isEmpty()) {
            model.addAttribute("FileUploadError", "Vui lòng chọn ảnh");
        } else {
            String photoPath = context.getRealPath("/WEB-INF/resources/images/brand/" + brand.getID() + ".jpeg");
            file.transferTo(new File(photoPath));
            brand.setLogoURL(brand.getID() + ".jpeg");
        }
        
        int created = brandDAO.createBrand(brand);
        if (created == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            return "admin/brand/create";
        }
        
        return "redirect:/adminbrand/index";
    }
    
    @RequestMapping("/adminbrand/update/index")
    public String updateBrandIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String ID = request.getParameter("brandid");
        Brand brand = brandDAO.getByID(ID);
        model.addAttribute("brand", brand);
        return "admin/brand/update";
    }
    
    @RequestMapping(value = "/adminbrand/update", method = RequestMethod.POST)
    public String updateBrand(HttpServletRequest request, Model model, @RequestParam("LogoURL") MultipartFile file) throws IOException {
        String ID = request.getParameter("BrandID");
        String BrandName = request.getParameter("BrandName");
        String Describe = request.getParameter("Describe");
        
        Brand brand = new Brand();
        brand.setID(ID);
        brand.setBrandName(BrandName);
        brand.setDescribe(Describe);
        brand.setStatus(Boolean.TRUE);
        
        if (file.isEmpty()) {
            model.addAttribute("FileUploadError", "Vui lòng chọn ảnh");
        } else {
            String photoPath = context.getRealPath("/WEB-INF/resources/images/brand/" + brand.getID() + ".jpeg");
            file.transferTo(new File(photoPath));
            brand.setLogoURL(brand.getID() + ".jpeg");
        }
        
        int updated = brandDAO.updateBrand(ID, brand);
        if (updated == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            return "admin/brand/update";
        }
        
        return "redirect:/adminbrand/index";
    }
}
