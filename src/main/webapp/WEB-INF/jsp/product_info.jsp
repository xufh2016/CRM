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
			url:'${ctx}/product/pageList.action',
			method:"get",
			pagination:true,
			fitColumns:true,
			fit:true,
			toolbar:'#tb',
			rownumbers:true,
			columns:[[
				{field:'cb',checkbox:true},
				{field:'id',title:'编号',align:'center'},
				{field:'name',title:'产品名称',align:'center'},
				{field:'model',title:'型号',align:'center'},
				{field:'unit',title:'单位',align:'center'},
				{field:'price',title:'价格',align:'center'},
				{field:'stock',title:'库存',align:'center'},
				{field:'remark',title:'备注',align:'center'},
			]],
		});
		//openDialog();
	});
	
	
	function doSearch(){
		$('#tt').datagrid('load',{
			name: $('#s_name').val(),
			model: $('#s_model').val(),
			price: $('#s_price').val(),
			stock: $('#s_stock').val(),
			remark: $('#s_remark').val(),
		});
	}
	function doDelete(){
		var ids = [];
		var rows = $('#tt').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].id);
		} 
		if(ids.length==0){
			$.messager.alert("系统提示","您尚未选择任何数据");
			return;
		} 
		$.messager.confirm('系统提示', '您确定要删除这'+ids.length+'条数据吗?', function(r){
			if (r){
				ids=ids.join(',');
				$.ajax({
					url:'${ctx}/product/doDelete.action',
					data:{ids:ids},
					dataType:'json',
					type:'post',
					success:function(jsonData){
						$.messager.alert("系统提示",jsonData.msg);
						if(jsonData.code == util.SUCCESS){
							$('#tt').datagrid('reload');
						}
					}
				});
			}
		});
	}
	
	
	function doAdd(){
		openDialog();
		$("#dialogId").dialog("open");
		$('#ff').form('clear');
	}
	function openDialog(){
		//alert("1.openDialog()")
		$("#dialogId").dialog({
			title:"添加商品",
			closed : true,
		    buttons : [
		        {
		        	text : "保存",
		        	iconCls : "icon-ok",
		        	handler : function() {
		        		var url="${ctx}/product/doAdd.action";
		        		operatingData(url);
		        	}
		        },
		        {
		        	text : "关闭",
		        	iconCls : "icon-cancel",
		        	handler : function() {
		        		$("#dialogId").dialog("close");
		        	}
		        }
		    ]
		}); 
	}
	function openUpdateDialog(){
		//alert("1.openDialog()")
		$("#dialogId").dialog({
			title:"更新商品",
			closed : true,
		    buttons : [
		        {
		        	text : "保存",
		        	iconCls : "icon-ok",
		        	handler : function() {
		        		var url="${ctx}/product/doUpdate.action";
		        		operatingData(url);
		        	}
		        },
		        {
		        	text : "关闭",
		        	iconCls : "icon-cancel",
		        	handler : function() {
		        		$("#dialogId").dialog("close");
		        	}
		        }
		    ]
		}); 
	}
	
	function operatingData(url){
		//alert("1.operatingData()");
		$('#ff').form('submit', {    
		    url:url,    
		    onSubmit: function(){  
		    	//alert("2."+url);
		        // do some check
		    	return $(this).form('enableValidation').form('validate');
		    },    
		    success:function(data){    
		    	var jsonData = eval('(' + data + ')');  // change the JSON string to javascript object    
		    	$.messager.alert("系统提示", jsonData.msg);
		    	if(jsonData.code==util.SUCCESS){
		    		$("#dialogId").dialog("close");
					$("#tt").datagrid("reload");
		    	}
		    }    
		});  

	}
	function doUpdate(){
		var selections = $("#tt").datagrid("getSelections");
		if(selections.length == 0) {
			$.messager.alert("系统提示", "请选择要修改的行");
			return;
		}
		var row = selections[0];
		openUpdateDialog();
		$("#dialogId").dialog("open"); 
		$("#ff").form("load", row);
	}
	
</script>



</head>
<body>
	<table id="tt" class="easyui-datagrid"></table>
	<div id="tb" style="padding:3px">
		<div>
			<a href="javascript:doAdd()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="javascript:doUpdate()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a href="javascript:doDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<span>产品名称:</span>
		<input id="s_name" class="easyui-textbox">
		<span>型号:</span>
		<input id="s_model" class="easyui-textbox">
		
		<span>价格:</span>
		<input id="s_price" class="easyui-textbox">
		<span>库存:</span>
		<input id="s_stock" class="easyui-textbox">
		<span>备注:</span>
		<input id="s_remark" class="easyui-textbox">
		
		<a  class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
	</div>
	
	
	<div id="dialogId" style="width:650;height:280,padding: 10px 20px">
		<form id="ff" method="post">
			<input type="hidden" name="id" id="id"/>
			<table cellspacing="8px">
				<tr>
					<td>产品名称：</td>
					<td><input type="text" id="name" name="name"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>型号：</td>
					<td><input type="text" id="model" name="model"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>单位：</td>
					<td><input type="text" id="unit" name="unit"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>价格：</td>
					<td><input type="text" id="price" name="price"
						class="easyui-validatebox" required="true" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>库存：</td>
					<td><input type="text" id="stock" name="stock"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>备注：</td>
					<td><input type="text" id="remark" name="remark"
						class="easyui-validatebox" required="true"  /><font
						color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>  
</body>
</html>