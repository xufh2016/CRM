package com.situ.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.bean.CustomerLinkman;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerLinkmanMapper;
import com.situ.crm.service.ICustomerLinkmanService;

@Service
public class CustomerLinkmanServiceImpl implements ICustomerLinkmanService {

	@Autowired
	private CustomerLinkmanMapper customerLinkmanMapper;

	@Override
	public DataGridResponse<CustomerLinkman> getAllLinkManByCusId(Integer customerId) {
		// TODO Auto-generated method stub
		List<CustomerLinkman> customerLinkmanList = customerLinkmanMapper.selectLinkMenByCusId(customerId);
		int total = customerLinkmanList.size();
		DataGridResponse<CustomerLinkman> dataGridResponse = new DataGridResponse<>(total, customerLinkmanList);
		return dataGridResponse;
	}

	@Override
	public ServerResponse<CustomerLinkman> saveLinkmanInfo(CustomerLinkman customerLinkman) {
		// TODO Auto-generated method stub
		int count = customerLinkmanMapper.insertSelective(customerLinkman);
		if (count > 0)
			return ServerResponse.createSuccess("添加信息成功");
		return ServerResponse.createError("添加信息失败");
	}

	@Override
	public ServerResponse<CustomerLinkman> updateLinkmanInfo(CustomerLinkman customerId) {
		// TODO Auto-generated method stub
		int count = customerLinkmanMapper.updateByPrimaryKeySelective(customerId);
		if (count > 0)
			return ServerResponse.createSuccess("添加信息成功");
		return ServerResponse.createError("添加信息失败");
	}

	@Override
	public ServerResponse<CustomerLinkman> delete(Integer id) {
		// TODO Auto-generated method stub
		int count = customerLinkmanMapper.deleteByPrimaryKey(id);
		if (count > 0)
			return ServerResponse.createSuccess("添加信息成功");
		return ServerResponse.createError("添加信息失败");
	}
}
