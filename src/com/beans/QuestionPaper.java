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


	
}
