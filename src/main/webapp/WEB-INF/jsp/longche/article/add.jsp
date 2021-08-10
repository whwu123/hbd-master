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
						<t:formvalid action="article/save" beforeSubmit="beforeSubmit">
							<input type="hidden" name="id" id="id" value="${article.id }">
							<input type="hidden" id="state" name="state" value="${article.state }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">标题*：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name"  minlength="2" maxlength="50" type="text" class="form-control" required="" value="${article.name }">
                                </div>
                            </div>
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">类型：</label>
                                <div class="col-sm-8">
                                    <input id="type" name="type" type="text"  minlength="2" class="form-control"   value="${article.type }">
                                    <select name="type" class="form-control help-block m-b-none" aria-invalid="false" id="type">
	                                    <option value="0"  <c:if test="${article.type==0}">selected="selected"</c:if> >公司简介</option>
	                                    <option value="1"  <c:if test="${article.type==1}">selected="selected"</c:if> >分销简介</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系方式*：</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone" minlength="2" maxlength="11" type="text" class="form-control" required="" value="${article.phone }">
                              		
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址：</label>
                                <div class="col-sm-8">
                                    <input id="address" name="address" type="text"  minlength="2" class="form-control"   value="${article.address }">
                                </div>
                            </div> --%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
                                	<input type="hidden" id="content" name="content" value="${article.content }">
                                    <textarea rows="10" id="editor1" name="editor1" class="form-control">${article.content }</textarea>
                                    <script>
						                // Replace the <textarea id="editor1"> with a CKEditor 4
						                // instance, using default configuration.
						                CKEDITOR.replace( 'editor1' );
						                
						            </script>
                                </div>
                            </div>
						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
	<script>
       function beforeSubmit(){
    	   var content = CKEDITOR.instances.editor1.getData(); //获取editor1的值
    	   $("#content").val(content)
    	  // alert(content)
    	   return true;
       }
	
	
    </script>
</body>

</html>

