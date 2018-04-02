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
			url:'${ctx}/datadic/pageList.action',
			method:"get",
			pagination:true,
			fitColumns:true,
			fit:true,
			toolbar:'#tb',
			rownumbers:true,
			columns:[[
				{field:'cb',checkbox:true},
				{field:'id',title:'编号',align:'center'},
				{field:'dataDicName',title:'数据字典名称',align:'center'},
				{field:'dataDicValue',title:'数据字典值',align:'center'},
			]],
		});
		//openDialog();
	});
	
	function doAjax(){
		$.ajax({
			url : "${ctx}/datadic/getDataDicName.action",
			dataType : 'json',
			type : 'post',
			success : function(jsonData) {
				var data=jsonData.data
				var str="<option value=''>--请选择--</option>";
				for (var i = 0; i < data.length; i++) {
					str += "<option  value="+data[i]+">" + data[i] + "</option>";
				}
				$("#d_name").html(str);
			}
		});
	}
	
	function doSearch(){
		$('#tt').datagrid('load',{
			dataDicName: $('#d_name').val(),
			dataDicValue: $('#d_value').val(),
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
					url:'${ctx}/datadic/doDelete.action',
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
				//window.location.reload();
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
			title:"添加数据字典",
			closed : true,
		    buttons : [
		        {
		        	text : "保存",
		        	iconCls : "icon-ok",
		        	handler : function() {
		        		var url="${ctx}/datadic/doAdd.action";
		        		operatingData(url);
		        		window.location.reload();
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
			title:"修改数据字典",
			closed : true,
		    buttons : [
		        {
		        	text : "保存",
		        	iconCls : "icon-ok",
		        	handler : function() {
		        		var url="${ctx}/datadic/doUpdate.action";
		        		operatingData(url);
		        		window.location.reload();
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
		//$("#d_name").doAjax();
	}
	
</script>

<script type="text/javascript">
//下拉列表框的ajax请求功能
 	$(function(){
 		doAjax();
	});

	function doDicNameAjax() {
		//alert("发生了点击事件；doDicNameAjax");
		doDicValueAjax();
	}
	
	function doDicValueAjax(){
		var dicName=$("#d_name").val();
		$.ajax({
			url:"${ctx}/datadic/getDataDicValue.action",
			dataType:'json',
			data:{dicName:dicName},
			type:'post',
			success:function(jsonData){
				var data=jsonData.data;
				var str="<option value=''>--请选择--</option>";
				for (var i = 0; i < data.length; i++) {
					str += "<option  value="+data[i]+">" + data[i] + "</option>";
				}
				$("#d_value").html(str);
			}
		});
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
		
		<span>数据字典名称:</span> 
		<select id="d_name"  style="width:150px;height:22px" panelHeight="auto" onchange="doDicNameAjax()" 
			editable="false" >
		</select>
		<span>数据字典值:</span>
		<select id="d_value"  style="width:150px;height:22px" panelHeight="auto" 
			editable="false" >
		</select>
		<a  class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
	</div>
	
	
	<div id="dialogId" style="width:650;height:280,padding: 10px 20px">
		<form id="ff" method="post">
			<input type="hidden" name="id" id="id"/>
			<table cellspacing="8px">
				<tr>
					<td>数据字典名称：</td>
					<td><input type="text" id="dataDicName" name="dataDicName"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>数据字典值：</td>
					<td><input type="text" id="dataDicValue" name="dataDicValue"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>  
</body>
</html>