package com.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.beans.Cls;
import com.beans.DBUtils;
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
		try {
			Connection connection = DBUtils.getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection
						.prepareStatement(Queries.get_subjects_list);
				preparedStatement.setString(1, classId);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Subject sub = new Subject();

					sub.setId(resultSet.getString(1));
					
					sub.setName(resultSet.getString(2));
					subjectsList.add(sub);
				}
				resultSet.close();
				preparedStatement.close();
			}
		} catch (Exception e) {

		}finally{
			
		}
		return subjectsList;
	}
	
	
	
}
