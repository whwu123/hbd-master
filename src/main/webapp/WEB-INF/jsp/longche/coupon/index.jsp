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
	<t:datagrid actionUrl="coupon/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="优惠卷管理" name="table_list_2" pageSize="20" sortName="createDate" sortOrder="desc">
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="name" label="优惠卷名称" width="90" ></t:dgCol>
		<t:dgCol name="lowConsumption" label="最低消费金额（元）" ></t:dgCol>
		<t:dgCol name="couponMoney" label="优惠金额（元）" width="90" ></t:dgCol>
		<t:dgCol name="preferentialWay" label="优惠方式" width="90" replace="时间_1,数量_0"></t:dgCol>
		<t:dgCol name="validTime" label="有效时间" width="60" ></t:dgCol>
		<t:dgCol name="number" label="数量" width="60" ></t:dgCol>
		<%-- <t:dgCol name="isJoin" label="加入领券中心" width="60" replace="加入_true,未加入_false"></t:dgCol> --%>
		<t:dgCol name="opt" label="操作" width="100"></t:dgCol>
		<t:dgDelOpt label="删除" url="coupon/del?id={id}" operationCode="sys:user:del"/>
	<%-- 	<t:dgFunOpt label="编辑" funName="update(id)" icon="fa fa-binoculars"></t:dgFunOpt> --%>
		<t:dgToolBar url="coupon/addorupdate" type="add" width="50%" height="60%" operationCode="sys:user:add"></t:dgToolBar>
		<t:dgToolBar url="coupon/addorupdate" type="edit" width="50%" height="60%" operationCode="sys:user:edit"></t:dgToolBar>
		<t:dgToolBar url="coupon/addorupdate" type="view" width="50%" height="60%" operationCode="sys:user:view"></t:dgToolBar>
	</t:datagrid>
<script type="text/javascript">
		function update(id){
			window.open("coupon/update?id="+id,"_blank");    
		}
</script>
</body>
</html>