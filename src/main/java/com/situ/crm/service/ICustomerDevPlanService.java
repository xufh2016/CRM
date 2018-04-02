package com.situ.crm.service;

import com.situ.crm.bean.CusDevPlan;
import com.situ.crm.common.ServerResponse;

public interface ICustomerDevPlanService {

	ServerResponse addDevPlan(CusDevPlan cusDevPlan);

	ServerResponse updateDevPlan(CusDevPlan cusDevPlan);

	ServerResponse deleteDevPlan(Integer id);

	

}
