<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
	
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="commodityType/save">
							<input type="hidden" name="id" id="id" value="${commodityType.id }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">名称*：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name"  maxlength="20" type="type" class="form-control" required="" value="${commodityType.name }">
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">状态*：</label>
                                <div class="col-sm-8">
                                    <select name="state" class="form-control help-block m-b-none" aria-invalid="false" id="state">
	                                    <option value="0"  <c:if test="${commodityType.state==0}">selected="selected"</c:if> >禁用</option>
	                                    <option value="1"  <c:if test="${commodityType.state==1}">selected="selected"</c:if> >正常</option>
                                    </select>
                                </div>
                            </div>
                           
						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/* $(document).ready(
		function () {
			$("#yhfs").change(function () {
				var fsValue= $("#yhfs").val();
				if(fsValue== 1 ){
					$("#yxts").show();
					$("#number").val(0)
					$("#yhNumber").hide();
				}else if(fsValue== 0 ){
					$("#yxts").hide();
					$("#validTime").val(0)
					$("#yhNumber").show();
				}
			});
		}) */
	</script>
</html>

