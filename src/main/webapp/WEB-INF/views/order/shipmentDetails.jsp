<%-- 
    Document   : shipmentDetails
    Created on : Oct 19, 2022, 2:09:42 PM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

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
                                    <input class="form-control" name="keyword" placeholder="B???n t??m g??..." />
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
                                            <i class="fas fa-home"></i>&nbsp;Trang ch??? <span class="sr-only">(current)</span>
                                        </a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="<c:url value="/product/index" />">
                                            <i class="fas fa-mobile-alt"></i>&nbsp;S???n ph???m
                                        </a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="<c:url value="/aboutus/index" />">
                                            <i class="fas fa-info"></i>&nbsp;V??? ch??ng t??i
                                        </a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="<c:url value="/contact/index" />">
                                            <i class="fas fa-address-card"></i>&nbsp;Li??n h???
                                        </a>
                                    </li>

                                    <c:choose>
                                        <c:when test="${Account == null}">
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-user"></i>&nbsp;T??i kho???n
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                                    <a class="dropdown-item" href="<c:url value="/account/signin/index" />">????ng Nh???p</a>
                                                    <a class="dropdown-item" href="<c:url value="/account/signup/index" />">????ng K??</a>
                                                </div>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-user"></i>&nbsp;Hi,&nbsp;${Name}
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                                    <a class="dropdown-item" href="<c:url value="/account/accountdetail" />">Th??ng tin t??i kho???n</a>
                                                    <a class="dropdown-item" href="<c:url value="/account/signout" />">????ng Xu???t</a>
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
                    <h4 class="text-danger"><b>TH??NG TIN ?????T H??NG</b></h4>
                    <div>
                        <div class="row ml-md-2 mr-md-2">
                        </div>
                        <div class="row ml-md-2 mr-md-2">
                            <div class="col-md-3 p-md-0">
                                <h6 class="text-center font-weight-bold text-danger">????ng nh???p</h6>
                                <hr class="border rounded-left bg-warning h-25" />
                            </div>
                            <div class="col-md-3 p-md-0">
                                <h6 class="text-center font-weight-bold text-danger">Th??ng tin</h6>
                                <hr class="border bg-warning h-25" />
                            </div>
                            <div class="col-md-3 p-md-0">
                                <h6 class="text-center font-weight-bold text-danger">Thanh to??n</h6>
                                <hr class="border bg-gray h-25" />
                            </div>
                            <div class="col-md-3 p-md-0">
                                <h6 class="text-center font-weight-bold text-danger">Ho??n th??nh</h6>
                                <hr class="border rounded-right bg-gray h-25" />
                            </div>
                        </div>
                    </div>
                    <div class="row mt-md-2">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <form action="<c:url value="/order/shipmentdetails/create" />" name="createShipmentDetails" method="POST">
                                <c:choose>
                                    <c:when test="${Account == null}">
                                        <button type="button"
                                                class="btn btn-danger btn-lg btn-block text-uppercase"
                                                onclick="location.href = '<c:url value="/account/signin/index" />'">
                                            ????ng nh???p l???i
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="rounded border border-1 pt-md-3 pl-md-3 pr-md-3 pb-md-1 mb-md-2">
                                            <h5 class="font-weight-bold">Th??ng tin ng?????i ?????t (Cho vi???c b???o h??nh)</h5>
                                            <input class="form-control mb-md-2" type="text" placeholder = "H??? v?? t??n (B???t bu???c)" name="FullName" value="${Account.getFullName()}" disabled />
                                            <input class="form-control mb-md-2" type="tel" placeholder = "S??? ??i???n tho???i (B???t bu???c)" name="PhoneNumber" value="${Account.getPhoneNumber()}" disabled />
                                            <input class="form-control mb-md-2" type="email" placeholder = "Email (B???t bu???c)" name="Email" value="${Account.getEmail()}" disabled />
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <div class="rounded border border-1 pt-md-1 pl-md-3 pr-md-3 pb-md-1 mb-md-2">
                                    <h5 class="font-weight-bold mt-md-3">Th??ng tin ng?????i nh???n</h5>
                                    <input class="form-control mb-md-2" type="text" placeholder = "H??? v?? t??n ng?????i nh???n (B???t bu???c)" name="OrderFullName" required />
                                    <input class="form-control mb-md-2" type="tel" placeholder = "S??? ??i???n tho???i ng?????i nh???n (B???t bu???c)" name="OrderPhoneNumber" required />
                                    <input class="form-control mb-md-2" type="text" placeholder = "?????a ch??? giao (B???t bu???c)" name="OrderAddress" required />
                                    <textarea class="form-control" placeholder = "Ghi ch?? th??m (Kh??ng b???t bu???c)" rows = "5" style = "resize: none;" name="OrderNote"></textarea>
                                </div>
                                <button type="submit" class="btn btn-danger btn-lg btn-block text-uppercase">Ti???p t???c</button>
                            </form>
                        </div>
                        <div class="col-md-3"></div>
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
                            <h5 class="text-center text-light font-weight-bold text-uppercase">Li??n h???</h5>
                            <a href="tel:${phoneContact }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">${phoneContact }</h6></a>
                            <a href="mailto:${adminEmail }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">${adminEmail }</h6></a>
                            <a href="mailto:${supportEmail }" class="m-md-0 text-decoration-none"><h6 class="text-center text-light">${supportEmail }</h6></a>
                        </div>
                        <div class="col-md-3">
                            <h5 class="text-center text-light font-weight-bold text-uppercase">Theo d??i ch??ng t??i</h5>
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