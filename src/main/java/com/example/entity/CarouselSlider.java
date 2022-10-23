package com.example.entity;

public class CarouselSlider {
	private String ID, SliderName, ImageURL, UrlTo;
	private Boolean Status;

	public CarouselSlider() {
	}

	public CarouselSlider(String iD, String sliderName, String imageURL, String urlTo, Boolean status) {
		ID = iD;
		SliderName = sliderName;
		ImageURL = imageURL;
		UrlTo = urlTo;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSliderName() {
		return SliderName;
	}

	public void setSliderName(String sliderName) {
		SliderName = sliderName;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public String getUrlTo() {
		return UrlTo;
	}

	public void setUrlTo(String urlTo) {
		UrlTo = urlTo;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

}
