/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.OrderDAO;
import com.example.dao.OrderDetailDAO;
import com.example.dao.ProductDAO;
import com.example.entity.AdminConfig;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.viewmodel.ShoppingCartViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author buing
 */
@Controller
public class AdminSellingController {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/adminselling/index")
    public String orderIndex(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        List<Order> orderList = orderDAO.getAll();
        model.addAttribute("orderList", orderList);
        return "admin/order/index";
    }

    @RequestMapping("/adminselling/details")
    public String orderDetail(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String ID = request.getParameter("orderid");
        Order order = orderDAO.getByID(ID);
        model.addAttribute("order", order);
        List<OrderDetail> orderDetailList = orderDetailDAO.getByOrderID(ID);
        List<ShoppingCartViewModel> orderDetailVMList = new ArrayList<>();
        for (OrderDetail item : orderDetailList) {
            ShoppingCartViewModel vm = new ShoppingCartViewModel();
            Product temp = productDAO.getByID(item.getProductID());
            vm.setID(item.getOrderID());
            vm.setProductName(temp.getProductName());
            vm.setPrice(Long.parseLong(String.valueOf(item.getPriceNow())));
            vm.setQuantity(item.getQuantity());
            vm.setImageUrl(temp.getImageURL());
            orderDetailVMList.add(vm);
        }
        model.addAttribute("orderDetailList", orderDetailVMList);
        return "admin/order/detail";
    }

    @RequestMapping("/adminselling/confirm")
    public String orderConfirm(HttpServletRequest request, Model model) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String ID = request.getParameter("orderid");
        orderDAO.confirmOrder(ID);
        return "redirect:/adminselling/index";
    }

    @RequestMapping("/adminselling/cancel")
    public String orderCancel(HttpServletRequest request) {
        AdminConfig admin = (AdminConfig) request.getSession().getAttribute("AdminAccount");
        if (admin == null) {
            return "redirect:/adminaccount/signin/index";
        }
        String ID = request.getParameter("orderid");
        orderDAO.cancelOrder(ID);
        return "redirect:/adminselling/index";
    }
}
