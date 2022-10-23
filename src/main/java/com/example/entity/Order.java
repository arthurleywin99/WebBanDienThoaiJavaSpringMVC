package com.example.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Order {
	private String ID, MemberID, OrderStatus, OrderPhone, OrderEmail, OrderName, OrderAddress, TransferID, Note;
	private Timestamp OrderDate, DeliveryDate;
	private Boolean isPaid;
	private BigInteger Discount, Total;

	public Order() {
	}

	public Order(String iD, String memberID, String orderStatus, String orderPhone, String orderEmail, String orderName,
			String orderAddress, String transferID, String note, Timestamp orderDate, Timestamp deliveryDate,
			Boolean isPaid, BigInteger discount, BigInteger total) {
		ID = iD;
		MemberID = memberID;
		OrderStatus = orderStatus;
		OrderPhone = orderPhone;
		OrderEmail = orderEmail;
		OrderName = orderName;
		OrderAddress = orderAddress;
		TransferID = transferID;
		Note = note;
		OrderDate = orderDate;
		DeliveryDate = deliveryDate;
		this.isPaid = isPaid;
		Discount = discount;
		Total = total;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMemberID() {
		return MemberID;
	}

	public void setMemberID(String memberID) {
		MemberID = memberID;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}

	public String getOrderPhone() {
		return OrderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		OrderPhone = orderPhone;
	}

	public String getOrderEmail() {
		return OrderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		OrderEmail = orderEmail;
	}

	public String getOrderName() {
		return OrderName;
	}

	public void setOrderName(String orderName) {
		OrderName = orderName;
	}

	public String getOrderAddress() {
		return OrderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		OrderAddress = orderAddress;
	}

	public String getTransferID() {
		return TransferID;
	}

	public void setTransferID(String transferID) {
		TransferID = transferID;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public Timestamp getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		OrderDate = orderDate;
	}

	public Timestamp getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(Timestamp deliveryDate) {
		DeliveryDate = deliveryDate;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public BigInteger getDiscount() {
		return Discount;
	}

	public void setDiscount(BigInteger discount) {
		Discount = discount;
	}

	public BigInteger getTotal() {
		return Total;
	}

	public void setTotal(BigInteger total) {
		Total = total;
	}

}
