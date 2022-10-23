<%-- 
    Document   : productDetail
    Created on : Oct 15, 2022, 7:38:05 AM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row justify-content-start">
        <div class="col-md pb-md-0">
            <h3 class="font-weight-bold">${product.getProductName()}</h3>
            <div class="d-flex">
                <div class="rating">
                    <c:choose>
                        <c:when test="${product.getAverageRatingStar()} == 0">
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
                </div>
                <p class="text-secondary">&nbsp;<b>${product.getRatingCount()} Đánh giá</b></p>
            </div>
        </div>
    </div>
    <hr />
    <div class="row">
        <div class="col-md-4 rounded border border-dark">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner w-100 mx-auto">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="<c:url value="/images/product/${product.getImageURL()}" />" alt="First slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="invisible carousel-control-prev-icon bg-primary" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="invisible carousel-control-next-icon bg-primary" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <h3>${product.getProductName()}</h3>
            <div class="w-25 rounded border border-danger p-md-1 small text-center text-danger mb-md-4">
                Trả góp 0%
            </div>
            <div class="mb-md-2">
                Số lượng tồn kho: <b>${product.getQuantityInStock()}</b>
            </div>
            <div>
                <h3 class="font-weight-bold text-danger">
                    <fmt:setLocale value = "vi_VN"/>
                    <fmt:formatNumber value="${product.getPrice()}" type="currency" pattern="##,###" />
                    đ
                </h3>
                <h4 class="mb-md-3">
                    <s>
                        <fmt:setLocale value = "vi_VN"/>
                        <fmt:formatNumber value="${product.getDiscount()}" type="currency" pattern="##,###"  />
                        đ
                    </s>
                </h4>
                <button 
                    type="button" 
                    class="btn btn-danger btn-lg btn-block p-md-0"
                    onclick="location.href = '<c:url value="/cart/addandgo?productid=${product.getID()}&productname=${product.getProductName()}&price=${product.getPrice()}&imageurl=${product.getImageURL()}" />'"
                    >
                    <h5 class="text-uppercase font-weight-bold text-light">Mua ngay</h5>
                    <h6 class="text-light">Giao tận nơi hoặc lấy tại cửa hàng</h6>
                </button>
                <button 
                    type="button" 
                    class="btn btn-primary btn-lg btn-block text-uppercase font-weight-bold"
                    onclick="location.href = '<c:url value="/cart/addtocart?productid=${product.getID()}&productname=${product.getProductName()}&price=${product.getPrice()}&imageurl=${product.getImageURL()}" />'"
                    >
                    Thêm vào giỏ hàng
                </button>
            </div>
        </div>
        <div class="col-md-4">
            <h3><b>Thông số kỹ thuật</b></h3>
            ${product.getConfig()}
        </div>
    </div>
</div>
