package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.Customer;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired 
	private ICustomerService customerService;
	
	@RequestMapping("/getCustomerInfoPage")
	public String getCustomerInfoPage() {
		return "customer_info_manage";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public DataGridResponse  pageList() {
		return customerService.pageList();
	}
	
	
	/*public  deleteCustomer(Integer customerId){
		
	}*/
	
	
}
