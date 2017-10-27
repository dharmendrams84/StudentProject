package com.utils;
import javax.servlet.http.HttpServletRequest;

import com.beans.QuestionPaper;


public class QuestionPaperUtility {

	
	public static boolean validateQuestionType(int marksValue, int quanityValue,QuestionPaper questionPaper){
		
		System.out.println(questionPaper.getClassId()+ " : "+questionPaper.getSubjectId());
		
		int totalQuestions = StudentsUtility.getTotalNoOfQuestions(marksValue, quanityValue, Integer.parseInt(questionPaper.getSubjectId()), Integer.parseInt(questionPaper.getClassId()));
		if(quanityValue>totalQuestions){
			
		return false;
		
		}
		return true;
	}
}
