package com.situ.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.bean.CustomerLossMeasure;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerLossMeasureMapper;
import com.situ.crm.service.ICustomerLossMeasureService;

@Service
public class CustomerLossMeasureServiceImpl implements ICustomerLossMeasureService {

	@Autowired
	private CustomerLossMeasureMapper customerLossMeasureMapper;

	@Override
	public DataGridResponse<CustomerLossMeasure> getAllMeasureByLossId(Integer id) {
		// TODO Auto-generated method stub
		List<CustomerLossMeasure> measureList = customerLossMeasureMapper.selectMeasuresByLossId(id);
		DataGridResponse<CustomerLossMeasure> dataGrid = new DataGridResponse<>(measureList.size(), measureList);
		return dataGrid;
	}

	@Override
	public DataGridResponse<CustomerLossMeasure> addMeasureByLossId(CustomerLossMeasure customerLossMeasure) {
		// TODO Auto-generated method stub
		int total = customerLossMeasureMapper.insertSelective(customerLossMeasure);
		List<CustomerLossMeasure> listMeasure = new ArrayList<CustomerLossMeasure>();
		listMeasure.add(customerLossMeasure);
		DataGridResponse<CustomerLossMeasure> dataGrid = new DataGridResponse<>(total, listMeasure);
		return dataGrid;
	}

	@Override
	public ServerResponse delete(Integer id) {
		// TODO Auto-generated method stub
		int line = customerLossMeasureMapper.deleteByPrimaryKey(id);
		if (line > 0) {
			return ServerResponse.createSuccess("删除成功");
		} 
		return ServerResponse.createError("删除失败");
	}
}
