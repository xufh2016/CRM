package com.situ.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.bean.CustomerOrder;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerOrderMapper;
import com.situ.crm.service.ICustomerOrderService;

@Service
public class CustomerOrderServiceImpl implements ICustomerOrderService {
	@Autowired
	private CustomerOrderMapper customerOrderMapper;

	@Override
	public DataGridResponse<CustomerOrder> getAllCusOrderByCusId(Integer customerId) {
		// TODO Auto-generated method stub
		List<CustomerOrder> customerOrderList = customerOrderMapper.selectAllCusOrderbyCusId(customerId);
		DataGridResponse<CustomerOrder> dataGridResponse = new DataGridResponse<>(customerOrderList.size(),
				customerOrderList);
		return dataGridResponse;
	}

	@Override
	public ServerResponse<CustomerOrder> saveCusOrderInfo(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		int count = customerOrderMapper.insertSelective(customerOrder);
		if (count > 0) {
			return ServerResponse.createSuccess("添加数据成功");
		}
		return ServerResponse.createSuccess("添加数据失败");
	}

	@Override
	public ServerResponse<CustomerOrder> updateCusOrderInfo(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		int count = customerOrderMapper.updateByPrimaryKeySelective(customerOrder);
		if (count > 0) {
			return ServerResponse.createSuccess("添加数据成功");
		}
		return ServerResponse.createSuccess("添加数据失败");
	}

	@Override
	public ServerResponse<CustomerOrder> delete(Integer customerId) {
		// TODO Auto-generated method stub
		int count = customerOrderMapper.deleteByPrimaryKey(customerId);
		if (count > 0) {
			return ServerResponse.createSuccess("添加数据成功");
		}
		return ServerResponse.createSuccess("添加数据失败");

	}
}
