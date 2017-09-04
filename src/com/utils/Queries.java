package com.utils;

public class Queries {

	protected static String get_classes_list = "SELECT * FROM CLS";
	
	protected static String get_subjects_list = "SELECT ID, SUBJ_NAME FROM SUBJ_TBL WHERE CLS_ID = ?";
	
	protected static String add_question = "insert into question (ID,CLS_ID,SUBJ_ID,NAME,DETAILS,MARKS) values (?,?,?,?,?,?)";
	
	protected static String get_max_question_id = "SELECT MAX(ID) FROM QUESTION";
			
			

}
