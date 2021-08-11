<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,jqgrid"></t:base>
</head>
<body class="gray-bg">
	<!-- 页面部分 -->
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-12" id="searchGroupId">
					</div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div id="recruitPlanTable" class="jqGrid_wrapper"></div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 脚本部分 -->
	<t:datagrid actionUrl="ycFileController/datagrid" tableContentId="recruitPlanTable" searchGroupId="searchGroupId" fit="true" caption="附件模板管理" name="recruitPlanTableList" pageSize="20" sortName="createDate" sortOrder="desc">
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="attachment" label="附件" hidden="true" width="20"></t:dgCol>
		<t:dgCol name="name" label="附件名称" width="120" query="true"></t:dgCol>
		<t:dgCol name="createDate" label="创建时间" width="100" ></t:dgCol>
		<t:dgCol name="opt" label="操作" width="60"></t:dgCol>
		<t:dgDelOpt label="删除" url="ycFileController/del?id={id}" operationCode="sys:file:del"/>
		<t:dgToolBar label="新增" icon="fa fa-file-o" type="define" funName="addfile" operationCode="sys:file:add"></t:dgToolBar>
		<t:dgToolBar label="附件下载" icon="glyphicon glyphicon-resize-full" type="define" funName="doAttachment"></t:dgToolBar>

		<t:dgToolBar type="refresh"></t:dgToolBar>
	</t:datagrid>

</body>
<script type="text/javascript">
		
		function doAttachment() {
			var rowId = $('#recruitPlanTableList').jqGrid('getGridParam','selrow');
			var rowData = $('#recruitPlanTableList').jqGrid('getRowData',rowId);
			if(!rowId) {
				qhAlert('请选择附件后再下载！');
				return;
			}
			location.href = "ycFileController/download?id=" + rowId;
		};
		function addfile() {
			var url ="ycFileController/addfile";
			parent.layer.open({
				type: 2,
				title: '新增附件',
				shadeClose: true,
				shade: 0.8,
				area: ['20%', '18%'],
				content: url, //iframe的url
				/*	btn : [  '取消' ],*/
				yes : function(index, layero) {
					//确定按钮回调
					//表单提交
					parent.frames['layui-layer-iframe' + index].submitL();

				},
				btn2 : function(index, layero) {
					//取消按钮回调

				},
				end: function() {
					// 操作结束，刷新表格
					$("#table_dic_list").trigger("reloadGrid");
				}
			});
		}
	</script>

</html>