package com.hackathon.dao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import com.hackathon.model.*;

public class RegistrationDAO 
{
	JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		
		
		return jdbcTemplate;
		
		
	}
	
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		
		
		this.jdbcTemplate = jdbcTemplate;
		
		
	}
	
	
	
	public int saveData(User user, Address addr)
	{
	
		
		System.out.println("in save data..");
		
		String addrTableQuery="insert into gr7_address values(ga_id_seq.nextval, '"+addr.getState()+"', '"+addr.getCity()+"', '"+addr.getZipcode()+"')";
		int j = jdbcTemplate.update(addrTableQuery);

		
		//		String sql_ga_id = "SELECT id FROM tableNmae WHERE column_name = ga_id_seq.currval";
//		int	current_address_id = (Integer) jdbcTemplate.queryForObject( sql_ga_id, new Object[] { ga_id }, Integer.class);
		
		
		String userTableQuery="insert into gr7_users values(gu_id_seq.nextval,'"+user.getFname()+"','"+user.getMobile()+"','"+user.getEmail()+"','"+user.getDob()+"', '"+addr.getId()+"', '"+user.getPassword()+"')";
		int i= jdbcTemplate.update(userTableQuery);

		return i;
	}
	
	public boolean validateUser(User user) {
		
		
		String email= "select gu_email from gr7_users where gu_email='" + user.getEmail() + "'";
		String password= "select gu_password from gr7_users where gu_password='" + user.getPassword() + "'";
		
		
		String e = jdbcTemplate.queryForObject(email, String.class);
		System.out.println(e);
	
		
		
		String p = jdbcTemplate.queryForObject(password, String.class);
		System.out.println(p);
		
		
		
		if(user.getEmail().equals(e) && user.getPassword().equals(p)) 
			return true;
		else
			return false;
		
		
	}
	
	public List<Map<String, Object>> getQuestion() {
		
		
		
		 String sql="select * from gr7_questions"; 

		 return jdbcTemplate.queryForList(sql); 
		 
		 
		
	}
}