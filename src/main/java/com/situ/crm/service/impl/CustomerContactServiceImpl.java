package com.situ.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.bean.CustomerContact;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerContactMapper;
import com.situ.crm.service.ICustomerContactService;

@Service
public class CustomerContactServiceImpl implements ICustomerContactService {
	@Autowired
	private CustomerContactMapper customerContactMapper;

	@Override
	public DataGridResponse<CustomerContact> getAllCusContactByCusId(Integer customerId) {
		// TODO Auto-generated method stub
		List<CustomerContact> customerContactList=customerContactMapper.selectCusContactsByCustomerId(customerId);
		int total=customerContactList.size();
		DataGridResponse<CustomerContact> dataGridResponse=new DataGridResponse<>(total, customerContactList);
		return dataGridResponse;
	}

	@Override
	public ServerResponse<CustomerContact> saveCustomerContactInfo(CustomerContact customerContact) {
		// TODO Auto-generated method stub
		int count = customerContactMapper.insertSelective(customerContact);
		if (count>0) {
			return ServerResponse.createSuccess("添加数据成功");
		}
		return ServerResponse.createError("添加数据失败");
	}

	@Override
	public ServerResponse<CustomerContact> updateCustomerContactInfo(CustomerContact customerContact) {
		// TODO Auto-generated method stub
		int count = customerContactMapper.updateByPrimaryKeySelective(customerContact);
		if (count>0) {
			return ServerResponse.createSuccess("更新数据成功");
		}
		return ServerResponse.createError("更新数据失败");
	}

	@Override
	public ServerResponse<CustomerContact> delete(Integer id) {
		// TODO Auto-generated method stub
		int count = customerContactMapper.deleteByPrimaryKey(id);
		if (count>0) {
			return ServerResponse.createSuccess("删除数据成功");
		}
		return ServerResponse.createError("删除数据失败");
	}

}
