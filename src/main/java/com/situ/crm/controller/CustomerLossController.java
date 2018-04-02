package com.situ.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.CustomerLoss;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ICustomerLossService;
import com.situ.crm.service.impl.CustomerLossServiceImpl;

@Controller
@RequestMapping("/customerloss")
public class CustomerLossController {

	@Autowired
	private ICustomerLossService customerLossService = new CustomerLossServiceImpl();

	@RequestMapping("/getCustomerLossAnalysisPage")
	public String getCustomerLossAnalysisPage() {
		return "customer_loss_analysis";
	}
	
	@RequestMapping("/getCustomerLossPage")
	public String getCustomerLossPage() {
		return "customer_loss";
	}
	
	@RequestMapping("/customerLossAnalysis")
	@ResponseBody
	public ServerResponse customerLossAnalysis() {
		System.out.println("dataAnalysis");
		return customerLossService.customerLossAnalysis();
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public DataGridResponse<CustomerLoss>  pageList(Integer page, Integer rows, CustomerLoss saleChance) {
		return customerLossService.pageList(page,rows,saleChance);
	}
	
	@RequestMapping("/getCustomerLossInfoById")
	@ResponseBody
	public DataGridResponse<CustomerLoss>  getCustomerLossInfoById(Integer id) {
		return customerLossService.getCustomerLossInfoById(id);
	}

	@RequestMapping("/getStatus")
	@ResponseBody
	public List<Map<String,Object>> getStatus(){
		
		return customerLossService.getStatus();
	} 
}
