package com.tricon.ticketmanagementsystem.dao;


import java.util.List;

import com.tricon.ticketmanagementsystem.vo.*;

public interface IAdminDao {
	
	public List<Employee> getAllEmployees();
	public List<Group> getGroups();
	public void addGroup(Group obj);
	public Group getGroup(int id);
	public void updateGroup(Group obj , int id);
	public List<Employee> getEmployee(int id);
	public void  deleteGroup(int id);
	
}