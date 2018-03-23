package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.User;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.IUserService;
import com.situ.crm.vo.SearchCondition;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/getUserInfoPage")
	public String  getUserInfoPage() {
		return "user_info";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public DataGridResponse<User>  pageList(Integer page,Integer rows,SearchCondition searchCondition) {
		
		return userService.pageList(page,rows,searchCondition);
	}
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public ServerResponse doDelete(String ids) {
		return userService.doDelete(ids);
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public ServerResponse doAdd(User user) {
		return userService.doAdd(user);
	}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ServerResponse doUpdate(User user) {
		return userService.doUpdate(user);
	}
}
