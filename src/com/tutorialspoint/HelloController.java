package com.tutorialspoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;

import com.beans.Question;
@Controller

public class HelloController {
   @RequestMapping(method = RequestMethod.GET)public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
   
   @RequestMapping("/") 
   public String login() {
	   System.out.println("inside login controller");
     /// model.addAttribute("message", "Hello Spring MVC Framework!");
      return "login";
   }
   
   @RequestMapping(value = "/loginAction")
   public String loginAction() {
	   System.out.println("inside login Action controller");
     
      return "studentHome";
   }
   
   
   @RequestMapping(value = "/addQuestionAction")
   public ModelAndView addQuestionAction(@Validated Question question,ModelMap map) {
	   System.out.println("inside add question");
	  /*List<Cls> clsList = StudentsUtility.getClassesList();
	  List<Subject> subjectsList = StudentsUtility.getSubjectsList();*/
	  ModelAndView model = new ModelAndView("addQuestion", "command", new Question());
	 // model.addObject("clsList", clsList);
	   return model;
   }
}
