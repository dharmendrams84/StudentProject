package com.beans;

import java.util.List;

public class QuestionPaper {

	
	private int id;
	
	
	private String classId;
	
	private String subjectId ;
	
	private int totalMarks;
	
	private int totalMarksSelected; 
	
	private int noOfQuestion;
	
	private String subjectName ;
	
	private int marks1;
	private int marks2;
	private int marks3;
	private int marks4;
	
	private int quantity1;
	private int quantity2;
	private int quantity3;
	private int quantity4;
	
	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	private List<Question> questionsSelected ;
	

	public List<Question> getQuestionsSelected() {
		return questionsSelected;
	}


	public void setQuestionsSelected(List<Question> questionsSelected) {
		this.questionsSelected = questionsSelected;
	}


	public int getNoOfQuestion() {
		return noOfQuestion;
	}


	public void setNoOfQuestion(int noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}


	public List<Question> questionsList ;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getTotalMarksSelected() {
		return totalMarksSelected;
	}


	public void setTotalMarksSelected(int totalMarksSelected) {
		this.totalMarksSelected = totalMarksSelected;
	}

	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}


	public String getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	public int getTotalMarks() {
		return totalMarks;
	}


	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}


	public List<Question> getQuestionsList() {
		return questionsList;
	}


	public void setQuestionsList(List<Question> questionsList) {
		this.questionsList = questionsList;
	}


	public int getMarks1() {
		return marks1;
	}


	public void setMarks1(int marks1) {
		this.marks1 = marks1;
	}


	public int getMarks2() {
		return marks2;
	}


	public void setMarks2(int marks2) {
		this.marks2 = marks2;
	}


	public int getMarks3() {
		return marks3;
	}


	public void setMarks3(int marks3) {
		this.marks3 = marks3;
	}


	public int getMarks4() {
		return marks4;
	}


	public void setMarks4(int marks4) {
		this.marks4 = marks4;
	}


	public int getQuantity1() {
		return quantity1;
	}


	public void setQuantity1(int quantity1) {
		this.quantity1 = quantity1;
	}


	public int getQuantity2() {
		return quantity2;
	}


	public void setQuantity2(int quantity2) {
		this.quantity2 = quantity2;
	}


	public int getQuantity3() {
		return quantity3;
	}


	public void setQuantity3(int quantity3) {
		this.quantity3 = quantity3;
	}


	public int getQuantity4() {
		return quantity4;
	}


	public void setQuantity4(int quantity4) {
		this.quantity4 = quantity4;
	}


	
}
