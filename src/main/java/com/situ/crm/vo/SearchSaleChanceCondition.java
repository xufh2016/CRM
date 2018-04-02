package com.situ.crm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchSaleChanceCondition {

	private Integer id;
	private String chanceSource;
	private String customerName;
	private String overview;
	private String linkMan;
	private Integer devResult;
	private String description;
	private String assignMan;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beginTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;

	public SearchSaleChanceCondition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchSaleChanceCondition(Integer id,String chanceSource, String customerName, String overview, String linkMan,
			Integer devResult, String description, String assignMan, Date beginTime, Date endTime) {
		super();
		this.id=id;
		this.chanceSource = chanceSource;
		this.customerName = customerName;
		this.overview = overview;
		this.linkMan = linkMan;
		this.devResult = devResult;
		this.description = description;
		this.assignMan = assignMan;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public String getChanceSource() {
		return chanceSource;
	}

	public void setChanceSource(String chanceSource) {
		this.chanceSource = chanceSource;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public Integer getStatus() {
		return devResult;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDevResult() {
		return devResult;
	}

	public void setDevResult(Integer devResult) {
		this.devResult = devResult;
	}

	public void setStatus(Integer devResult) {
		this.devResult = devResult;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignMan() {
		return assignMan;
	}

	public void setAssignMan(String assignMan) {
		this.assignMan = assignMan;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "SearchSaleChanceCondition [chanceSource=" + chanceSource + ", customerName=" + customerName
				+ ", overview=" + overview + ", linkMan=" + linkMan + ", devResult=" + devResult + ", description="
				+ description + ", assignMan=" + assignMan + ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}

}
