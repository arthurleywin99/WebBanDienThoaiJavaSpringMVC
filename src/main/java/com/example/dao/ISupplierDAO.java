/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.Supplier;
import java.util.List;

/**
 *
 * @author buing
 */
public interface ISupplierDAO {
    Supplier getByID(String ID);
    List<Supplier> getAll();
    int createSupplier(Supplier supplier);
    int updateSupplier(String ID, Supplier newSupplier);
}
