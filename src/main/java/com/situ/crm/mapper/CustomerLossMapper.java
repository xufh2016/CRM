package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.CustomerLoss;
import com.situ.crm.vo.CustomerLossCountVO;

public interface CustomerLossMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerLoss record);

    int insertSelective(CustomerLoss record);

    CustomerLoss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerLoss record);

    int updateByPrimaryKey(CustomerLoss record);

	List<CustomerLossCountVO> customerLossAnalysis();

	List<CustomerLoss> pageList(CustomerLoss saleChance);

	List<Integer> getStatus();
}