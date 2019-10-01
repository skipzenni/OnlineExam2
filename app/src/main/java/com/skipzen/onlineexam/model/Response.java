package com.skipzen.onlineexam.model;

import com.google.gson.annotations.SerializedName;
import com.skipzen.onlineexam.model.Data;
import com.skipzen.onlineexam.model.Meta;

public class Response{

	@SerializedName("data")
	private Data data;

	@SerializedName("meta")
	private Meta meta;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}

}