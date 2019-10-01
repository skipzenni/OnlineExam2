package com.skipzen.onlineexam.model;

import com.google.gson.annotations.SerializedName;

public class AnswersItem{

	@SerializedName("answer_text")
	private String answerText;

	@SerializedName("answer_image")
	private Object answerImage;

	@SerializedName("answer_id")
	private int answerId;

	public void setAnswerText(String answerText){
		this.answerText = answerText;
	}

	public String getAnswerText(){
		return answerText;
	}

	public void setAnswerImage(Object answerImage){
		this.answerImage = answerImage;
	}

	public Object getAnswerImage(){
		return answerImage;
	}

	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}

	public int getAnswerId(){
		return answerId;
	}

	@Override
 	public String toString(){
		return 
			"AnswersItem{" + 
			"answer_text = '" + answerText + '\'' + 
			",answer_image = '" + answerImage + '\'' + 
			",answer_id = '" + answerId + '\'' + 
			"}";
		}
}