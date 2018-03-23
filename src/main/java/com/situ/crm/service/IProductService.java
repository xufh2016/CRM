package com.situ.crm.service;

import com.situ.crm.bean.Product;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface IProductService {

	/**
	 * 
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param searchCondition 搜索条件
	 * @return 返回基于easyui表格的json数据
	 */
	DataGridResponse<Product> pageList(Integer page, Integer rows, Product searchCondition);

	ServerResponse doDelete(String ids);

	ServerResponse doAdd(Product product);

	ServerResponse doUpdate(Product product);
}
