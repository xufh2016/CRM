package com.situ.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.User;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/checkLoginParam")
	@ResponseBody
	public ServerResponse checkLoginParam(String name,String password, HttpSession session) {

		ServerResponse serverResponse = userService.checkLoginParam(name,password);
		if (serverResponse.isSuccess()) {
			User user = (User) serverResponse.getData();
			session.setAttribute("name", user.getName());
		}
		return serverResponse;
	}
}
