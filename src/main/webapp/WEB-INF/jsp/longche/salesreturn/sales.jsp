<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
	<!-- <script type="text/javascript">
		$(function() {
			$("#roleid").val("${roleId}".split(",")).trigger("change");
		});
	
	</script> -->
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="sales/saveSales">
							<input type="hidden" name="id" id="id" value="${sales.id }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">姓名*：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" <c:if test="${not empty sales.id }">readonly="readonly"</c:if> minlength="2" maxlength="20" type="text" class="form-control" required="" value="${sales.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系电话*：</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone" minlength="2" maxlength="11" type="text" class="form-control" required="" value="${sales.phone }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址*：</label>
                                <div class="col-sm-8">
                                    <input id="returnAddress" name="returnAddress" type="text"  minlength="2" class="form-control" required="" value="${sales.returnAddress }">
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

