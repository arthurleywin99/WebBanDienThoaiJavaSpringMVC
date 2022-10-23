/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.dao.WebInfoDAO;
import com.example.entity.MemberAccount;
import com.example.entity.Product;
import com.example.entity.WebInfo;
import com.example.viewmodel.ShoppingCartViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author buing
 */
@Controller
public class ShoppingCartController {

    @Autowired
    private WebInfoDAO webInfoDAO;
    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/cart/index")
    public String cartIndex(HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "cart/index";
    }

    @RequestMapping("/cart/addandgo")
    public String addAndGo(HttpServletRequest request, Model model, @RequestParam("productid") String ProductID,
            @RequestParam("productname") String ProductName, @RequestParam("price") String Price, @RequestParam("imageurl") String ImageURL) {
        List<ShoppingCartViewModel> sessionCart = (List<ShoppingCartViewModel>) request.getSession().getAttribute("Cart");
        boolean isExist = false;
        if (sessionCart != null && !sessionCart.isEmpty()) {
            for (ShoppingCartViewModel item : sessionCart) {
                if (item.getID().equals(ProductID)) {
                    isExist = true;
                    item.setQuantity(item.getQuantity() + 1);
                }
            }
        } else {
            sessionCart = new ArrayList<>();
        }
        if (!isExist) {
            ShoppingCartViewModel shoppingCartViewModel = new ShoppingCartViewModel();
            shoppingCartViewModel.setID(ProductID);
            shoppingCartViewModel.setImageUrl(ImageURL);
            shoppingCartViewModel.setPrice(Long.parseLong(Price));
            shoppingCartViewModel.setProductName(ProductName);
            shoppingCartViewModel.setQuantity(1);
            sessionCart.add(shoppingCartViewModel);
        }
        request.getSession().setAttribute("Cart", sessionCart);
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "redirect:/cart/index";
    }

    @RequestMapping("/cart/addtocart")
    public String addToCart(HttpServletRequest request, Model model, @RequestParam("productid") String ProductID,
            @RequestParam("productname") String ProductName, @RequestParam("price") String Price, @RequestParam("imageurl") String ImageURL) {
        List<ShoppingCartViewModel> sessionCart = (List<ShoppingCartViewModel>) request.getSession().getAttribute("Cart");
        boolean isExist = false;
        if (sessionCart != null && !sessionCart.isEmpty()) {
            for (ShoppingCartViewModel item : sessionCart) {
                if (item.getID().equals(ProductID)) {
                    isExist = true;
                    item.setQuantity(item.getQuantity() + 1);
                }
            }
        } else {
            sessionCart = new ArrayList<>();
        }
        if (!isExist) {
            ShoppingCartViewModel shoppingCartViewModel = new ShoppingCartViewModel();
            shoppingCartViewModel.setID(ProductID);
            shoppingCartViewModel.setImageUrl(ImageURL);
            shoppingCartViewModel.setPrice(Long.parseLong(Price));
            shoppingCartViewModel.setProductName(ProductName);
            shoppingCartViewModel.setQuantity(1);
            sessionCart.add(shoppingCartViewModel);
        }
        request.getSession().setAttribute("Cart", sessionCart);
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "redirect:http://localhost:3080/EcommerceWeb/product/detail?id=" + ProductID;
    }

    @RequestMapping("/cart/removeitem")
    public String removeItem(HttpServletRequest request, Model model, @RequestParam("productid") String productID) {
        List<ShoppingCartViewModel> sessionCart = (List<ShoppingCartViewModel>) request.getSession().getAttribute("Cart");
        if (sessionCart != null && !sessionCart.isEmpty()) {
            for (ShoppingCartViewModel item : sessionCart) {
                if (item.getID().equals(productID)) {
                    sessionCart.remove(item);
                    break;
                }
            }
        }
        request.getSession().setAttribute("Cart", sessionCart);
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "redirect:/cart/index";
    }

    @RequestMapping("/cart/update")
    public String updateItem(HttpServletRequest request, Model model, @RequestParam("productid") String productID, @RequestParam("quantity") String quantity) {
        List<ShoppingCartViewModel> sessionCart = (List<ShoppingCartViewModel>) request.getSession().getAttribute("Cart");
        if (sessionCart != null && !sessionCart.isEmpty()) {
            for (ShoppingCartViewModel item : sessionCart) {
                if (item.getID().equals(productID)) {
                    Product thisProduct = productDAO.getByID(productID);
                    if ("".equals(quantity)) {
                        item.setQuantity(1);
                    } else {
                        if (thisProduct.getQuantityInStock() < Integer.valueOf(quantity)) {
                            sendWebInfoData(model);
                            sendSessionData(request, model);
                            return "cart/index";
                        } else if (Integer.parseInt(quantity) < 1) {
                            item.setQuantity(1);
                        } else {
                            item.setQuantity(Integer.parseInt(quantity));
                        }
                    }
                }
            }
        }
        request.getSession().setAttribute("Cart", sessionCart);
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "cart/index";
    }

    @RequestMapping("/cart/removeall")
    public String removeAll(HttpServletRequest request, Model model) {
        request.getSession().setAttribute("Cart", null);
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "cart/index";
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
        WebInfo deliveryFee = webInfoDAO.getWebInfo("DeliveryFee");

        model.addAttribute("headerLogo", headerLogo.getValue());
        model.addAttribute("footerLogo", footerLogo.getValue());
        model.addAttribute("phoneContact", phoneContact.getValue());
        model.addAttribute("adminEmail", adminEmail.getValue());
        model.addAttribute("supportEmail", supportEmail.getValue());
        model.addAttribute("facebook", facebook.getValue());
        model.addAttribute("instagram", instagram.getValue());
        model.addAttribute("youtube", youtube.getValue());
        model.addAttribute("tiktok", tiktok.getValue());
        model.addAttribute("deliveryFee", deliveryFee.getValue());
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
        model.addAttribute("cartPrice", (sessionCart == null) ? "0" : String.valueOf(getTotal(sessionCart)));
        model.addAttribute("cartTotal", (sessionCart == null) ? "0" : String.valueOf(getTotal(sessionCart) + Long.parseLong(webInfoDAO.getWebInfo("DeliveryFee").getValue())));
        model.addAttribute("cartCountItem", (sessionCart == null) ? "0" : String.valueOf(cartCountItem(sessionCart)));
    }

    private long cartCountItem(List<ShoppingCartViewModel> list) {
        long res = 0;
        for (ShoppingCartViewModel item : list) {
            res += item.getQuantity();
        }
        return res;
    }

    private long getTotal(List<ShoppingCartViewModel> list) {
        long res = 0;
        for (ShoppingCartViewModel item : list) {
            res += item.getQuantity() * item.getPrice();
        }
        return res;
    }
}
