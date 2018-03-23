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
			url:'${ctx}/user/pageList.action',
			method:"get",
			pagination:true,
			fitColumns:true,
			fit:true,
			toolbar:'#tb',
			rownumbers:true,
			columns:[[
				{field:'cb',checkbox:true},
				{field:'id',title:'编号',align:'center'},
				{field:'name',title:'用户名',align:'center'},
				{field:'trueName',title:'姓名',align:'center'},
				{field:'email',title:'邮箱',align:'center'},
				{field:'phone',title:'电话',align:'center'},
				{field:'roleName',title:'权限',align:'center'},
			]],
		});
		//openDialog();
	});
	
	
	function doSearch(){
		$('#tt').datagrid('load',{
			s_name: $('#s_name').val(),
			s_truename: $('#s_truename').val(),
			s_email: $('#s_email').val(),
			s_phone: $('#s_phone').val(),
			s_rolename: $('#s_rolename').val(),
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
					url:'${ctx}/user/doDelete.action',
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
			title:"添加用户",
			closed : true,
		    buttons : [
		        {
		        	text : "保存",
		        	iconCls : "icon-ok",
		        	handler : function() {
		        		var url="${ctx}/user/doAdd.action";
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
			title:"添加用户",
			closed : true,
		    buttons : [
		        {
		        	text : "保存",
		        	iconCls : "icon-ok",
		        	handler : function() {
		        		var url="${ctx}/user/doUpdate.action";
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
		        if($("#roleName").combobox("getValue")==''){
		        	$.messager.alert("系统提示","请选择权限");
			        // return false to prevent submit;    
		        	return false;
		        }
	        	return true;
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
		//$("#dialogId").dialog("open").dialog("setTitle", "修改信息");
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="javascript:doDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<span>用户名:</span>
		<input id="s_name" class="easyui-textbox">
		<span>姓名:</span>
		<input id="s_truename" class="easyui-textbox">
		<span>邮箱:</span>
		<input id="s_email" class="easyui-textbox">
		<span>电话:</span>
		<input id="s_phone" class="easyui-textbox">
		<span>权限:</span>
		<select id="s_rolename" class="easyui-combobox" style="width:100px" panelHeight="auto"
			editable="false" >
			<option value="">请选择...</option>
			<option value="系统管理员">系统管理员</option>
			<option value="销售主管">销售主管</option>
			<option value="客户经理">客户经理</option>
			<option value="高管">高管</option>
		</select>
		<a  class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
	</div>
	
	
	<div id="dialogId" style="width:650;height:280,padding: 10px 20px">
		<form id="ff" method="post">
			<input type="hidden" name="id" id="id"/>
			<table cellspacing="8px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="name" name="name"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>密码：</td>
					<td><input type="text" id="password" name="password"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" id="trueName" name="trueName"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>邮箱：</td>
					<td><input type="text" id="email" name="email"
						class="easyui-validatebox" required="true" validType="email" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td><input type="text" id="phone" name="phone"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>用户角色：</td>
					<td>
						<select class="easyui-combobox" id="roleName" 
							panelHeight="auto" editable="false" name="roleName"
							style="width: 152">
								<option selected value="">请选择...</option>
								<option value="系统管理员">系统管理员</option>
								<option value="销售主管">销售主管</option>
								<option value="客户经理">客户经理</option>
								<option value="高管">高管</option>
						</select> 
						<font color="red">*</font>
					</td>
				</tr>
			</table>
		</form>
	</div>  
</body>
</html>