<%-- 
    Document   : main
    Created on : Oct 18, 2022, 8:28:07 PM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="container">
    <h4><b>GIỎ HÀNG</b></h4>
    <div class="row">
        <div class="col-md-9">
            <div class="row">
                <div class="col-md-3 p-md-0"></div>
                <div class="col-md-7 p-md-0"></div>
                <div class="col-md-2 p-md-0">
                    <div class="font-weight-bold text-uppercase text-right mr-md-4 text-secondary" style="font-size: 12px;">Giá</div>
                </div>
            </div>
            <c:forEach var="item" items="${Cart}">
                <form method="POST" action="<c:url value="/cart/update" />" name="updateItem">
                    <c:set var="firstReplace" value="${fn:replace(item, ' ', '')}" />
                    <c:set var="secondReplace" value="${fn:replace(firstReplace, '/', '')}" />
                    <c:set var="thirdReplace" value="${fn:replace(secondReplace, '+', 'plus')}" />
                    <div id="${thirdReplace}" class="row rounded border border-secondary p-md-2 mb-md-1">
                        <div class="col-md-3">
                            <img class="img-thumbnail mx-auto d-block rounded float-left w-75" src="<c:url value='/images/product/${item.getImageUrl()}' />" alt="@item.ProductName" />
                        </div>
                        <div class="col-md-5 p-md-1">
                            <input type="text" name="productid" value="${item.getID()}" style="display: none;" />
                            <h5 class="font-weight-bold">${item.getProductName()}</h5>
                            <h6 class="text-success">Còn hàng</h6>
                            <div class="d-md-flex">
                                <div class="text-center d-md-flex align-items-center mr-md-2">Số lượng: </div>
                                <input class="form-control"
                                       type="number"
                                       style="width: 20% !important;"
                                       value="${item.getQuantity()}"
                                       min="1"
                                       name="quantity"
                                       onchange="this.form.submit()" />
                            </div>
                            <div class="d-md-flex mt-md-2">
                                <div>Giá tiền:&nbsp;</div>
                                <h5 class="font-weight-bold text-danger">
                                    <fmt:setLocale value = "vi_VN"/>
                                    <fmt:formatNumber value="${item.getPrice()}" type="currency" pattern="##,###" />
                                    đ
                                </h5>
                            </div>
                            <button type="button"
                                    class="btn btn-danger mt-md-2"
                                    onclick="location.href = '<c:url value="/cart/removeitem?productid=${item.getID()}" />'">
                                Xoá khỏi giỏ hàng
                            </button>
                        </div>
                        <div class="col-md-4">
                            <div class="font-weight-bold text-danger text-right" style="font-size: 22px;">
                                <fmt:setLocale value = "vi_VN"/>
                                <fmt:formatNumber value="${item.getPrice() * item.getQuantity()}" type="currency" pattern="##,###" />
                                đ
                            </div>
                        </div>   
                    </div>
                </form>
            </c:forEach>
        </div>
        <div class="col-md-3">
            <div class="rounded border border-secondary p-md-4">
                <div class="d-md-flex">
                    <h6>Tổng số sản phẩm:&nbsp;</h6>
                    <h6 class="font-weight-bold text-success">${cartCountItem}</h6>
                </div>
                <div class="d-md-flex">
                    <h6>Phí ship:&nbsp;</h6>
                    <h6 class="font-weight-bold text-success">
                        <fmt:setLocale value = "vi_VN"/>
                        <fmt:formatNumber value="${deliveryFee}" type="currency" pattern="##,###" />
                        đ
                    </h6>
                </div>
                <div class="d-md-flex">
                    <h6>Giá:&nbsp;</h6>
                    <h6 class="font-weight-bold text-success">
                        <fmt:setLocale value = "vi_VN"/>
                        <fmt:formatNumber value="${cartPrice}" type="currency" pattern="##,###" />
                        đ
                    </h6>
                </div>
                <div class="d-md-flex">
                    <h6>Tổng tiền:&nbsp;</h6>
                    <h6 class="font-weight-bold text-danger">
                        <fmt:setLocale value = "vi_VN"/>
                        <fmt:formatNumber value="${cartTotal}" type="currency" pattern="##,###" />
                        đ
                    </h6>
                </div>
                <c:if test="${Cart.size() > 0}">
                    <button type="button"
                            class="btn btn-danger btn-lg btn-block p-md-2 text-uppercase font-weight-bold mt-md-2"
                            onclick="location.href = '<c:url value="/order/shipmentdetails" />'">
                        Đặt hàng
                    </button>
                </c:if>
                <button type="button"
                        class="btn btn-outline-info btn-lg btn-block p-md-2 text-uppercase font-weight-bold"
                        onclick="location.href = '<c:url value="/product/index" />'">
                    Chọn thêm
                </button>
                <c:if test="${Cart.size() > 0}">
                    <button type="button"
                            class="btn btn-outline-dark btn-lg btn-block p-md-2 text-uppercase font-weight-bold"
                            onclick="location.href = '<c:url value="/cart/removeall" />'">
                        Xoá tất cả
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>