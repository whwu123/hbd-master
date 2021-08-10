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
	<t:datagrid actionUrl="commodity/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="列表管理" name="table_list_2" pageSize="20" sortName="createDate" sortOrder="desc">
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="type" label="类型"  width="60" replace="${commodityTypeReplace }" valueId="type"></t:dgCol>
		<t:dgCol name="name" label="名称"  width="180"></t:dgCol>
		<t:dgCol name="thumbnail" label="缩略图"  width="120" imageSize="100,100" image="true" ></t:dgCol>
		<t:dgCol name="repertory" label="库存"  width="60"></t:dgCol>
		<t:dgCol name="state" label="状态" replace="已上架_1,未上架_0" width="90" display="zeroOrOne"></t:dgCol>
		<t:dgCol name="sales" label="已出售量"  width="60"></t:dgCol>
		<t:dgCol name="opt" label="操作" width="100"></t:dgCol>
		<t:dgDelOpt label="删除" url="commodity/del?id={id}" />
		<t:dgFunOpt label="上架" funName="shangjia(id)" icon="glyphicon glyphicon-saved"></t:dgFunOpt>
		<t:dgFunOpt label="下架" funName="xiajia(id)" icon="fa fa-binoculars"></t:dgFunOpt>
		<t:dgToolBar url="commodity/addorupdate" type="add" width="60%" height="90%" operationCode="sys:user:add"></t:dgToolBar>
		<t:dgToolBar url="commodity/addorupdate" type="edit" width="60%" height="90%" operationCode="sys:user:edit"></t:dgToolBar>
	</t:datagrid>
<script type="text/javascript">
	function shangjia(rowId) {
		qhConfirm("你确定要上架该商品吗?", function(index) {
			//关闭询问
			parent.layer.close(index);
			
			//是
			$.post("commodity/shangjia/" + rowId, {}, function(data){
				if(data.success) {
					qhTipSuccess(data.msg);
					//操作结束，刷新表格
					reloadTable('table_list_2');
				}else {
					qhTipWarning(data.msg);
				}
			}); 
			
		}, function() {
			//否
		});
	}
	
	function xiajia(rowId) {
		qhConfirm("你确定要下架该商品吗?", function(index) {
			//关闭询问
			parent.layer.close(index);
			
			//是
			$.post("commodity/xiajia/" + rowId, {}, function(data){
				if(data.success) {
					qhTipSuccess(data.msg);
					//操作结束，刷新表格
					reloadTable('table_list_2');
				}else {
					qhTipWarning(data.msg);
				}
			}); 
			
		}, function() {
			//否
		});
	}
</script>
</body>
</html>