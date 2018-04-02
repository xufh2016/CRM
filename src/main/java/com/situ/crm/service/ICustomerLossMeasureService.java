package com.situ.crm.service;

import com.situ.crm.bean.CustomerLossMeasure;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface ICustomerLossMeasureService {

	DataGridResponse<CustomerLossMeasure> getAllMeasureByLossId(Integer id);

	DataGridResponse<CustomerLossMeasure> addMeasureByLossId(CustomerLossMeasure customerLossMeasure);

	ServerResponse delete(Integer id);

}
