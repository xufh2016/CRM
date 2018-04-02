package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.CustomerOrder;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ICustomerOrderService;

@Controller
@RequestMapping("/customerorder")
public class CustomerOrderController {
	
	@Autowired
	private ICustomerOrderService customerOrderService;

	@RequestMapping("/getCustomerOrderPage")
	public String getCustomerOrderPage() {
		return "customer_order";
	}
	
	
	@RequestMapping("/getAllCusOrderByCusId")
	@ResponseBody
	public DataGridResponse<CustomerOrder> getAllCusOrderByCusId(Integer customerId){
		return customerOrderService.getAllCusOrderByCusId(customerId);
	}
	
	@RequestMapping("/saveCusOrderInfo")
	@ResponseBody
	public ServerResponse<CustomerOrder> saveCusOrderInfo(CustomerOrder customerOrder){
		return customerOrderService.saveCusOrderInfo(customerOrder);
	}
	@RequestMapping("/updateCusOrderInfo")
	@ResponseBody
	public ServerResponse<CustomerOrder> updateCusOrderInfo(CustomerOrder customerOrder){
		return customerOrderService.updateCusOrderInfo(customerOrder);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse<CustomerOrder> delete(Integer customerId){
		return customerOrderService.delete(customerId);
	}
	
	
	
	
	
}
