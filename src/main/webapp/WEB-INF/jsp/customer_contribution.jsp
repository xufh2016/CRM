<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			url : '${ctx}/count/customerContribution.action',
			fitColumns:true,
			fit:true,
			columns : [ [ 
					{field : 'customerName',title : '客户名称',width : 200},
					{field : 'totalPrice',title : '总金额',width : 200}, 
				] ]
		});
	});
</script>
</head>
<body>
	<table id="dg"></table>
</body>
</html>