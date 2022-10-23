package com.example.entity;

public class Banner {
	private String ID, BannerName, ImageURL, LinkTo;
	private Boolean Status;

	public Banner() {

	}

	public Banner(String iD, String bannerName, String imageURL, String linkTo, Boolean status) {
		ID = iD;
		BannerName = bannerName;
		ImageURL = imageURL;
		LinkTo = linkTo;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBannerName() {
		return BannerName;
	}

	public void setBannerName(String bannerName) {
		BannerName = bannerName;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public String getLinkTo() {
		return LinkTo;
	}

	public void setLinkTo(String linkTo) {
		LinkTo = linkTo;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

}
