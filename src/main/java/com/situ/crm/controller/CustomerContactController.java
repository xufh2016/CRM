package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.CustomerContact;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ICustomerContactService;

@Controller
@RequestMapping("/customercontact")
public class CustomerContactController {
	@Autowired
	private ICustomerContactService customerContactService;
	
	@RequestMapping("/getContactDetailPage")
	public String getContactDetailPage() {
		return "customer_contact_detail";
	}
	
	
	@RequestMapping("/getAllCusContactByCusId")
	@ResponseBody
	public DataGridResponse<CustomerContact> getAllCusContactByCusId(Integer customerId){
		return customerContactService.getAllCusContactByCusId(customerId);
	}
	
	@RequestMapping("/saveCustomerContactInfo")
	@ResponseBody
	public ServerResponse<CustomerContact> saveCustomerContactInfo(CustomerContact customerContact){
		return customerContactService.saveCustomerContactInfo(customerContact);
	}
	@RequestMapping("/updateCustomerContactInfo")
	@ResponseBody
	public ServerResponse<CustomerContact> updateCustomerContactInfo(CustomerContact customerContact){
		return customerContactService.updateCustomerContactInfo(customerContact);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse<CustomerContact> delete(Integer id){
		return customerContactService.delete(id);
	}

}
