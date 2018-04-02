package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.Customer;
import com.situ.crm.vo.CustomerContribution;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

	List<Customer> pageList(Customer customer);

	int doDelete(String[] idArray);

	List<String> getSatisfy();

	List<String> getCredit();

	List<String> getLevel();

	List<String> getManagerName();

	List<String> getRegion();

	/**
	 * 查询即将流失的客户
	 * @return
	 */
	List<Customer> selectBeingLossCustomer();
	List<Customer> selectByCustomerId(Integer id);

	List<CustomerContribution> selectAllCustomerContribution();
}