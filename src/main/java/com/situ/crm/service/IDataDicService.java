package com.situ.crm.service;

import com.situ.crm.bean.DataDic;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface IDataDicService {

	/**
	 * 
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param searchCondition 搜索条件
	 * @return 返回基于easyui表格的json数据
	 */
	DataGridResponse<DataDic> pageList(Integer page, Integer rows, DataDic searchCondition);

	ServerResponse doDelete(String ids);

	ServerResponse doAdd(DataDic datadic);

	ServerResponse doUpdate(DataDic datadic);

	ServerResponse getDataDicName();

	ServerResponse getDataDicValue(String dicName);
}
