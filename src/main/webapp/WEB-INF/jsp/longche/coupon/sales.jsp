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
						<t:formvalid action="coupon/save">
							<input type="hidden" name="id" id="id" value="${coupon.id }">
							<input type="hidden" name="state" id="state" value="${coupon.state }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">优惠卷名称*：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name"  minlength="2" maxlength="50" type="text" class="form-control" required="" value="${coupon.name }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最低消费金额（元）*：</label>
                                <div class="col-sm-8">
                                    <input id="lowConsumption" name="lowConsumption" minlength="2" maxlength="11" type="text" class="form-control" required="" value="${coupon.lowConsumption }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">优惠金额（元）*：</label>
                                <div class="col-sm-8">
                                    <input id="couponMoney" name="couponMoney" type="text"  minlength="2" class="form-control" required="" value="${coupon.couponMoney }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">优惠券方式*：</label>
                                <div class="col-sm-8">
                                    <select name="preferentialWay" class="form-control help-block m-b-none" aria-invalid="false" id="yhfs">
	                                    <option value="0"  <c:if test="${coupon.preferentialWay==0}">selected="selected"</c:if> >数量</option>
	                                    <option value="1"  <c:if test="${coupon.preferentialWay==1}">selected="selected"</c:if> >有效时间</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" id="yxts" style="display: none">
                                <label class="col-sm-3 control-label">有效天数*：</label>
                                <div class="col-sm-8">
                                    <input id="validTime" name="validTime" type="text"  class="form-control" required="" value="${coupon.validTime }">
                                </div>
                            </div>
                            <div class="form-group" id="yhNumber" >
                                <label class="col-sm-3 control-label">数量*：</label>
                                <div class="col-sm-8">
                                    <input id="number" name="number" type="number" placeholder="数量0是无限制"  class="form-control"  required="" value="${coupon.number }" >
                                </div>
                            </div>
                            <div class="form-group" style="display: none">
                                <label class="col-sm-3 control-label">加入领劵中心*：</label>
                                <div class="col-sm-8">
                                   <%--  <t:dictSelect name="isJoin" type="radio" typeGroupCode="byesorno" defaultVal="false"></t:dictSelect> --%>
                                
                                	<c:choose>
                                		<c:when test="${empty coupon.isJoin}">
                                			<t:dictSelect name="isJoin" type="radio" typeGroupCode="byesorno" defaultVal="true"></t:dictSelect>
                                		</c:when>
                                		<c:otherwise>
                                			<t:dictSelect name="isJoin" type="radio" typeGroupCode="byesorno" defaultVal="${coupon.isJoin}"></t:dictSelect>
                                		</c:otherwise>
                                	</c:choose>
                                
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">使用说明*：</label>
                                <div class="col-sm-8">
                                   <textarea name="content"  class="form-control" rows="5">${coupon.content}</textarea>
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

