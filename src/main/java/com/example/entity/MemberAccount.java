package com.example.entity;

import java.sql.Timestamp;

public class MemberAccount {
	private String ID, MemberTypeID, Email, Password, FullName, Address, PhoneNumber, IDQuestion, Answer, ResetPasswordCode;
	private Timestamp BirthDate, JoinDate;
	private Boolean Status;

	public MemberAccount() {
	}

	public MemberAccount(String iD, String memberTypeID, String email, String password, String fullName, String address,
			String phoneNumber, String iDQuestion, String answer, String resetPasswordCode, Timestamp birthDate,
			Timestamp joinDate, Boolean status) {
		ID = iD;
		MemberTypeID = memberTypeID;
		Email = email;
		Password = password;
		FullName = fullName;
		Address = address;
		PhoneNumber = phoneNumber;
		IDQuestion = iDQuestion;
		Answer = answer;
		ResetPasswordCode = resetPasswordCode;
		BirthDate = birthDate;
		JoinDate = joinDate;
		Status = status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMemberTypeID() {
		return MemberTypeID;
	}

	public void setMemberTypeID(String memberTypeID) {
		MemberTypeID = memberTypeID;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getIDQuestion() {
		return IDQuestion;
	}

	public void setIDQuestion(String iDQuestion) {
		IDQuestion = iDQuestion;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public String getResetPasswordCode() {
		return ResetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		ResetPasswordCode = resetPasswordCode;
	}

	public Timestamp getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		BirthDate = birthDate;
	}

	public Timestamp getJoinDate() {
		return JoinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		JoinDate = joinDate;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

}
