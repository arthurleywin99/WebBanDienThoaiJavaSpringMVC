<%-- 
    Document   : signin
    Created on : Oct 22, 2022, 10:50:02 PM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <div class="container">
            <div class="row">
                <div class="col-md-3 align-self-start"></div>
                <div class="col-md-6 align-self-center">
                    <img class="mx-auto d-block" src="<c:url value="/images/${headerLogo}" />" style="width: 50%;" alt="Alternate Text" />
                    <div class="rounded border border-secondary p-md-5">
                        <h2 class="font-weight-bold mb-md-4">Chào mừng đến với trang quản trị</h2>
                        <h3 class="font-weight-bold mb-md-4">Đăng nhập để tiếp tục</h3>
                        <form action="<c:url value="/adminaccount/signin" />" name="signin" method="POST">
                            <h6>Tên đăng nhập</h6>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-user"></i>
                                    </span>
                                </div>
                                <input class="form-control" name="Username" type = "text" placeholder="Nhập email hoặc số điện thoại" required />
                            </div>
                            <div class="mb-md-3">
                                <div class="text-center text-danger">${UsernameError}</div>
                            </div>
                            <h6>Mật khẩu</h6>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-lock"></i>
                                    </span>
                                </div>
                                <input class="form-control" name="Password" type = "password" placeholder="Nhập mật khẩu" required />
                            </div>
                            <div class="mb-md-3">
                                <div class="text-center text-danger">${PasswordError}</div>
                            </div>
                            <p class="text-danger text-center">${LoginError}</p>
                            <button type="submit" class="btn btn-primary btn-lg btn-block text-uppercase mb-md-3">Đăng nhập</button>
                        </form>
                        <a href="/account/forgotpassword" class="text-secondary font-weight-bold text-decoration-none" style="cursor: pointer;">Quên mật khẩu?</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

