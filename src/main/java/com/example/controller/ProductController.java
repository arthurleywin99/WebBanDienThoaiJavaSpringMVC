/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.dao.ProductTypeDAO;
import com.example.dao.WebInfoDAO;
import com.example.entity.MemberAccount;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.entity.Product;
import com.example.entity.ProductType;
import com.example.entity.WebInfo;
import com.example.viewmodel.ProductViewModel;
import com.example.viewmodel.ShoppingCartViewModel;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author buing
 */
@Controller
public class ProductController {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ProductTypeDAO productTypeDAO;
    @Autowired
    private WebInfoDAO webInfoDAO;

    @RequestMapping("/product/index")
    public String index(HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        sendProductTypeData(model);
        sendAllProductData(model);
        return "product/index";
    }

    @RequestMapping("/product")
    public String productByType(@RequestParam("producttypeid") String producttypeid, HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        sendProductTypeData(model);
        sendProductByProductType(producttypeid, model);
        return "product/index";
    }

    @RequestMapping("/product/detail")
    public String productDetail(@RequestParam("id") String id, HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        sendProductTypeData(model);
        sendProductDetail(id, model);
        return "productdetail/index";
    }

    private void sendWebInfoData(Model model) {
        WebInfo headerLogo = webInfoDAO.getWebInfo("headerLogo");
        WebInfo footerLogo = webInfoDAO.getWebInfo("FooterLogo");
        WebInfo phoneContact = webInfoDAO.getWebInfo("PhoneContact");
        WebInfo adminEmail = webInfoDAO.getWebInfo("AdminEmail");
        WebInfo supportEmail = webInfoDAO.getWebInfo("supportEmail");
        WebInfo facebook = webInfoDAO.getWebInfo("Facebook");
        WebInfo instagram = webInfoDAO.getWebInfo("Instagram");
        WebInfo youtube = webInfoDAO.getWebInfo("Youtube");
        WebInfo tiktok = webInfoDAO.getWebInfo("Tiktok");

        model.addAttribute("headerLogo", headerLogo.getValue());
        model.addAttribute("footerLogo", footerLogo.getValue());
        model.addAttribute("phoneContact", phoneContact.getValue());
        model.addAttribute("adminEmail", adminEmail.getValue());
        model.addAttribute("supportEmail", supportEmail.getValue());
        model.addAttribute("facebook", facebook.getValue());
        model.addAttribute("instagram", instagram.getValue());
        model.addAttribute("youtube", youtube.getValue());
        model.addAttribute("tiktok", tiktok.getValue());
    }

    private void sendSessionData(HttpServletRequest request, Model model) {
        MemberAccount sessionAccount = (MemberAccount) request.getSession().getAttribute("Account");
        List<ShoppingCartViewModel> sessionCart = (List<ShoppingCartViewModel>) request.getSession().getAttribute("Cart");
        model.addAttribute("Account", sessionAccount);
        model.addAttribute("Cart", sessionCart);
        if (sessionAccount != null) {
            String[] nameSplit = sessionAccount.getFullName().split(" ");
            String name = nameSplit[nameSplit.length - 1];
            model.addAttribute("Name", name);
        }
        model.addAttribute("cartQuantity", (sessionCart == null) ? "0" : String.valueOf(sessionCart.size()));
    }

    private void sendProductTypeData(Model model) {
        List<ProductType> productTypes = productTypeDAO.getAll();
        model.addAttribute("productTypes", (productTypes != null) ? productTypes : "");
    }

    private void sendAllProductData(Model model) {
        List<Product> allProducts = productDAO.getAll();
        List<ProductViewModel> allProductsVM = new ArrayList<>();
        for (Product item : allProducts) {
            ProductViewModel temp = new ProductViewModel();
            temp.setID(item.getID());
            temp.setSupplierID(item.getSupplierID());
            temp.setProductTypeID(item.getProductTypeID());
            temp.setBrandID(item.getBrandID());
            temp.setProductName(item.getProductName());
            temp.setPrice(item.getPrice());
            temp.setDiscount(item.getDiscount());
            temp.setUpdateDate(item.getUpdateDate());
            temp.setConfig(item.getConfig());
            temp.setDescribe(item.getDescribe());
            temp.setImageURL(item.getImageURL());
            temp.setQuantityInStock(item.getQuantityInStock());
            temp.setStatus(item.getStatus());
            Double averageRating = productDAO.getAverageRatingStar(item.getID());
            Integer ratingCount = productDAO.getRatingCount(item.getID());
            temp.setAverageRatingStar(convertRatingFormat(averageRating));
            temp.setRatingCount(ratingCount);
            allProductsVM.add(temp);
        }
        model.addAttribute("allProducts", allProductsVM);
        model.addAttribute("ResCount", allProductsVM.size());
    }

    private void sendProductByProductType(String producttypeid, Model model) {
        List<Product> getByTypeId = productDAO.getByProductTypeID(producttypeid);
        List<ProductViewModel> productsVM = new ArrayList<>();
        for (Product item : getByTypeId) {
            ProductViewModel temp = new ProductViewModel();
            temp.setID(item.getID());
            temp.setSupplierID(item.getSupplierID());
            temp.setProductTypeID(item.getProductTypeID());
            temp.setBrandID(item.getBrandID());
            temp.setProductName(item.getProductName());
            temp.setPrice(item.getPrice());
            temp.setDiscount(item.getDiscount());
            temp.setUpdateDate(item.getUpdateDate());
            temp.setConfig(item.getConfig());
            temp.setDescribe(item.getDescribe());
            temp.setImageURL(item.getImageURL());
            temp.setQuantityInStock(item.getQuantityInStock());
            temp.setStatus(item.getStatus());
            Double averageRating = productDAO.getAverageRatingStar(item.getID());
            Integer ratingCount = productDAO.getRatingCount(item.getID());
            temp.setAverageRatingStar(convertRatingFormat(averageRating));
            temp.setRatingCount(ratingCount);
            productsVM.add(temp);
        }
        model.addAttribute("allProducts", productsVM);
        model.addAttribute("ResCount", productsVM.size());
    }

    private void sendProductDetail(String id, Model model) {
        Product getDetail = productDAO.getDetail(id);
        ProductViewModel productVM = new ProductViewModel();
        productVM.setID(getDetail.getID());
        productVM.setSupplierID(getDetail.getSupplierID());
        productVM.setProductTypeID(getDetail.getProductTypeID());
        productVM.setBrandID(getDetail.getBrandID());
        productVM.setProductName(getDetail.getProductName());
        productVM.setPrice(getDetail.getPrice());
        productVM.setDiscount(getDetail.getDiscount());
        productVM.setUpdateDate(getDetail.getUpdateDate());
        productVM.setConfig(getDetail.getConfig());
        productVM.setDescribe(getDetail.getDescribe());
        productVM.setImageURL(getDetail.getImageURL());
        productVM.setQuantityInStock(getDetail.getQuantityInStock());
        productVM.setStatus(getDetail.getStatus());
        Double averageRating = productDAO.getAverageRatingStar(getDetail.getID());
        Integer ratingCount = productDAO.getRatingCount(getDetail.getID());
        productVM.setAverageRatingStar(convertRatingFormat(averageRating));
        productVM.setRatingCount(ratingCount);
        model.addAttribute("product", productVM);
    }

    private Double convertRatingFormat(Double star) {
        if (star > 4.5) {
            return 5.0;
        } else if (star > 4.0) {
            return 4.5;
        } else if (star > 3.5) {
            return 4.0;
        } else if (star > 3.0) {
            return 3.5;
        } else if (star > 2.5) {
            return 3.0;
        } else if (star > 2.0) {
            return 2.5;
        } else if (star > 1.5) {
            return 2.0;
        } else if (star > 1.0) {
            return 1.5;
        } else if (star > 0.5) {
            return 1.0;
        } else if (star > 0) {
            return 0.5;
        }
        return 0.0;
    }
}
