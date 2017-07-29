package com.utils;

public class Queries {

	protected static String get_classes_list = "SELECT * FROM CLS";
	
	protected static String get_subjects_list = "SELECT ID, SUBJ_NAME FROM SUBJ_TBL WHERE CLS_ID = ?";
}
