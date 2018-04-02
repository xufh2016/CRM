package com.situ.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.bean.DataDic;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.DataDicMapper;
import com.situ.crm.service.IDataDicService;

@Service
public class DataDicServiceImpl implements IDataDicService {

	@Autowired
	private DataDicMapper datadicMapper;

	@Override
	public DataGridResponse<DataDic> pageList(Integer page, Integer rows, DataDic searchCondition) {
		// TODO Auto-generated method stub
		//设置开启分页
		PageHelper.startPage(page,rows);
		List<DataDic> listPage=datadicMapper.pageList(searchCondition);
		
		PageInfo<DataDic> pageInfo=new PageInfo<DataDic>(listPage);
		//获取总记录的条数
		Integer total=(int) pageInfo.getTotal();
		
		//Integer total=(int) PageHelper.count(select);
		DataGridResponse<DataDic> dataGridResponse=new DataGridResponse<>(total, listPage);
		return dataGridResponse;
	}

	@Override
	public ServerResponse doDelete(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		int count=datadicMapper.doDelete(idArray);
		if (count==idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

	@Override
	public ServerResponse doAdd(DataDic datadic) {
		// TODO Auto-generated method stub
		int count = datadicMapper.insert(datadic);
		if (count>0) {
			return ServerResponse.createSuccess("添加成功");
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse doUpdate(DataDic datadic) {
		// TODO Auto-generated method stub
		int count = datadicMapper.updateByPrimaryKey(datadic);
		if (count>0) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse getDataDicName() {
		// TODO Auto-generated method stub
		List list=datadicMapper.selectDataDicName();
		//return list;
		int count=list.size();
		if (count>0) {
			return ServerResponse.createSuccess("查询成功",list);
		}
		return ServerResponse.createError("查询失败");
	}

	@Override
	public ServerResponse getDataDicValue(String dicName) {
		// TODO Auto-generated method stub
		List list=datadicMapper.getDataDicValue(dicName);
		int count=list.size();
		if (count>0) {
			return ServerResponse.createSuccess("查询成功",list);
		}
		return ServerResponse.createError("查询失败");
	}
}
