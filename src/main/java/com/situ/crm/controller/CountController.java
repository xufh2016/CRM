package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.DataGridResponse;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.vo.CustomerContribution;

@Controller
@RequestMapping("/count")
public class CountController {

	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping("getCustomerContributionPage")
	public String getCustomerContributionPage() {
		return "customer_contribution";
	}

	@RequestMapping("/customerContribution")
	@ResponseBody
	public DataGridResponse<CustomerContribution> customerContribution() {
		return customerService.customerContribution();
	}
}
