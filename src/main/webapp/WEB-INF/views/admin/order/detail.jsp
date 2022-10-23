<%-- 
    Document   : detail
    Created on : Oct 22, 2022, 9:18:23 PM
    Author     : buing
--%>

<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Admin</title>  
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.2.0/css/all.css">
        <link rel="stylesheet" href="<c:url value="/admincss/adminlte.min.css" />" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="https://cdn.tiny.cloud/1/0p8ytis8rbqi1tnjmq6xsexrjsq3vvuzwwhzi3c0ocvcd0e3/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
        <script>tinymce.init({selector: 'textarea'});</script>
    </head>
    <body class="hold-transition sidebar-mini">
        <div class="wrapper">
            <nav class="main-header navbar navbar-expand navbar-white navbar-light">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <div class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
                            <c:choose>
                                <c:when test="${AdminAccount != null}">
                                    <div>
                                        Hi, ${AdminAccount.getName()} &nbsp;&nbsp;<i class="fal fa-angle-down"></i>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <a href="<c:url value="/adminaccount/signin/index" />">Đăng nhập</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </li>
                </ul>
            </nav>
            <aside class="main-sidebar sidebar-dark-primary elevation-4">
                <a href="#" class="brand-link">
                    <img src="<c:url value="/adminimages/AdminLTELogo.png" />" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
                    <span class="brand-text font-weight-light">Apple Shop</span>
                </a>
                <div class="sidebar">
                    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                        <div class="image">
                            <img src="<c:url value="/adminimages/user2-160x160.jpg" />" class="img-circle elevation-2" alt="User Image">
                        </div>
                        <c:choose>
                            <c:when test="${AdminAccount != null}">
                                <div class="info">
                                    <a href="#" class="d-block">${AdminAccount.getName()}</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="info">
                                    <a href="#" class="d-block">Unknown</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="form-inline">
                        <div class="input-group" data-widget="sidebar-search">
                            <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
                            <div class="input-group-append">
                                <button class="btn btn-sidebar">
                                    <i class="fas fa-search fa-fw"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <nav class="mt-2">
                        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                            <li class="nav-header">THỐNG KÊ</li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminstatistic/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-tachometer-alt"></i>
                                    <p>
                                        Thống kê doanh thu
                                    </p>
                                </a>
                            </li>
                            <li class="nav-header">QUẢN LÝ</li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminproduct/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-chart-line"></i>
                                    <p>
                                        Quản lý sản phẩm
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminproducttype/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-sitemap"></i>
                                    <p>
                                        Quản lý loại sản phẩm
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminbrand/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-copyright"></i>
                                    <p>
                                        Quản lý thương hiệu
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminsupplier/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-warehouse-alt"></i>
                                    <p>
                                        Quản lý nhà cung cấp
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminselling/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-money-bill"></i>
                                    <p>
                                        Bán hàng
                                    </p>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </aside>
            <div class="content-wrapper">
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-5">
                                <h2 class="mb-md-3">Chi tiết đơn hàng</h2>
                                <div class="row m-md-1" style="border: 1px solid #17a2b8; border-radius: 10px;">
                                    <div class="col-md-4">
                                        <p class="font-weight-bold">Mã đơn hàng</p>
                                        <p class="font-weight-bold">Ngày đặt</p>
                                        <p class="font-weight-bold">Ngày giao dự kiến</p>
                                        <p class="font-weight-bold">Người nhận</p>
                                        <p class="font-weight-bold">Địa chỉ nhận</p>
                                        <p class="font-weight-bold">Số điện thoại nhận</p>
                                        <p class="font-weight-bold">Tổng tiền</p>
                                        <p class="font-weight-bold">Hình thức thanh toán</p>
                                        <p class="font-weight-bold">Ghi chú</p>
                                    </div>
                                    <div class="col-md-8">
                                        <p>${order.getID()}</p>
                                        <p>${order.getOrderDate()}</p>
                                        <p>${order.getDeliveryDate()}</p>
                                        <p>${order.getOrderName()}</p>
                                        <p>${order.getOrderAddress()}</p>
                                        <p>${order.getOrderPhone()}</p>
                                        <p>${order.getTotal()}</p>
                                        <p>
                                            <c:choose>
                                                <c:when test="${order.getIsPaid() == true}">
                                                    "Thanh toán trực tuyến"
                                                </c:when>
                                                <c:otherwise>
                                                    Thanh toán khi nhận hàng
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <p>${order.getNote()}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                            <div class="col-md-4">
                                <h2 class="mb-md-3">Danh sách sản phẩm</h2>
                                <div style="border: 1px solid #dc3545; border-radius: 10px;">
                                    <c:forEach var="item" items="${orderDetailList}">
                                        <div class="row m-md-1 mb-md-4">
                                            <div class="col-md-2">
                                                <img src="<c:url value="/images/product/${item.getImageUrl()}" />" style="width: 50px;" />
                                            </div>
                                            <div class="col-md-7">
                                                <p class="font-weight-bold">${item.getProductName()}</p>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="d-md-flex">
                                                    <p>Số lượng:&nbsp;</p>
                                                    <p class="font-weight-bold">${item.getQuantity()}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                    </div>
                </div>
            </div>
            <aside class="control-sidebar control-sidebar-dark"></aside>
            <footer class="main-footer">
                <strong>Copyright &copy; 2022 <a href="https://adminlte.io">AppleShop</a>.</strong>
                All rights reserved.
                <div class="float-right d-none d-sm-inline-block">
                    <b>Version</b> 1.0
                </div>
            </footer>
        </div>
    </body>
</html>
