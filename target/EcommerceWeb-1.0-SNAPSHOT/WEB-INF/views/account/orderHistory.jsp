<%-- 
    Document   : orderHistory
    Created on : Oct 23, 2022, 12:02:02 AM
    Author     : buing
--%>


<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>My Website</title>  
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.2.0/css/all.css">
        <link rel="stylesheet" href="<c:url value="/css/Site.css" />" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    </head>
    <body>
        <div class="grid-container">
            <header>
                <div class="container-fluid bg-header">
                    <div class="container bg-header">
                        <nav class="navbar navbar-expand-lg navbar-light">
                            <a class="navbar-brand" href="<c:url value="/home/index" />">
                                <img src="<c:url value="/images/${headerLogo}"/>" alt="Logo" width="125" />
                            </a>
                            <div class="form-inline">
                                <form action="<c:url value="/home/search" />" method="POST">
                                    <input class="form-control" name="keyword" placeholder="Bạn tìm gì..." />
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                                        <i class="icon_search far fa-search"></i>
                                    </button>
                                </form>
                            </div>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
                                <ul class="navbar-nav float-md-right">
                                    <li class="nav-item active">
                                        <a class="nav-link" href="<c:url value="/home/index" />">
                                            <i class="fas fa-home"></i>&nbsp;Trang chủ <span class="sr-only">(current)</span>
                                        </a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="<c:url value="/product/index" />">
                                            <i class="fas fa-mobile-alt"></i>&nbsp;Sản phẩm
                                        </a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="<c:url value="/contact/index" />">
                                            <i class="fas fa-address-card"></i>&nbsp;Liên hệ
                                        </a>
                                    </li>

                                    <c:choose>
                                        <c:when test="${Account == null}">
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-user"></i>&nbsp;Tài khoản
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                                    <a class="dropdown-item" href="<c:url value="/account/signin/index" />">Đăng Nhập</a>
                                                    <a class="dropdown-item" href="<c:url value="/account/signup/index" />">Đăng Ký</a>
                                                </div>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-user"></i>&nbsp;Hi,&nbsp;${Name}
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                                    <a class="dropdown-item" href="<c:url value="/account/accountdetail" />">Thông tin tài khoản</a>
                                                    <a class="dropdown-item" href="<c:url value="/account/signout" />">Đăng Xuất</a>
                                                </div>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>

                                    <li>
                                        <a href="<c:url value="/cart/index" />" style="position: relative;">
                                            <img src="<c:url value="/images/cart.png" />" alt="Cart" width="30" height="30" style="position: absolute;">
                                            <span class="badge badge-danger" style="position: relative; margin-left: 15px;">${cartQuantity}</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </header>

            <main>
                <div class="container">
                    <div class="row">
                        <div class="col-md-3 pl-md-3 pt-md-3">
                            <div class="rounded border border-gray" style="border-radius: 15px !important;">
                                <a href="<c:url value='/account/accountdetail' />" class="d-flex text-decoration-none">
                                    <div class="col-md-2">
                                        <i class="fal fa-home text-secondary" style="font-size: 18px;"></i>
                                    </div>
                                    <div class="col-md-10">
                                        <h6 class="text-secondary p-md-0" style="font-size: 16px;">Trang chủ</h6>
                                    </div>
                                </a>
                                <a href="<c:url value='/account/orderhistory' />" class="d-flex rounded border border-danger bg-red_custom text-decoration-none">
                                    <div class="col-md-2">
                                        <i class="fal fa-history text-danger" style="font-size: 18px;"></i>
                                    </div>
                                    <div class="col-md-9">
                                        <h6 class="font-weight-bold text-danger p-md-0" style="font-size: 16px; ">Lịch sử đặt hàng</h6>
                                    </div>
                                </a>
                                <a href="<c:url value='/account/signout' />" class="d-flex text-decoration-none">
                                    <div class="col-md-2">
                                        <i class="fal fa-sign-out-alt text-secondary" style="font-size: 18px;"></i>
                                    </div>
                                    <div class="col-md-9">
                                        <h6 class="text-secondary" style="font-size: 16px;">Đăng xuất</h6>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-9 d-flex rounded border border-danger bg-red_custom row" style="border-radius: 10px !important;">
                            <div class="row">
                                <div class="col-md-3 p-md-0"></div>
                                <div class="col-md-7 p-md-0"></div>
                                <div class="col-md-2 p-md-0">
                                    <div class="font-weight-bold text-uppercase text-right mr-md-4 text-secondary" style="font-size: 12px;">Giá</div>
                                </div>
                            </div>
                            <c:forEach var="item" items="${orderList}">
                                <div class="row rounded border border-secondary p-md-2 mb-md-1">
                                    <div class="col-md-5 p-md-1">
                                        <h5 class="font-weight-bold">Mã đơn hàng: ${item.getID()}</h5>
                                        <div class="d-md-flex">
                                            <h5 class="text-center d-md-flex align-items-center mr-md-2">Ngày đặt: ${item.getOrderDate()}</h5>
                                        </div>
                                        <div class="d-md-flex">
                                            <h5>Trạng thái đơn:&nbsp;</h5>
                                            <h5 class="font-weight-bold text-danger">${item.getOrderStatus()}</h5>
                                        </div>
                                        <div class="d-md-flex">
                                            <h5>Hình thức thanh toán:&nbsp;</h5>
                                            <c:choose>
                                                <c:when test="${item.getIsPaid()}">
                                                    <h5 class="font-weight-bold text-danger">Trực tuyến</h5>
                                                </c:when>
                                                <c:otherwise>
                                                    <h5 class="font-weight-bold text-danger">Khi nhận hàng</h5>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="d-md-flex">
                                            <h5>Ghi chú:&nbsp;</h5>
                                            <h5 class="font-weight-bold">${item.getNote()}</h5>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="font-weight-bold text-danger text-right" style="font-size: 22px;">
                                            <fmt:setLocale value = "vi_VN"/>
                                            <fmt:formatNumber value="${item.getTotal()}" type="currency" pattern="##,###"  />
                                            đ
                                        </div>
                                    </div>
                                    <c:choose>
                                        <c:when test="${item.getOrderStatus() == 'Đang Xử Lý'}">
                                            <button  class="btn btn-info mr-md-2"
                                                     onclick="location.href = '<c:url value="/order/details?orderid=${item.getID()}" />'">
                                                Xem chi tiết
                                            </button>
                                            <button class="btn btn-danger"
                                                    onclick="location.href = '<c:url value="/order/cancelorder?orderid=${item.getID()}" />'">
                                                Huỷ đơn
                                            </button>`
                                        </c:when>
                                        <c:when test="${item.getOrderStatus() == 'Đang Giao'}">
                                            <button class="btn btn-info mr-md-2"
                                                    onclick="location.href = '<c:url value="/order/details?orderid=${item.getID()}" />'">
                                                Xem chi tiết
                                            </button>
                                            <button class="btn btn-danger"
                                                    onclick="location.href = '<c:url value="/order/rating?orderid=${item.getID()}" />'">
                                                Huỷ đơn
                                            </button>`
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </main>

            <footer class="bg-footer">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <a class="navbar-brand" href="<c:url value="/index" />"> <img
                                    src="<c:url value="/images/${footerLogo}" />" alt="Logo"
                                    width="125" />
                            </a>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-3">
                            <h5 class="text-center text-light font-weight-bold text-uppercase">Liên hệ</h5>
                            <a href="tel:${phoneContact }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">${phoneContact }</h6></a>
                            <a href="mailto:${adminEmail }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">${adminEmail }</h6></a>
                            <a href="mailto:${supportEmail }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">${supportEmail }</h6></a>
                        </div>
                        <div class="col-md-3">
                            <h5 class="text-center text-light font-weight-bold text-uppercase">Theo dõi chúng tôi</h5>
                            <a href="${facebook }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">Facebook</h6></a>
                            <a href="${instagram }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">Instagram</h6></a>
                            <a href="${youtube }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">Youtube</h6></a>
                            <a href="${tiktok }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">Tiktok</h6></a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</html>
