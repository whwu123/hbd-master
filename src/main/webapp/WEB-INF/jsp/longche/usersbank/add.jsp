<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
<script src="<%=basePath%>static/ckeditor/ckeditor.js"></script>

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="usersbank/save" beforeSubmit="beforeSubmit">
							<input type="hidden" name="id" id="id" value="${article.id }">
							<input type="hidden" id="state" name="state" value="${article.state }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">开户行*：</label>
                                <div class="col-sm-8">
                                    <input id="opening" name="opening"  minlength="2" maxlength="50" type="text" class="form-control" required="" value="${article.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">银行卡号*：</label>
                                <div class="col-sm-8">
                                    <input id="card" name="card"  maxlength="50" type="text" class="form-control" required="" value="${article.phone }">
                              		
                                </div>
                            </div>
						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	
    </script>
</body>

</html>

