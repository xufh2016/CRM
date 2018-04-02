package com.situ.crm.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.bean.User;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.UserMapper;
import com.situ.crm.service.IUserService;
import com.situ.crm.vo.SearchCondition;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public DataGridResponse<User> pageList(Integer page, Integer rows, SearchCondition searchCondition) {
		// TODO Auto-generated method stub
		//设置开启分页
		PageHelper.startPage(page,rows);
		List<User> listPage=userMapper.pageList(searchCondition);
		
		PageInfo<User> pageInfo=new PageInfo<User>(listPage);
		//获取总记录的条数
		Integer total=(int) pageInfo.getTotal();
		
		//Integer total=(int) PageHelper.count(select);
		DataGridResponse<User> dataGridResponse=new DataGridResponse<>(total, listPage);
		return dataGridResponse;
	}

	@Override
	public ServerResponse doDelete(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		int count=userMapper.doDelete(idArray);
		if (count==idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

	@Override
	public ServerResponse doAdd(User user) {
		// TODO Auto-generated method stub
		int count = userMapper.insert(user);
		if (count>0) {
			return ServerResponse.createSuccess("添加成功");
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse doUpdate(User user) {
		// TODO Auto-generated method stub
		int count = userMapper.updateByPrimaryKey(user);
		if (count>0) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}
	@Override
	public List<Map<String, String>> getAssignMan() {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = new ArrayList<>();
		List<String> manList = userMapper.selectAssignMan();
		for (String assignMan : manList) {
			Map<String, String> map = new HashMap<>();
			map.put("assignMan", assignMan);
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public ServerResponse checkLoginParam(String name,String password) {
		// TODO Auto-generated method stub
		User user=userMapper.selectUserExist(name,password);
		if(user!=null)
			return ServerResponse.createSuccess("登录成功",user);
		return ServerResponse.createError("登录失败");
	}
}
