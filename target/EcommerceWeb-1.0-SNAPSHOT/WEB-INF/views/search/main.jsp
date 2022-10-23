<%-- 
    Document   : main
    Created on : Oct 20, 2022, 8:10:24 AM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h5 class="mt-md-1"><b>${ResCount} kết quả cho "${Keyword}"</b></h5>
    <div class="dropdown" align="right">
        <button class="btn btn-outline-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-expanded="false">
            Sắp xếp theo:
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="#">Giá tăng dần</a>
            <a class="dropdown-item" href="#">Giá giảm dần</a>
            <a class="dropdown-item" href="#">Theo thứ tự A - Z</a>
            <a class="dropdown-item" href="#">Theo thứ tự Z - A</a>
        </div>
    </div>
    <div class="row">
        <c:forEach var="item" items="${ProductsResult}">
            <div class="col-md-2 p-md-1">
                <div class="card" style="border-radius: 10px !important;">
                    <a class="text-decoration-none mt-md-3" href="<c:url value="/product/detail?id=${item.getID()}" />">
                        <img class="card-img-top" src="<c:url value="/images/product/${item.getImageURL()}" />" alt="${item.getProductName()}">
                    </a>
                    <div class="card-body">
                        <a class="text-decoration-none" href="<c:url value="/product/detail?id=${item.getID()}" />">
                            <h6 class="card-title">${item.getProductName()}</h6>
                        </a>
                        <h6 class="card-title font-weight-bold text-danger">
                            <fmt:setLocale value = "vi_VN"/>
                            <fmt:formatNumber value="${item.getPrice()}" type="currency" pattern="##,###" />
                            đ
                        </h6>
                        <h6 class="card-title font-weight-bold">
                            <s>
                                <fmt:setLocale value = "vi_VN"/>
                                <fmt:formatNumber value="${item.getDiscount()}" type="currency" pattern="##,###" />
                                đ
                            </s>
                        </h6>
                    </div>
                    <div class="rating">
                        <c:choose>
                            <c:when test="${item.getAverageRatingStar()} == 0">
                                <i class="fal fa-star text-warning"></i>
                                <i class="fal fa-star text-warning"></i>
                                <i class="fal fa-star text-warning"></i>
                                <i class="fal fa-star text-warning"></i>
                                <i class="fal fa-star text-warning"></i>
                            </c:when>
                            <c:otherwise>
                                <c:set var="star" value="${item.getAverageRatingStar()}" />
                                <c:forEach begin="1" end="5" step="1">
                                    <c:choose>
                                        <c:when test="${star} >= 1">
                                            <i class="fas fa-star text-warning"></i>
                                            <c:set var="star" value="${star - 1}" />
                                        </c:when>
                                        <c:when test="${star} >= 0.5">
                                            <i class="fas fa-star-half-alt text-warning"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fal fa-star text-warning"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        <p class="text-secondary" style="font-size: 11px;">${item.getRatingCount()} Đánh giá</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>