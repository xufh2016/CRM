package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.CustomerOrder;

public interface CustomerOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerOrder record);

    int insertSelective(CustomerOrder record);

    CustomerOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerOrder record);

    int updateByPrimaryKey(CustomerOrder record);

	CustomerOrder selectCustomerOrderByCusId(Integer id);

	List<CustomerOrder> selectAllCusOrderbyCusId(Integer customerId);
}