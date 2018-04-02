package com.situ.crm.service;

import java.util.List;
import java.util.Map;

import com.situ.crm.bean.CustomerService;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface ICSService {

	List<Map<String, String>> getServiceType();

	ServerResponse addCustomerService(CustomerService customerService);

	DataGridResponse<CustomerService> getAllServiceByStatus();

	ServerResponse doAssignment(CustomerService customerService);

	DataGridResponse<CustomerService> getAllNotHandleService();

	ServerResponse doServiceHandling(CustomerService customerService);

	DataGridResponse<CustomerService> getAllFillingService();

}
