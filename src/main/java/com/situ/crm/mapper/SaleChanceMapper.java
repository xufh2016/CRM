package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.SaleChance;
import com.situ.crm.vo.SearchSaleChanceCondition;

public interface SaleChanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleChance record);

    int insertSelective(SaleChance record);

    SaleChance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleChance record);

    int updateByPrimaryKey(SaleChance record);

	int doDelete(String[] idArray);

	List<SaleChance> pageList(SearchSaleChanceCondition saleChance);

	List<Integer> selectStatus();

	List<Integer> getDevResult();
	//List<CusDevPlan> selectBySaleChanceId(SaleChance cusDevPlan);

	int updateDevResult(Integer saleChanceId, Integer devResult);

	List<SaleChance> selectAll();


}