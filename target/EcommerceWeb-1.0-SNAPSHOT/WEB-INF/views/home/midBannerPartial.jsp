<%-- 
    Document   : midBannerPartial
    Created on : Oct 13, 2022, 9:30:31 PM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="justify-content-center">
    <a href="${midBanner.getLinkTo()}">
        <img class="w-100" src="<c:url value="/images/${midBanner.getImageURL()}" />" alt="${midBanner.getBannerName()}" />
    </a>
</div>
