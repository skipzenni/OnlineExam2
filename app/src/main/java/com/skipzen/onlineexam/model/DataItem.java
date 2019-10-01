package com.skipzen.onlineexam.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("question_text")
	private String questionText;

	@SerializedName("question_type")
	private String questionType;

	@SerializedName("question_image")
	private Object questionImage;

	@SerializedName("answers")
	private List<AnswersItem> answers;

	@SerializedName("question_id")
	private String questionId;

	@SerializedName("question_number")
	private int questionNumber;

	public void setQuestionText(String questionText){
		this.questionText = questionText;
	}

	public String getQuestionText(){
		return questionText;
	}

	public void setQuestionType(String questionType){
		this.questionType = questionType;
	}

	public String getQuestionType(){
		return questionType;
	}

	public void setQuestionImage(Object questionImage){
		this.questionImage = questionImage;
	}

	public Object getQuestionImage(){
		return questionImage;
	}

	public void setAnswers(List<AnswersItem> answers){
		this.answers = answers;
	}

	public List<AnswersItem> getAnswers(){
		return answers;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setQuestionNumber(int questionNumber){
		this.questionNumber = questionNumber;
	}

	public int getQuestionNumber(){
		return questionNumber;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"question_text = '" + questionText + '\'' + 
			",question_type = '" + questionType + '\'' + 
			",question_image = '" + questionImage + '\'' + 
			",answers = '" + answers + '\'' + 
			",question_id = '" + questionId + '\'' + 
			",question_number = '" + questionNumber + '\'' + 
			"}";
		}
}