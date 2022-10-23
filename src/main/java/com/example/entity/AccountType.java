package com.example.entity;

import java.math.BigDecimal;

public class AccountType {
	private String ID, UserTypeName;
	private BigDecimal Discount;
	private Boolean Status;

	public AccountType() {

	}

	public AccountType(String iD, String userTypeName, BigDecimal discount, Boolean status) {
		ID = iD;
		UserTypeName = userTypeName;
		Discount = discount;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUserTypeName() {
		return UserTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		UserTypeName = userTypeName;
	}

	public BigDecimal getDiscount() {
		return Discount;
	}

	public void setDiscount(BigDecimal discount) {
		Discount = discount;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}
}
