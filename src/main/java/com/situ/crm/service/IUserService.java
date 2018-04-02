package com.situ.crm.service;

import java.util.List;
import java.util.Map;

import com.situ.crm.bean.User;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.vo.SearchCondition;

public interface IUserService {

	/**
	 * 
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param searchCondition 搜索条件
	 * @return 返回基于easyui表格的json数据
	 */
	DataGridResponse<User> pageList(Integer page, Integer rows, SearchCondition searchCondition);

	ServerResponse doDelete(String ids);

	ServerResponse doAdd(User user);

	ServerResponse doUpdate(User user);

	List<Map<String, String>> getAssignMan();

	ServerResponse checkLoginParam(String name,String password);
}
