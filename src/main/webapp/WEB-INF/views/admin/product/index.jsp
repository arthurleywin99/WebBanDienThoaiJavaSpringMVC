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
                                        <a href="<c:url value="/adminaccount/signin/index" />">????ng nh???p</a>
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
                            <li class="nav-header">TH???NG K??</li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminstatistic/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-tachometer-alt"></i>
                                    <p>
                                        Th???ng k?? doanh thu
                                    </p>
                                </a>
                            </li>
                            <li class="nav-header">QU???N L??</li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminproduct/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-chart-line"></i>
                                    <p>
                                        Qu???n l?? s???n ph???m
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminproducttype/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-sitemap"></i>
                                    <p>
                                        Qu???n l?? lo???i s???n ph???m
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminbrand/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-copyright"></i>
                                    <p>
                                        Qu???n l?? th????ng hi???u
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminsupplier/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-warehouse-alt"></i>
                                    <p>
                                        Qu???n l?? nh?? cung c???p
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/adminselling/index" />" class="nav-link">
                                    <i class="nav-icon fas fa-money-bill"></i>
                                    <p>
                                        B??n h??ng
                                    </p>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </aside>
            <div class="content-wrapper">
                <div class="content">
                    <h2>Product</h2>
                    <div class="container-fluid bg-gray-light">
                        <a href="<c:url value="/adminproduct/create/index" />" class="btn btn-success text-uppercase mb-md-1">T???o m???i</a>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nh?? cung c???p</th>
                                    <th scope="col">Lo???i s???n ph???m</th>
                                    <th scope="col">Th????ng hi???u</th>
                                    <th scope="col">T??n s???n ph???m</th>
                                    <th scope="col">Gi?? ti???n</th>
                                    <th scope="col">Ng??y t???o</th>
                                    <th scope="col">C???u h??nh</th>
                                    <th scope="col">???nh</th>
                                    <th scope="col">S??? l?????ng t???n</th>
                                    <th scope="col">Tr???ng th??i</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="i" value="1" />
                                <c:forEach var="item" items="${productList}">
                                    <tr>
                                        <th scope="row">${i}</th>
                                        <c:set var="i" value="${i + 1}" />
                                        <td>${item.getSupplierName()}</td>
                                        <td>${item.getProductTypeName()}</td>
                                        <td>${item.getBrandName()}</td>
                                        <td>${item.getProductName()}</td>
                                        <td>${item.getPrice()}</td>
                                        <td>${item.getUpdateDate()}</td>
                                        <td>${item.getConfig()}</td>
                                        <td><img src="<c:url value='${item.getImageURL()}' />" alt="${item.getProductName()}" style="width: 60px;"/></td>
                                        <td>${item.getQuantityInStock()}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.getStatus() == 'TRUE'}">
                                                    <p class="btn btn-success text-uppercase" style="cursor: unset;">
                                                        ??ang b??n
                                                    </p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="btn btn-danger text-uppercase" style="cursor: unset;">
                                                        B??? g???
                                                    </p>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="<c:url value="/adminproduct/update/index?productid=${item.getID()}" />" class="btn btn-warning text-uppercase">Update</a>
                                            <c:choose>
                                                <c:when test="${item.getStatus() == 'TRUE'}">
                                                    <a href="<c:url value="/adminproduct/disable?productid=${item.getID()}" />" class="btn btn-primary text-uppercase">Selling</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="<c:url value="/adminproduct/disable?productid=${item.getID()}" />" class="btn btn-danger text-uppercase">Disabled</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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
