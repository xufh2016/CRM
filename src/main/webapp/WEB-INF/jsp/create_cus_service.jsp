<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$('#service_type').combobox({
	    url:'${ctx}/customerservice/getServiceType.action',
	    valueField:'serviceType',
	    textField:'serviceType'
	});
	$("#create_time").val(util.getCurrentDateTime());
	
	
});

function submitForm(){
	$('#ff').form('submit', {
	    url:"${ctx}/customerservice/addCustomerService.action",
	    onSubmit: function(){
	        // do some check
	        // return false to prevent submit;
	    },
	    success:function(jsonData){
	    	if(jsonData.code==util.SUCCESS){
	    		$.messager.alert(jsonData.msg);
	    		clearForm();
	    	}else{
	    		$.messager.alert(jsonData.msg);
	    	}
	    }
	});
}
function clearForm(){
	$("#ff").form('reset');
	$("#create_time").val(util.getCurrentDateTime());
}

</script>


</head>
<body>
	<div id="cus_deferred_basic_div" class="easyui-panel" title="销售机会基本信息"
		style="width: 640px; height: 300px; padding: 5px;">
		<form id="ff">
			<table cellspacing="8px">
				<tr>
					<td>服务类型：</td>
					<td><input type="text" id="service_type" name="serviceType" class="easyui-combobox"
						 /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户：</td>
					<td><input type="text" id="customer" name="customer"
						/></td>
				</tr>
				<tr>
		   			<td>概要：</td>
		   			<td colspan="4"><input type="text" id="overview" name="overview" style="width: 420px" /></td>
		   		</tr>
		   		<tr>
		   			<td>服务请求：</td>
		   			<td colspan="4">
		   				<textarea rows="5" cols="50" id="service_request" name="serviceRequest"></textarea>
		   			</td>
		   		</tr>
		   		<tr>
					<td>创建人：</td>
					<td><input type="text" id="create_people"
						name="createPeople" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>创建时间：</td>
					<td><input type="text" id="create_time" name="createTime"  
						/></td>
				</tr>
			</table>
			<div style="text-align:center;padding:5px">
		    	<a href="javascript:void(0)" iconCls="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
		    	<a href="javascript:void(0)" iconCls="icon-redo" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	   		</div>
		</form>
	</div>
</body>
</html>