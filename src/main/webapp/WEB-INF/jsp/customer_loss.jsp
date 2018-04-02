<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#ff>table>tr>td{
		align-content: center;
	}

</style>
<script type="text/javascript">
	$(function(){
		$("#tt").datagrid({
			url:'${ctx}/customerloss/pageList.action',
			method:"get",
			pagination:true,
			fitColumns:true,
			fit:true,
			toolbar:'#tb',
			rownumbers:true,
			singleSelect:true,
			columns:[[
				{field:'cb',checkbox:true},
				{field:'customerNo',title:'客户编号',align:'center'},
				{field:'customerName',title:'客户姓名',align:'center'},
				{field:'customerManager',title:'客户经理',align:'center'},
				{field:'lastOrderTime',title:'上次订单时间',align:'center'},
				{field:'confirmLossTime',title:'确认流失时间',align:'center'},
				{field:'lossReason',title:'流失原因',align:'center'},
				{field:'status',title:'分配状态',align:'center',
					formatter:function(value,row,index){
						if(value==1){
							return '确认流失';
						}
						if(value==0){
							return '暂缓流失';
						}
					}	
				},
				{field:'a',title:'操作',align:'center',
					formatter:function(value,row,index){
						console.log(row);
						if(row.status==1){
							return '<span>客户确认流失</span>';
						}
						if(row.status==0){
							return "<a href='javascript:getDeferredLossTab("+row.id+")'>暂缓客户流失</a>";
						}
					}		
				},
			]],
		});
		//openDialog();
		$('#status').combobox({    
		    url:'${ctx}/customerloss/getStatus.action',    
		    valueField:'status',    
		    textField:'value',
		});  
	});
	
	
	function doSearch(){
		$('#tt').datagrid('load',{
			customerName: $('#customerName').val(),
			customerManager: $('#customerManager').val(),
			status: $('#status').val(),
		});
	}
	
	function getDeferredLossTab(id){
		window.parent.addTab('客户暂缓流失措施管理','${ctx}/customerlossmeasure/getDeferredLossPage.action?id='+id,'icon-khlsgl');
	}
	
</script>

</head>
<body>
	<table id="tt" class="easyui-datagrid" ></table>
	<div id="tb" style="padding:3px">
		<span>用户名:</span>
		<input id="customerName" class="easyui-textbox" name="customerName">
		<span>姓名:</span>
		<input id="customerManager" class="easyui-textbox" name="customerManager">
		<span>分配状态:</span>
		<select id="status" class="easyui-combobox" style="width:100px" panelHeight="auto" name="status"
			editable="false" >
		</select>
		<a  class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
	</div>
	
</body>
</html>