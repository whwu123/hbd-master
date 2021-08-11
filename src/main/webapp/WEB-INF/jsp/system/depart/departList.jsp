<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,jqgrid,treeview,icheck"></t:base>
</head>
<body class="gray-bg">
	<!-- 页面部分 -->
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-7">
				<div class="row">
					<div class="col-sm-12" id="departGroupId"></div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div id="departTable" class="jqGrid_wrapper"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-5">
				<div class="row">
					<div class="col-sm-12" id="usersGroupId"></div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div id="usersTable" class="jqGrid_wrapper"></div>
					</div>
				</div>
			</div>
			<!-- 页面隐藏字段 -->
			<input type="hidden" id="departid" name="departid" value="">
		</div>
	</div>

	<!-- 脚本部门 -->
	<!-- 部门表格 -->
	<t:datagrid actionUrl="sys/dept/departTreeGrid" treeGrid="true" treeColomnName="departName" tableContentId="departTable" fit="true" caption="组织架构列表" name="table_depart_list" pageSize="1000" height="600px" >
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="departName" label="机构名称" width="200" query="true"></t:dgCol>
		<t:dgCol name="departNo" label="机构编号" width="80"></t:dgCol>
		<t:dgCol name="type" label="机构类型" width="80" dictionary="tsdepart"></t:dgCol>
		<%--<t:dgCol name="description" label="职能描述" width="200"></t:dgCol>--%>
		<t:dgCol name="id" label="ID" width="200"></t:dgCol>
		<t:dgCol name="opt" label="操作" width="140"></t:dgCol>
		<t:dgDelOpt label="删除" url="sys/dept/del?id={id}"/>
		<t:dgFunOpt label="查看人员" funName="getUsersByDepart(id)" icon="fa fa-check"></t:dgFunOpt>
		<t:dgToolBar url="sys/dept/add" type="add" width="60%"></t:dgToolBar>
		<t:dgToolBar url="sys/dept/update" type="edit" width="60%"></t:dgToolBar>
		<t:dgToolBar label="导入" icon="fa fa-file-o" type="import" funName="arrowDown" operationCode="sys:dept:importschool" ></t:dgToolBar>
	</t:datagrid>
	<!-- 部门下人员表格 -->
	<t:datagrid actionUrl="sys/dept/userDatagrid" tableContentId="usersTable" searchGroupId="usersGroupId" fit="true" caption="用户管理" name="table_user_list" pageSize="20" sortName="createDate" sortOrder="desc" height="500px">
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="userName" label="用户名" query="true" labelCol="2" inputCol="4"></t:dgCol>
		<t:dgCol name="realName" label="真实姓名" query="true" labelCol="2" inputCol="4"></t:dgCol>
		<t:dgCol name="admin" label="是否管理员" dictionary="byesorno"></t:dgCol>
		<t:dgCol name="status" label="状态" replace="正常_1,禁用_0" labelCol="2" inputCol="4"></t:dgCol>
	</t:datagrid>
	
	<script type="text/javascript">
		//根据部门ID获取用户
		function getUsersByDepart(id) {
			$("#departid").val(id);
			
			//用户表的查询
			$("#table_user_list").jqGrid('setGridParam',{postData:{deptId : id}}).trigger('reloadGrid');
		}

		function arrowDown() {
			var url ="exportChargeController/index";
			parent.layer.open({
				type: 2,
				title: '导入数据',
				shadeClose: true,
				shade: 0.8,
				area: ['20%', '20%'],
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
</body>
</html>
