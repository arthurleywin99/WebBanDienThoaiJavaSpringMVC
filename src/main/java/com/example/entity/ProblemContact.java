package com.example.entity;

public class ProblemContact {
	private String ID, Content;
	
	public ProblemContact() {
		
	}
	
	public ProblemContact(String ID, String Content) {
		this.ID = ID;
		this.Content = Content;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		Content = content;
	}
}
