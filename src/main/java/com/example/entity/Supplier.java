package com.example.entity;

public class Supplier {
	private String ID, SupplierName, Address, Email, PhoneNumber;
	private Boolean Status;

	public Supplier() {

	}

	public Supplier(String iD, String supplierName, String address, String email, String phoneNumber, Boolean status) {
		ID = iD;
		SupplierName = supplierName;
		Address = address;
		Email = email;
		PhoneNumber = phoneNumber;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

}
