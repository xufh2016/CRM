<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="tt" class="easyui-datagrid"
		style="width: 600px; height: 250px"
		url="${ctx }/customer/pageList.action" rownumbers="true" fit=true
		fitColumns=true pagination="true"  toolbar='#tb'>
		<thead>
			<tr>
				<th field="id" width="80" id="customerId">Id</th>
				<th field="num" width="80">编号</th>
				<th field="name" width="80" align="right">名称</th>
				<th field="region" width="80" align="right">地址</th>
				<th field="manager_name" width="150">客户经理</th>
				<th field="level" width="60" align="center">等级</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="${ctx}/customer/getAddCustomerPage.action" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> 
			<a href="${ctx}/customer/deleteCustomer.action?id='+$('customerId').val()+'" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<div>
			<input class="easyui-textbox" data-options="prompt:'请输入编号'," style="width:18%;height:32px" />
			<input class="easyui-textbox" data-options="prompt:'请输入公司名称'," style="width:18%;height:32px" />
			<input class="easyui-textbox" data-options="prompt:'请输入编号'," style="width:18%;height:32px" />
			<a href="${ctx}/customer/searchByCondition.action" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		</div>
	</div>
</body>
</html>