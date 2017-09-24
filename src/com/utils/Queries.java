package com.utils;

public class Queries {

	protected static String get_classes_list = "SELECT * FROM CLS";
	
	protected static String get_subjects_list = "SELECT ID, SUBJ_NAME FROM SUBJ_TBL WHERE CLS_ID = ?";
	
	protected static String get_subject = "SELECT ID, SUBJ_NAME FROM SUBJ_TBL WHERE ID = ?";
	
	protected static String add_question = "insert into question (ID,CLS_ID,SUBJ_ID,NAME,DETAILS,MARKS) values (?,?,?,?,?,?)";
	
	protected static String get_max_question_id = "SELECT MAX(ID) FROM QUESTION";
			
	protected static String get_questions_list = "SELECT id,cls_id,subj_id,name,details,marks FROM QUESTION WHERE CLS_ID = ? AND SUBJ_ID = ?";
	
	protected static String get_question = "select id,cls_id,subj_id,name,details,marks from question where CLS_ID = ? and subj_id = ? and id = ?";

}
