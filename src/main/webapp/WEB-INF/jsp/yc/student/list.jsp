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
<t:datagrid actionUrl="ycStudent/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="学生管理" name="table_list_2" pageSize="20" sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>

	<c:if test="${isquxianAdmin=='0'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="90" query="true" replace="${departsReplace}"></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" query="true"></t:dgCol>
	</c:if>

	<c:if test="${isquxianAdmin=='1'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="90" ></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" query="true"></t:dgCol>
	</c:if>

	<c:if test="${isquxianAdmin=='2'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="90" ></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" ></t:dgCol>
	</c:if>
	<t:dgCol name="studentName" label="学生名字" width="90" query="true"></t:dgCol>
	<t:dgCol name="studentSex" label="性别" width="90" query="true" replace="男_男,女_女"></t:dgCol>
	<t:dgCol name="studentSchoolBm" label="院系编码" width="90" query="true"></t:dgCol>
	<t:dgCol name="studentCard" label="身份证" width="120" query="true"></t:dgCol>
	<t:dgCol name="studentXueli" label="学历" width="90" query="true"></t:dgCol>
	<t:dgCol name="studentNianji" label="年级" width="90" query="true" ></t:dgCol>
	<t:dgCol name="studentBanji" label="班级" width="90" query="true"></t:dgCol>
	<t:dgToolBar label="导入" icon="fa fa-file-o" type="import" funName="arrowDown" operationCode="stu:user:daoru" ></t:dgToolBar>
	<t:dgToolBar url="ycStudent/updateStu" type="edit" width="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">
	function arrowDown() {
		var url ="exportChargeController/index2";
		parent.layer.open({
			type: 2,
			title: '导入数据',
			shadeClose: true,
			shade: 0.8,
			area: ['20%', '18%'],
			content: url, //iframe的url
			/*btn : [  '取消' ],*/
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