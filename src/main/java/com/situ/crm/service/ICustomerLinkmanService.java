package com.situ.crm.service;

import com.situ.crm.bean.CustomerLinkman;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;

public interface ICustomerLinkmanService {

	DataGridResponse<CustomerLinkman> getAllLinkManByCusId(Integer customerId);

	ServerResponse<CustomerLinkman> saveLinkmanInfo(CustomerLinkman customerLinkman);

	ServerResponse<CustomerLinkman> updateLinkmanInfo(CustomerLinkman customerLinkman);

	ServerResponse<CustomerLinkman> delete(Integer id);

}
