package com.situ.crm.service;

import com.situ.crm.bean.CustomerContact;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface ICustomerContactService {

	DataGridResponse<CustomerContact> getAllCusContactByCusId(Integer customerId);

	ServerResponse<CustomerContact> saveCustomerContactInfo(CustomerContact customerContact);

	ServerResponse<CustomerContact> updateCustomerContactInfo(CustomerContact customerContact);

	ServerResponse<CustomerContact> delete(Integer id);

}
