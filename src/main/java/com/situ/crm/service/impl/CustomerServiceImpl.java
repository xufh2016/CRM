package com.situ.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.common.DataGridResponse;
import com.situ.crm.mapper.CustomerMapper;
import com.situ.crm.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public DataGridResponse pageList() {
		// TODO Auto-generated method stub
		Integer total=customerMapper.pageList().size();
		List rows=customerMapper.pageList();
		DataGridResponse dataGridResponse = new DataGridResponse(total, rows);
		return dataGridResponse;
	}
	
	
}
