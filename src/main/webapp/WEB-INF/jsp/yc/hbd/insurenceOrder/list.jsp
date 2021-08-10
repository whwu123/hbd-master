<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,jqgrid"></t:base>
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
						<div id="jqGrid_wrapper" class="jqGrid_wrapper"></div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 脚本部分 -->
	<t:datagrid actionUrl="ycInsuranceOrders/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="保单管理" name="table_list_2" pageSize="20" sortName="createDate" sortOrder="desc">
		<t:dgCol name="studentName" label="学生名字" width="90"  query="true"></t:dgCol>
		<t:dgCol name="baoxianName" label="保险名称" width="90"  query="true"></t:dgCol>
		<t:dgCol name="baoxianMoney" label="保险金额" width="90" ></t:dgCol>
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="diquId" label="地区名称" replace="${departsReplace}" query="true"  queryId="diquId" valueId="diquId"></t:dgCol>

		<t:dgCol name="quxianName" label="区县名称" width="90"></t:dgCol>
		<t:dgCol name="xiangzhenName" label="乡镇名称" width="90" ></t:dgCol>
		<t:dgCol name="xuexiaoName" label="学校名称" width="90" query="true"></t:dgCol>
		<t:dgCol name="nianjiName" label="所在年级" width="90" query="true"></t:dgCol>

		<t:dgToolBar label="导出" icon="fa fa-cog" funName="exportData" type="define" width="50%" height="70%"></t:dgToolBar>
		<%--<t:dgCol name="status" label="状态" replace="正常_1,禁用_0" query="true"></t:dgCol>
		<t:dgCol name="taskNumber" label="任务数量"  ></t:dgCol>
		<t:dgCol name="opt" label="操作" width="290"></t:dgCol>
		<t:dgDelOpt label="删除" url="sys/user/del?id={id}" operationCode="sys:user:del"/>
		<t:dgToolBar url="sys/user/addorupdate" type="add" width="60%" operationCode="sys:user:add"></t:dgToolBar>
		<t:dgToolBar url="sys/user/addorupdate" type="edit" width="60%" operationCode="sys:user:edit"></t:dgToolBar>
		<t:dgToolBar label="重置密码" icon="fa fa-cog" url="sys/user/changepwd" type="pop" width="40%" height="50%" operationCode="sys:user:password"></t:dgToolBar>
		<t:dgToolBar label="锁定用户" icon="fa fa-lock" type="define" funName="lockUser" operationCode="sys:user:lock"></t:dgToolBar>
		<t:dgToolBar label="解锁用户" icon="fa fa-unlock" type="define" funName="unLockUser" operationCode="sys:user:unlock"></t:dgToolBar>--%>
	</t:datagrid>
<script type="text/javascript">
	function exportData(){
		alert("导出");
	}

</script>
</body>
</html>