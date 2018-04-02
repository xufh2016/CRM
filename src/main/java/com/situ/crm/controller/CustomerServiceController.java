package com.situ.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.CustomerService;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ICSService;

@Controller
@RequestMapping("/customerservice")
public class CustomerServiceController {
	
	@Autowired
	private ICSService csService;
	
	@RequestMapping("/getCustomerServicePage")
	public String getCustomerServicePage() {
		return "create_cus_service";
	}
	@RequestMapping("/getServiceAssignmentPage")
	public String getServiceAssignmentPage() {
		return "service_assignment";
	}
	@RequestMapping("/getServiceHandlingPage")
	public String getServiceHandlingPage() {
		return "service_handling";
	}
	@RequestMapping("/getServiceResponsePage")
	public String getServiceResponsePage() {
		return "service_response";
	}
	@RequestMapping("/getServiceFillingPage")
	public String getServiceFillingPage() {
		return "service_filling";
	}
	
	@RequestMapping("/getServiceType")
	@ResponseBody
	public List<Map<String,String>> getServiceType(){
		return csService.getServiceType();
	}
	
	@RequestMapping("/addCustomerService")
	@ResponseBody
	public ServerResponse addCustomerService(CustomerService customerService) {
		return csService.addCustomerService(customerService);
	}
	
	@RequestMapping("/getAllServiceByStatus")
	@ResponseBody
	public DataGridResponse<CustomerService> getAllServiceByStatus() {
		return csService.getAllServiceByStatus();
	}
	@RequestMapping("/getAllNotHandleService")
	@ResponseBody
	public DataGridResponse<CustomerService> getAllNotHandleService() {
		return csService.getAllNotHandleService();
	}
	@RequestMapping("/getAllFillingService")
	@ResponseBody
	public DataGridResponse<CustomerService> getAllFillingService() {
		return csService.getAllFillingService();
	}
	
	
	@RequestMapping("/doAssignment")
	@ResponseBody
	//分配任务其实就是一个更新信息的过程
	public ServerResponse doAssignment(CustomerService customerService) {
		return csService.doAssignment(customerService);
	}
	@RequestMapping("/doServiceHandling")
	@ResponseBody
	//分配任务其实就是一个更新信息的过程
	public ServerResponse doServiceHandling(CustomerService customerService) {
		return csService.doServiceHandling(customerService);
	}
	
}
