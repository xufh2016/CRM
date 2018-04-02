package com.situ.crm.service;

import java.util.List;
import java.util.Map;

import com.situ.crm.bean.Customer;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.vo.CustomerContribution;

public interface ICustomerService {

	DataGridResponse pageList(Integer page, Integer rows, Customer customer);

	ServerResponse doDelete(String ids);

	ServerResponse doAdd(Customer customer);

	ServerResponse doUpdate(Customer customer);

	List<Map<String, String>> getRegion();

	List<Map<String, String>> getManagerName();

	List<Map<String, String>> getLevel();

	List<Map<String, String>> getCredit();

	List<Map<String, String>> getSatisfy();

	DataGridResponse<Customer> getAllLinkManByCusId(Integer id);

	DataGridResponse<Customer> getCustomerBasicInfo(Integer id);

	DataGridResponse<CustomerContribution> customerContribution();
}
