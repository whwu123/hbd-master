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
<t:datagrid actionUrl="ycUpdateStulogController/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="学生管理" name="table_list_2" pageSize="20"  sortOrder="desc" sortName="createDate" >
	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>

	<c:if test="${isquxianAdmin=='0'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="160" query="true"></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" query="true"></t:dgCol>
	</c:if>
	<c:if test="${isquxianAdmin=='1'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="160" ></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" query="true"></t:dgCol>
	</c:if>
	<c:if test="${isquxianAdmin=='2'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="160" ></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" ></t:dgCol>
	</c:if>

	<t:dgCol name="studentName" label="学生名字" width="100" query="true"></t:dgCol>
	<t:dgCol name="studentCard" label="身份证" width="120" query="true"></t:dgCol>
	<t:dgCol name="studentNianji" label="年级" width="90" query="true" dictionary="school_nianji"></t:dgCol>
	<t:dgCol name="studentBanji" label="班级" width="80"  query="true"></t:dgCol>
	<t:dgCol name="toubaorenName" label="投保人姓名" width="90" query="true"></t:dgCol>
	<t:dgCol name="toubaorenPhone" label="投保人电话" width="90" query="true"></t:dgCol>
	<t:dgCol name="createDate" label="修改时间" datefmt="yyyy-MM-dd HH:mm"  width="120"></t:dgCol>
	<t:dgCol name="state" label="状态" replace="未审核_1,已审核_2,已完成_0" width="60" display="level"></t:dgCol>
	<t:dgCol name="opt" label="操作" width="80"></t:dgCol>
	<%--<t:dgDelOpt label="删除" url="sys/user/del?id={id}" />--%>
	<t:dgFunOpt label="审核" funName="auditingStu(id,studentName)" icon="fa fa-check"></t:dgFunOpt>
	<t:dgToolBar url="ycUpdateStulogController/updateStu" type="edit" width="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">
	function auditingStu(id,studentName) {
		//alert(id);
		qhConfirm("确定要审核学生《"+studentName+"》的信息吗?", function(index) {
			//关闭询问
			parent.layer.close(index);

			//是
			$.post("ycUpdateStulogController/auditingStu", {id : id}, function(data){
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