<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,treeview"></t:base>
<script type="text/javascript">
	$(function() {
		
		//表单提交
		$("#MellInfoForm").validate({
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					success : function(o) {
						if (o.success) {
							qhTipSuccess('保存成功');
							//doBtnRefreshTreeAction();
						} else {
							qhTipWarning(o.msg);
						}
					},
					error : function(data) {
						qhTipError('系统错误，请联系系统管理员');
					}
				});
			}
		});
	});
	
	//提交
	function submitAction() {
		$("#MellInfoForm").submit();
	}
	
	
</script>
</head>
<body class="gray-bg">

	<div class="wrapper wrapper-content">
		<div class="row animated fadeInRight">
			<!-- <div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>部门列表</h5>
					</div>
					<div>
						<div class="ibox-content">
							<p>
								<button class="btn btn-primary" type="button" onclick="doBtnNewAction();"><i class="fa fa-file-o"></i>&nbsp;新增</button>
								<button class="btn btn-primary" type="button" onclick="doBtnDeleteAction();"><i class="fa fa-remove"></i>&nbsp;删除</button>
	                        	<button class="btn btn-primary" type="button" onclick="doBtnExpandedAction();"><i class="fa fa-check-square-o"></i>&nbsp;展开</button>
	                        	<button class="btn btn-primary" type="button" onclick="doBtnUnExpandedAction();"><i class="fa fa-circle-o"></i>&nbsp;收起</button>
                      	    </p>
						<div id="select-departs"></div>
						</div>
					</div>
				</div>
			</div> -->
			<div class="col-sm-6" style="width: 100%;">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>商城设置</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="MellInfoForm" action="mell/save" method="post">
							<input id="id" name="id" type="hidden" value="${mell.id }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">商城名称 *</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" minlength="2" maxlength="20" type="text" class="form-control" required="" value="${mell.name }">
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">联系电话 *</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone" minlength="2" maxlength="11" type="text" class="form-control" required="" value="${mell.phone }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">未支付订单超时时间（小时） *</label>
                                <div class="col-sm-8">
                                   <input id="overTime" name="overTime" minlength="1" maxlength="3" type="number" class="form-control" required="" value="${mell.overTime }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">收货时间 （天）*</label>
                                <div class="col-sm-8">
                                     <input id="receivingTime" name="receivingTime" minlength="1" maxlength="3" type="number" class="form-control" required="" value="${mell.receivingTime }">
                                </div>
                            </div>
                           <%--  <div class="form-group">
                                <label class="col-sm-3 control-label">售后时间 （天）*</label>
                                <div class="col-sm-8">
                                    <input id="supportTime" name="supportTime" minlength="1" maxlength="3" type="number" class="form-control" required="" value="${mell.supportTime }">
                                </div>
                            </div> --%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">退货地址 *</label>
                                <div class="col-sm-8">
                                    <textarea id="returnAddress" name="returnAddress" class="form-control"  required=""  rows="3">${mell.returnAddress }</textarea>
                                </div>
                            </div>
						</form>
						<div class="row">
							<div class="col-sm-11 text-right" style="margin-top: 30px;">
								<p>
									<button class="btn btn-info" onclick="submitAction();" type="submit">
										<i class="fa fa-check"></i>&nbsp;&nbsp;保存
									</button>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>