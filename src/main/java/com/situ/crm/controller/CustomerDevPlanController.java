package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.CusDevPlan;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ICustomerDevPlanService;

@Controller
@RequestMapping("/cusdevplan")
public class CustomerDevPlanController {

	@Autowired
	private ICustomerDevPlanService customerDevService;

	@RequestMapping("/develop")
	public String develop() {
		return "customer_develop";
	}
	@RequestMapping("/detail")
	public String detail() {
		return "customer_detail";
	}
	
	@RequestMapping("/addDevPlan")
	@ResponseBody
	public ServerResponse addDevPlan(CusDevPlan cusDevPlan) {
		return customerDevService.addDevPlan(cusDevPlan);
	}
	@RequestMapping("/updateDevPlan")
	@ResponseBody
	public ServerResponse updateDevPlan(CusDevPlan cusDevPlan) {
		return customerDevService.updateDevPlan(cusDevPlan);
	}
	@RequestMapping("/deleteDevPlan")
	@ResponseBody
	public ServerResponse deleteDevPlan(Integer id) {
		return customerDevService.deleteDevPlan(id);
	}
}
