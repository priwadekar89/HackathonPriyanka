package com.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hackathon.dao.RegistrationDAO;
import com.hackathon.model.*;

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
			model.setViewName("UserProfile");
			return model;
		}
		else {
			model.setViewName("UserLogin");
			return model;
		}
	}
	
	@RequestMapping("/startExam")
	public ModelAndView startExam(ModelAndView model) {
		Questions q = edao.getQuestion();
		System.out.println(q);
		model.addObject("questionData", q);
		model.setViewName("ExamPage");
		return model;
	}

	/*
	@RequestMapping("/checkBal")	
	public ModelAndView Check(HttpServletRequest request,HttpServletResponse response, @ModelAttribute RechargeForm rf) throws ServerException,IOException
	{

	//	RechargeForm r  = rf;
	System.out.println("inside check "+rf.getAmount()+" "+rf.getUserId());
		User u= edao.checkBalance(rf);
		//System.out.println("checked bal"+u.getBalance()+" "+rf.getAmount());
		if(u.getBalance()>rf.getAmount())
		{
			HttpSession ses = request.getSession();
			ses.setAttribute("obj",rf);
			ses.setAttribute("user", u);

			return new ModelAndView("confirm");




			//System.out.println("save");
		}
		else
		{
			return new ModelAndView("error");

		}
	}


	@RequestMapping("/rechargeNow")
	public ModelAndView Save(HttpServletRequest request,HttpServletResponse response )throws ServerException,IOException
	{
		System.out.println("test2....");

		HttpSession ses = request.getSession();
		RechargeForm rf = (RechargeForm)ses.getAttribute("obj");
		User u = (User) ses.getAttribute("user");


		int j = edao.updateBalance(u.getBalance()-rf.getAmount(), rf);

		System.out.println("got user session"+u.getBalance());
		if(j>0)
		{


		}
		else
		{
			return new ModelAndView("recharge");
		}



		int i = edao.saveTransaction(rf);

		if(i>0)
		{
			return new ModelAndView("sucess");
		}
		else
		{
			System.out.println("not save..");
		}
		return new ModelAndView("index");
	}

	@RequestMapping("/display")
	public ModelAndView DisplayAll(HttpServletRequest request, HttpServletResponse response, @ModelAttribute RechargeForm rf) throws ServerException,IOException, ServletException
	{
		System.out.println("test in display");
		List<RechargeForm> lst = new ArrayList<RechargeForm>();
		lst = edao.getAllTransaction(rf);
		request.setAttribute("transactionList",lst);
		HttpSession session = request.getSession();
		session.setAttribute("transactionList",lst);

		HttpSession ses = request.getSession();
		ses.setAttribute("transactionList", lst);	

		return new ModelAndView("display");
	}
	 */
	/*
		@RequestMapping("/viewemp")  
	    public ModelAndView viewemp(){  
	        List<Employee> list=edao.getAllEmployees();  
	        return new ModelAndView("viewemp","list",list);  
	    }  

		@RequestMapping(value="/editemp/{fname}")  
	    public ModelAndView edit(@PathVariable String fname){  
	        Employee emp=edao.getEmpByFname(fname);  
	        return new ModelAndView("empeditform","command",emp);  
	    }  

	  @RequestMapping(value="/editsave",method = RequestMethod.POST)  
	    public ModelAndView editsave(@ModelAttribute("emp") Employee emp){  
	        edao.update(emp);  
	        return new ModelAndView("redirect:/viewemp");  
	    }  

	  @RequestMapping(value="/deleteemp/{fname}",method = RequestMethod.GET)  
	    public ModelAndView delete(@PathVariable String fname){  
	        edao.delete(fname);  
	        return new ModelAndView("redirect:/viewemp");  
	    }  */


}
