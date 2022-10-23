/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Product;
import java.util.List;
import com.example.entity.Brand;

/**
 *
 * @author buing
 */
public interface IBrandDAO {
    List<Product> getByBrandName(String key);
    Brand getByID(String ID);
    List<Brand> getAll();
    int createBrand(Brand brand);
    int updateBrand(String ID, Brand newBrand);
}
