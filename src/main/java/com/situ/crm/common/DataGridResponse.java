package com.situ.crm.common;

import java.io.Serializable;
import java.util.List;

/**
 * easyUI的表格需要返回这样一个数据格式
 * @author Administrator
 *
 * @param <T>
 */
public class DataGridResponse<T> implements Serializable{
	private Integer total;
	private List<T> rows;
	public DataGridResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DataGridResponse(Integer total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "DataGridResponse [total=" + total + ", rows=" + rows + "]";
	}
	
 }
