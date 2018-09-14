package com.tricon.ticketmanagementsystem.daoimpl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tricon.ticketmanagementsystem.dao.ITicketManagementSystemDao;
import com.tricon.ticketmanagementsystem.vo.Employee;
import com.tricon.ticketmanagementsystem.vo.Status;

@Repository
public class TicketManagementSystemDao implements ITicketManagementSystemDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String FETCH_EMP_NAME= "SELECT empId,fName,mName,lName FROM ticketmanagementdb.employee where userName=?";
	
	public static final String DELETE_STATUS="UPDATE `ticketmanagementdb`.`ticket_status` SET `isArchived` = '1' WHERE (`id` = ?)";
	
	public static final String FETCH_STATUS="SELECT * FROM `ticketmanagementdb`.`ticket_status` ORDER BY updatedOn DESC";
	
	public static final String INSERT_INTO_STATUS="INSERT INTO `ticketmanagementdb`.`ticket_status` (`status`, `isArchived`, `createdOn`, `updatedOn`) VALUES (?,'0',now(),now())";
	
	public static final String MYSQL_CHECK_FOR_PRESENCE_OF_STATUS_DETAILS="SELECT COUNT(*) FROM `ticketmanagementdb`.`ticket_status` where status=?";
	
	public static final String UPDATE_STATUS="UPDATE `ticketmanagementdb`.`ticket_status` SET `status` = ? , `isArchived` = '0', `updatedOn` = now() WHERE (`id` = ?)";
	
	public static final String UPDATE_STATUS_In="UPDATE `ticketmanagementdb`.`ticket_status` SET `status` = ? , `isArchived` = '1', `updatedOn` = now() WHERE (`id` = ?)";
	
	private static final String FETCH_USER_ENTITLEMENTS = "SELECT entitlements from accessmanagementdb.user_role_ref urr inner join accessmanagementdb.role_entitlements_ref rer on rer.roleId = urr.roleId inner join accessmanagementdb.entitlements e on rer.entitlementsId = e.id  where empId = ?";
	
	private static final String FETCH_USER_ROLE = "SELECT role FROM accessmanagementdb.role r inner join accessmanagementdb.user_role_ref urr on urr.roleId = r.id where empId = ?";
	
	@Override
	public Employee getName(String userName) {
		
	return this.jdbcTemplate.queryForObject(FETCH_EMP_NAME, new RowMapper<Employee>() {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employeeDetails = new Employee();
			employeeDetails.setEmpId(rs.getInt("empid"));
			employeeDetails.setfName(rs.getString("fName"));
			employeeDetails.setmName(rs.getString("mName"));
			employeeDetails.setlName(rs.getString("lName"));
			return employeeDetails;
			}
		},userName);
	}
	
	@Override
	public String  addStatus(Status status) {
		int re;
		String stat;
		System.out.println("dao");
		stat=status.getStatus();
		System.out.println(stat);
		//stat="in development";
		re= jdbcTemplate.queryForObject(MYSQL_CHECK_FOR_PRESENCE_OF_STATUS_DETAILS,new Object[] { stat },Integer.class);
		System.out.println(re);
		if(re==0)
		{
		jdbcTemplate.update(INSERT_INTO_STATUS,status.getStatus());
		return "SUCCESS";
		}
		else
		{
			return "Record already exist";
		}
		
	}
	
	@Override
	public List<String> getEntitlements(int empId) {
		
		List<String> entitlementsList = this.jdbcTemplate.query(FETCH_USER_ENTITLEMENTS, new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("e.entitlements");								
			}
		 }, empId);
		
		return entitlementsList;
	}
	

	@Override
	public String getRole(int empId) {
		
		 String role = (String) jdbcTemplate.queryForObject( FETCH_USER_ROLE, new Object[] { empId }, String.class);
				
		return role;
	}

	@Override
	public String updateStatus(Status status) {
		String s =status.getIsArchived();
		if(s.compareTo("Active")== 0) {
		     jdbcTemplate.update(UPDATE_STATUS,status.getStatus(),status.getId());}
		else {
		     jdbcTemplate.update(UPDATE_STATUS_In,status.getStatus(),status.getId());
		}
		return "SUCCESS";
	}
	
	@Override
	public String deleteStatus(Status status) {
		
		jdbcTemplate.update(DELETE_STATUS,status.getId());
		return "SUCCESS";
	}
	
	@Override 
	 	public List<Status> getStatus() { 
	  		return this.jdbcTemplate.query(FETCH_STATUS, new RowMapper<Status>() { 
	  			public Status mapRow(ResultSet rs, int rowNum) throws SQLException { 
	  			Status s=new Status(); 
	  			s.setId(rs.getInt("id")); 
	  			s.setStatus(rs.getString("status"));
	  			s.setIsArchived(rs.getString("IsArchived"));
	  			String employeeStatus= (rs.getString("isArchived"));
				
				if(employeeStatus.compareTo("0")== 0)
				{
					s.setIsArchived("Active");
				}
				else {
					s.setIsArchived("Inactive");
				}
	  			return s; 
	  			} 
	  		}); 
	  	} 

	
	}
