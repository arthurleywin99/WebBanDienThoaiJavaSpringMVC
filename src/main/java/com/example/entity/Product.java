package com.example.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Product {

    private String ID, ProductTypeID, SupplierID, BrandID, ProductName, Config, Describe, ImageURL;
    private BigInteger Price, Discount;
    private Timestamp UpdateDate;
    private Integer QuantityInStock;
    private Boolean Status;

    public Product() {

    }

    public Product(String ID, String ProductTypeID, String SupplierID, String BrandID, String ProductName, String Config, String Describe, String ImageURL, BigInteger Price, BigInteger Discount, Timestamp UpdateDate, Integer QuantityInStock, Boolean Status) {
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
        this.Status = Status;
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

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

}
