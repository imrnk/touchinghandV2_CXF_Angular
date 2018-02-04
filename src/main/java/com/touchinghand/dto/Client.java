package com.touchinghand.dto;

import java.io.Serializable;

enum Gender {
	
	MALE("M"), FEMALE("F"), OTHER("O");
	
	private String g;
	Gender(String s) {
		this.g = s;
	}
	
	public String getValue() {
		return g;
	}
	
	public String toString() {
		return g;
	}
	
}

enum Status {
	ONGOING("Y"), CLOSED("C"), LEFT("L");
	
	private String s;
	Status(String s){
		this.s = s;
	}
	
	public String getValue() {
		return s;
	}
	
	public String toString() {
		return s;
	}
	
}

public class Client implements Serializable{


	private Integer clientId;
	
	private String clientName;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private Integer age;
	
	private String maritalStatus;
	
	private String profession;
	
	private String education;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private Integer pin;
	
	private String country;
	
	private String mobile;
	
	private String secondPhone;
	
	private String email;
	
	private String reference;

	private String status;
	
	private String followupdate;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSecondPhone() {
		return secondPhone;
	}

	public void setSecondPhone(String secondPhone) {
		this.secondPhone = secondPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(String followupdate) {
		this.followupdate = followupdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String statusVal) {
		this.status = statusVal;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
