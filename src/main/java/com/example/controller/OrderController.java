/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.constant.AppConstant;
import com.example.dao.OrderDAO;
import com.example.dao.OrderDetailDAO;
import com.example.dao.WebInfoDAO;
import com.example.entity.MemberAccount;
import com.example.entity.WebInfo;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.viewmodel.PayDetailsViewModel;
import com.example.viewmodel.ShoppingCartViewModel;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class OrderController {
    @Autowired
    protected WebInfoDAO webInfoDAO;
    @Autowired
    protected OrderDetailDAO orderDetailDAO;
    @Autowired
    protected OrderDAO orderDAO;
    
    @RequestMapping("/order/shipmentdetails")
    public String shipmentDetailsIndex(HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "order/shipmentDetails";
    }
    
    @RequestMapping(value = "/order/shipmentdetails/create", method = RequestMethod.POST)
    public String createShipmentDetails(HttpServletRequest request) {
        MemberAccount sessionAccount = (MemberAccount) request.getSession().getAttribute("Account");
        String OrderFullName = request.getParameter("OrderFullName");
        String OrderPhoneNumber = request.getParameter("OrderPhoneNumber");
        String OrderAddress = request.getParameter("OrderAddress");
        String OrderNote = request.getParameter("OrderNote") != null ? request.getParameter("OrderNote") : "";
        PayDetailsViewModel payDetailsViewModel = new PayDetailsViewModel();
        if (sessionAccount != null) {
            payDetailsViewModel.setOrderUserID(sessionAccount.getID());
        } else {
            return "redirect:/account/signin/index";
        }
        payDetailsViewModel.setOrderFullName(OrderFullName);
        payDetailsViewModel.setOrderPhoneNumber(OrderPhoneNumber);
        payDetailsViewModel.setOrderAddress(OrderAddress);
        payDetailsViewModel.setOrderNote(OrderNote);
        request.getSession().setAttribute("ShipmentDetails", payDetailsViewModel);
        return "redirect:/order/paymentdetails";
    }
    
    @RequestMapping("/order/paymentdetails")
    public String paymentDetailsIndex(HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        return "order/paymentDetails";
    }
    
    @RequestMapping(value = "/order/paymentdetails/create", method = RequestMethod.POST)
    public String createPaymentDetails(HttpServletRequest request, Model model) {
        PayDetailsViewModel payDetails = (PayDetailsViewModel) request.getSession().getAttribute("ShipmentDetails");
        MemberAccount account = (MemberAccount) request.getSession().getAttribute("Account");
        if (account == null) {
            return "redirect:/account/signin/index";
        }
        List<ShoppingCartViewModel> carts = (List<ShoppingCartViewModel>) request.getSession().getAttribute("Cart");
        Order order = new Order();
        order.setID(UUID.randomUUID().toString());
        order.setMemberID(payDetails.getOrderUserID());
        order.setOrderDate(Timestamp.valueOf(LocalDateTime.now()));
        order.setOrderStatus(AppConstant.PENDING);
        order.setDeliveryDate(Timestamp.valueOf(LocalDateTime.now().plusDays(3)));
        String isPaid = request.getParameter("IsPaid");
        if (isPaid.equals("false")) {
            order.setIsPaid(Boolean.FALSE);
            order.setTransferID("");
        } else {
            order.setIsPaid(Boolean.TRUE);
            order.setTransferID(payDetails.getTransferID());
        }
        order.setDiscount(BigInteger.valueOf(0));
        order.setTotal(BigInteger.valueOf(getTotal(carts)));
        order.setOrderPhone(payDetails.getOrderPhoneNumber());
        order.setOrderEmail(account.getEmail());
        order.setOrderName(payDetails.getOrderFullName());
        order.setOrderAddress(payDetails.getOrderAddress());
        order.setNote(payDetails.getOrderNote());
        int createOrder = orderDAO.createOrder(order);
        if (createOrder == 0) {
            return "redirect:/cart/index";
        }
        for (ShoppingCartViewModel item : carts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(order.getID());
            orderDetail.setProductID(item.getID());
            orderDetail.setPriceNow(BigInteger.valueOf(item.getPrice()));
            orderDetail.setQuantity(Integer.valueOf(String.valueOf(item.getQuantity())));
            int createDetail = orderDetailDAO.createOrderDetail(orderDetail);
            if (createDetail == 0) {
                return "redirect:/cart/index";
            }
        }
        request.getSession().setAttribute("ShipmentDetails", null);
        request.getSession().setAttribute("Order", order);
        return "redirect:/order/orderconfirm";
   }
    
    @RequestMapping("/order/orderconfirm")
    public String orderConfimIndex(HttpServletRequest request, Model model) {
        sendWebInfoData(model);
        sendSessionData(request, model);
        Order order = (Order) request.getSession().getAttribute("Order");
        model.addAttribute("order", order);
        return "order/orderConfirm";
    }
    
    @RequestMapping("order/confirmed")
    public String orderCofirmed(HttpServletRequest request) {
        request.getSession().setAttribute("Cart", null);
        request.getSession().setAttribute("Order", null);
        request.getSession().setAttribute("ShipmentDetails", null);
        return "redirect:/home/index";
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

    private long getTotal(List<ShoppingCartViewModel> carts) {
        long res = 0;
        for (ShoppingCartViewModel item : carts) {
            res += item.getPrice() * item.getQuantity();
        }
        return res;
    }
}
