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
	<t:datagrid actionUrl="couponGrant/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="发放管理" name="table_list_2" pageSize="20" sortName="createDate" sortOrder="desc">
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="30"></t:dgCol>
		<t:dgCol name="coupomId" label="优惠卷名称" replace="${couponReplace}"  valueId="coupomId"></t:dgCol>
		<t:dgCol name="disbursement" label="发放方式 " width="90" replace="注册新用户_0,购买并付款_1"></t:dgCol>
		<t:dgCol name="number" label="发放次数限制" width="100" ></t:dgCol>
		<t:dgCol name="opt" label="操作" width="100"></t:dgCol>
		<t:dgDelOpt label="删除" url="couponGrant/del?id={id}" operationCode="sys:user:del"/>
		<t:dgToolBar url="couponGrant/addorupdate" type="add" width="50%" height="40%" operationCode="sys:user:add"></t:dgToolBar>
		<t:dgToolBar url="couponGrant/addorupdate" type="edit" width="50%" height="40%" operationCode="sys:user:edit"></t:dgToolBar>
	</t:datagrid>
<script type="text/javascript">
		
</script>
</body>
</html>