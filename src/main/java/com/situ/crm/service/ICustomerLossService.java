package com.situ.crm.service;

import java.util.List;
import java.util.Map;

import com.situ.crm.bean.CustomerLoss;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface ICustomerLossService {

	ServerResponse customerLossAnalysis();

	DataGridResponse<CustomerLoss> pageList(Integer page, Integer rows, CustomerLoss saleChance);

	List<Map<String, Object>> getStatus();

	DataGridResponse<CustomerLoss> getCustomerLossInfoById(Integer id);

	void timerAddLossCustomer();
}
