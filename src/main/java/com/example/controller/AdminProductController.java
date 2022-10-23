/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.BrandDAO;
import com.example.dao.ProductDAO;
import com.example.dao.ProductTypeDAO;
import com.example.dao.SupplierDAO;
import com.example.entity.AdminConfig;
import com.example.entity.Supplier;
import com.example.entity.ProductType;
import com.example.entity.Brand;
import java.util.List;
import com.example.entity.Product;
import com.example.viewmodel.AdminProductViewModel;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
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
public class AdminProductController {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private SupplierDAO supplierDAO;
    @Autowired
    private ProductTypeDAO productTypeDAO;
    @Autowired
    private BrandDAO brandDAO;

    @RequestMapping("/adminproduct/index")
    public String adminProductIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        DecimalFormat df = new DecimalFormat("##,###");
        LocalDate now = LocalDate.now();

        List<Product> products = productDAO.getAll();
        List<AdminProductViewModel> productList = new ArrayList<>();
        for (Product item : products) {
            AdminProductViewModel temp = new AdminProductViewModel();
            temp.setID(item.getID());
            temp.setSupplierName(supplierDAO.getByID(item.getSupplierID()).getSupplierName());
            temp.setProductTypeName(Truncate(productTypeDAO.getByID(item.getProductTypeID()).getProductTypeName(), 15));
            temp.setBrandName(Truncate(brandDAO.getByID(item.getBrandID()).getBrandName(), 15));
            temp.setProductName(Truncate(item.getProductName(), 15));
            temp.setPrice(df.format(item.getPrice()));
            temp.setUpdateDate(String.valueOf(now));
            temp.setConfig(Truncate(item.getConfig(), 15));
            temp.setDescribe(Truncate(item.getDescribe(), 15));
            temp.setImageURL("/images/product/" + item.getImageURL());
            temp.setQuantityInStock(String.valueOf(item.getQuantityInStock()));
            temp.setStatus(item.getStatus() ? "TRUE" : "FALSE");
            productList.add(temp);
        }
        model.addAttribute("productList", productList);
        return "admin/product/index";
    }

    @RequestMapping("/adminproduct/create/index")
    public String createProductIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        sendData(model);
        return "admin/product/create";
    }

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/adminproduct/create", method = RequestMethod.POST)
    public String createProduct(HttpServletRequest request, Model model, @RequestParam("ImageURL") MultipartFile file) throws IOException {
        String SupplierID = request.getParameter("SupplierID");
        String ProductTypeID = request.getParameter("ProductTypeID");
        String BrandID = request.getParameter("BrandID");
        String ProductName = request.getParameter("ProductName");
        String Price = request.getParameter("Price");
        String Discount = request.getParameter("Discount");
        String Config = request.getParameter("Config");
        String Describe = request.getParameter("Describe");
        String QuantityInStock = request.getParameter("QuantityInStock");

        if (!TryParseLong(Price) || Long.parseLong(Price) < 0) {
            model.addAttribute("PriceError", "Phải là một số nguyên dương");
            sendData(model);
            return "admin/product/create";
        }
        if (!TryParseLong(Discount) || Long.parseLong(Discount) < 0) {
            model.addAttribute("DiscountError", "Phải là một số nguyên dương");
            sendData(model);
            return "admin/product/create";
        }
        if (!TryParseInt(QuantityInStock) || Integer.parseInt(QuantityInStock) < 0) {
            model.addAttribute("QuantityInStockError", "Phải là một số nguyên dương");
            sendData(model);
            return "admin/product/create";
        }

        Product product = new Product();
        product.setID(UUID.randomUUID().toString());
        product.setSupplierID(SupplierID);
        product.setProductTypeID(ProductTypeID);
        product.setBrandID(BrandID);
        product.setProductName(ProductName);
        product.setPrice(BigInteger.valueOf(Long.parseLong(Price)));
        product.setDiscount(BigInteger.valueOf(Long.parseLong(Discount)));
        product.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
        product.setConfig(Config);
        product.setDescribe(Describe);

        if (file.isEmpty()) {
            model.addAttribute("FileUploadError", "Vui lòng chọn ảnh");
        } else {
            String photoPath = context.getRealPath("/WEB-INF/resources/images/product/" + product.getID() + ".jpeg");
            file.transferTo(new File(photoPath));
            product.setImageURL(product.getID() + ".jpeg");
        }
        product.setQuantityInStock(Integer.valueOf(QuantityInStock));
        product.setStatus(Boolean.TRUE);

        int created = productDAO.createProduct(product);
        if (created == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            sendData(model);
            return "admin/product/create";
        }
        return "redirect:/adminproduct/index";
    }
    
    @RequestMapping("/adminproduct/update/index")
    public String updateProductIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String ProductID = request.getParameter("productid");
        Product product = productDAO.getByID(ProductID);
        sendData(model);
        model.addAttribute("Product", product);
        return "admin/product/update";
    }
    
    @RequestMapping(value = "/adminproduct/update", method = RequestMethod.POST)
    public String updateProduct(HttpServletRequest request, Model model, @RequestParam("ImageURL") MultipartFile file) throws IOException {
        String ID = request.getParameter("ProductID");
        String SupplierID = request.getParameter("SupplierID");
        String ProductTypeID = request.getParameter("ProductTypeID");
        String BrandID = request.getParameter("BrandID");
        String ProductName = request.getParameter("ProductName");
        String Price = request.getParameter("Price");
        String Discount = request.getParameter("Discount");
        String Config = request.getParameter("Config");
        String Describe = request.getParameter("Describe");
        String QuantityInStock = request.getParameter("QuantityInStock");
        
        if (!TryParseLong(Price) || Long.parseLong(Price) < 0) {
            model.addAttribute("PriceError", "Phải là một số nguyên dương");
            sendData(model);
            return "admin/product/update";
        }
        if (!TryParseLong(Discount) || Long.parseLong(Discount) < 0) {
            model.addAttribute("DiscountError", "Phải là một số nguyên dương");
            sendData(model);
            return "admin/product/update";
        }
        if (!TryParseInt(QuantityInStock) || Integer.parseInt(QuantityInStock) < 0) {
            model.addAttribute("QuantityInStockError", "Phải là một số nguyên dương");
            sendData(model);
            return "admin/product/update";
        }
        
        Product product = new Product();
        product.setID(ID);
        product.setSupplierID(SupplierID);
        product.setProductTypeID(ProductTypeID);
        product.setBrandID(BrandID);
        product.setProductName(ProductName);
        product.setPrice(BigInteger.valueOf(Long.parseLong(Price)));
        product.setDiscount(BigInteger.valueOf(Long.parseLong(Discount)));
        product.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
        product.setConfig(Config);
        product.setDescribe(Describe);
        
        if (file.isEmpty()) {
            model.addAttribute("FileUploadError", "Vui lòng chọn ảnh");
        } else {
            String photoPath = context.getRealPath("/WEB-INF/resources/images/product/" + product.getID() + ".jpeg");
            file.transferTo(new File(photoPath));
            product.setImageURL(product.getID() + ".jpeg");
        }
        product.setQuantityInStock(Integer.valueOf(QuantityInStock));
        product.setStatus(Boolean.TRUE);
        
        int updated = productDAO.updateProduct(ID, product);
        if (updated == 0) {
            model.addAttribute("Error", "Có lỗi xảy ra");
            sendData(model);
            return "admin/product/update";
        }
        return "redirect:/adminproduct/index";
    }

    @RequestMapping("/adminproduct/disable")
    public String disableProduct(HttpServletRequest request) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String productID = request.getParameter("productid");
        Product product = productDAO.getByID(productID);
        if (Objects.equals(product.getStatus(), Boolean.TRUE)) {
            productDAO.disableProduct(Boolean.FALSE, productID);
        } else {
            productDAO.disableProduct(Boolean.TRUE, productID);
        }
        return "redirect:/adminproduct/index";
    }
    
    private void sendData(Model model) {
        List<Supplier> Suppliers = supplierDAO.getAll();
        List<ProductType> ProductTypes = productTypeDAO.getAll();
        List<Brand> Brands = brandDAO.getAll();

        model.addAttribute("Suppliers", Suppliers);
        model.addAttribute("ProductTypes", ProductTypes);
        model.addAttribute("Brands", Brands);
    }

    private String Truncate(String s, int len) {
        if (s == null) {
            return null;
        }
        return s.substring(0, Math.min(len, s.length()));
    }

    private boolean TryParseLong(String text) {
        try {
            Long temp = Long.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean TryParseInt(String text) {
        try {
            Integer temp = Integer.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
