package com.tricon.Student.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tricon.Student.dao.IStudentDao;
import com.tricon.Student.model.Student;

@Repository
public class StudentDao implements IStudentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String FETCH_STUDENT="select * from records";

	
	@Override
	public List<Student> getName() {
		return this.jdbcTemplate.query(FETCH_STUDENT, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student s=new Student();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setAddress(rs.getString("address"));
			return s;
			}
		});
	}

}
