/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.viewmodel;

/**
 *
 * @author buing
 */
public class ShoppingCartViewModel {

    private String ID, ProductName, ImageUrl;
    private long Price, Quantity;

    public ShoppingCartViewModel() {

    }

    public ShoppingCartViewModel(String ID, String ProductName, String ImageUrl, long Price, long Quantity) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.ImageUrl = ImageUrl;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long Price) {
        this.Price = Price;
    }

    public long getQuantity() {
        return Quantity;
    }

    public void setQuantity(long Quantity) {
        this.Quantity = Quantity;
    }

}
