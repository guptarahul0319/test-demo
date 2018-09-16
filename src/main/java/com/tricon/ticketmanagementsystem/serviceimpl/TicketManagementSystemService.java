package com.tricon.ticketmanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.ticketmanagementsystem.dao.ITicketManagementSystemDao;
import com.tricon.ticketmanagementsystem.service.ITicketManagementSystemService;
import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Status;
import com.tricon.ticketmanagementsystem.vo.UserLoginInfo;

@Service
public class TicketManagementSystemService implements ITicketManagementSystemService {

	@Autowired
	private ITicketManagementSystemDao ticketManagementSystemDao; 
	
	@Override
	public UserLoginInfo getName(String userName) {
		
		Employee employee =  ticketManagementSystemDao.getName(userName);
		
		UserLoginInfo userLoginInfo = new UserLoginInfo();
		
		userLoginInfo.setEmployee(employee);
		
		System.out.println(ticketManagementSystemDao.getEntitlements(employee.getEmpId()));
		List<String> entitlements = ticketManagementSystemDao.getEntitlements(employee.getEmpId());
		userLoginInfo.setEntitlements(entitlements);
		
		String role = ticketManagementSystemDao.getRole(employee.getEmpId());
		userLoginInfo.setRole(role);
		
		return userLoginInfo;
		
	}
	
	@Override
	public String addStatus(Status status) {
		System.out.println("service");
		return ticketManagementSystemDao.addStatus(status);
	}
	
	@Override
	public String updateStatus(Status status) {
		System.out.println("service");
		return ticketManagementSystemDao.updateStatus(status);
	}
	
	@Override
	public String deleteStatus(Status status) {
		System.out.println("service");
		return ticketManagementSystemDao.deleteStatus(status);
	}
 
	@Override 
	 public List<Status>  getStatus() { 
	  		System.out.println("service"); 
	  		return ticketManagementSystemDao.getStatus(); 
	  	} 
	
	@Override 
	public String hasPermission( int empId , String entitlement) {
		
		System.out.println("service"); 
		int has_Permission = 0;
		List<String> entitlements =  ticketManagementSystemDao.getEntitlements(empId); 
		
		for(String check_entitlements : entitlements) {
			
			if(check_entitlements.equals(entitlement)) {
				
				has_Permission = 1;
				break;
			}
		}
		
		
		if(has_Permission == 1) {
	
			return "Has Permission";
		}
		else {
			
			return "Access Denied";
		}
	}
}
