package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.CustomerLinkman;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ICustomerLinkmanService;

@Controller
@RequestMapping("/customerlinkman")
public class CustomerLinkManController {
	
	@Autowired
	private ICustomerLinkmanService customerLinkmanService;
	
	@RequestMapping("/getLinkManDetailPage")
	public  String getLinkManDetailPage() {
		return "link_man_detail";
	}
	@RequestMapping("/getAllLinkMenByCusId")
	@ResponseBody
	public DataGridResponse<CustomerLinkman> getAllLinkManByCusId(Integer customerId){
		return customerLinkmanService.getAllLinkManByCusId(customerId);
	}
	@RequestMapping("/saveLinkmanInfo")
	@ResponseBody
	public ServerResponse<CustomerLinkman> saveLinkmanInfo(CustomerLinkman customerLinkman){
		return customerLinkmanService.saveLinkmanInfo(customerLinkman);
	}
	@RequestMapping("/updateLinkmanInfo")
	@ResponseBody
	public ServerResponse<CustomerLinkman> updateLinkmanInfo(CustomerLinkman customerLinkman){
		return customerLinkmanService.updateLinkmanInfo(customerLinkman);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse<CustomerLinkman> delete(Integer id){
		return customerLinkmanService.delete(id);
	}
	
}
