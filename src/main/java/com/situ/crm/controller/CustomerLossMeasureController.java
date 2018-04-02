package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.CustomerLossMeasure;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ICustomerLossMeasureService;

@Controller
@RequestMapping("/customerlossmeasure")
public class CustomerLossMeasureController {

	@Autowired
	private ICustomerLossMeasureService customerLossMeasureService;

	@RequestMapping("/getDeferredLossPage")
	public String getDeferredLossPage(Integer id) {
		return "deferred_loss";
	}

	@RequestMapping("/getCusDevPlanPage")
	public String getCusDevPlanPage(Integer id) {
		return "customer_develop_plan";
	}

	@RequestMapping("/getAllMeasureByLossId")
	@ResponseBody
	public DataGridResponse<CustomerLossMeasure> getAllMeasureByLossId(Integer id){
		return customerLossMeasureService.getAllMeasureByLossId(id);
	}
	
	@RequestMapping("/addMeasureByLossId")
	@ResponseBody
	public DataGridResponse<CustomerLossMeasure> addMeasureByLossId(CustomerLossMeasure customerLossMeasure){
		return customerLossMeasureService.addMeasureByLossId(customerLossMeasure);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(Integer id) {
		return customerLossMeasureService.delete(id);
	}
	

}
