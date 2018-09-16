package com.tricon.ticketmanagementsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tricon.ticketmanagementsystem.service.ITicketManagementSystemService;
import com.tricon.ticketmanagementsystem.vo.Status;
import com.tricon.ticketmanagementsystem.vo.TicketManagementSystemUserCredential;
import com.tricon.ticketmanagementsystem.vo.UserInfo;
import com.tricon.ticketmanagementsystem.vo.UserLoginInfo;

@CrossOrigin("*")
@RestController
public class TicketManagementSystemController {
	@Autowired
	private LdapTemplate ldapTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketManagementSystemController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserInfo> auth(@RequestBody TicketManagementSystemUserCredential user) {
		UserInfo userInfo = new UserInfo();
		String userName, password;
		userName = user.getUserName();
		password = user.getPassword();
		boolean value = false;

		try {
			AndFilter filter = new AndFilter();

			ldapTemplate.setIgnorePartialResultException(true);

			filter.and(new EqualsFilter("sAMAccountName", userName));

			value = ldapTemplate.authenticate("", filter.toString(), password);

		} catch (Exception e) {

			LOGGER.error("TicketManagementSystemController - auth -" + e.getMessage());

		}
		if (value) {
			userInfo.setStatusid(1);
			userInfo.setUsername(userName);
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		} else {
			userInfo.setStatusid(0);
			userInfo.setUsername(userName);
			return new ResponseEntity<>(userInfo, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@Autowired
	private ITicketManagementSystemService  ticketManagementSystem;

	@RequestMapping(value="/empName/{userName}", method=RequestMethod.GET)
	public UserLoginInfo getName(@PathVariable String userName) {
		return ticketManagementSystem.getName(userName);
	}
	
	@RequestMapping(value="/status", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addStatus(@RequestBody Status status) {
		System.out.println("Inside: Controller");
		String responseStatus=ticketManagementSystem.addStatus(status);
		if(responseStatus=="SUCCESS")
		    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
		{
			return new ResponseEntity<String>("RECORD ALREADY EXISTS", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/updatestatus", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateStatus(@RequestBody Status status) {
		System.out.println("Inside: Controller");
		String responseStatus=ticketManagementSystem.updateStatus(status);
		if(responseStatus=="SUCCESS")
		    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
		{
			return new ResponseEntity<String>("RECORD ALREADY EXISTS", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/deletestatus", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteStatus(@RequestBody Status status) {
		System.out.println("Inside: Controller");
		String responseStatus=ticketManagementSystem.deleteStatus(status);
		if(responseStatus=="SUCCESS")
		    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
		{
			return new ResponseEntity<String>("RECORD ALREADY EXISTS", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/liststatus",params = { "","total"}, method=RequestMethod.GET) 
	  	public List<Status> getStatus() { 
	  		System.out.println("Inside: Controller"); 
	  		return ticketManagementSystem.getStatus(); 
	 	} 
	

	@RequestMapping(value="/haspermission" , params = { "empId" , "entitlement"} , method=RequestMethod.GET)   
	  	public ResponseEntity<String> hasPermission(@RequestParam( "empId" ) int empId , @RequestParam( "entitlement" ) String entitlement) { 
		
	  		System.out.println("Inside: Controller"); 
	  		String responseStatus = ticketManagementSystem.hasPermission(empId , entitlement);
			
			if(responseStatus.equals( "Has Permission" ))
			    return new ResponseEntity<String>( "{\"Success\":\"true\"}", HttpStatus.OK );
			else
			{
			return new ResponseEntity<String>( "{\"Success\":\"false\"}", HttpStatus.UNAUTHORIZED );
			}
	 	} 
	
}