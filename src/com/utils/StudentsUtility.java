package com.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.beans.Cls;
import com.beans.DBUtils;
import com.beans.Question;
import com.beans.QuestionPaper;
import com.beans.Subject;

import org.springframework.*;
import org.springframework.stereotype.Component;

public class StudentsUtility {

    public static Connection   connection = null;
	
    public static List<Question> questionsList = new ArrayList<Question>();
    
	public static List<Cls> getClassesList() {
		List<Cls> classesList = new ArrayList<Cls>();
		try {
			connection = DBUtils.getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection
						.prepareStatement(Queries.get_classes_list);
				ResultSet resultSet = preparedStatement.executeQuery(); 
				while (resultSet.next()) {
					Cls cls = new Cls();
					cls.setId(resultSet.getString(1));
					cls.setName(resultSet.getString(2));
					classesList.add(cls);
				}
				resultSet.close();
				preparedStatement.close();
			}
		} catch (Exception e) {

		}finally{
			try{
			
			if(connection!=null){
				connection.close();
			}
			}catch(Exception e){
				
			}
		}
		return classesList;
	}
	
	public static List<Subject> getSubjectsList(String classId) {
		List<Subject> subjectsList = new ArrayList<Subject>();
		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement(Queries.get_subjects_list);
				preparedStatement.setString(1, classId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Subject sub = new Subject();

					sub.setId(resultSet.getString(1));
					
					sub.setName(resultSet.getString(2));
					subjectsList.add(sub);
				}
				
			}
		} catch (Exception e) {

		}finally{
			closeResources(connection, resultSet, preparedStatement);
		}
		return subjectsList;
	}
	
	public static Subject getSubject(String subjectId) {
		Subject subject = new Subject();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement= null;
		try {
			connection = DBUtils.getConnection();
			
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement(Queries.get_subject);
				preparedStatement.setString(1, subjectId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					subject.setId(resultSet.getString(1));
				    subject.setName(resultSet.getString(2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeResources(connection, resultSet, preparedStatement);
		}
		return subject;
	}
	
	
	public static int addQuestion(Question question){
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		int noOfRowsAdded = 0 ;
		try{
			String questionId = question.getId()+"";
			String classId = question.getClassId();
			String subjectId = question.getSubjectId();
			String name = question.getName();
			String details = question.getDetails();
			String marksString =  question.getMarks();
			int marks = 0; 
			if(!Utility.isEmpty(marksString)){
				marks = Integer.parseInt(marksString);
			}
			int maxId = getMaxQuestionId();
			
			connection =DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(Queries.add_question);
			preparedStatement.setInt(1, maxId+1);
			preparedStatement.setString(2, question.getClassId());
			preparedStatement.setString(3, question.getSubjectId());
			preparedStatement.setString(4, question.getName());
			preparedStatement.setString(5, question.getDetails());
			preparedStatement.setInt(6,marks);
			
			noOfRowsAdded = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeResources(connection, null, preparedStatement);
		}
		return noOfRowsAdded;
		
	}
	
    public static int getMaxQuestionId(){
		int maxId = 0 ;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String query = Queries.get_max_question_id;
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String maxIdStr = resultSet.getString(1);
				maxId = resultSet.getInt(1);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeResources(connection, resultSet, preparedStatement);
		}
		return maxId;
		
	}
    
    
    public static List<Question> getQuestionsForQuestionPaper(String classId,String subjectId){
    	List<Question> list = new ArrayList<Question>();
    	Connection connection = DBUtils.getConnection();
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	String query = Queries.get_questions_list;
    	try{
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setString(1, classId);
    	preparedStatement.setString(2, subjectId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
		Question question = new Question();
		populateQuestion(question, resultSet);
		list.add(question);
		}
		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		closeResources(connection, resultSet, preparedStatement);
    	}
    	return list;
    }
    	
    
    public static void closeResources(Connection connection, ResultSet resultSet,PreparedStatement preparedStatement){
    	try{
    		if(resultSet!=null){
    			resultSet.close();
    		}
    		if(preparedStatement!=null){
    			preparedStatement.close();
    		}
    		if(connection!=null){
    			connection.close();
    		}
    		
    	}catch(Exception e){
    		
    	}
    }
	
	public static Question getQuestion(String classId,String subjectId,String questionId){
		Connection connection = DBUtils.getConnection();
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	String query = Queries.get_question;
    	Question question = new Question();
    	try{
    		preparedStatement = connection.prepareStatement(query);
    		preparedStatement.setString(1, classId);
    		preparedStatement.setString(2, subjectId);
    		preparedStatement.setInt(3, Integer.parseInt(questionId));
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()){
    			populateQuestion(question, resultSet);
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return question;
    	
	}
	
	public static void populateQuestion(Question question, ResultSet resultSet) throws SQLException{
		
		question.setId(Integer.parseInt(resultSet.getString(1)));
		question.setClassId(resultSet.getString(2));
		question.setSubjectId(resultSet.getString(3));
		question.setName(resultSet.getString(4));
		question.setDetails(resultSet.getString(5));
		question.setMarks(resultSet.getString(6));
	}
	
	public static int getTotalNoOfQuestions(int marks,int quantity,int subjectId,int classId){
		int count = 0 ;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String query = Queries.count_questions;
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, classId);
			preparedStatement.setInt(2, subjectId);
			preparedStatement.setInt(3, marks);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeResources(connection, resultSet, preparedStatement);
		}
		return count;
		
	}
    
	public static boolean areSufficientQuestionsAvailable(QuestionPaper questionPaper){
		boolean areSufficientQuestiosAvailable = true;
		return areSufficientQuestiosAvailable;
		
	}
	
	
	
	public static boolean validateAllMarksQuantity(QuestionPaper questionPaper,Map<String,String> errorMessage){
		boolean isValid = true ;
		if (questionPaper.getMarks1() != 0 && questionPaper.getQuantity1() != 0) {
			int count = getTotalNoOfQuestions(questionPaper.getMarks1(),
					questionPaper.getQuantity1(),
					Integer.parseInt(questionPaper.getSubjectId()),
					Integer.parseInt(questionPaper.getClassId() + ""));
			if (count <= questionPaper.getQuantity1()) {
				isValid = false;
				errorMessage.put("message", "There are not enough questions of marks "+questionPaper.getMarks1());
			}
		}
		
		if (isValid && questionPaper.getMarks2() != 0 && questionPaper.getQuantity2() != 0) {
			int count = getTotalNoOfQuestions(questionPaper.getMarks2(),
					questionPaper.getQuantity2(),
					Integer.parseInt(questionPaper.getSubjectId()),
					Integer.parseInt(questionPaper.getClassId() + ""));
			if (count <= questionPaper.getQuantity2()) {
				isValid = false;
				errorMessage.put("message", "There are not enough questions of marks "+questionPaper.getMarks2());
			}
		}
		
		
		if (isValid && questionPaper.getMarks3() != 0 && questionPaper.getQuantity3() != 0) {
			int count = getTotalNoOfQuestions(questionPaper.getMarks3(),
					questionPaper.getQuantity3(),
					Integer.parseInt(questionPaper.getSubjectId()),
					Integer.parseInt(questionPaper.getClassId() + ""));
			if (count <= questionPaper.getQuantity3()) {
				isValid = false;
				errorMessage.put("message", "There are not enough questions of marks "+questionPaper.getMarks3());
			}
		}
		
		
		if (isValid && questionPaper.getMarks4() != 0 && questionPaper.getQuantity4() != 0) {
			int count = getTotalNoOfQuestions(questionPaper.getMarks4(),
					questionPaper.getQuantity4(),
					Integer.parseInt(questionPaper.getSubjectId()),
					Integer.parseInt(questionPaper.getClassId() + ""));
			if (count <= questionPaper.getQuantity4()) {
				isValid = false;
				errorMessage.put("message", "There are not enough questions of marks "+questionPaper.getMarks4());	
			}
		}
		return isValid;
	}
	
	
	
}
