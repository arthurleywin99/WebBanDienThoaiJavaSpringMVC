package com.example.entity;

public class ProductType {

    private String ID, ProductTypeName, IconURL;
    private Boolean Status;

    public ProductType() {

    }

    public ProductType(String iD, String productTypeName, String iconURL, Boolean status) {
        ID = iD;
        ProductTypeName = productTypeName;
        IconURL = iconURL;
        Status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        ProductTypeName = productTypeName;
    }

    public String getIconURL() {
        return IconURL;
    }

    public void setIconURL(String iconURL) {
        IconURL = iconURL;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

}
