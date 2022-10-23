<%-- 
    Document   : carouselSliderPartial
    Created on : Oct 13, 2022, 9:29:03 PM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="carouselExampleIndicators" class="carousel slide mb-2 w-75 mx-auto" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner rounded mx-auto">
        <div class="carousel-item w-100 active">
            <img class="d-block w-100" src="<c:url value="/images/slider/slider1.png" />" alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="<c:url value="/images/slider/slider2.png" />" alt="Second slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="<c:url value="/images/slider/slider3.png" />" alt="Third slide">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
