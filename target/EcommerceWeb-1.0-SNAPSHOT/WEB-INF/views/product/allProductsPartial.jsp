<%-- 
    Document   : allProductsPartial
    Created on : Oct 15, 2022, 7:01:57 AM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<h5 class="mt-md-1"><b>${ResCount} kết quả</b></h5>

<div class="row">
    <c:forEach var="item" items="${allProducts}">
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
