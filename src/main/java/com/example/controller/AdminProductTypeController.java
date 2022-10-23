/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.ProductTypeDAO;
import com.example.entity.AdminConfig;
import com.example.entity.ProductType;
import com.example.viewmodel.ProductTypeViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author buing
 */
@Controller
public class AdminProductTypeController {

    @Autowired
    private ProductTypeDAO productTypeDAO;

    @RequestMapping("/adminproducttype/index")
    public String adminProductTypeIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        sendData(model);
        return "admin/producttype/index";
    }

    @RequestMapping("/adminproducttype/create/index")
    public String createProductTypeIndex(HttpServletRequest request) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        return "admin/producttype/create";
    }

    @RequestMapping(value = "/adminproducttype/create", method = RequestMethod.POST)
    public String createProductType(HttpServletRequest request, Model model) {
        String ProductTypeName = request.getParameter("ProductTypeName");
        String IconURL = request.getParameter("IconURL");
        ProductType productType = new ProductType();
        productType.setID(UUID.randomUUID().toString());
        productType.setProductTypeName(ProductTypeName);
        productType.setIconURL(IconURL);
        productType.setStatus(Boolean.TRUE);

        int created = productTypeDAO.createProductType(productType);
        if (created == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            return "admin/producttype/create";
        }
        sendData(model);
        return "admin/producttype/index";
    }

    @RequestMapping("/adminproducttype/update/index")
    public String updateProductTypeIndex(HttpServletRequest request, @RequestParam("producttypeid") String ID, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        ProductType productType = productTypeDAO.getByID(ID);
        model.addAttribute("PType", productType);
        return "admin/producttype/update";
    }

    @RequestMapping(value = "/adminproducttype/update", method = RequestMethod.POST)
    public String updateProductType(HttpServletRequest request, Model model) {
        String ID = request.getParameter("ProductTypeID");
        String ProductTypeName = request.getParameter("ProductTypeName");
        String IconURL = request.getParameter("IconURL");

        ProductType productType = new ProductType(ID, ProductTypeName, IconURL, Boolean.TRUE);
        int updated = productTypeDAO.updateProductType(ID, productType);
        if (updated == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            model.addAttribute("PType", productType);
            return "/admin/producttype/update";
        }

        return "redirect:/adminproducttype/index";
    }

    @RequestMapping("/adminproducttype/disable")
    public String disableProductType(HttpServletRequest request) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String ID = request.getParameter("producttypeid");
        ProductType pType = productTypeDAO.getByID(ID);
        if (Objects.equals(pType.getStatus(), Boolean.TRUE)) {
            productTypeDAO.disableProductType(Boolean.FALSE, ID);
        } else {
            productTypeDAO.disableProductType(Boolean.TRUE, ID);
        }
        return "redirect:/adminproducttype/index";
    }

    private void sendData(Model model) {
        List<ProductType> productTypeList = productTypeDAO.getAll();

        List<ProductTypeViewModel> productTypeVMList = new ArrayList<>();
        for (ProductType item : productTypeList) {
            ProductTypeViewModel temp = new ProductTypeViewModel();
            temp.setID(item.getID());
            temp.setProductTypeName(Truncate(item.getProductTypeName(), 30));
            temp.setIconURL(item.getIconURL());
            temp.setStatus(item.getStatus() ? "TRUE" : "FALSE");
            productTypeVMList.add(temp);
        }
        model.addAttribute("productTypeList", productTypeVMList);
    }

    private String Truncate(String s, int len) {
        if (s == null) {
            return null;
        }
        return s.substring(0, Math.min(len, s.length()));
    }
}
