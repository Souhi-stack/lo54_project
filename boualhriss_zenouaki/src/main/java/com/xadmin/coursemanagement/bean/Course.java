package com.xadmin.coursemanagement.bean;

public class Course {

	private int id ; 
	
	
	public Course(int id, String code2, String title) {
		super();
		this.id = id;
		this.code = code2;
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String code; 
	private String title ;
	
	
	public Course(String title) {
		super();
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	} 

}
