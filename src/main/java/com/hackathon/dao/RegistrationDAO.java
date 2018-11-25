package com.hackathon.dao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
		int i=0;
		System.out.println("in save data..");
		String addrTableQuery="insert into gr7_address values(ga_id_seq.nextval, '"+addr.getState()+"', '"+addr.getCity()+"', '"+addr.getZipcode()+"')";
		int j = jdbcTemplate.update(addrTableQuery);
//		String sql_ga_id = "SELECT id FROM tableNmae WHERE column_name = ga_id_seq.currval";
//		int	current_address_id = (Integer) jdbcTemplate.queryForObject( sql_ga_id, new Object[] { ga_id }, Integer.class);
		String userTableQuery="insert into gr7_users values(gu_id_seq.nextval,'"+user.getFname()+"','"+user.getMobile()+"','"+user.getEmail()+"','"+user.getDob()+"', '"+addr.getId()+"', '"+user.getPassword()+"')";
		i= jdbcTemplate.update(userTableQuery);


		//String accountTableQuery="insert into g13_accounts values('+g13_account_seq.nextval+','"+account.getAccount_type()+"','"+50000+"','+g13_customer_seq.currval')";

		//i =  jdbcTemplate.update(userTableQuery);



		//String addressTableQuery="insert into g13_addresses values('+g13_address_seq.nextval+','"+address.getAddress_line_1()+"','"+address.getAddress_line_2()+"','"+address.getCity()+"','"+address.getPin_code()+"','"+address.getState()+"','+g13_customer_seq.currval')";

		//	String query="insert into customers values('"+rf.getUserId()+"','"+rf.getMobileNo()+"','"+rf.getAmount()+"','"+rf.getOperator()+"')";

		//i =  jdbcTemplate.update(addressTableQuery);

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
	
	public Questions getQuestion() {
		
		 String sql="select * from gr7_questions where gq_id=102"; 

		 return (Questions)jdbcTemplate.queryForObject(sql, Questions.class); 
		
	}
}

//search account number before this

/*public int register(InternetBankingUser ibu)
	{
		int i =0;
		String register="insert into g13_internet_banking_users values('"+ibu.getUser_id()+"','"+ibu.getLogin_password()+"','"+ibu.getTransaction_password()+"','"+ibu.getAttempts()+"','"+ibu.getStatus()+"','"+ibu.getSecurity_questions()+"','"+ibu.getSecurity_answers()+"','"+ibu.getAccount_number()+"')";

	//	i= jdbcTemplate.update(register);



		return i;

	}
	/*
	public List<RechargeForm> getAllTransaction(RechargeForm rf){  
		 String sql="select * from Transactions where mobile=?"; 

		 return jdbcTemplate.query(sql, new Object[] {rf.getMobileNo()}, new RowMapper<RechargeForm>(){  
			    public RechargeForm mapRow(ResultSet rs, int rownumber) throws SQLException {  
			        RechargeForm e=new RechargeForm();  
			      //  e.setMobileNo(rs.getInt(2)); 
			        e.setMobileNo(rs.getLong(2));
                     e.setAmount(rs.getInt(3));
                     e.setOperator(rs.getString(4));


			        System.out.println(e.getAmount());
			        return e;  
			    }  
			    }); 		 

	 }  

	public User checkBalance(RechargeForm rf){  
		System.out.println("inside dao"+rf.getUserId());
		 String sql="select * from users where users_pk="+rf.getUserId(); 

		 return (User)jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(User.class) ); 	 

	 }

	public int updateBalance(int balance, RechargeForm rf){  
		System.out.println("inside update balance "+balance +" "+rf.getUserId());
	    String sql="update Users set balance='"+balance+"' where users_pk="+rf.getUserId()+"";  
	    return jdbcTemplate.update(sql);  
	}  

 */
