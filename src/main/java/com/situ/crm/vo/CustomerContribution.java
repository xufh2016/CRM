package com.situ.crm.vo;

public class CustomerContribution {
	private String customerName;
	private Integer totalPrice;
	public CustomerContribution() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerContribution(String customerName, Integer totalPrice) {
		super();
		this.customerName = customerName;
		this.totalPrice = totalPrice;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "CustomerContribution [customerName=" + customerName + ", totalPrice=" + totalPrice + "]";
	}

}
