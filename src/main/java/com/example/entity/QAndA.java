package com.example.entity;

public class QAndA {
	private String ID, Email, Fullname, PhoneNumber, Title, Content, IDProblem;
	private Boolean Status;
	
	public QAndA() {
		
	}

	public QAndA(String iD, String email, String fullName, String phoneNumber, String title, String content,
			String iDProblem, Boolean status) {
		ID = iD;
		Email = email;
		Fullname = fullName;
		PhoneNumber = phoneNumber;
		Title = title;
		Content = content;
		IDProblem = iDProblem;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getIDProblem() {
		return IDProblem;
	}

	public void setIDProblem(String iDProblem) {
		IDProblem = iDProblem;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}
	
	
}
