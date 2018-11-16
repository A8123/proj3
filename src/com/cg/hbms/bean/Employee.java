package com.cg.hbms.bean;

public class Employee {
	private String userName;
	private String userPassword;
	private String userRole;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Employee(String userName, String userPassword, String userRole) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee [userName=" + userName + ", userPassword="
				+ userPassword + ", userRole=" + userRole + "]";
	}

}
