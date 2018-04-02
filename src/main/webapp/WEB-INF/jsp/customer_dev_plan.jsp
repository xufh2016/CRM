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
			url:'${ctx}/salechance/pageList.action',
			method:"get",
			pagination:true,
			fitColumns:true,
			fit:true,
			toolbar:'#tb',
			singleSelect:true, 
			rownumbers:true,
			columns:[[
				{field:'cb',checkbox:true},
				{field:'id',title:'编号',align:'center'},
				{field:'customerName',title:'客户名称',align:'center'},
				{field:'overview',title:'概要',align:'center'},
				{field:'linkMan',title:'联系人',align:'center'},
				{field:'createMan',title:'创建人',align:'center'},
				{field:'createTime',title:'创建时间',align:'center'},
				{field:'assignMan',title:'指派人',align:'center'},
				{field:'assignTime',title:'指派时间',align:'center'},
				{field:'devResult',title:'客户开发状态',align:'center',
					formatter:function(value,row,index){
						if(value==1){
							return '开发中';
						}
						else if(value==0){
							return '未开发';
						}else if(value==2){
							return '开发成功';
						}else if(value==3){
							return '开发失败';
						}
					}	
				},
				{field:'a',title:'操作',align:'center',
					formatter:function(value,row,index){
						console.log(row);
						if(row.devResult==1||row.devResult==0){
							return "<a href='javascript:getDevlopTab("+row.id+")'>开发</a>";
						}
						if(row.devResult==2||row.devResult==3){
							return "<a href='javascript:getDetailTab("+row.id+")'>查看详细信息</a>";
						}
					}		
				},
			]],
		});
		//openDialog();
		$(function(){
			$('#dev_result').combobox({
				url : '${ctx}/salechance/getDevResult.action',
				valueField : 'devResult',
				textField : 'value',
			});
		});
	});
	
	function getDevlopTab(id){
		window.parent.addTab('客户开发计划项管理','${ctx}/cusdevplan/develop.action?saleChanceId='+id+'&show=true','icon-khkfjh');
	}
	function getDetailTab(id){
		window.parent.addTab('查看客户开发计划项','${ctx}/cusdevplan/detail.action?saleChanceId='+id,'icon-khkfjh');
	}
	function doSearch(){
		alert($('#dev_result').combobox('getValue'));
		$('#tt').datagrid('load',{
			customerName: $('#customer_name').val(),
			assignMan: $('#assign_man').val(),
			devResult: $('#dev_result').combobox('getValue'),
			beginTime: $('#begin_time').val(),
			endTime: $('#end_time').val(),
		});
	}
	function formatZero(n){
	     if(n>=0&&n<=9){
	         return "0"+n;
	     }else{
	         return n;
	     }
	 }

	$('#begin_time').datebox({
		required : true,
		formatter : $.fn.datebox.defaults.formatter = function(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			return y + '-' + formatZero(m) + '-' + formatZero(d);
		}
	});
	$('#end_time').datebox({
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
	<table id="tt" class="easyui-datagrid"></table>
	<div id="tb" style="padding:3px">
		<span>客户名称:</span>
		<input id="customer_name" class="easyui-textbox">
		<span>指派人:</span>
		<input id="assign_man" class="easyui-textbox">
		<span>开发状态:</span>
		<select id="dev_result" class="easyui-combobox" style="width:150px" panelHeight="auto"
			editable="false" >
		</select>
			<span>创建开始时间:</span>
			<input id="begin_time" type= "text" class="easyui-datebox">
			<span>创建结束时间:</span>
			<input id="end_time" type= "text" class="easyui-datebox">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
	</div>
</body>
</html>