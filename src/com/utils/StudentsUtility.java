package com.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.beans.Cls;
import com.beans.DBUtils;
import com.beans.Question;
import com.beans.Subject;

import org.springframework.*;
import org.springframework.stereotype.Component;

public class StudentsUtility {

	static Connection   connection = null;
	
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
	
	
}
