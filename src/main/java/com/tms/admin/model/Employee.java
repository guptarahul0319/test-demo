package com.tms.admin.model;

public class Employee {

	private String empName;
	private int empId;
	private String emailId;
	private String isArchived;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(String  isArchived) {
		
			this.isArchived = isArchived;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
		
}
