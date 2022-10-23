package com.example.entity;

import java.math.BigInteger;

public class OrderDetail {
	private String OrderID, ProductID, Content;
	private BigInteger PriceNow;
	private Integer Quantity, RatingStar;

	public OrderDetail() {
	}

	public OrderDetail(String orderID, String productID, String content, BigInteger priceNow, Integer quantity,
			Integer ratingStar) {
		OrderID = orderID;
		ProductID = productID;
		Content = content;
		PriceNow = priceNow;
		Quantity = quantity;
		RatingStar = ratingStar;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getProductID() {
		return ProductID;
	}

	public void setProductID(String productID) {
		ProductID = productID;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public BigInteger getPriceNow() {
		return PriceNow;
	}

	public void setPriceNow(BigInteger priceNow) {
		PriceNow = priceNow;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public Integer getRatingStar() {
		return RatingStar;
	}

	public void setRatingStar(Integer ratingStar) {
		RatingStar = ratingStar;
	}

}
