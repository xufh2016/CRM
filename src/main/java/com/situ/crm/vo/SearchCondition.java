package com.situ.crm.vo;

public class SearchCondition {
	private String s_name;
	private String s_truename;
	private String s_email;
	private String s_phone;
	private String s_rolename;
	public SearchCondition(String s_name, String s_truename, String s_email, String s_phone, String s_rolename) {
		super();
		this.s_name = s_name;
		this.s_truename = s_truename;
		this.s_email = s_email;
		this.s_phone = s_phone;
		this.s_rolename = s_rolename;
	}
	public SearchCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_truename() {
		return s_truename;
	}
	public void setS_truename(String s_truename) {
		this.s_truename = s_truename;
	}
	public String getS_email() {
		return s_email;
	}
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	public String getS_rolename() {
		return s_rolename;
	}
	public void setS_rolename(String s_rolename) {
		this.s_rolename = s_rolename;
	}
	
	
	
	
}
