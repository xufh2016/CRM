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
	"${ctx}/salechance/getCustomerDevPlan.action", {
			id : '${param.saleChanceId}'
		}, function(jsonData) {
			console.log(jsonData);
			$("#customer_name").val(jsonData.data.customerName);
			$("#chance_source").val(jsonData.data.chanceSource);
			$("#link_man").val(jsonData.data.linkMan);
			$("#link_phone").val(jsonData.data.linkPhone);
			$("#overview").val(jsonData.data.overview);
			$("#description").val(jsonData.data.description);
			$("#create_man").val(jsonData.data.createMan);
			$("#create_time").val(jsonData.data.createTime);
			$("#assign_man").val(jsonData.data.assignMan);
			$("#assign_time").val(jsonData.data.assignTime);
		}, "json");
	
	$('#cus_deferred_manage').edatagrid({    
	    url: '${ctx}/cusdevplan/getMeasuresBySaleChanceId.action?saleChanceId=${param.saleChanceId}',    
	    saveUrl: '${ctx}/cusdevplan/addDevPlan.action?saleChanceId=${param.saleChanceId}',    
	    updateUrl:'${ctx}/cusdevplan/updateDevPlan.action?saleChanceId=${param.saleChanceId}',    
	    destroyUrl:"${ctx}/cusdevplan/deleteDevPlan.action",
	  	columns:[[    
			 {field:'id',title:'编号',width:50,align:'center'},    
			 {field:'planDate',title:'日期',width:100,align:'center',id:'plan_date',editor:{type:'datebox',options:{required:true}}},    
			 {field:'planItem',title:'计划项',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			 {field:'exeAffect',title:'执行效果',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
		]],
		onSuccess : function(){
			$("#cus_deferred_manage").edatagrid('reload');
		},
		onDestroy : function(){
			$("#cus_deferred_manage").edatagrid('reload');
		}
	}); 
});
function formatZero(n){
    if(n>=0&&n<=9){
        return "0"+n;
    }else{
        return n;
    }
}

$('#plan_date').datebox({
	required : true,
	formatter : $.fn.datebox.defaults.formatter = function(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + formatZero(m) + '-' + formatZero(d);
	}
});


function updateSaleChanceDevResult(devResult){
	$.post(
		"${ctx}/salechance/updateDevResult.action",
		{saleChanceid:'${param.saleChanceId}',devResult:devResult},
		function(result){
			if(result.status==util.SUCCESS){
				$.messager.alert("系统提示","执行成功!");
				$("#tb").hide();
			}else{
				$.messager.alert("系统提示","执行失败！");
			}
		},"json"
	);
}


</script>

</head>
<body>
	<div id="cus_deferred_basic_div" class="easyui-panel" title="销售机会基本信息"
		style="width: 640px; height: 300px; padding: 5px;">
		<table cellspacing="8px">
			<tr>
				<td>客户名称：</td>
				<td><input type="text" id="customer_name" name="customerName"
					readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>机会来源：</td>
				<td><input type="text" id="chance_source" name="chanceSource"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td>联系人：</td>
				<td><input type="text" id="link_man"
					name="linkMan" readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>联系电话：</td>
				<td><input type="text" id="link_phone" name="linkPhone"
					readonly="readonly" /></td>
			</tr>
			<tr>
	   			<td>概要：</td>
	   			<td colspan="4"><input type="text" id="overview" name="overview" style="width: 420px" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>机会描述：</td>
	   			<td colspan="4">
	   				<textarea rows="5" cols="50" id="description" name="description" readonly="readonly"></textarea>
	   			</td>
	   		</tr>
	   		<tr>
				<td>创建人：</td>
				<td><input type="text" id="create_man"
					name="createMan" readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>创建时间：</td>
				<td><input type="text" id="create_time" name="createTime"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td>指派给：</td>
				<td><input type="text" id="assign_man""
					name="assignMan" readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>指派时间：</td>
				<td><input type="text" id="assign_time" name="assignTimeassignTime"
					readonly="readonly" /></td>
			</tr>
		</table>

	</div>
	<br />
	<table  id="cus_deferred_manage" style="width: 640px; height: 300px; "
		data-options="rownumbers:true,singleSelect:true,toolbar:'#tb'"  idField="id">
	</table>
	<div id="tb" style="padding:1px;width:638px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:$('#cus_deferred_manage').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增措施</a>
			<a href="javascript:$('#cus_deferred_manage').edatagrid('destroyRow')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">销毁措施</a>
			<a href="javascript:$('#cus_deferred_manage').edatagrid('saveRow')" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存措施</a>
			<a href="javascript:$('#cus_deferred_manage').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true">撤销措施</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-kfcg" plain="true" onclick="updateSaleChanceDevResult(2)">开发成功</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" onclick="updateSaleChanceDevResult(3)">终止开发</a>
		</div>
	</div>
</body>
</html>