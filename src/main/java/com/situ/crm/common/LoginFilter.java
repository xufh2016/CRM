package com.situ.crm.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		String servletPath = request2.getServletPath();
		String uri = request2.getRequestURI();
		System.out.println(uri + "--------------------doFilter");

		System.out.println(servletPath + "----------------doFilter");
		int lastIndex = servletPath.lastIndexOf(".");
		String substring = "";
		if (lastIndex != -1) {
			substring = servletPath.substring(lastIndex);
		}

		System.out.println(servletPath);
		StringBuffer requestURL = request2.getRequestURL();
		if ("/login/checkLoginParam.action".equalsIgnoreCase(servletPath)
				|| "/login/login.action".equalsIgnoreCase(servletPath) || ".css".equalsIgnoreCase(substring)
				|| ".js".equalsIgnoreCase(substring) || ".gif".equalsIgnoreCase(substring)
				|| ".png".equalsIgnoreCase(substring)) {
			chain.doFilter(request, response);
			return;
		} else {
			HttpSession session = request2.getSession();
			String name = (String) session.getAttribute("name");
			if (null == name) {
				response2.sendRedirect(request2.getContextPath() + "/login/login.action");
				return;
			}else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
