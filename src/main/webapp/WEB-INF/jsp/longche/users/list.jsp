<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,jqgrid"></t:base>
<style type="text/css">
.rankClass{
background-color: #23c6c8;
color: #FFF
}
</style>
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
	<t:datagrid actionUrl="userslc/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="用户管理" name="table_list_2" pageSize="20" sortName="createDate" sortOrder="desc">
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="name" label="昵称"  width="90"> ></t:dgCol>
		<t:dgCol name="openId" label="OpenId" width="90"></t:dgCol>
		<t:dgCol name="department" label="所属平台" width="60"></t:dgCol>
		<t:dgCol name="phone" label="手机号码" width="80"  ></t:dgCol>
		<t:dgCol name="remark" label="备注"   width="60" ></t:dgCol>
		<t:dgCol name="rank" label="身份"   width="60" ></t:dgCol>
		<t:dgCol name="orderNum" label="订单数"  width="60"></t:dgCol>
		<t:dgCol name="couponNum" label="卡劵数"   width="60"></t:dgCol>
		<t:dgCol name="integral" label="当前积分"   width="60"></t:dgCol>
		<t:dgCol name="balance" label="当前余额"   width="60"></t:dgCol>
		<t:dgCol name="opt" label="操作" width="100"></t:dgCol>
		<t:dgDelOpt label="删除" url="article/del?id={id}" operationCode="sys:user:del"/>
		<t:dgToolBar url="article/addorupdate" type="add" width="60%" height="80%" operationCode="sys:user:add"></t:dgToolBar>
		<t:dgToolBar url="article/addorupdate" type="edit" width="60%" height="80%" operationCode="sys:user:edit"></t:dgToolBar>
	</t:datagrid>
<script type="text/javascript">
		
</script>
</body>
</html>