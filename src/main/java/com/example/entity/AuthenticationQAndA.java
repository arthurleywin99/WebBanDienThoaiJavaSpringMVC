package com.example.entity;

public class AuthenticationQAndA {
	private String ID, Question;
	
	public AuthenticationQAndA() {
		
	}
	
	public AuthenticationQAndA(String ID, String Question) {
		this.ID = ID;
		this.Question = Question;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getQuestion() {
		return Question;
	}
	
	public void setQuestion(String question) {
		Question = question;
	}
}
