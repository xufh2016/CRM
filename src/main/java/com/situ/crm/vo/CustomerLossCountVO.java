package com.situ.crm.vo;

public class CustomerLossCountVO {
	private String name;
	private Integer count;
	public CustomerLossCountVO(String name, Integer count) {
		super();
		this.name = name;
		this.count = count;
	}
	public CustomerLossCountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
