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
	
	"${ctx}/customerloss/getCustomerLossInfoById.action", {
			id : '${param.id}'
		}, function(jsonData) {
			console.log(jsonData);
			$("#customerNo").val(jsonData.rows[0].customerNo);
			$("#customerName").val(jsonData.rows[0].customerName);
			$("#customerManager").val(jsonData.rows[0].customerManager);
			$("#lastOrderTime").val(jsonData.rows[0].lastOrderTime);
		}, "json");
	
	$('#cus_deferred_manage').edatagrid({    
	    url: '${ctx}/customerlossmeasure/getAllMeasureByLossId.action?id=${param.id}',    
	    saveUrl: '${ctx}/customerlossmeasure/addMeasureByLossId.action?lossId=${param.lossId}',    
	    //updateUrl: ...,    
	    destroyUrl:"${ctx}/customerlossmeasure/delete.action",
	  	columns:[[    
			 {field:'id',title:'编号',width:50,align:'center'},    
			 {field:'measure',title:'暂缓措施',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
		]]
	}); 
});
</script>



</head>
<body>
	<div id="cus_deferred_basic_div" class="easyui-panel" title="客户流失基本信息"
		style="width: 640px; height: 150px; padding: 5px;">
		<table cellspacing="8px">
			<tr>
				<td>客户编号：</td>
				<td><input type="text" id="customerNo" name="customerNo"
					readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>客户名称：</td>
				<td><input type="text" id="customerName" name="customerName"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td>客户经理：</td>
				<td><input type="text" id="customerManager"
					name="customerManager" readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>上次下单时间：</td>
				<td><input type="text" id="lastOrderTime" name="lastOrderTime"
					readonly="readonly" /></td>
			</tr>
		</table>

	</div>
	<br />
		<table class="easyui-datagrid"  style="width: 640px; height: 150px; padding: 5px;" id="cus_deferred_manage"
			data-options="rownumbers:true,singleSelect:true,toolbar:'#tb'"  idField="id">
		</table>
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:$('#cus_deferred_manage').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增措施</a>
				<a href="javascript:$('#cus_deferred_manage').edatagrid('destroyRow')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">销毁措施</a>
				<a href="javascript:$('#cus_deferred_manage').edatagrid('saveRow')" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存措施</a>
				<a href="javascript:$('#cus_deferred_manage').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true">撤销措施</a>
			</div>
			
		</div>
</body>
</html>