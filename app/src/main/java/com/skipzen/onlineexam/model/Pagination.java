package com.skipzen.onlineexam.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Pagination{

	@SerializedName("total_data")
	private int totalData;

	@SerializedName("links")
	private List<String> links;

	@SerializedName("item_per_page")
	private int itemPerPage;

	public void setTotalData(int totalData){
		this.totalData = totalData;
	}

	public int getTotalData(){
		return totalData;
	}

	public void setLinks(List<String> links){
		this.links = links;
	}

	public List<String> getLinks(){
		return links;
	}

	public void setItemPerPage(int itemPerPage){
		this.itemPerPage = itemPerPage;
	}

	public int getItemPerPage(){
		return itemPerPage;
	}

	@Override
 	public String toString(){
		return 
			"Pagination{" + 
			"total_data = '" + totalData + '\'' + 
			",links = '" + links + '\'' + 
			",item_per_page = '" + itemPerPage + '\'' + 
			"}";
		}
}