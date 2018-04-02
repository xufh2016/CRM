package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.DataDic;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.IDataDicService;

@Controller
@RequestMapping("/datadic")
public class DataDicController {

	@Autowired
	private IDataDicService datadicService;
	
	@RequestMapping("/getDataDicInfoPage")
	public String  getDataDicInfoPage() {
		return "datadic_info";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public DataGridResponse<DataDic>  pageList(Integer page,Integer rows,DataDic datadic) {
		
		return datadicService.pageList(page,rows,datadic);
	}
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public ServerResponse doDelete(String ids) {
		return datadicService.doDelete(ids);
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public ServerResponse doAdd(DataDic datadic) {
		return datadicService.doAdd(datadic);
	}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ServerResponse doUpdate(DataDic datadic) {
		return datadicService.doUpdate(datadic);
	}
	
	@RequestMapping("/getDataDicName")
	@ResponseBody
	public ServerResponse getDataDicName() {
		return datadicService.getDataDicName();
	}
	
	@RequestMapping("/getDataDicValue")
	@ResponseBody
	public ServerResponse getDataDicValue(String dicName) {
		return datadicService.getDataDicValue(dicName);
	}
	
//	List<Map<String,String >>
}
