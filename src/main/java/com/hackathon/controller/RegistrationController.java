package com.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
		List<Map<String, Object>> qnlist = edao.getQuestion();
		for(Map<String, Object> qn: qnlist) {
			Questions q = new Questions();
			q.setQuestion((String)qn.get("gq_question"));
			q.setOp1((String)qn.get("gq_op1"));
			q.setOp2((String)qn.get("gq_op2"));
			q.setOp3((String)qn.get("gq_op3"));
			q.setOp4((String)qn.get("gq_op4"));
			System.out.println(q);
			model.addObject("questionData", q);
		}
		System.out.println(qnlist);
		//System.out.println(q);
		//model.addObject("questionData", q);
		model.setViewName("ExamPage");
		return model;
	}
	
	@RequestMapping("/nextQn")
	public ModelAndView nextQn(ModelAndView model, @ModelAttribute Questions option) {
		return model;
	}
}
