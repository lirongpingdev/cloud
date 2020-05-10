package com.cloud.platform.producer.bean;

public class User {
	private int userId;
	private String userName;
	private String tel;
	private String address;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(int userId, String userName, String tel, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.tel = tel;
		this.address = address;
	}
	
}
