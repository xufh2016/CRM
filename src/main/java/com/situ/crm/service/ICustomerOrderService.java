package com.situ.crm.service;

import com.situ.crm.bean.CustomerOrder;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface ICustomerOrderService {

	DataGridResponse<CustomerOrder> getAllCusOrderByCusId(Integer customerId);

	ServerResponse<CustomerOrder> saveCusOrderInfo(CustomerOrder customerOrder);

	ServerResponse<CustomerOrder> updateCusOrderInfo(CustomerOrder customerOrder);

	ServerResponse<CustomerOrder> delete(Integer customerId);

}
