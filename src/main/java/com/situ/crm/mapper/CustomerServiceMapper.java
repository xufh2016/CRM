package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.CustomerService;

public interface CustomerServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerService record);

    int insertSelective(CustomerService record);

    CustomerService selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerService record);

    int updateByPrimaryKey(CustomerService record);

	List<String> selectServiceType();

	List<CustomerService> selectAllServiceByStatus();

	List<CustomerService> selectNotHandleServices();

	List<CustomerService> selectAllFillingService();
}