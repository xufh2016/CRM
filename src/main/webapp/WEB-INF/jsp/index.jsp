<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp" %>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			.easyui-accordion .easyui-linkbutton {
				width: 230px;
				margin: 5px;
			}
		</style>
	</head>

	<body>
		<div id="panelId" class="easyui-panel" title="客户关系管理系统" data-options="fit:true,iconCls:'icon-man',collapsible:true,minimizable:true,maximizable:true,closable:true">
			<div class="easyui-layout" data-options="fit:true ">
				<div data-options="region:'north'" style="height:85px; font-size: x-large;" align="absmiddle">
					CRM System
					<div class="easyui-layout" data-options="fit:true ">
						<div id="" data-options="region:'east'" align="center" style="border :hidden;width: 260px;">
							<label style="margin: 5px;font-size:16px;">&nbsp;用&nbsp;户：</label><span style="margin: 5px;font-size:16px;"></span>
							<br/><br/>
							<label style="margin: 5px;font-size:16px;">权&nbsp;限：</label><span style="margin: 5px;font-size:16px;"></span>
						</div>
					</div>
				</div>
				<div data-options="region:'west',split:true,iconCls:'icon-nav'" title="导航菜单" style="width:252px;">
					<div class="easyui-accordion" data-options="fit:true,border:false">
						<div title="客户管理" data-options="selected:true,iconCls:'icon-khgl'" style="overflow: auto;">
							<a href="javascript:addTab('客户信息管理','customerManage.jsp','icon-khxxgl')" class="easyui-linkbutton" data-options="iconCls:'icon-khxxgl'">客户信息管理</a>
							<a href="javascript:addTab('客户流失管理','customerLossManage.jsp','icon-khlsgl')" class="easyui-linkbutton" data-options="iconCls:'icon-khlsgl'">客户流失管理</a>

						</div>
						<div title="服务管理" data-options="iconCls:'icon-fwgl'">
							<a href="javascript:addTab('服务创建','customerServiceCreate.jsp','icon-fwcj')" class="easyui-linkbutton" data-options="iconCls:'icon-fwcj'">服务创建</a>
							<a href="javascript:addTab('服务分配','customerServiceAssign.jsp','icon-fwfp')" class="easyui-linkbutton" data-options="iconCls:'icon-fwfp'">服务分配</a>
							<a href="javascript:addTab('服务处理','customerServiceProce.jsp','icon-fwcl')" class="easyui-linkbutton" data-options="iconCls:'icon-fwcl'">服务处理</a>
							<a href="javascript:addTab('服务反馈','customerServiceFeedback.jsp','icon-fwfk')" class="easyui-linkbutton" data-options="iconCls:'icon-fwfk'">服务反馈</a>
							<a href="javascript:addTab('服务归档','customerServiceFile.jsp','icon-fwgd')" class="easyui-linkbutton" data-options="iconCls:'icon-fwgd'">服务归档</a>
						</div>
						<div title="统计报表" data-options="iconCls:'icon-tjbb'">
							<a href="javascript:addTab('客户贡献分析','khgxfx.jsp','icon-khgxfx')" class="easyui-linkbutton" data-options="iconCls:'icon-khgxfx'">客户贡献分析</a>
							<a href="javascript:addTab('客户构成分析','khgcfx.jsp','icon-khgcfx')" class="easyui-linkbutton" data-options="iconCls:'icon-khgcfx'">客户构成分析</a>
							<a href="javascript:addTab('客户服务分析','khfwfx.jsp','icon-khfwfx')" class="easyui-linkbutton" data-options="iconCls:'icon-khfwfx'">客户服务分析</a>
							<a href="javascript:addTab('客户流失分析','khlsfx.jsp','icon-khlsfx')" class="easyui-linkbutton" data-options="iconCls:'icon-khlsfx'">客户流失分析</a>
						</div>
						<div title="基础数据管理" data-options="iconCls:'icon-jcsjgl'">
							<a href="javascript:addTab('数据字典管理','dataDicManage.jsp','icon-sjzdgl')" class="easyui-linkbutton" data-options="iconCls:'icon-sjzdgl'">数据字典管理</a>
							<a href="javascript:addTab('产品信息查询','productSearch.jsp','icon-cpxxgl')" class="easyui-linkbutton" data-options="iconCls:'icon-cpxxgl'">产品信息查询</a>
							<a href="javascript:addTab('用户信息管理','userManage.jsp','icon-user')" class="easyui-linkbutton" data-options="iconCls:'icon-user'">用户信息管理</a>
						</div>
						<div title="信息管理" data-options="iconCls:'icon-item'">
							<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-modifyPassword'">修改密码</a>
							<a href="javascript:logout()" class="easyui-linkbutton" data-options="iconCls:'icon-exit'">安全退出</a>
						</div>

					</div>
				</div>
				<div data-options="region:'center',iconCls:'icon-ok'">
					<div class="easyui-tabs" id="tabsId" style="width:700px;height:250px" data-options="fit:true">
						<div title="首页" style="padding:10px" data-options="iconCls:'icon-home'">
							<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
						</div>
					</div>
				</div>
				<div data-options="region:'south'" align="center" style="height:24px; font-size: 16px;">版权所有©Java1711</div>
			</div>
		</div>
		<script>
			$("#panelId").window({
				onMinimize: function() {
					//最下化移动到右下角并折叠
					$("#panelId").window('move', {
						left: "0%",
						top: "94%"
					}).window('collapse').window('open');
				}
			});

			function addTab(title, url, icon) {
				if($('#tabsId').tabs('exists', title)) {
					$('#tabsId').tabs('select', title);
				} else {
					var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
					$('#tabsId').tabs('add', {
						title: title,
						iconCls: icon,
						content: content,
						closable: true
					});
				}
			}

			$.extend($.fn.tabs.methods, {
				/**
				 * 绑定双击事件
				 * @param {Object} jq
				 * @param {Object} caller 绑定的事件处理程序
				 */
				bindDblclick: function(jq, caller) {
					return jq.each(function() {
						var that = this;
						$(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs').delegate('li', 'dblclick.tabs', function(e) {
							if(caller && typeof(caller) == 'function') {
								var title = $(this).text();
								var index = $(that).tabs('getTabIndex', $(that).tabs('getTab', title));
								caller(index, title);
							}
						});
					});
				},
				/**
				 * 解除绑定双击事件
				 * @param {Object} jq
				 */
				unbindDblclick: function(jq) {
					return jq.each(function() {
						$(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs');
					});
				}
			});
			//添加双击关闭当前页
			$(function() {
				$('#tabsId').tabs('bindDblclick', function(index, title) {
					if(title == '首页')
						return;
					else
						$('#tabsId').tabs('close', title);
				});
			});
		</script>
	</body>

</html>