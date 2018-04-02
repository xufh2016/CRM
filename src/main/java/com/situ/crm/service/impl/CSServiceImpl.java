package com.situ.crm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.bean.CustomerService;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerServiceMapper;
import com.situ.crm.service.ICSService;

@Service
public class CSServiceImpl implements ICSService {

	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Override
	public List<Map<String, String>> getServiceType() {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = new ArrayList<>();
		List<String> serviceTypeList = customerServiceMapper.selectServiceType();
		for (String serviceType : serviceTypeList) {
			Map<String, String> map = new HashMap<>();
			map.put("serviceType", serviceType);
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public ServerResponse addCustomerService(CustomerService customerService) {
		// TODO Auto-generated method stub
		int count = customerServiceMapper.insertSelective(customerService);
		if (count > 0) {
			ServerResponse.createSuccess("添加服务成功");
		}
		return ServerResponse.createError("添加服务失败");
	}

	@Override
	public DataGridResponse<CustomerService> getAllServiceByStatus() {
		// TODO Auto-generated method stub
		List<CustomerService> rows = customerServiceMapper.selectAllServiceByStatus();
		Integer total = rows.size();
		DataGridResponse<CustomerService> dataGridResponse = new DataGridResponse<CustomerService>(total, rows);

		return dataGridResponse;
	}

	@Override
	public ServerResponse doAssignment(CustomerService customerService) {
		// TODO Auto-generated method stub

		int count = customerServiceMapper.updateByPrimaryKeySelective(customerService);
		if (count > 0) {
			ServerResponse.createSuccess("分配任务成功");
		}
		return ServerResponse.createError("分配任务失败");
	}

	@Override
	public DataGridResponse<CustomerService> getAllNotHandleService() {
		// TODO Auto-generated method stub
		List<CustomerService> rows = customerServiceMapper.selectNotHandleServices();

		Integer total = rows.size();
		DataGridResponse<CustomerService> dataGridResponse = new DataGridResponse<>(total, rows);
		return dataGridResponse;
	}

	@Override
	public ServerResponse doServiceHandling(CustomerService customerService) {
		// TODO Auto-generated method stub
		int count = customerServiceMapper.updateByPrimaryKeySelective(customerService);
		if (count > 0) {
			ServerResponse.createSuccess("服务处理成功");
		}
		return ServerResponse.createError("服务处理失败");
	}

	@Override
	public DataGridResponse<CustomerService> getAllFillingService() {
		// TODO Auto-generated method stub
		List<CustomerService> rows = customerServiceMapper.selectAllFillingService();
		Integer total = rows.size();
		DataGridResponse<CustomerService> dataGridResponse = new DataGridResponse<>(total, rows);
		return dataGridResponse;
	}

}
