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
						<t:formvalid action="maintenance/save">
							<input type="hidden" name="id" id="id" value="${maintenance.id }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">维修厂名称*：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name"  minlength="2" maxlength="50" type="text" class="form-control" required="" value="${maintenance.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">维修厂电话*：</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone"  type="text" class="form-control" required="" value="${maintenance.phone }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">维修厂地址*：</label>
                                <div class="col-sm-8">
                                    <input id="address" name="address" type="text"  minlength="2" class="form-control" required="" value="${maintenance.address }">
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

