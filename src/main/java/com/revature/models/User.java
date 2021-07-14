package com.revature.models;

public class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String phoneNumber;
	private String address;
	private String email;
	
	public User(String firstName, String lastName, String userName, String phoneNumber, String address, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}

	public User() {
		super();
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
