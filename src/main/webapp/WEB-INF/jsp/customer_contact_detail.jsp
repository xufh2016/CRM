<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
$(function(){
	$.post(
	
	"${ctx}/customer/getCustomerBasicInfoById.action", {
			id : '${param.id}'
		}, function(jsonData) {
			$("#customerNum").val(jsonData.rows[0].num);
			$("#customerName").val(jsonData.rows[0].name);
		}, "json");
	
	$('#link_man').edatagrid({    
		//url string 通过URL向服务器检索数据。 
	    url: '${ctx}/customercontact/getAllCusContactByCusId.action?customerId=${param.id}',
	    //saveUrl string 通过URL保存数据到服务器并返回添加的行。 
	    saveUrl:'${ctx}/customercontact/saveCustomerContactInfo.action?customerId=${param.id}',  
	    //updateUrl string 通过URL更新数据到服务器并返回更新的行。 		
	   	updateUrl:'${ctx}/customercontact/updateCustomerContactInfo.action?customerId=${param.id}',    
	   	//destroyUrl string 通过URL将'id'参数发送到服务器以销毁行。 
	    destroyUrl:"${ctx}/customercontact/delete.action",
	   fitColumns:true,
	   columns:[[    
			 {field:'id',title:'编号',width:50,align:'center',editor:{type:'text'}},    
			 {field:'customerId',title:'客户编号',width:50,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			 {field:'time',id:'time',title:'交往时间',width:50,align:'center',editor:{type:'datebox',options:{required:true}}},    
			 {field:'address',title:'交往地点',width:50,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			 {field:'overview',title:'概要',width:50,align:'center',editor:{type:'validatebox',options:{required:true}}},    
		]]
	}); 
});
</script>
<script type="text/javascript">
//事件格式转换
function formatZero(n) {
	if (n >= 0 && n <= 9) {
		return "0" + n;
	} else {
		return n;
	}
}

$('#time').datebox({
	required : true,
	formatter : $.fn.datebox.defaults.formatter = function(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + formatZero(m) + '-' + formatZero(d);
	}
});

</script>

</head>
<body>
	<div id="cus_deferred_basic_div" class="easyui-panel" title="客户基本信息"
		style="width: 640px; height: 150px; padding: 5px;">
		<table cellspacing="8px" >
			<tr>
				<td>客户编号：</td>
				<td><input type="text" id="customerNum" name="num"
					readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>客户名称：</td>
				<td><input type="text" id="customerName" name="name"
					readonly="readonly" /></td>
			</tr>
		</table>

	</div>
	<br />
		<table  style="width: 640px; height: 350px; padding: 5px;" id="link_man"
			data-options="rownumbers:'true',singleSelect:'true',toolbar:'#tb'"  idField="id">
		</table>
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:$('#link_man').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增措施</a>
				<a href="javascript:$('#link_man').edatagrid('destroyRow')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">销毁措施</a>
				<a href="javascript:$('#link_man').edatagrid('saveRow')" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存措施</a>
				<a href="javascript:$('#link_man').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true">撤销措施</a>
				<a href="javascript:$('#link_man').edatagrid('editRow')" class="easyui-linkbutton" iconCls="icon-edit" plain="true">更新措施</a>
			</div>
			
		</div>
</body>
</html>