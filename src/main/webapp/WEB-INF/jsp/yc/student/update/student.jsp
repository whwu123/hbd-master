<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
	<script type="text/javascript">
		$(function() {
			//$("#roleid").val("${roleId}".split(",")).trigger("change");
		});
	
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="ycUpdateStulogController/saveStu">
							<input type="hidden" name="id" id="id" value="${stu.id }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">区县名称：</label>
                                <div class="col-sm-8">
                                    <input id="quxianDepartment" name="quxianDepartment"  type="text" class="form-control" value="${stu.quxianDepartment }" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校名称：</label>
                                <div class="col-sm-8">
                                    <input id="studentSchool" name="studentSchool" type="text" class="form-control"  value="${stu.studentSchool }" readonly>
                                </div>
                            </div>

	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">学生姓名*：</label>
	                                <div class="col-sm-8">
	                                    <input id="studentName" name="studentName"  type="text" class="form-control" required=""  value="${stu.studentName }">
	                                </div>
	                            </div>
							<div class="form-group">
								<label class="col-sm-3 control-label">身份证*：</label>
								<div class="col-sm-8">
									<input id="studentCard" name="studentCard"  type="text" class="form-control" value="${stu.studentCard }" required="" >
								</div>
							</div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">年级*：</label>
	                                <div class="col-sm-8">
	                                    <%--<input id="studentNianji" name="studentNianji"  type="text" class="form-control" value="${stu.studentNianji }">--%>

										<select class="form-control help-block m-b-none" name="studentNianji">
											<option value="幼儿园一" <c:if test="${stu.studentNianji=='幼儿园一'}">selected</c:if>>幼儿园一</option>
											<option value="幼儿园二" <c:if test="${stu.studentNianji=='幼儿园二'}">selected</c:if>>幼儿园二</option>
											<option value="幼儿园三" <c:if test="${stu.studentNianji=='幼儿园三'}">selected</c:if>>幼儿园三</option>
											<option value="一年级" <c:if test="${stu.studentNianji=='一年级'}">selected</c:if>>一年级</option>
											<option value="二年级" <c:if test="${stu.studentNianji=='二年级'}">selected</c:if>>二年级</option>
											<option value="三年级" <c:if test="${stu.studentNianji=='三年级'}">selected</c:if>>三年级</option>
											<option value="四年级" <c:if test="${stu.studentNianji=='四年级'}">selected</c:if>>四年级</option>
											<option value="五年级" <c:if test="${stu.studentNianji=='五年级'}">selected</c:if>>五年级</option>
											<option value="六年级" <c:if test="${stu.studentNianji=='六年级'}">selected</c:if>>六年级</option>
											<option value="初一" <c:if test="${stu.studentNianji=='初一'}">selected</c:if>>初一</option>
											<option value="初二" <c:if test="${stu.studentNianji=='初二'}">selected</c:if>>初二</option>
											<option value="初三" <c:if test="${stu.studentNianji=='初三'}">selected</c:if>>初三</option>
											<option value="高一/中专一" <c:if test="${stu.studentNianji=='高一/中专一'}">selected</c:if>>高一/中专一</option>
											<option value="高二/中专二" <c:if test="${stu.studentNianji=='高二/中专二'}">selected</c:if>>高二/中专二</option>
											<option value="高三/中专三" <c:if test="${stu.studentNianji=='高三/中专三'}">selected</c:if>>高三/中专三</option>
										</select>
	                                </div>
	                            </div>
								<div class="form-group">
									<label class="col-sm-3 control-label">班级*：</label>
									<div class="col-sm-8">
										<input id="studentBanji" name="studentBanji"  type="text" class="form-control" required="" value="${stu.studentBanji }">
									</div>
								</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">投保人姓名*：</label>
								<div class="col-sm-8">
									<input id="toubaorenName" name="toubaorenName" type="text" class="form-control" required=""  value="${stu.toubaorenName }">
								</div>
							</div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">投保人电话*：</label>
                                <div class="col-sm-8">
                                    <input id="toubaorenPhone" name="toubaorenPhone" type="text" class="form-control" required=""  value="${stu.toubaorenPhone }">
                                </div>
                            </div>


						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>

