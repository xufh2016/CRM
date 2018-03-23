package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.bean.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	int doDelete(String[] idArray);

	List<Product> pageList(Product searchCondition);
}