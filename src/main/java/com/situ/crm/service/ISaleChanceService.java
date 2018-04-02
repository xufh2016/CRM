package com.situ.crm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.situ.crm.bean.SaleChance;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.vo.SearchSaleChanceCondition;

public interface ISaleChanceService {

	/**
	 * 
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param SaleChance 搜索条件
	 * @return 返回基于easyui表格的json数据
	 */
	DataGridResponse<SaleChance> pageList(Integer page, Integer rows, SearchSaleChanceCondition saleChance);

	ServerResponse doDelete(String ids);

	ServerResponse doAdd(SaleChance salechance);

	ServerResponse doUpdate(SaleChance salechance);

	List<Map<String, Object>> getStatus();

	List<Map<String, Object>> getDevResult();
	ServerResponse getCustomerDevPlan(Integer id);

	ServerResponse updateDevResult(Integer saleChanceId, Integer devResult);

	void exportExcel(HttpServletResponse response);

}
