/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.AccountTypeDAO;
import com.example.dao.AuthenticationQAndADAO;
import com.example.dao.MemberAccountDAO;
import com.example.dao.MemberTypeDAO;
import com.example.dao.OrderDAO;
import com.example.dao.OrderDetailDAO;
import com.example.dao.ProductDAO;
import com.example.dao.WebInfoDAO;
import com.example.entity.AccountType;
import com.example.entity.AuthenticationQAndA;
import com.example.entity.WebInfo;
import com.example.entity.MemberAccount;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.viewmodel.OrderDetailsViewModel;
import com.example.viewmodel.ShoppingCartViewModel;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class AccountController {
    
    @Autowired
    private WebInfoDAO webInfoDAO;
    @Autowired
    private MemberAccountDAO memberAccountDAO;
    @Autowired
    private AuthenticationQAndADAO authenticationQAndADAO;
    @Autowired
    private MemberTypeDAO memberTypeDAO;
    @Autowired
    private AccountTypeDAO accountTypeDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ProductDAO productDAO;
    
    @RequestMapping("/account/signin/index")
    public String signInIndex(Model model) {
        sendWebInfoData(model);
        return "account/signin";
    }
    
    @RequestMapping(value = "/account/signin", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model) {
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        List<MemberAccount> signInByEmail = memberAccountDAO.signInByEmail(username, MD5Hash(password));
        List<MemberAccount> signInByPhone = memberAccountDAO.signInByPhone(username, MD5Hash(password));
        if (!signInByEmail.isEmpty()) {
            request.getSession().setAttribute("Account", signInByEmail.get(0));
            return "redirect:/home/index";
        } else if (!signInByPhone.isEmpty()) {
            request.getSession().setAttribute("Account", signInByPhone.get(0));
            return "redirect:/home/index";
        } else {
            sendWebInfoData(model);
            model.addAttribute("LoginError", "Sai tài khoản hoặc mật khẩu");
            return "account/signin";
        }
    }
    
    @RequestMapping("/account/signup/index")
    public String signUpIndex(Model model) {
        sendWebInfoData(model);
        List<AuthenticationQAndA> AuthenticationQAList = authenticationQAndADAO.getAll();
        model.addAttribute("AuthenticationQAList", AuthenticationQAList);
        return "account/signup";
    }
    
    @RequestMapping(value = "/account/signup", method = RequestMethod.POST)
    public String signUp(Model model, HttpServletRequest request) {
        String Email = request.getParameter("Email");
        String PhoneNumber = request.getParameter("PhoneNumber");
        String Password = request.getParameter("Password");
        String RePassword = request.getParameter("RePassword");
        String FullName = request.getParameter("FullName");
        String IDQuestion = request.getParameter("IDQuestion");
        String Answer = request.getParameter("Answer");
        sendWebInfoData(model);
        if (!isValidPassword(Password)) {
            model.addAttribute("PasswordError", "Mật khẩu không đúng định dạng, chứa ít nhất 1 ký tự in hoa, in thường, ký tự số và ký tự đặc biệt");
            List<AuthenticationQAndA> AuthenticationQAList = authenticationQAndADAO.getAll();
            model.addAttribute("AuthenticationQAList", AuthenticationQAList);
            return "account/signup";
        }
        if (!Password.equals(RePassword)) {
            model.addAttribute("RePasswordError", "Mật khẩu không khớp");
            List<AuthenticationQAndA> AuthenticationQAList = authenticationQAndADAO.getAll();
            model.addAttribute("AuthenticationQAList", AuthenticationQAList);
            return "account/signup";
        }
        if (FullName.matches("[0-9]+") || FullName.matches("[!@#$%^&*()_+=\\[{\\]};:<>|./?,-]")) {
            model.addAttribute("FullnameError", "Tên không chứa ký tự đặc biệt");
            List<AuthenticationQAndA> AuthenticationQAList = authenticationQAndADAO.getAll();
            model.addAttribute("AuthenticationQAList", AuthenticationQAList);
            return "account/signup";
        }
        if (memberAccountDAO.isEmailExist(Email.trim())) {
            model.addAttribute("SignupError", "Email đã tồn tại trong hệ thống. Vui lòng thử lại");
            List<AuthenticationQAndA> AuthenticationQAList = authenticationQAndADAO.getAll();
            model.addAttribute("AuthenticationQAList", AuthenticationQAList);
            return "account/signup";
        }
        if (memberAccountDAO.isPhoneNumberExist(PhoneNumber.trim())) {
            model.addAttribute("SignupError", "Số điện thoại này đã tồn tại trong hệ thống. Vui lòng thử lại");
            List<AuthenticationQAndA> AuthenticationQAList = authenticationQAndADAO.getAll();
            model.addAttribute("AuthenticationQAList", AuthenticationQAList);
            return "account/signup";
        }
        MemberAccount newAccount = new MemberAccount();
        newAccount.setID(UUID.randomUUID().toString());
        newAccount.setMemberTypeID(memberTypeDAO.getByName("Basic").getID());
        newAccount.setEmail(Email.trim());
        newAccount.setPassword(MD5Hash(Password.trim()));
        newAccount.setFullName(FullName.trim());
        newAccount.setAddress("");
        newAccount.setPhoneNumber(PhoneNumber.trim());
        newAccount.setIDQuestion(IDQuestion);
        newAccount.setAnswer(Answer.trim());
        newAccount.setResetPasswordCode("");
        newAccount.setBirthDate(Timestamp.valueOf("1970-01-01 00:00:00.0"));
        newAccount.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));
        newAccount.setStatus(Boolean.TRUE);
        if (memberAccountDAO.signUp(newAccount) == 1) {
            sendWebInfoData(model);
            model.addAttribute("LoginError", "Đăng ký thành công. Vui lòng đăng nhập để sử dụng dịch vụ");
            return "/account/signin";
        } else {
            model.addAttribute("SignupError", "Có lỗi trong quá trình tạo tài khoản. Vui lòng thử lại");
            return "account/signup";
        }
    }
    
    @RequestMapping("/account/signout")
    public String signOut(HttpServletRequest request) {
        request.getSession().setAttribute("Account", null);
        return "redirect:/home/index";
    }
    
    @RequestMapping("/account/accountdetail")
    public String accountDetail(HttpServletRequest request, Model model) {
        MemberAccount memAccount = (MemberAccount) request.getSession().getAttribute("Account");
        if (memAccount == null) {
            return "redirect:/account/signin/index";
        }
        sendWebInfoData(model);
        sendSessionData(request, model);
        MemberAccount account = (MemberAccount) request.getSession().getAttribute("Account");
        model.addAttribute("account", account);
        AccountType accountType = accountTypeDAO.getByID(account.getMemberTypeID());
        model.addAttribute("AccountType", accountType.getUserTypeName());
        List<Order> orderList = orderDAO.getByMemberID(memAccount.getID());
        model.addAttribute("accumulateMoney", getTotal(orderList));
        return "account/accountDetail";
    }
    
    @RequestMapping("/account/orderhistory")
    public String orderHistory(HttpServletRequest request, Model model) {
        MemberAccount memAccount = (MemberAccount) request.getSession().getAttribute("Account");
        if (memAccount == null) {
            return "redirect:/account/signin/index";
        }
        sendWebInfoData(model);
        sendSessionData(request, model);
        List<Order> orderList = orderDAO.getByMemberID(memAccount.getID());
        model.addAttribute("orderList", orderList);
        return "account/orderHistory";
    }
    
    @RequestMapping("/order/details")
    public String orderDetails(HttpServletRequest request, Model model) {
        MemberAccount memAccount = (MemberAccount) request.getSession().getAttribute("Account");
        if (memAccount == null) {
            return "redirect:/account/signin/index";
        }
        sendWebInfoData(model);
        sendSessionData(request, model);
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
        return "account/orderDetails";
    }
    
    @RequestMapping("/order/cancelorder")
    public String cancelOrder(HttpServletRequest request, Model model) {
        MemberAccount memAccount = (MemberAccount) request.getSession().getAttribute("Account");
        if (memAccount == null) {
            return "redirect:/account/signin/index";
        }
        String ID = request.getParameter("orderid");
        orderDAO.cancelOrder(ID);
        return "redirect:/account/orderhistory";
    } 
    
    @RequestMapping("/order/rating")
    public String ratingIndex(HttpServletRequest request, Model model) {
        /*String Id = request.getParameter("orderid");
        List<OrderDetail> orderDetailList = orderDetailDAO.getByOrderID(Id);
        List<OrderDetailsViewModel> orderDetails = new ArrayList<>();
        for (OrderDetail item : orderDetailList) {
            OrderDetailsViewModel detail = new OrderDetailsViewModel();
            detail.setOrderId(item.getOrderID());
            detail.setProductId(item.getProductID());
            Product temp = productDAO.getByID(item.getProductID());
            detail.setImageURL(temp.getImageURL());
            detail.setProductName(temp.getProductName());
            detail.setQuantity(item.getQuantity());
            detail.setPriceNow(Long.parseLong(String.valueOf(item.getPriceNow())));
            detail.setRatingStar(item.getRatingStar());
            detail.setContent(item.getContent());
        }*/
        return "account/rating";
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
    
    private long getTotal(List<Order> orders) {
        long res = 0;
        for (Order item : orders) {
            res += Long.parseLong(String.valueOf(item.getTotal()));
        }
        return res;
    }
    
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,24}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    private String MD5Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
