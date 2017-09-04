package com.tutorialspoint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.beans.Cls;
import com.beans.Question;
import com.beans.Subject;
import com.utils.StudentsUtility;
@Controller

public class HelloController {
   @RequestMapping(method = RequestMethod.GET)public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
   
   @RequestMapping("/") 
   public String login() {
	   System.out.println("inside login controller");
     
      return "login";
   }
   
   @RequestMapping(value = "/loginAction")
   public String loginAction(ModelMap map) {
	   System.out.println("inside login Action controller");
	   
      return "studentHome";
   }
   
   
   @RequestMapping(value = "/addQuestionAction")
   public ModelAndView addQuestionAction(@Validated Question question,ModelMap map) {
	   System.out.println("inside add question");
	   List<Cls> clsList = StudentsUtility.getClassesList();
	   
	  /*;
	  List<Subject> subjectsList = StudentsUtility.getSubjectsList();*/
	  ModelAndView model = new ModelAndView("addQuestion", "command", new Question());
	  model.addObject("clsList", clsList);
	   return model;
   }
   
   
   @RequestMapping(value = "/addQuestionDtlsAction" , method=RequestMethod.POST)
   public String addQuestionDtlsAction(@Validated Question question,ModelMap map,HttpServletRequest request) {
	  String subjectId = request.getParameter("classId");
	  question.setSubjectId(subjectId);
	 
	int noOfRowsAdded =  StudentsUtility.addQuestion(question);
	if(noOfRowsAdded==0){
		map.put("message", "Question could not be added");
	}else{
	    map.put("message", "Question added successfully");
	}
	   return "questionAdded";
   }
  
   @RequestMapping(value = "/getSubjects" , method=RequestMethod.GET)   
   
   public @ResponseBody String getSubjects(ModelMap model, HttpServletRequest request, HttpServletResponse response
		   ) {
	   String classId  = request.getParameter("classId");
	   System.out.println("inside getSubjects ajax className "+classId);
	   List<Subject> subjects = StudentsUtility.getSubjectsList(classId);
	  /* JsonResponse res = new JsonResponse();*/

	   model.put("subjects",subjects);
	   response.setStatus(200);
	   String jsonString =  "";
	   try {
		   jsonString = JSONObject.valueToString(subjects);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return jsonString;
   }
   
   
  /* @RequestMapping(value = "/getSubjects" , method=RequestMethod.POST)
   public String getSubjects(@Validated Question question,ModelMap map,HttpServletRequest request) {
	  System.out.println("inside getSubjects");
	  return "";
   }*/
}
