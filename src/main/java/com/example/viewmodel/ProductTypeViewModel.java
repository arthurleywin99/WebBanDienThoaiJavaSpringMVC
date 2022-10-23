/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.viewmodel;

/**
 *
 * @author buing
 */
public class ProductTypeViewModel {
    String ID, ProductTypeName, IconURL, Status;

    public ProductTypeViewModel() {
    }

    public ProductTypeViewModel(String ID, String ProductTypeName, String IconURL, String Status) {
        this.ID = ID;
        this.ProductTypeName = ProductTypeName;
        this.IconURL = IconURL;
        this.Status = Status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public void setProductTypeName(String ProductTypeName) {
        this.ProductTypeName = ProductTypeName;
    }

    public String getIconURL() {
        return IconURL;
    }

    public void setIconURL(String IconURL) {
        this.IconURL = IconURL;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
