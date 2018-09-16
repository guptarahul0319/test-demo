package com.tricon.ticketmanagementsystem.vo;

import java.util.List;

public class UserLoginInfo {
	
	private Employee employee;
	private List<String> entitlements;
	private String role;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getEntitlements() {
		return entitlements;
	}
	public void setEntitlements(List<String> entitlements) {
		this.entitlements = entitlements;
	}
}
