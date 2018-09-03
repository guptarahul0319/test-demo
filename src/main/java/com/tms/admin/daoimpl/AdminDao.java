package com.tms.admin.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tms.admin.dao.IAdminDao;
import com.tms.admin.model.Employee;
import com.tms.admin.model.Group;

@Repository
public class AdminDao implements IAdminDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String FETCH_EMPLOYEES="SELECT empId,fName,mName,lName,emailId,isArchived from employee e";
	private static final String FETCH_GROUPS="SELECT DISTINCT ga.adminId,g.id,e.fName,e.mName,e.lName,g.name,gd.description,g.isArchived FROM ticketmanagementdb.group g inner join ticketmanagementdb.group_admin ga on g.id=ga.groupId inner join ticketmanagementdb.group_description gd on g.id=gd.groupId inner join ticketmanagementdb.employee_group_ref egr on egr.groupId=g.id inner join ticketmanagementdb.employee e on ga.adminId = e.empId";
	private static final String FETCH_ONE_GROUP="SELECT DISTINCT ga.adminId,e.empId,g.id,e.fName,e.mName,e.lName,g.name,gd.description,g.isArchived FROM ticketmanagementdb.group g inner join ticketmanagementdb.group_admin ga on g.id=ga.groupId inner join ticketmanagementdb.group_description gd on g.id=gd.groupId inner join ticketmanagementdb.employee_group_ref egr on egr.groupId=g.id inner join ticketmanagementdb.employee e on egr.empId = e.empId WHERE g.id=?";
	private static final String ADD_GROUP_NAME ="INSERT INTO `ticketmanagementdb`.`group` (`name`, `isArchived`) VALUES (?,?)";
	private static final String ADD_GROUP_ADMIN ="INSERT INTO `ticketmanagementdb`.`group_admin` (`groupId`, `adminId`, `isArchived`) VALUES (?,?,?)";
	private static final String ADD_GROUP_EMPLOYEES ="INSERT INTO `ticketmanagementdb`.`employee_group_ref` (`groupId`, `empId`, `isArchived`) VALUES (?,?,?)";	
	private static final String ADD_GROUP_DESCRIPTION = "INSERT INTO `ticketmanagementdb`.`group_description` (`groupId`, `description`,`isArchived`) VALUES (?,?,?)";
	
	
	
	String groupStatus= "";
	String employeeStatus= "";
	String groupEmpId= "";
	Group group;

	public static boolean compare(String str1) {
	    if(str1 == null) {
	    	return false;
	    }
	    else {
	    	return true;
	    }
	}
	

	@Override
	public List<Employee> getAllEmployees() {
		return this.jdbcTemplate.query(FETCH_EMPLOYEES, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee s=new Employee();
			

			if(compare(rs.getString("e.mName"))) {
				
				if(compare(rs.getString("e.lName"))) {
					
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
				} 
				else {
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
				}
			}
			else {
				if(compare(rs.getString("e.lName"))) {
					
					s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
				}
				else {
					
					s.setEmpName(rs.getString("e.fName"));
				}
			}
			
	
			s.setEmailId(rs.getString("emailId"));
			s.setEmpId(rs.getInt("empId"));
			
			employeeStatus= (rs.getString("e.isArchived"));
			
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
	
	

	@Override
	public List<Group> getAllGroups() {

		return this.jdbcTemplate.query(FETCH_GROUPS, new RowMapper<Group>() {
			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
				group=new Group();			
			//loop and append
			
			group.setSerialNumber(rs.getInt("g.id"));
			

			if(compare(rs.getString("e.mName"))) {
				
				if(compare(rs.getString("e.lName"))) {
					
					group.setAdminName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
				} 
				else {
					group.setAdminName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
				}
			}
			else {
				if(compare(rs.getString("e.lName"))) {
					
					group.setAdminName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
				}
				else {
					
					group.setAdminName(rs.getString("e.fName"));
				}
			}
	
			
			group.setAdminId(rs.getInt("ga.adminId"));
			group.setGroupName(rs.getString("g.name"));
			group.setDescription(rs.getString("gd.description"));
			groupStatus= (rs.getString("g.isArchived"));
			
			
			if(groupStatus.compareTo("0")== 0)
			{
				group.setIsArchived("Active");
			}
			else {
				group.setIsArchived("Inactive");
			}
			
			
			return group;
			}
		});	
	}
	
	@Override
	public Group getOneGroup(int id) {
		
		group=new Group();
		 List <Group> groplist = this.jdbcTemplate.query(FETCH_ONE_GROUP, new RowMapper<Group>() {
			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("Inside getOneGroup");
			
			//loop and append
//			s.setAdminName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
			
			group.setSerialNumber(rs.getInt("g.id"));
			group.setGroupName(rs.getString("g.name"));
			group.setDescription(rs.getString("gd.description"));
			group.setAdminId(rs.getInt("ga.adminId"));
			System.out.println("getOneGroup parameters set");
			groupStatus= (rs.getString("g.isArchived"));
			
			System.out.println(rs.getInt("ga.adminId") == rs.getInt("e.empId"));
			if(rs.getInt("ga.adminId") == rs.getInt("e.empId"))
			{
				if(compare(rs.getString("e.mName"))) {
					
					if(compare(rs.getString("e.lName"))) {
						
						group.setAdminName(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName"));
					} 
					else {
						group.setAdminName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
					}
				}
				else {
					if(compare(rs.getString("e.lName"))) {
						
						group.setAdminName(rs.getString("e.fName")+" "+rs.getString("e.lName"));
					}
					else {
						
						group.setAdminName(rs.getString("e.fName"));
					}
				}
			}
			
			if(compare(rs.getString("e.mName"))) {
				
				if(compare(rs.getString("e.lName"))) {
					
					groupEmpId = groupEmpId +	(rs.getString("e.fName")+" "+rs.getString("e.mName")+" "+rs.getString("e.lName") + "," );
				} 
				else {
					groupEmpId = groupEmpId  + (rs.getString("e.fName")+" "+rs.getString("e.mName") + "," );
					//s.setEmpName(rs.getString("e.fName")+" "+rs.getString("e.mName"));
				}
			}
			else {
				if(compare(rs.getString("e.lName"))) {
					
					groupEmpId = groupEmpId + (rs.getString("e.fName")+" "+rs.getString("e.lName") + "," );
					//s.setEmpName();
				}
				else {
					
					groupEmpId = groupEmpId +  rs.getString("e.fName") + "," ;
					//s.setEmpName();
				}
			}
			
			

			
			if(groupStatus.compareTo("0")== 0)
			{
				group.setIsArchived("Active");
			}
			else {
				group.setIsArchived("Inactive");
			}

			return group;
			}
		},id);
		 group.setGroupEmpIds(groupEmpId);
		 groupEmpId= "";
		 return group;
	}
	
	
	@Override
	public void addGroup(Group obj) {
		
		
			jdbcTemplate.update(ADD_GROUP_NAME, obj.getGroupName(),0);

		    String FETCH_GROUP_ID	 = "SELECT id FROM ticketmanagementdb.`group` where name='"+ obj.getGroupName() + "'";
		    int group_id = jdbcTemplate.queryForObject(FETCH_GROUP_ID, int.class);
		     
	        jdbcTemplate.update(ADD_GROUP_DESCRIPTION, group_id, obj.getDescription(),0); 
	        jdbcTemplate.update(ADD_GROUP_ADMIN, group_id, obj.getAdminId(),0); 
	        
	        String groupEmpIds = obj.getGroupEmpIds();
	        
	        String[] empIdStrings = groupEmpIds.split(" "); 
	    	int[] array_of_employees = new int[empIdStrings.length]; 
	    
	    	for (int i = 0; i < empIdStrings.length; i++){
	    		array_of_employees[i] = Integer.parseInt(empIdStrings[i]); 
	    	}		
	    	
	        
	        List<Object[]> data_of_employee_group_ref = new ArrayList<Object[]>();
	        
	        for(int emp: array_of_employees){
	            Object[] tmp = { group_id, emp, 0};
	            data_of_employee_group_ref.add(tmp);
	        }
	        jdbcTemplate.batchUpdate(ADD_GROUP_EMPLOYEES, data_of_employee_group_ref); 
	        	        
	    }
	}
