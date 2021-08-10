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
<t:datagrid actionUrl="ycPayMentRecord/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="学生管理" name="table_list_2" pageSize="20"  sortOrder="desc" sortName="createDate">
	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<c:if test="${isquxianAdmin=='1'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="90"  ></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" query="true"></t:dgCol>
	</c:if>
	<c:if test="${isquxianAdmin=='0'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="90" query="true" replace="${departsReplace}"></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" query="true"></t:dgCol>
	</c:if>
	<c:if test="${isquxianAdmin=='2'}">
		<t:dgCol name="quxianDepartment" label="区县名称" width="90" ></t:dgCol>
		<t:dgCol name="studentSchool" label="学校名称" width="120" ></t:dgCol>
	</c:if>
	<t:dgCol name="studentName" label="学生名字" width="90" query="true"></t:dgCol>
	<t:dgCol name="studentCard" label="身份证" width="120" query="true"></t:dgCol>
	<t:dgCol name="studentNianji" label="年级" width="90" query="true" dictionary="school_nianji"></t:dgCol>

	<t:dgCol name="studentBanji" label="班级" width="80"  query="true"></t:dgCol>
	<t:dgCol name="type" label="险种"  width="120" replace="监护人责任险_监护人责任险,交通意外、重大疾病险_交通意外、重大疾病险,学生平安保险_学生平安保险" query="true"></t:dgCol>
	<t:dgCol name="baofeiMoney" label="保险金额" width="80" ></t:dgCol>
	<t:dgCol name="casName" label="方案名称" width="90" query="true"></t:dgCol>
	<t:dgCol name="zhifuNumber" label="支付流水号" width="90" query="true"></t:dgCol>
	<t:dgCol name="payMoney" label="支付金额" width="90" ></t:dgCol>

	<t:dgCol name="createDate" label="缴费日期" width="90" ></t:dgCol>
	<t:dgCol name="supplier" label="供应商" width="90" ></t:dgCol>
	<t:dgCol name="toubaorenName" label="投保人姓名" width="90" query="true"></t:dgCol>
	<t:dgCol name="toubaorenPhone" label="投保人电话" width="90" query="true"></t:dgCol>

    <t:dgToolBar label="导出"  type="export" funName="arrowUp"></t:dgToolBar>
	<t:dgToolBar label="导入"  type="import" funName="importPay"></t:dgToolBar>

	<t:dgToolBar type="refresh"></t:dgToolBar>
	<%--<t:dgToolBar label="上传"  type="upload" funName="arrowUp"></t:dgToolBar>--%>

	<%--<t:dgToolBar label="导入" icon="fa fa-file-o" type="define" funName="arrowDown" ></t:dgToolBar>--%>


</t:datagrid>
<script type="text/javascript">
	function arrowUp() {
		var quxianDepartment = $("#quxianDepartment").val();
		var studentSchool = $("#studentSchool").val();
		var studentName = $("#studentName").val();
		var studentCard = $("#studentCard").val();
		var studentNianji = $("#studentNianji").val();
		var studentBanji = $("#studentBanji").val();
		var type = $("#type").val();
		var zhifuNumber = $("#zhifuNumber").val();
		var toubaorenName = $("#toubaorenName").val();
		var toubaorenPhone = $("#toubaorenPhone").val();

		window.location.href="reportController/export?quxianDepartment="+quxianDepartment+"&studentSchool="+studentSchool+"&studentName="+studentName+"&studentCard="+studentCard+"&studentNianji="+studentNianji+"&studentBanji="+studentBanji+"&type="+type+"&zhifuNumber="+zhifuNumber+"&toubaorenName="+toubaorenName+"&toubaorenPhone="+toubaorenPhone;
	}

	function importPay() {
		var url ="exportChargeController/index3";
		parent.layer.open({
			type: 2,
			title: '导入数据',
			shadeClose: true,
			shade: 0.8,
			area: ['30%', '20%'],
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

	function arrowDown() {
		var url ="exportChargeController/index";
		parent.layer.open({
			type: 2,
			title: '导入数据',
			shadeClose: true,
			shade: 0.8,
			area: ['30%', '40%'],
			content: url, //iframe的url
			btn : [  '取消' ],
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