/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.viewmodel;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 *
 * @author buing
 */
public class ProductViewModel {
    private String ID, ProductTypeID, SupplierID, BrandID, ProductName, Config, Describe, ImageURL;
    private BigInteger Price, Discount;
    private Timestamp UpdateDate;
    private Integer QuantityInStock, RatingCount;
    private Boolean Status;
    private Double AverageRatingStar;

    public ProductViewModel() {
    }

    public ProductViewModel(String ID, String ProductTypeID, String SupplierID, String BrandID, String ProductName, String Config, String Describe, String ImageURL, BigInteger Price, BigInteger Discount, Timestamp UpdateDate, Integer QuantityInStock, Integer RatingCount, Boolean Status, Double AverageRatingStar) {
        this.ID = ID;
        this.ProductTypeID = ProductTypeID;
        this.SupplierID = SupplierID;
        this.BrandID = BrandID;
        this.ProductName = ProductName;
        this.Config = Config;
        this.Describe = Describe;
        this.ImageURL = ImageURL;
        this.Price = Price;
        this.Discount = Discount;
        this.UpdateDate = UpdateDate;
        this.QuantityInStock = QuantityInStock;
        this.RatingCount = RatingCount;
        this.Status = Status;
        this.AverageRatingStar = AverageRatingStar;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductTypeID() {
        return ProductTypeID;
    }

    public void setProductTypeID(String ProductTypeID) {
        this.ProductTypeID = ProductTypeID;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getConfig() {
        return Config;
    }

    public void setConfig(String Config) {
        this.Config = Config;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String Describe) {
        this.Describe = Describe;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public BigInteger getPrice() {
        return Price;
    }

    public void setPrice(BigInteger Price) {
        this.Price = Price;
    }

    public BigInteger getDiscount() {
        return Discount;
    }

    public void setDiscount(BigInteger Discount) {
        this.Discount = Discount;
    }

    public Timestamp getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Timestamp UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public Integer getQuantityInStock() {
        return QuantityInStock;
    }

    public void setQuantityInStock(Integer QuantityInStock) {
        this.QuantityInStock = QuantityInStock;
    }

    public Integer getRatingCount() {
        return RatingCount;
    }

    public void setRatingCount(Integer RatingCount) {
        this.RatingCount = RatingCount;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    public Double getAverageRatingStar() {
        return AverageRatingStar;
    }

    public void setAverageRatingStar(Double AverageRatingStar) {
        this.AverageRatingStar = AverageRatingStar;
    }
}
