<%-- 
    Document   : update
    Created on : Oct 22, 2022, 9:55:12 AM
    Author     : buing
--%>
<%-- 
    Document   : index
    Created on : Oct 21, 2022, 7:17:34 PM
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
                    <h2>Cập nhật sản phẩm</h2>
                    <div class="container">
                        <form action="<c:url value="/adminproduct/update" />" method="POST" enctype="multipart/form-data">
                            <div class="text-danger">${error}</div>
                            <div class="form-horizontal">
                                <h4>Sản phẩm <b>${Product.getProductName()}</b></h4>
                                <input style="display: none;" name="ProductID" value="${Product.getID()}" />
                                <hr />
                                <div class="form-group">
                                    <label class="control-label col-md-2">Nhà cung cấp</label>
                                    <div class="col-md-10">
                                        <select class="form-control" id="inputGroupSelect01" name="SupplierID">
                                            <c:forEach items="${Suppliers}" var="item">
                                                <option value="${item.getID()}">${item.getSupplierName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Loại sản phẩm</label>
                                    <div class="col-md-10">
                                        <select class="form-control" id="inputGroupSelect01" name="ProductTypeID">
                                            <c:forEach items="${ProductTypes}" var="item">
                                                <option value="${item.getID()}">${item.getProductTypeName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Thương hiệu</label>
                                    <div class="col-md-10">
                                        <select class = "form-control" id="inputGroupSelect01" name="BrandID">
                                            <c:forEach items="${Brands}" var="item">
                                                <option value="${item.getID()}">${item.getBrandName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Tên sản phẩm</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="ProductName" type="text" value="${Product.getProductName()}" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Giá gốc</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="Price" type="text" value="${Product.getPrice()}" required />
                                    </div>
                                </div>
                                <div class="mb-md-3">
                                    <div class="text-center text-danger">${PriceError}</div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Giá bán</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="Discount" type="text" value="${Product.getDiscount()}" required />
                                    </div>
                                </div>
                                <div class="mb-md-3">
                                    <div class="text-center text-danger">${DiscountError}</div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Thông số kỹ thuật</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" name="Config">${Product.getConfig()}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Mô tả</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" name="Describe">${Product.getDescribe()}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Ảnh</label>
                                    <div class="col-md-10">
                                        <input class="form-control" type="file" value="${Product.getImageURL()}" accept="image/jpeg, image/jpg, image/png" name="ImageURL" required />
                                    </div>
                                </div>
                                <div class="mb-md-3">
                                    <div class="text-center text-danger">${FileUploadError}</div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">Số lượng tồn</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="QuantityInStock" value="${Product.getQuantityInStock()}" type="number" min="0" required />
                                    </div>
                                </div>
                                <div class="mb-md-3">
                                    <div class="text-center text-danger">${QuantityInStockError}</div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-2 col-md-10">
                                        <input type="submit" value="Cập nhật" class="btn btn-outline-danger" />
                                    </div>
                                </div>
                            </div>
                        </form>
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