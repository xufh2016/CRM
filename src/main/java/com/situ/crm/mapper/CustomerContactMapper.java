package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.CustomerContact;

public interface CustomerContactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerContact record);

    int insertSelective(CustomerContact record);

    CustomerContact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerContact record);

    int updateByPrimaryKey(CustomerContact record);

	List<CustomerContact> selectCusContactsByCustomerId(Integer customerId);
}