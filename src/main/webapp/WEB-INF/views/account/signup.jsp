<%-- 
    Document   : signup
    Created on : Oct 15, 2022, 8:46:14 PM
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
                        <h2 class="font-weight-bold mb-md-4">Đăng Ký</h2>
                        <h6 class="font-weight-bold mb-md-4 text-danger">${SignupError}</h6>
                        <form action="<c:url value="/account/signup" />" name="signup" method="POST">
                            <h6>Địa chỉ Email</h6>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-envelope"></i>
                                    </span>
                                </div>
                                <input class="form-control" name="Email" type = "email" placeholder = "Nhập địa chỉ Email" required />
                            </div>
                            <div class="mb-md-3"></div>
                            <h6>Số điện thoại</h6>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-phone"></i>
                                    </span>
                                </div>
                                <input class="form-control" type="tel" placeholder="Nhập số điện thoại" name="PhoneNumber" required />
                            </div>
                            <div class="mb-md-3"></div>
                            <h6>Mật khẩu</h6>
                            <div class="input-group mb-md-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-lock"></i>
                                    </span>
                                </div>
                                <input class="form-control" type="password" placeholder = "Nhập mật khẩu" name="Password" minlength="8" maxlength="24" required />
                            </div>
                            <div class="mb-md-3">
                                <div class="text-center text-danger">${PasswordError}</div>
                            </div>
                            <h6>Nhập lại mật khẩu</h6>
                            <div class="input-group mb-md-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-lock-open"></i>
                                    </span>
                                </div>
                                <input class="form-control" type="password" placeholder = "Nhập lại mật khẩu" name="RePassword" minlength="8" maxlength="24" required />
                            </div>
                            <div class="mb-md-3">
                                <div class="text-center text-danger">${RePasswordError}</div>
                            </div>
                            <h6>Họ và tên</h6>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-phone"></i>
                                    </span>
                                </div>
                                <input class="form-control" type="text" placeholder="Nhập họ và tên" name="FullName" required />
                            </div>
                            <div class="mb-md-3">
                                <div class="text-center text-danger">${FullnameError}</div>
                            </div>
                            <h6>Xác thực</h6>
                            <div class="input-group mb-md-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputGroupSelect01">Câu hỏi bảo mật</label>
                                </div>
                                <select class="custom-select" id="inputGroupSelect01" name="IDQuestion">
                                    <c:forEach items="${AuthenticationQAList}" var="item">
                                        <option value="${item.getID()}">${item.getQuestion()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <h6>Câu trả lời</h6>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fal fa-question-circle"></i>
                                    </span>
                                </div>
                                <input class="form-control" name="Answer" type="text" placeholder = "Câu trả lời" required />
                            </div>
                            <div class="mb-md-3"></div>
                            <div class="input-group input-group-sm mt-md-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <input type="checkbox" name="IsChecked" value="Checked" required />
                                    </div>
                                </div>
                                <input type="text" class="form-control" value="Đồng ý với các điều khoản của chúng tôi" disabled>
                            </div>
                            <div class="mb-md-3">
                                <div class="text-center text-danger">${IsCheckedError}</div>
                            </div>
                            <button type="submit" class="btn btn-warning btn-lg btn-block text-uppercase mt-md-3 mb-md-2">Đăng ký</button>
                        </form>
                        <hr />
                        <p class="text-md-left text-secondary">
                            Đã có tài khoản?&nbsp;
                            <a class="text-decoration-none" href="<c:url value="/account/signin/index" />">Đăng nhập</a>
                        </p>                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
