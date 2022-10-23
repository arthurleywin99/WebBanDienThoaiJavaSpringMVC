package com.example.entity;

public class BrandAdvertisement {
	private String ID, BrandAdName, ImageURL, URLTo;
	private Boolean Status;

	public BrandAdvertisement() {

	}

	public BrandAdvertisement(String iD, String brandAdName, String imageURL, String uRLTo, Boolean status) {
		ID = iD;
		BrandAdName = brandAdName;
		ImageURL = imageURL;
		URLTo = uRLTo;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBrandAdName() {
		return BrandAdName;
	}

	public void setBrandAdName(String brandAdName) {
		BrandAdName = brandAdName;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public String getURLTo() {
		return URLTo;
	}

	public void setURLTo(String uRLTo) {
		URLTo = uRLTo;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

}
