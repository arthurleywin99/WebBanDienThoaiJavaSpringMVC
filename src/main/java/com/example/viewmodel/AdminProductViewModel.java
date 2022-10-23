/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.viewmodel;

/**
 *
 * @author buing
 */
public class AdminProductViewModel {

    private String ID, SupplierName, ProductTypeName, BrandName, ProductName, Price, UpdateDate, Config, Describe, ImageURL, QuantityInStock, Status;

    public AdminProductViewModel() {
    }

    public AdminProductViewModel(String ID, String SupplierName, String ProductTypeName, String BrandName, String ProductName, String Price, String UpdateDate, String Config, String Describe, String ImageURL, String QuantityInStock, String Status) {
        this.ID = ID;
        this.SupplierName = SupplierName;
        this.ProductTypeName = ProductTypeName;
        this.BrandName = BrandName;
        this.ProductName = ProductName;
        this.Price = Price;
        this.UpdateDate = UpdateDate;
        this.Config = Config;
        this.Describe = Describe;
        this.ImageURL = ImageURL;
        this.QuantityInStock = QuantityInStock;
        this.Status = Status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public void setProductTypeName(String ProductTypeName) {
        this.ProductTypeName = ProductTypeName;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String UpdateDate) {
        this.UpdateDate = UpdateDate;
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

    public String getQuantityInStock() {
        return QuantityInStock;
    }

    public void setQuantityInStock(String QuantityInStock) {
        this.QuantityInStock = QuantityInStock;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
