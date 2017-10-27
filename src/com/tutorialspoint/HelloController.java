package com.tutorialspoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.beans.QuestionPaper;
import com.beans.Subject;
import com.google.gson.Gson;
import com.utils.QuestionPaperUtility;
import com.utils.StudentsUtility;
import com.utils.Utility;
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
	    
	 
	  ModelAndView model = new ModelAndView("addQuestion", "command", new Question());
	  model.addObject("clsList", clsList);
	   return model;
   }
   
   
   @RequestMapping(value = "/classSubjectAction")
   public ModelAndView classSubjectAction(@Validated QuestionPaper questionPaper,ModelMap map) {
	   
	  List<Cls> clsList = StudentsUtility.getClassesList();
	  map.addAttribute("clsList", clsList);
	  ModelAndView model = new ModelAndView("selectClassAndSubject", "command", new QuestionPaper());
	  
	  return model; 
   }
   
   @RequestMapping(value = "/addNewQuestionsAction")
   public ModelAndView addNewQuestionsAction(@Validated QuestionPaper questionPaper,ModelMap map) {
	   
	System.out.println("inside addQuestionsAction"+questionPaper.getClassId());
	List<Question> questionsList = StudentsUtility.getQuestionsForQuestionPaper(questionPaper.getClassId(), questionPaper.getSubjectId());
	questionPaper.setQuestionsList(questionsList);
	ModelAndView model = new ModelAndView("questionPaper", "command", questionPaper);
	
	return model; 
   }
   
   @RequestMapping(value = "/prepareQuestionPaperAction")
	public ModelAndView prepareQuestionPaperAction(
			@Validated QuestionPaper questionPaper, ModelMap map,
			HttpServletRequest request) {

		if (!Utility.isEmpty(request.getParameter("marks1"))
				&& !Utility.isEmpty(request.getParameter("quantity1"))) {
			int marks1 = Integer.parseInt(request.getParameter("marks1"));
			int quantity1 = Integer.parseInt(request.getParameter("quantity1"));
			questionPaper.setMarks1(marks1);
			questionPaper.setQuantity1(quantity1);
		}
		
		if (!Utility.isEmpty(request.getParameter("marks2"))
				&& !Utility.isEmpty(request.getParameter("quantity2"))) {
			int marks2 = Integer.parseInt(request.getParameter("marks2"));
			int quantity2 = Integer.parseInt(request.getParameter("quantity2"));
			questionPaper.setMarks2(marks2);
			questionPaper.setQuantity2(quantity2);
		}
		
		if (!Utility.isEmpty(request.getParameter("marks3"))
				&& !Utility.isEmpty(request.getParameter("quantity3"))) {
			int marks3 = Integer.parseInt(request.getParameter("marks3"));
			int quantity3 = Integer.parseInt(request.getParameter("quantity3"));
			questionPaper.setMarks3(marks3);
			questionPaper.setQuantity3(quantity3);
		}
		
		if (!Utility.isEmpty(request.getParameter("marks4"))
				&& !Utility.isEmpty(request.getParameter("quantity4"))) {
			int marks4 = Integer.parseInt(request.getParameter("marks4"));
			int quantity4 = Integer.parseInt(request.getParameter("quantity4"));
			questionPaper.setMarks4(marks4);
			questionPaper.setQuantity4(quantity4);
		}
		
		
		
		
		boolean questionsExist = true;
		Map<String,String> errorMessage = new HashMap<String,String>();
		questionsExist = StudentsUtility.validateAllMarksQuantity(questionPaper,errorMessage);	
		ModelAndView model = null;
		
		if(!questionsExist){
		
		model = new ModelAndView("notEnoughQuestions", "command",
					"");
		model.addObject("message", errorMessage.get("message")+ " for class "+questionPaper.getClassId()+ " and subject "+questionPaper.getSubjectName());
		
		}else{
		
		List<Question> questionsList = StudentsUtility
				.getQuestionsForQuestionPaper(questionPaper.getClassId(),
						questionPaper.getSubjectId());
		questionPaper.setQuestionsList(questionsList);
		model = new ModelAndView("question", "command",
				questionPaper);
		}
		return model;
	}
   
   @RequestMapping(value = "/addQuestionToPaperAction" , method=RequestMethod.POST)
   public ModelAndView addQuestionToPaperAction(@Validated QuestionPaper questionPaper,HttpServletRequest request) {
	   
	   String classId  = request.getParameter("classId");
	   String subjectId  = request.getParameter("subjectId");
	   String questionId  = request.getParameter("questionId");
	   Question question = StudentsUtility.getQuestion(classId, subjectId, questionId);
	   if(questionPaper.getQuestionsSelected()==null){
		   List<Question> selectedQuestions = new ArrayList<Question>();
		   selectedQuestions.add(question);
		   questionPaper.setQuestionsSelected(selectedQuestions);
	   }else{
		   questionPaper.getQuestionsSelected().add(question);
	   }
	   List<Question> quList = StudentsUtility.getQuestionsForQuestionPaper(classId, subjectId);
	   questionPaper.setQuestionsList(quList);
	   StudentsUtility.questionsList.add(question);
	   ModelAndView model = new ModelAndView("questionPaper", "command", questionPaper);
	
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
  
   
  
   @RequestMapping(value = "/getQuestionDetails" , method=RequestMethod.GET)   
   public @ResponseBody String getQuestionDetails(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   String classId  = request.getParameter("classId");
	   String subjectId  = request.getParameter("subjectId");
	   String questionId  = request.getParameter("questionId");
	   Question question = StudentsUtility.getQuestion(classId, subjectId, questionId);
	   String jsonString =  "";
	  try{
		  Gson gson = new Gson();

		  jsonString = gson.toJson(question);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	   return jsonString;
   }
   
   
   @RequestMapping(value = "/getSubjects" , method=RequestMethod.GET)   
   public @ResponseBody String getSubjects(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
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
   
   @RequestMapping(value = "/getSubjectDetails" , method=RequestMethod.GET)   
   public @ResponseBody String getSubjectDetails(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   String subjectId  = request.getParameter("subjectId");
	   System.out.println("inside getSubjects ajax subjectId "+subjectId);
	   Subject subjects = StudentsUtility.getSubject(subjectId);
	  /* JsonResponse res = new JsonResponse();*/

	  
	   response.setStatus(200);
	   String jsonString =  "";
	   Gson gson = new Gson();

		  jsonString = gson.toJson(subjects);
	   return jsonString;
   }
   

}
