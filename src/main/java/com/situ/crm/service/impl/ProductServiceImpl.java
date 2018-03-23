package com.situ.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.bean.Product;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.ProductMapper;
import com.situ.crm.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public DataGridResponse<Product> pageList(Integer page, Integer rows, Product searchCondition) {
		// TODO Auto-generated method stub
		//设置开启分页
		PageHelper.startPage(page,rows);
		List<Product> listPage=productMapper.pageList(searchCondition);
		
		PageInfo<Product> pageInfo=new PageInfo<Product>(listPage);
		//获取总记录的条数
		Integer total=(int) pageInfo.getTotal();
		
		//Integer total=(int) PageHelper.count(select);
		DataGridResponse<Product> dataGridResponse=new DataGridResponse<>(total, listPage);
		return dataGridResponse;
	}

	@Override
	public ServerResponse doDelete(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		int count=productMapper.doDelete(idArray);
		if (count==idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

	@Override
	public ServerResponse doAdd(Product product) {
		// TODO Auto-generated method stub
		int count = productMapper.insert(product);
		if (count>0) {
			return ServerResponse.createSuccess("添加成功");
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse doUpdate(Product product) {
		// TODO Auto-generated method stub
		int count = productMapper.updateByPrimaryKey(product);
		if (count>0) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}
}
