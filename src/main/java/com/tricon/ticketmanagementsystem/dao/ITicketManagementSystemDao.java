package com.tricon.ticketmanagementsystem.dao;


import java.util.List;

import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Status;

public interface ITicketManagementSystemDao {

	public Employee getName(String userName);
	public String  addStatus(Status status);
	public String  updateStatus(Status status);
	public String  deleteStatus(Status status);
	public List<Status>  getStatus();
	public List<String> getEntitlements(int id);
	public String getRole(int empId);
	public List<String> hasPermission(int empId, String entitlements);
}
