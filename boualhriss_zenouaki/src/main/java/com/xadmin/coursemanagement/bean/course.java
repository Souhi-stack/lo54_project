package com.xadmin.coursemanagement.bean;

public class course {

	private int code; 
	private String title ;
	
	
	public course(String title) {
		super();
		this.title = title;
	}
	public course(int code, String title) {
		super();
		this.code = code;
		this.title = title;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	} 

}
