package com.situ.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.Customer;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
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
	public DataGridResponse  pageList(Integer page,Integer rows,Customer customer) {
		return customerService.pageList(page,rows,customer);
	}
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public ServerResponse doDelete(String ids) {
		return customerService.doDelete(ids);
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public ServerResponse doAdd(Customer customer) {
		return customerService.doAdd(customer);
	}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ServerResponse doUpdate(Customer customer) {
		return customerService.doUpdate(customer);
	}
	
	@RequestMapping("/getRegion")
	@ResponseBody
	public List<Map<String,String>> getRegion(){
		
		return customerService.getRegion();
	}
	@RequestMapping("/getManagerName")
	@ResponseBody
	public List<Map<String,String>> getManagerName(){
		
		return customerService.getManagerName();
	}
	@RequestMapping("/getLevel")
	@ResponseBody
	public List<Map<String,String>> getLevel(){
		
		return customerService.getLevel();
	}
	@RequestMapping("/getCredit")
	@ResponseBody
	public List<Map<String,String>> getCredit(){
		
		return customerService.getCredit();
	}
	@RequestMapping("/getSatisfy")
	@ResponseBody
	public List<Map<String,String>> getSatisfy(){
		
		return customerService.getSatisfy();
	}
	
	
	@RequestMapping("/getCustomerBasicInfoById")
	@ResponseBody
	public DataGridResponse<Customer> getCustomerBasicInfo(Integer id){
		return customerService.getCustomerBasicInfo(id);
	}
	
	@RequestMapping("/getAllLinkManByCusId")
	@ResponseBody
	public DataGridResponse<Customer> getAllLinkManByCusId(Integer id){
		return customerService.getAllLinkManByCusId(id);
	}	
}
