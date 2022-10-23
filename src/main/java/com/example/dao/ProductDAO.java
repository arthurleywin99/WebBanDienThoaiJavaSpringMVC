
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.mapper.OrderDetailMapper;
import com.example.mapper.ProductMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author buing
 */
@Repository
public class ProductDAO implements IProductDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Product> getByName(String key) {
        String sql = "SELECT * FROM Product WHERE ProductName LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + key + "%"}, new ProductMapper());
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM Product";
        return jdbc.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> getTopCellPhones() {
        String sql = "SELECT E.ID, SupplierID, ProductTypeID, BrandID, ProductName, Price, Discount, UpdateDate, Config, Describe, ImageURL, QuantityInStock, E.[Status] FROM ( "
                + "SELECT TOP 12 WITH TIES ID, CountOrder FROM ( "
                + "SELECT A.ID, SUM(B.Quantity) AS CountOrder FROM Product AS A "
                + "INNER JOIN OrderDetail AS B ON A.ID = B.ProductID "
                + "INNER JOIN ProductType AS C ON A.ProductTypeID = C.ID "
                + "WHERE C.ProductTypeName = N'Điện thoại' "
                + "GROUP BY A.ID, ProductName ) AS C ORDER BY CountOrder DESC "
                + ") AS D INNER JOIN Product AS E ON D.ID = E.ID";
        return jdbc.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> getTopLaptops() {
        String sql = "SELECT E.ID, SupplierID, ProductTypeID, BrandID, ProductName, Price, Discount, UpdateDate, Config, Describe, ImageURL, QuantityInStock, E.[Status] FROM ( "
                + "SELECT TOP 12 WITH TIES ID, CountOrder FROM ( "
                + "SELECT A.ID, SUM(B.Quantity) AS CountOrder FROM Product AS A "
                + "INNER JOIN OrderDetail AS B ON A.ID = B.ProductID "
                + "INNER JOIN ProductType AS C ON A.ProductTypeID = C.ID "
                + "WHERE C.ProductTypeName = 'Laptop' "
                + "GROUP BY A.ID, ProductName ) AS C ORDER BY CountOrder DESC "
                + ") AS D INNER JOIN Product AS E ON D.ID = E.ID";
        return jdbc.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> getByProductTypeID(String TypeID) {
        String sql = "SELECT A.ID, SupplierID, ProductTypeID, BrandID, ProductName, Price, Discount, UpdateDate, Config, Describe, ImageURL, QuantityInStock, A.[Status] "
                + "FROM Product AS A INNER JOIN ProductType AS B "
                + "ON A.ProductTypeID = B.ID "
                + "WHERE B.ID = ?";
        return jdbc.query(sql, new ProductMapper(), TypeID);
    }

    @Override
    public Product getDetail(String ID) {
        String sql = "SELECT * FROM Product WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{ID}, new ProductMapper());
    }

    @Override
    public Double getAverageRatingStar(String ID) {
        String sql = "SELECT B.OrderID, B.ProductID, B.PriceNow, B.Quantity, B.[Content], B.RatingStar "
                + "FROM Product AS A INNER JOIN OrderDetail AS B ON A.ID = B.ProductID "
                + "WHERE B.RatingStar <> null AND B.Content <> null";
        List<OrderDetail> result = jdbc.query(sql, new OrderDetailMapper());
        Double sum = 0.0;
        for (OrderDetail item : result) {
            sum += item.getRatingStar();
        }
        return (result != null && !result.isEmpty()) ? sum / result.size() : 0.0;
    }

    @Override
    public Integer getRatingCount(String ID) {
        String sql = "SELECT B.OrderID, B.ProductID, B.PriceNow, B.Quantity, B.[Content], B.RatingStar "
                + "FROM Product AS A INNER JOIN OrderDetail AS B ON A.ID = B.ProductID "
                + "WHERE B.RatingStar <> null AND B.Content <> null";
        List<OrderDetail> result = jdbc.query(sql, new OrderDetailMapper());
        return (result != null && !result.isEmpty()) ? result.size() : 0;
    }

    @Override
    public Product getByID(String ID) {
        String sql = "SELECT * FROM Product WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{ID}, new ProductMapper());
    }

    @Override
    public void disableProduct(Boolean Status, String ID) {
        String sql = "UPDATE Product SET [Status] = ? WHERE ID = ?";
        jdbc.update(sql, new Object[]{Status, ID});
    }

    @Override
    public int createProduct(Product product) {
        String sql = "INSERT INTO Product (ID, SupplierID, ProductTypeID, BrandID, ProductName, Price, Discount, UpdateDate, Config, Describe, ImageURL, QuantityInStock, Status) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            return jdbc.update(sql, new Object[]{product.getID(), product.getSupplierID(), product.getProductTypeID(), product.getBrandID(),
                product.getProductName(), product.getPrice(), product.getDiscount(), product.getUpdateDate(), product.getConfig(), product.getDescribe(),
                product.getImageURL(), product.getQuantityInStock(), product.getStatus()});
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public int updateProduct(String ID, Product newProduct) {
        String sql = "UPDATE Product SET SupplierID = ?, ProductTypeID = ?, BrandID = ?, ProductName = ?, Price = ?, Discount = ?, UpdateDate = ?, Config = ?, Describe = ?, ImageURL = ?, QuantityInStock = ?, [Status] = ? WHERE ID = ?";
        try {
            return jdbc.update(sql, new Object[]{newProduct.getSupplierID(), newProduct.getProductTypeID(), newProduct.getBrandID(), newProduct.getProductName(),
                newProduct.getPrice(), newProduct.getDiscount(), newProduct.getUpdateDate(), newProduct.getConfig(), newProduct.getDescribe(), newProduct.getImageURL(),
                newProduct.getQuantityInStock(), newProduct.getStatus(), ID});
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
