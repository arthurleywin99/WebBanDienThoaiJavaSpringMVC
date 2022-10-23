<%-- 
    Document   : productTypePartial
    Created on : Oct 15, 2022, 6:59:44 AM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light bg-producttype rounded mb-2">
    <div class="navbar-collapse justify-content-center">
        <div class="navbar-nav">
            <c:forEach var="item" items="${productTypes}">
                <div class="d-inline-flex">
                    <a class="nav-item nav-link d-block text-center" href="<c:url value="/product?producttypeid=${item.getID()}" />">
                        <i class="${item.getIconURL()} text-light"></i>
                        <span class="d-block text-uppercase text-light">${item.getProductTypeName()}</span>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</nav>
