/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Product;
import com.example.entity.ProductType;
import java.util.List;

/**
 *
 * @author buing
 */
public interface IProductTypeDAO {
    List<Product> getByTypeName(String key);
    List<ProductType> getAll();
    ProductType getByID(String ID);
    int createProductType(ProductType productType);
    int updateProductType(String ID, ProductType newProductType);
    void disableProductType(Boolean Status, String ID);
}
