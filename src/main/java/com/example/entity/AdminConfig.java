package com.example.entity;

public class AdminConfig {
	private String ID, AdEmail, AdPhoneNumber, AdPassword, Name;
	private Boolean Status;

	public AdminConfig() {
	}

	public AdminConfig(String iD, String adEmail, String adPhoneNumber, String adPassword, String name,
			Boolean status) {
		ID = iD;
		AdEmail = adEmail;
		AdPhoneNumber = adPhoneNumber;
		AdPassword = adPassword;
		Name = name;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getAdEmail() {
		return AdEmail;
	}

	public void setAdEmail(String adEmail) {
		AdEmail = adEmail;
	}

	public String getAdPhoneNumber() {
		return AdPhoneNumber;
	}

	public void setAdPhoneNumber(String adPhoneNumber) {
		AdPhoneNumber = adPhoneNumber;
	}

	public String getAdPassword() {
		return AdPassword;
	}

	public void setAdPassword(String adPassword) {
		AdPassword = adPassword;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}
}
