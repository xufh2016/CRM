package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.CustomerLinkman;

public interface CustomerLinkmanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerLinkman record);

    int insertSelective(CustomerLinkman record);

    CustomerLinkman selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerLinkman record);

    int updateByPrimaryKey(CustomerLinkman record);

	List<CustomerLinkman> selectLinkMenByCusId(Integer customerId);

	int updateLinkmanInfoByCusId(CustomerLinkman customerId);

	
}