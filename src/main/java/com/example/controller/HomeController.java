/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.BannerDAO;
import com.example.dao.BrandAdvertisementDAO;
import com.example.dao.BrandDAO;
import com.example.dao.ProductDAO;
import com.example.dao.ProductTypeDAO;
import com.example.dao.WebInfoDAO;
import com.example.entity.Banner;
import com.example.entity.BrandAdvertisement;
import com.example.entity.MemberAccount;
import com.example.entity.Product;
import com.example.entity.ProductType;
import com.example.entity.WebInfo;
import com.example.viewmodel.ProductViewModel;
import com.example.viewmodel.ShoppingCartViewModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author buing
 */
@Controller
public class HomeController {

    @Autowired
    private WebInfoDAO webInfoDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ProductTypeDAO productTypeDAO;
    @Autowired
    private BrandDAO brandDAO;
    @Autowired
    private BannerDAO bannerDAO;
    @Autowired
    private BrandAdvertisementDAO brandAdvertisementDAO;

    @RequestMapping("/home/index")
    public String index(HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        sendProductTypeData(model);
        sendMidBannerData(model);
        sendBrandAdvertisementData(model);
        sendTopProductData(model);
        return "home/index";
    }

    @RequestMapping(value = "/home/search", method = RequestMethod.POST)
    public String homePageSearch(HttpServletRequest request, Model model, @RequestParam("keyword") String keyword) {
        List<Product> productByName = productDAO.getByName(keyword);
        List<Product> productByTypeName = productTypeDAO.getByTypeName(keyword);
        List<Product> productByBrandName = brandDAO.getByBrandName(keyword);
        HashSet<Product> temp = new HashSet<>();
        for (Product item : productByName) {
            temp.add(item);
        }
        for (Product item : productByTypeName) {
            temp.add(item);
        }
        for (Product item : productByBrandName) {
            temp.add(item);
        }
        List<Product> result = new ArrayList<>(temp);
        sendSearchResult(result, model);
        model.addAttribute("Keyword", keyword);
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "search/index";
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

    private void sendMidBannerData(Model model) {
        Banner midBanner = bannerDAO.getByName("MID");
        model.addAttribute("midBanner", (midBanner != null) ? midBanner : "");
    }

    private void sendBrandAdvertisementData(Model model) {
        List<BrandAdvertisement> brandAdList = brandAdvertisementDAO.getAll();
        model.addAttribute("brandAdList", (brandAdList != null) ? brandAdList : "");
    }

    private void sendTopProductData(Model model) {
        List<Product> topCellphones = productDAO.getTopCellPhones();
        List<Product> topLaptops = productDAO.getTopLaptops();
        
        List<ProductViewModel> topCellphonesVM = new ArrayList<>();
        for (Product item : topCellphones) {
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
            topCellphonesVM.add(temp);
        }

        List<ProductViewModel> topLaptopsVM = new ArrayList<>();
        for (Product item : topLaptops) {
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
            topLaptopsVM.add(temp);
        }
        model.addAttribute("topCellphones", topCellphonesVM);
        model.addAttribute("topLaptops", topLaptopsVM);
    }
    
    private void sendSearchResult(List<Product> result, Model model) {
        List<ProductViewModel> allProductsVM = new ArrayList<>();
        for (Product item : result) {
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
        model.addAttribute("ProductsResult", allProductsVM);
        model.addAttribute("ResCount", allProductsVM.size());
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
