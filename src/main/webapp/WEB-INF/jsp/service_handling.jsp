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
	$('#tt').datagrid({
	    url:'${ctx}/customerservice/getAllNotHandleService.action',
	    toolbar:'#tb',
	    columns:[[
	        {field:'id',title:'编号',width:100},
	        {field:'customer',title:'客户',width:100},
	        {field:'overview',title:'概要',width:100,align:'right'},
	        {field:'serviceType',title:'服务类型 ',width:100,align:'right'},
	        {field:'createPeople',title:'创建人',width:100,align:'right'},
	        {field:'createTime',title:'创建日期',width:100,align:'right'},
	        {field:'assigner',title:'分配人',width:100,align:'right'},
	        {field:'assignTime',title:'分配日期',width:100,align:'right'},
	    ]]
	});
	openUpdateDialog();
});
	

	function getAssignMan() {
		$('#dassign_man').combobox({
			url : '${ctx}/user/getAssignMan.action',
			valueField : 'assignMan',
			textField : 'assignMan',
		});
	}

	function openUpdateDialog() {
		$("#dialogId").dialog({
			title : "处理服务",
			closed : true,
			buttons : [ {
				text : "保存",
				iconCls : "icon-ok",
				handler : function() {
					var url = "${ctx}/customerservice/doServiceHandling.action";
					operatingData(url);
				}
			}, {
				text : "关闭",
				iconCls : "icon-cancel",
				handler : function() {
					$("#dialogId").dialog("close");
				}
			} ]
		});
		getAssignMan();
	}

	function operatingData(url) {
		//alert("1.operatingData()");
		$('#ff').form('submit', {
			url : url,
			onSubmit : function() {
				//alert("2."+url);
				// do some check
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var jsonData = eval('(' + data + ')'); // change the JSON string to javascript object    
				$.messager.alert("系统提示", jsonData.msg);
				if (jsonData.code == util.SUCCESS) {
					$("#dialogId").dialog("close");
					$("#tt").datagrid("reload");
				}
			}
		});

	}
	function doAssignment() {

		var selections = $("#tt").datagrid("getSelections");
		if (selections.length == 0) {
			$.messager.alert("系统提示", "请选择要修改的行");
			return;
		}
		var row = selections[0];
		openUpdateDialog();
		$("#dialogId").dialog("open");
		$("#ff").form("load", row);
	}

	function formatZero(n) {
		if (n >= 0 && n <= 9) {
			return "0" + n;
		} else {
			return n;
		}
	}

	$('#createTime').datebox({
		required : true,
		formatter : $.fn.datebox.defaults.formatter = function(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			return y + '-' + formatZero(m) + '-' + formatZero(d);
		}
	});
	$('#assignTime').datebox({
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
	<div id="tt" class="easyui-datagrid"></div>
	<div id="tb" style="padding:3px">
		<div>
			<a href="javascript:doAssignment()" class="easyui-linkbutton" iconCls="icon-add" plain="true">处理服务</a>
		</div>
	</div>
	
	
	<div id="dialogId" class="easyui-dialog" style="width: 620px; height: 340px; padding: 5px;">
		<form id="ff" method="post">
			<input type="hidden" name="id" id="id"/>
			<table cellspacing="8px">
			<tr>
				<td>服务类型：</td>
				<td><input type="text" id="serviceType" name="serviceType"
					/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>客户：</td>
				<td><input type="text" id="customer" name="customer"
					/></td>
			</tr>
			<tr>
	   			<td>概要：</td>
	   			<td colspan="4"><input type="text" id="doverview" name="overview" style="width: 420px"/></td>
	   		</tr>
	   		<tr>
	   			<td>服务请求：</td>
	   			<td colspan="4">
	   				<textarea rows="5" cols="50" id="serviceRequest" name="serviceRequest"></textarea>
	   			</td>
	   		</tr>
	   		<tr>
				<td>创建人：</td>
				<td><input type="text" id="createPeople"
					name="createPeople" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>创建时间：</td>
				<td><input type="text" id="createTime" name="createTime"
					 /></td>
			</tr>
			<tr>
				<td>指派给：</td>
				<td>
					<input id="dassign_man" class="easyui-combobox" name="assigner"></input>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>指派时间：</td>
				<td>
					<input type="text" id="assignTime" name="assignTime"/>
				</td>
			</tr>
			<tr>
	   			<td>服务处理：</td>
	   			<td colspan="4">
	   				<textarea rows="5" cols="50" id="serviceDeal" name="serviceDeal"></textarea>
	   			</td>
	   		</tr>
		</table>
		</form>
	</div>  
</body>
</html>