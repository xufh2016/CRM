package com.situ.crm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.SaleChance;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.vo.SearchSaleChanceCondition;

@Controller
@RequestMapping("/salechance")
public class SaleChanceController {

	@Autowired
	private ISaleChanceService salechanceService;
	
	@RequestMapping("/getSaleChanceInfoPage")
	public String  getSaleChanceInfoPage() {
		return "salechance_info";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public DataGridResponse<SaleChance>  pageList(Integer page, Integer rows, SearchSaleChanceCondition saleChance) {
		
		//System.out.println(saleChance.ge);
		System.out.println(saleChance.getStatus());
		return salechanceService.pageList(page,rows,saleChance);
	}
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public ServerResponse doDelete(String ids) {
		return salechanceService.doDelete(ids);
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public ServerResponse doAdd(SaleChance salechance) {
		return salechanceService.doAdd(salechance);
	}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ServerResponse doUpdate(SaleChance salechance) {
		return salechanceService.doUpdate(salechance);
	}
	@RequestMapping("/getStatus")
	@ResponseBody
	public List<Map<String,Object>> getStatus(){
		
		return salechanceService.getStatus();
	} 
	
	@RequestMapping("/getDevResult")
	@ResponseBody
	public List<Map<String,Object>> getDevResult(){
		
		return salechanceService.getDevResult();
	} 
	
	
	@RequestMapping("/getCustomerDevPlanPage")
	public String cusDevPlan() {
		
		return "customer_dev_plan";
	}
	@RequestMapping("/getCustomerDevPlan")
	@ResponseBody
	public ServerResponse getCustomerDevPlan(Integer id) {
		return salechanceService.getCustomerDevPlan(id);
	}
	@RequestMapping("/updateDevResult")
	@ResponseBody
	public ServerResponse updateDevResult(Integer saleChanceId,Integer devResult) {
		return salechanceService.updateDevResult(saleChanceId,devResult);
	}
	
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response) {
		salechanceService.exportExcel(response);
	}
	
}
