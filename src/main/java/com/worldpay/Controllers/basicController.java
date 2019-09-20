package com.worldpay.Controllers;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.worldpay.Models.Student;

import com.worldpay.utility.Util;

@Controller
public class basicController {

	SessionFactory sessionFactory = Util.getSF();
	Session session = sessionFactory.openSession();

	@RequestMapping("showDetails")
	public ModelAndView showdetails(@ModelAttribute("save") Student student) {

		Transaction tr = session.beginTransaction();
		session.save(student);
		ModelAndView modelAndView = new ModelAndView("show.jsp");
		modelAndView.addObject("student", student);
		modelAndView.addObject("message","Successfully Saved..!!!");
		tr.commit();
		return modelAndView;
	}

	@RequestMapping("search")
	public String Search() {
		return "searchPage.jsp";
	}

	@RequestMapping("remove")
	public String Remove() {
		return "removeDetails.jsp";
	}

	@RequestMapping("removeDetails")
	public ModelAndView removeStudent(@RequestParam("rno") int rno) {
		ModelAndView modelAndView = new ModelAndView("show.jsp");
		ModelAndView mav = modelAndView;
		Student student = session.get(Student.class, rno);
		modelAndView.addObject("student", student);
		mav.addObject("message","Successfully Deleted..!!!");
		Transaction tr = session.beginTransaction();
			session.delete(student);
			tr.commit();
		
	    return mav;
	}

	@RequestMapping("searchPage")
	public ModelAndView searchDeatils(@RequestParam("rno") int rno) {
	
		Student student = session.get(Student.class, rno);
		ModelAndView modelAndView = new ModelAndView("Fetch.jsp");
        modelAndView.addObject("student", student);
		return modelAndView;

	}

	@RequestMapping("allstudent")
	public ModelAndView getAllStudents() {

		Criteria cr = session.createCriteria(Student.class);
		List<Student> students = cr.list();

		ModelAndView mv = new ModelAndView("viewall.jsp");
		mv.addObject("students", students);
		return mv;

	}

	@RequestMapping("inputpage")
	public String showInputForm() {
		return "dataEntry.jsp";
	}

}
