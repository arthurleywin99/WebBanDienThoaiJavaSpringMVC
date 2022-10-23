/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.ProblemContactDAO;
import com.example.dao.ProductTypeDAO;
import com.example.dao.QAndADAO;
import com.example.dao.WebInfoDAO;
import com.example.entity.MemberAccount;
import com.example.entity.ProblemContact;
import com.example.entity.ProductType;
import com.example.entity.WebInfo;
import com.example.entity.QAndA;
import com.example.viewmodel.ShoppingCartViewModel;
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
public class ContactController {

    @Autowired
    private WebInfoDAO webInfoDAO;
    @Autowired
    private ProductTypeDAO productTypeDAO;
    @Autowired
    private ProblemContactDAO ProblemContactDAO;
    @Autowired
    private QAndADAO QAndADAO;

    @RequestMapping("/contact/index")
    public String contactIndex(HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        sendProductTypeData(model);
        List<ProblemContact> problemContacts = ProblemContactDAO.getAlls();
        model.addAttribute("problemContacts", problemContacts);
        return "/contact/index";
    }

    @RequestMapping(value = "/contact/sendcontact", method = RequestMethod.POST)
    public String sendContact(HttpServletRequest request, Model model) {
        QAndA qAndA = new QAndA();
        qAndA.setID(UUID.randomUUID().toString());
        qAndA.setEmail(request.getParameter("Email").trim().trim().trim());
        qAndA.setFullname(request.getParameter("Fullname").trim().trim());
        qAndA.setPhoneNumber(request.getParameter("PhoneNumber").trim().trim().trim().trim());
        qAndA.setTitle(request.getParameter("Title").trim());
        qAndA.setContent(request.getParameter("Content").trim().trim());
        qAndA.setIDProblem(request.getParameter("IDProblem"));
        qAndA.setStatus(Boolean.FALSE);
        sendWebInfoData(model);
        sendSessionData(request, model);
        sendProductTypeData(model);
        List<ProblemContact> problemContacts = ProblemContactDAO.getAlls();
        model.addAttribute("problemContacts", problemContacts);
        if (QAndADAO.sendQAndA(qAndA) == 1) {
            model.addAttribute("Success", "Success");
        } else {
            model.addAttribute("Failure", "Failure");
        }
        return "/contact/index";
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
        WebInfo HREmail = webInfoDAO.getWebInfo("HREmail");
        WebInfo PhoneContact = webInfoDAO.getWebInfo("PhoneContact");
        WebInfo CCEmail = webInfoDAO.getWebInfo("CCEmail");
        WebInfo GoogleMapLocation = webInfoDAO.getWebInfo("GoogleMapLocation");

        model.addAttribute("headerLogo", headerLogo.getValue());
        model.addAttribute("footerLogo", footerLogo.getValue());
        model.addAttribute("phoneContact", phoneContact.getValue());
        model.addAttribute("adminEmail", adminEmail.getValue());
        model.addAttribute("supportEmail", supportEmail.getValue());
        model.addAttribute("facebook", facebook.getValue());
        model.addAttribute("instagram", instagram.getValue());
        model.addAttribute("youtube", youtube.getValue());
        model.addAttribute("tiktok", tiktok.getValue());
        model.addAttribute("HREmail", HREmail.getValue());
        model.addAttribute("PhoneContact", PhoneContact.getValue());
        model.addAttribute("CCEmail", CCEmail.getValue());
        model.addAttribute("GoogleMapLocation", GoogleMapLocation.getValue());
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
}