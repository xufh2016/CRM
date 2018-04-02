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
			rownumbers:true,
			columns:[[
				{field:'cb',checkbox:true},
				{field:'id',title:'编号',align:'center'},
				{field:'chanceSource',title:'机会来源',align:'center'},
				{field:'customerName',title:'客户名称',align:'center'},
				{field:'successRate',title:'成功几率',align:'center'},
				{field:'overview',title:'概要',align:'center'},
				{field:'linkMan',title:'联系人',align:'center'},
				{field:'linkPhone',title:'联系电话',align:'center'},
				{field:'description',title:'机会描述',align:'center'},
				{field:'createMan',title:'创建人',align:'center'},
				{field:'createTime',title:'创建时间',align:'center'},
				{field:'assignMan',title:'指派人',align:'center'},
				{field:'assignTime',title:'指派时间',align:'center'},
				{field:'status',title:'分配状态',align:'center',
					formatter:function(value,row,index){
						if(value==1){
							return '已分配';
						}
						if(value==0){
							return '未分配';
						}
					}
				},
				{field:'devResult',title:'客户开发状态',align:'center',
					formatter:function(value,row,index){
						if(value==1){
							return '已开发';
						}
						else if(value==0){
							return '未开发';
						}else if(value==2){
							return '开发失败';
						}
					}	
				},
			]],
		});
		//openDialog();
		$(function(){
			$('#status').combobox({
				url : '${ctx}/salechance/getStatus.action',
				valueField : 'status',
				textField : 'value'
			});
		});
		
		
	});
	
	function doSearch(){
		console.log($('#status'));
		$('#tt').datagrid('load',{
			chanceSource: $('#chance_source').val(),
			customerName: $('#customer_name').val(),
			overview: $('#overview').val(),
			linkMan: $('#link_man').val(),
			status: $('#status').combobox('getValue'), 
			/*status: $('#status').val(), */
			description: $('#description').val(),
			assignMan: $('#assign_man').val(),
			beginTime: $('#begin_time').val(),
			endTime: $('#end_time').val(),
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
					url:'${ctx}/salechance/doDelete.action',
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
	function getAssignMan(){
		$('#dassign_man').combobox({    
		    url:'${ctx}/user/getAssignMan.action',    
		    valueField:'assignMan',    
		    textField:'assignMan',
		    onSelect:function(user){
		    	if(user.trueName!='')
	    		{
			    	$('#dassign_time').val(util.getCurrentDateTime());
	    		}else{
	    			$('#dassign_time').val('');
	    		}
		    }
		}); 
	}
	
	
	function doAdd(){
		openDialog();
		//为id=assign_man的下拉列表框填充数据
		getAssignMan();
		
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
		        		var url="${ctx}/salechance/doAdd.action";
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
		$("#dialogId").dialog({
			title:"添加用户",
			closed : true,
		    buttons : [
		        {
		        	text : "保存",
		        	iconCls : "icon-ok",
		        	handler : function() {
		        		var url="${ctx}/salechance/doUpdate.action";
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
		getAssignMan();
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
		/* $("#tt").datagrid(
			singleSelect:true,		
		); */
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
	
	
	function formatZero(n) {
		if (n >= 0 && n <= 9) {
			return "0" + n;
		} else {
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
	
	function doExport(){
		window.location.href="${ctx}/salechance/exportExcel.action";
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
			&nbsp; &nbsp; 
			<a href="javascript:doExport()" class="easyui-linkbutton"  plain="true">导出表格</a>
			<a href="javascript:doImport()" class="easyui-linkbutton"  plain="true">导入表格</a>
			<a href="javascript:doPrint()" class="easyui-linkbutton"  plain="true">打印表格</a>
		</div>
		
		<span>机会来源:</span>
		<input id="chance_source" class="easyui-textbox">
		<span>客户名称:</span>
		<input id="customer_name" class="easyui-textbox">
		<span>概要:</span>
		<input id="overview" class="easyui-textbox">
		<span>联系人:</span>
		<input id="link_man" class="easyui-textbox">
		<span>分配状态:</span>
		<!-- <input id="status" class="easyui-combobox" style="width:150px" panelHeight="auto"
			editable="false"></input> -->
		<select id="status" class="easyui-combobox" style="width:150px" panelHeight="auto"
			editable="false" >
			<option value="">-请选择-</option>
		</select>
		<div>
			<span>机会描述:</span>
			<input id="description" class="easyui-textbox">
			<span>指派人:</span>
			<input id="assign_man" class="easyui-textbox">
			<span>创建开始时间:</span>
			<input id="begin_time" type= "text" class="easyui-datebox">
			<span>创建结束时间:</span>
			<input id="end_time" type= "text" class="easyui-datebox">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
		</div>
	</div>
	
	
	<div id="dialogId" style="width: 620px; height: 340px; padding: 5px;">
		<form id="ff" method="post">
			<input type="hidden" name="id" id="id"/>
			<table cellspacing="8px">
			<tr>
				<td>客户名称：</td>
				<td><input type="text" id="dcustomer_name" name="customerName"
					/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>机会来源：</td>
				<td><input type="text" id="dchance_source" name="chanceSource"
					/></td>
			</tr>
			<tr>
				<td>联系人：</td>
				<td><input type="text" id="dlink_man"
					name="linkMan" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>联系电话：</td>
				<td><input type="text" id="dlink_phone" name="linkPhone"
					 /></td>
			</tr>
			<tr>
	   			<td>概要：</td>
	   			<td colspan="4"><input type="text" id="doverview" name="overview" style="width: 420px"/></td>
	   		</tr>
	   		<tr>
	   			<td>机会描述：</td>
	   			<td colspan="4">
	   				<textarea rows="5" cols="50" id="ddescription" name="description"></textarea>
	   			</td>
	   		</tr>
	   		<tr>
				<td>创建人：</td>
				<td><input type="text" id="dcreate_man"
					name="createMan" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>创建时间：</td>
				<td><input type="text" id="dcreate_time" name="createTime"
					 /></td>
			</tr>
			<tr>
				<td>指派给：</td>
				<td>
					<input id="dassign_man" class="easyui-combobox" name="assignMan"></input>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>指派时间：</td>
				<td>
					<input type="text" id="dassign_time" name="assignTimeassignTime"/>
				</td>
			</tr>
		</table>
		</form>
	</div>  
</body>
</html>