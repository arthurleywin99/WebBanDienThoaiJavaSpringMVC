<%-- 
    Document   : brandAdvertisementsPartial
    Created on : Oct 13, 2022, 9:34:49 PM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4><b>CHUYÊN TRANG THƯƠNG HIỆU</b></h4>
<div class="row d-flex justify-content-around">
    <c:forEach var="item" items="${brandAdList}">
        <div class="col-md-3">
            <div class="card">
                <img class="card-img-top" src="<c:url value="/images/${item.getImageURL()}" />" alt="${item.getBrandAdName()}">
            </div>
        </div>
    </c:forEach>
</div>
