package com.hackathon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hackathon.dao.RegistrationDAO;
import com.hackathon.model.Address;
import com.hackathon.model.Questions;
import com.hackathon.model.User;




@Controller

public class RegistrationController
{
	@Autowired
	RegistrationDAO edao;

	@RequestMapping("/save")
	public ModelAndView userRegister(ModelAndView model, @ModelAttribute User user, Address addr)
	{

		int i = edao.saveData(user, addr);

		model.setViewName("welcome");

		return model;

	}

	@RequestMapping("/userLogin")
	public ModelAndView userLogin(ModelAndView model, @ModelAttribute User user) {

		boolean valid = edao.validateUser(user);
		if(valid) {
			if(user.getEmail().equals("Admin123@gmail.com") && user.getPassword().equals("1234")) {
				model.setViewName("AdminHome");
				return model;
			}
			
			else {
				model.setViewName("UserProfile");
				return model;
			}
			
			
		}
		else {
			
			
			model.setViewName("UserLogin");
			return model;
		}
	}

	int listcount=0;
	int count=0;
	@RequestMapping("/startExam")
	public ModelAndView startExam(HttpServletRequest req, HttpServletResponse res, ModelAndView model) {
		
		List<Map<String, Object>> qnlist = edao.getQuestion();
		
		HttpSession ses = req.getSession(true);
			ses = req.getSession(true);
			ses.setAttribute("counter", count);
			Questions q = new Questions();
			q.setQuestion((String)qnlist.get(count).get("gq_question"));
			q.setOp1((String)qnlist.get(count).get("gq_op1"));
			q.setOp2((String)qnlist.get(count).get("gq_op2"));
			q.setOp3((String)qnlist.get(count).get("gq_op3"));
			q.setOp4((String)qnlist.get(count).get("gq_op4"));
			model.addObject("questionData", q);
			model.setViewName("ExamPage");
			return model;
	}

	@RequestMapping("/nextQn")
	public ModelAndView nextQn(HttpServletRequest req, HttpServletResponse res, ModelAndView model) {
		List<Map<String, Object>> qnlist = edao.getQuestion();
		System.out.println("in nextQn");
		System.out.println();
		HttpSession ses = req.getSession(false);
		int count = (Integer)ses.getAttribute("counter");
		if(listcount<=qnlist.size()) {
			String option = req.getParameter("op");
			System.out.println(option);
			for(int i=1; i<=4; i++) {
				
				System.out.println("gq_op"+i);
				System.out.println(count);
				
				System.out.println((String)qnlist.get(count).get("gq_op"+i+"Ans"));
				if(option.equals((String)qnlist.get(count).get("gq_op"+i+"Ans"))) {
					System.out.println("yay");
				}
			}
			
			
			System.out.println(count);
			Questions q = new Questions();
			q.setQuestion((String)qnlist.get(count).get("gq_question"));
			q.setOp1((String)qnlist.get(count).get("gq_op1"));
			q.setOp2((String)qnlist.get(count).get("gq_op2"));
			q.setOp3((String)qnlist.get(count).get("gq_op3"));
			q.setOp4((String)qnlist.get(count).get("gq_op4"));
			listcount++;
			count++;
			ses.setAttribute("counter", count);
			model.addObject("questionData", q);
			model.setViewName("ExamPage");
		}
		else {
		           	
		}
		return model;
	}
	
//	/*@RequestMapping("/upload")
//	public ModelAndView uploadExcel(HttpServletRequest req, HttpServletResponse res, ModelAndView model) throws IOException {
//		try {
//			Class.forName ("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e4) {
//			// TODO Auto-generated catch block
//			e4.printStackTrace();
//		} 
//        Connection conn = null;
//		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.100.198:1521/xe", "system", "Newuser123");
//		} catch (SQLException e3) {
//			// TODO Auto-generated catch block
//			e3.printStackTrace();
//		}
//		PreparedStatement sql_statement=null;
//        String jdbc_insert_sql = "INSERT INTO GR7_QUESTIONS"+ " VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        try {
//			sql_statement = conn.prepareStatement(jdbc_insert_sql);
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		 
//        
//        // We should now load excel objects and loop through the worksheet data 
//	
//	
//    	FileInputStream 	input_document = new FileInputStream(new File("C:\\Users\\AE103_PC19\\Desktop\\demo.xlsx"));
//        // Load workbook 
//		XSSFWorkbook my_xls_workbook = new XSSFWorkbook(input_document);
//        /* Load worksheet */
//		XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
//        // we loop through and insert data
//        Iterator<Row> rowIterator = my_worksheet.rowIterator();
//     
//        while(rowIterator.hasNext()) {
//                Row row = rowIterator.next(); 
//                Iterator<Cell> cellIterator = row.cellIterator();
//                
//                        while(cellIterator.hasNext()) {
//                        	
//                                Cell cell = cellIterator.next();
//                                String s=String.valueOf(cell.getCellType());
//                                if(s.equals("NUMERIC") && (cell.getColumnIndex()==0)) { 
//                                	   //System.out.println("lol");
//                                	try {
//                                		sql_statement.setDouble(1, cell.getNumericCellValue());
//										
//										
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}                                                                                     
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==1)){
//                                	try {
//                                		sql_statement.setString(2, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("NUMERIC".equals(s) && (cell.getColumnIndex()==2)){
//                                	try {
//                                		sql_statement.setDouble(3, cell.getNumericCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==3)){
//                                	try {
//                                		sql_statement.setString(4, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==4)){
//                                	try {
//                                		sql_statement.setString(5, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==5)){
//                                	try {
//                                		sql_statement.setString(6, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==6)){
//                                	try {
//                                		sql_statement.setString(7, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==7)){
//                                	try {
//                                		sql_statement.setString(8, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==8)){
//                                	try {
//                                		sql_statement.setString(9, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==9)){
//                                	try {
//                                		sql_statement.setString(10, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==10)){
//                                	try {
//                                		sql_statement.setString(11, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==11)){
//                                	try {
//                                		sql_statement.setString(12, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                else if("STRING".equals(s) && (cell.getColumnIndex()==12)){
//                                	try {
//                                		sql_statement.setString(13, cell.getStringCellValue());
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//                                }
//                                
//                        }
//                        try {
//							sql_statement.executeUpdate();
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                }
//        /* Close input stream */
//        input_document.close();
//        
//        try {
//        	/* Close prepared statement */
//			sql_statement.close();
//			/* COMMIT transaction */
//	        conn.commit();
//	        /* Close connection */
//	        conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        model.setViewName("Practice");
//		return model;
//		
//	}
}
