/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.viewmodel;

/**
 *
 * @author buing
 */
public class OrderDetailsViewModel {
    private String OrderId, ProductId, ImageURL, ProductName, Content;
    private long PriceNow;
    private Integer Quantity, RatingStar;

    public OrderDetailsViewModel() {
    }

    public OrderDetailsViewModel(String OrderId, String ProductId, String ImageURL, String ProductName, Integer RatingStar, String Content, long PriceNow, Integer Quantity) {
        this.OrderId = OrderId;
        this.ProductId = ProductId;
        this.ImageURL = ImageURL;
        this.ProductName = ProductName;
        this.RatingStar = RatingStar;
        this.Content = Content;
        this.PriceNow = PriceNow;
        this.Quantity = Quantity;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public Integer getRatingStar() {
        return RatingStar;
    }

    public void setRatingStar(Integer RatingStar) {
        this.RatingStar = RatingStar;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public long getPriceNow() {
        return PriceNow;
    }

    public void setPriceNow(long PriceNow) {
        this.PriceNow = PriceNow;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    
    
    
}
