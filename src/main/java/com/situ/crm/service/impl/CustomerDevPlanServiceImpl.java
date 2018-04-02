package com.situ.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.bean.CusDevPlan;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CusDevPlanMapper;
import com.situ.crm.service.ICustomerDevPlanService;

@Service
public class CustomerDevPlanServiceImpl implements ICustomerDevPlanService {

	@Autowired
	private CusDevPlanMapper cusDevPlanMapper;

	@Override
	public ServerResponse addDevPlan(CusDevPlan cusDevPlan) {
		// TODO Auto-generated method stub
		int count = cusDevPlanMapper.insert(cusDevPlan);
		if (count > 0)
			return ServerResponse.createSuccess("添加成功");
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse updateDevPlan(CusDevPlan cusDevPlan) {
		// TODO Auto-generated method stub
		int count = cusDevPlanMapper.updateByPrimaryKey(cusDevPlan);
		if (count > 0)
			return ServerResponse.createSuccess("添加成功");
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse deleteDevPlan(Integer id) {
		// TODO Auto-generated method stub
		int count = cusDevPlanMapper.deleteByPrimaryKey(id);
		if (count > 0)
			return ServerResponse.createSuccess("添加成功");
		return ServerResponse.createError("添加失败");
	}

}
