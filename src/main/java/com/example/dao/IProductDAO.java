/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Product;
import java.util.List;

/**
 *
 * @author buing
 */
public interface IProductDAO {
    List<Product> getByName(String key);
    List<Product> getAll();
    List<Product> getTopCellPhones();
    List<Product> getTopLaptops();
    List<Product> getByProductTypeID(String TypeID);
    Product getDetail(String ID);
    Product getByID(String ID);
    Double getAverageRatingStar(String ID);
    Integer getRatingCount(String ID);
    void disableProduct(Boolean Status, String ID);
    int createProduct(Product product);
    int updateProduct(String ID, Product newProduct);
}
