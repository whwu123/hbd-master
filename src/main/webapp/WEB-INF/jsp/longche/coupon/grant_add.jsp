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
						<t:formvalid action="couponGrant/save">
							<input type="hidden" name="id" id="id" value="${grant.id }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">优惠卷*：</label>
                                <div class="col-sm-8">
                                   <select name="coupomId" class="form-control help-block m-b-none" aria-invalid="false" id="coupomId">
	                                   <c:forEach var="clist" items="${couponList }"> 
	                                   		<option value="${clist.id }"  <c:if test="${clist.id==grant.coupomId}">selected="selected"</c:if> >${clist.name}</option>
	                                   </c:forEach>
                                   </select>
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">发放方式*：</label>
                                <div class="col-sm-8">
                                    <select name="disbursement" class="form-control help-block m-b-none" aria-invalid="false" id="disbursement">
	                                    <option value="0"  <c:if test="${grant.disbursement==0}">selected="selected"</c:if> >注册新用户</option>
	                                    <option value="1"  <c:if test="${grant.disbursement==1}">selected="selected"</c:if> >购买并付款</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">该方案下最多发放次数*：</label>
                                <div class="col-sm-8">
                                    <input id="number" name="number"  maxlength="6" type="number" class="form-control" required="" value="${grant.number }">如不限制发放次数，请填写0
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
	$(document).ready(
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
		})
	</script>
</html>

