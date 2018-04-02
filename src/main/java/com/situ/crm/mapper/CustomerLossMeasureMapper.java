package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.CustomerLossMeasure;

public interface CustomerLossMeasureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerLossMeasure record);

    int insertSelective(CustomerLossMeasure record);

    CustomerLossMeasure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerLossMeasure record);

    int updateByPrimaryKey(CustomerLossMeasure record);

	List<CustomerLossMeasure> selectMeasuresByLossId(Integer id);

	int insertMeasureById(Integer id);
}