<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function doSearch() {
		$('#tt').datagrid('load', {
			num : $('#snum').val(),
			name : $('#sname').val(),
			region : $('#sregion').val(),
		});
	}
	function doDelete() {
		var ids = [];
		var rows = $('#tt').datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		if (ids.length == 0) {
			$.messager.alert("系统提示", "您尚未选择任何数据");
			return;
		}
		$.messager.confirm('系统提示', '您确定要删除这' + ids.length + '条数据吗?',
				function(r) {
					if (r) {
						ids = ids.join(',');
						$.ajax({
							url : '${ctx}/customer/doDelete.action',
							data : {
								ids : ids
							},
							dataType : 'json',
							type : 'post',
							success : function(jsonData) {
								$.messager.alert("系统提示", jsonData.msg);
								if (jsonData.code == util.SUCCESS) {
									$('#tt').datagrid('reload');
								}
							}
						});
					}
				});
	}

	function doAdd() {
		openDialog();
		$("#dialogId").dialog("open");
		$('#region').combobox({
			url : '${ctx}/customer/getRegion.action',
			valueField : 'region',
			textField : 'region',
		});
		$('#managerName').combobox({
			url : '${ctx}/customer/getManagerName.action',
			valueField : 'managerName',
			textField : 'managerName',
		});
		$('#level').combobox({
			url : '${ctx}/customer/getLevel.action',
			valueField : 'level',
			textField : 'level',
		});
		$('#credit').combobox({
			url : '${ctx}/customer/getCredit.action',
			valueField : 'credit',
			textField : 'credit',
		});
		$('#satisfy').combobox({
			url : '${ctx}/customer/getSatisfy.action',
			valueField : 'satisfy',
			textField : 'satisfy',
		});
		$('#ff').form('clear');
	}
	function openDialog() {
		//alert("1.openDialog()")
		$("#dialogId").dialog({
			title : "添加用户",
			closed : true,
			buttons : [ {
				text : "保存",
				iconCls : "icon-ok",
				handler : function() {
					var url = "${ctx}/customer/doAdd.action";
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
	}
	function openUpdateDialog() {
		//alert("1.openDialog()")
		$("#dialogId").dialog({
			title : "添加用户",
			closed : true,
			buttons : [ {
				text : "保存",
				iconCls : "icon-ok",
				handler : function() {
					var url = "${ctx}/customer/doUpdate.action";
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
	}

	function operatingData(url) {
		//alert("1.operatingData()");
		$('#ff').form('submit', {
			url : url,
			onSubmit : function() {
				if ($("#region").combobox("getValue") == ''
							|| $("#satisfy").combobox("getValue") == ''
							|| $("#managerName").combobox("getValue") == ''
							|| $("#level").combobox("getValue") == ''
							|| $("#credit").combobox("getValue") == '') {
						$.messager.alert("系统提示", "请选择下拉列表框内容");
						// return false to prevent submit;    
						return false;
					}
					return true;
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
	function doUpdate() {
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
</script>
<script type="text/javascript">
//<a href="javascript:linkManManagment()" class="easyui-linkbutton" iconCls="icon-lxr" plain="true">联系人管理</a>
//<a href="javascript:communicationRecord()" class="easyui-linkbutton" iconCls="icon-jwjl" plain="true">交往记录管理</a> 
//<a href="javascript:orderHistroy()" class="easyui-linkbutton" iconCls="icon-lsdd" plain="true">历史订单查看</a> 
function linkManManagment(id){
	var selections=$("#tt").datagrid("getSelections");
	if(selections.length==0){
		$.messager.alert("系统提示","您尚未选择任何数据，请选择您要操作的行。");
		return;
	}else{
		id=$("#tt").datagrid("getSelected").id;
		window.parent.addTab('联系人管理','${ctx}/customerlinkman/getLinkManDetailPage.action?id='+id,'icon-lxr');
	}
}
function communicationRecord(id){
	var selections=$("#tt").datagrid("getSelections");
	if(selections.length==0){
		$.messager.alert("系统提示","您尚未选择任何数据，请选择您要操作的行。");
		return;
	}else{
		id=$("#tt").datagrid("getSelected").id;
		window.parent.addTab('交往记录管理','${ctx}/customercontact/getContactDetailPage.action?id='+id,'icon-jwjl');
	}
}
function orderHistroy(id){
	var selections=$("#tt").datagrid("getSelections");
	if(selections.length==0){
		$.messager.alert("系统提示","您尚未选择任何数据，请选择您要操作的行。");
		return;
	}else{
		id=$("#tt").datagrid("getSelected").id;
		window.parent.addTab('历史订单管理','${ctx}/customerorder/getCustomerOrderPage.action?id='+id,'icon-lsdd');
	}
}

</script>

</head>
<body>
	<table id="tt" class="easyui-datagrid"
		style="width: 600px; height: 250px"
		url="${ctx}/customer/pageList.action" rownumbers="true" fit=true
		fitColumns=true pagination="true"  toolbar='#tb'>
		<thead>
			<tr>
				<th field="id" width="80" id="customerId">Id</th>
				<th field="num" width="80">编号</th>
				<th field="name" width="80" align="right">名称</th>
				<th field="address" width="80" align="right">地址</th>
				<th field="managerName" width="150">客户经理</th>
				<th field="level" width="60" align="center">客户等级</th>
				<th field="phone" width="60" align="center">联系电话</th>
				<th field="region" width="60" align="center">地区</th>
				<th field="satisfy" width="60" align="center">客户满意度</th>
				<th field="credit" width="60" align="center">客户信誉度</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="javascript:doAdd()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加客户</a>
			<a href="javascript:doUpdate()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">更改客户信息</a> 
			<a href="javascript:doDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除客户</a>
			<a href="javascript:linkManManagment()" class="easyui-linkbutton" iconCls="icon-lxr" plain="true">联系人管理</a>
			<a href="javascript:communicationRecord()" class="easyui-linkbutton" iconCls="icon-jwjl" plain="true">交往记录管理</a> 
			<a href="javascript:orderHistroy()" class="easyui-linkbutton" iconCls="icon-lsdd" plain="true">历史订单查看</a>
		</div>
		<div>
			<input class="easyui-textbox" id="snum" name="num" data-options="prompt:'请输入客户编号'," style="width:18%;height:32px" />
			<input class="easyui-textbox" id="sname" name="name" data-options="prompt:'请输入客户名称'," style="width:18%;height:32px" />
			<input class="easyui-textbox" id="sregion" name="region" data-options="prompt:'请输入客户地址'," style="width:18%;height:32px" />
			<a  class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
		</div>
	</div>

	<div id="dialogId" style="width:700;height:880,padding: 10px 20px">
		<form id="ff" method="post">
			<input type="hidden" name="id" id="id"/>
			<table cellspacing="8px">
				<tr>
					<td>客户名称：</td>
					<td><input type="text" id="name" name="name"
						class="easyui-validatebox" required="true" /><font color="red"> *</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户地区：</td>
					<td><select id="region" class="easyui-combobox" name="region"
						style="width: 150px" panelHeight="auto" editable="true">
					</select><font color="red">*</font></td>
				</tr>
				<tr>
					<td>客户经理：</td>
					<td><select id="managerName" class="easyui-combobox" name="managerName"
						style="width: 150px" panelHeight="auto" editable="true">
					</select><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户等级：</td>
					<td><select id="level" class="easyui-combobox" name="level"
						style="width: 150px" panelHeight="auto" editable="true">
					</select><font color="red">*</font></td>
				</tr>
				<tr>
					<td>客户信用度：</td>
					<td><select id="credit" class="easyui-combobox" name="credit"
						style="width: 150px" panelHeight="auto" editable="true">
					</select><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户满意度：</td>
					<td><select id="satisfy" class="easyui-combobox" name="satisfy"
						style="width: 150px" panelHeight="auto" editable="true">
					</select> <font color="red">*</font></td>
				</tr>
				<tr>
					<td>邮政编码：</td>
					<td><input type="text" id="postCode" name="postCode"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>联系电话：</td>
					<td><input type="text" id="phone" name="phone"
						class="easyui-validatebox" required="true" validType="email" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>传真：</td>
					<td><input type="text" id="fax" name="fax"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>网址：</td>
					<td><input type="text" id="webSite" name="webSite"
						class="easyui-validatebox" required="true" validType="email" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>客户地址：</td>
					<td><input type="text" id="address" name="address"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>营业执照注册号：</td>
					<td><input type="text" id="licenceNo" name="licenceNo"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>法人：</td>
					<td><input type="text" id="legalPerson" name="legalPerson"
						class="easyui-validatebox" required="true" validType="email" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>注册资金(万元)：</td>
					<td><input type="text" id="bankroll" name="bankroll"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>年营业额(万元)：</td>
					<td><input type="text" id="turnover" name="turnover"
						class="easyui-validatebox" required="true" validType="email" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>开户银行：</td>
					<td><input type="text" id="bankName" name="bankName"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>开户账号：</td>
					<td><input type="text" id="bankAccount" name="bankAccount"
						class="easyui-validatebox" required="true" validType="email" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>地税登记号：</td>
					<td><input type="text" id="localTaxNo" name="localTaxNo"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>国税登记号：</td>
					<td><input type="text" id="nationalTaxNo" name="nationalTaxNo"
						class="easyui-validatebox" required="true" validType="email" /><font
						color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>  
</body>
</html>