package com.example.entity;

public class Brand {
	private String ID, BrandName, LogoURL, Describe;
	private Boolean Status;

	public Brand() {

	}

	public Brand(String iD, String brandName, String logoURL, String describe, Boolean status) {
		ID = iD;
		BrandName = brandName;
		LogoURL = logoURL;
		Describe = describe;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	public String getLogoURL() {
		return LogoURL;
	}

	public void setLogoURL(String logoURL) {
		LogoURL = logoURL;
	}

	public String getDescribe() {
		return Describe;
	}

	public void setDescribe(String describe) {
		Describe = describe;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

}
