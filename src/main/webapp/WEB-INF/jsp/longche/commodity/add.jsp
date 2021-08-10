<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
<script src="<%=basePath%>static/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/webuploader/style.css">
<script src="<%=basePath%>static/webuploader/webuploader.js"></script>
<style type="text/css">

.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}

</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="commodity/save" beforeSubmit="beforeSubmit">
							<input type="hidden" name="id" id="id" value="${commodity.id }">
							<input type="hidden" id="state" name="state" value="${commodity.state }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">商品分类*：</label>
                                <div class="col-sm-8">
                                   <%--  <input id="type" name="type"  maxlength="20" type="type" class="form-control" required="" value="${commodity.type }"> --%>
                                	
                                	<select name="type" class="form-control help-block m-b-none" aria-invalid="false" id="type">
	                                    <c:forEach var="typeList" items="${commodityTypeEntities }">
	                                    	<option value="${typeList.id }"  <c:if test="${typeList.id==commodity.type}">selected="selected"</c:if> >${typeList.name }</option>
	                                    </c:forEach>
                                    </select>
                                
                                </div>
                            </div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">商品名称*：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name"  maxlength="20" type="type" class="form-control" required="" value="${commodity.name }">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">商品简介*：</label>
                                <div class="col-sm-8">
                                    <input id="introduction" name="introduction"  maxlength="20" type="type" class="form-control" required="" value="${commodity.introduction }">
                                </div>
                            </div>
                            
                           <div class="form-group">
                                <label class="col-sm-3 control-label">商品缩略图*：</label>
                                <div class="col-sm-8">
                                    <input id="thumbnail" name="thumbnail"  type="hidden"  value="${commodity.thumbnail }"> 
                                    <input type="hidden" name="img"  id="photoUrl"/>
                                    <a href="javascript:;" class="file" style="margin-top: 10px">选择图片
									    <input type="file" name="logoFile" id="logoFile" onchange="setImg(this);" class="file">
									</a>
    								<p style="margin-top: -10px;"><img id="photourlShow" src="${commodity.thumbnail }" width="110px" height="110px"/></p>
                                </div>
                            </div> 
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">商品图片*：</label>
                                <div class="col-sm-8" id="pictureList">
                                    <input id="picture" name="picture"  type="hidden" class="form-control"  value="${commodity.picture }">
                                    <input id="pictureok"  value=""  type="hidden">
                                    <input id="pictureFlag"  value="0"  type="hidden">
                                    <div id="wrapper">
								        <div id="container" style="margin-top: 0px;">
								            <!--头部，相册选择和格式选择-->
								
								            <div id="uploader">
								                <div class="queueList">
								                    <div id="dndArea" class="placeholder">
								                        <div id="filePicker"></div>
								                        <p>或将照片拖到这里，单次最多可选5张</p>
								                    </div>
								                </div>
								                <div class="statusBar" style="display:none;">
								                    <div class="progress">
								                        <span class="text">0%</span>
								                        <span class="percentage"></span>
								                    </div><div class="info"></div>
								                    <div class="btns">
								                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
								                    </div>
								                </div>
								            </div>
								        </div>
								    </div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">运费设置*：</label>
                                <div class="col-sm-8">
                                    <input id="freight" name="freight"  maxlength="20" type="type" class="form-control" required="" value="${commodity.freight }">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">规格价格*：</label>
                                <div class="col-sm-8" id="ggjgDiv">
                                	<a onclick="addSpecification()">新增一列+</a>
                                    <input id="specification" name="specification"  placeholder="前面填规格，后台填价格。格式要：guige:jiage" type="type" class="form-control" required="" value="${commodity.specification }" >
                                </div>
                            </div>
                            
                            <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">价格*：</label>
                                <div class="col-sm-8">
                                    <input id="price" name="price"    type="type" class="form-control" required="" value="${commodity.price }">
                                </div>
                            </div>
                             --%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">库存*：</label>
                                <div class="col-sm-8">
                                    <input id="repertory" name="repertory"  type="type" class="form-control" required="" value="${commodity.repertory }">
                                </div>
                            </div>
                            
                           
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">优惠劵*：</label>
                                <div class="col-sm-8">
                                    <%-- <input id="couponId" name="couponId"  maxlength="20" type="type" class="form-control" required="" value="${commodity.couponId }"> --%>
                               			
                              		<select name="couponId" class="form-control help-block m-b-none" aria-invalid="false" id="couponId">
	                                    <c:forEach var="coupon" items="${couponEntities }">
	                                    	<option value="${coupon.id }"  <c:if test="${coupon.id==commodity.couponId}">selected="selected"</c:if> >${coupon.name}</option>
	                                    </c:forEach>
                                    </select>
                               
                                </div>
                            </div>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">使用里程*：</label>
                                <div class="col-sm-8">
                                    <select name="gongliNumber" class="form-control help-block m-b-none" aria-invalid="false" id="gongliNumber">
	                                    	<option value="1"  <c:if test="${commodity.gongliNumber ==1}">selected="selected"</c:if> >10W公里内</option>
	                                    	<option value="2"  <c:if test="${commodity.gongliNumber ==2}">selected="selected"</c:if> >10W公里外</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">图文详情：</label>
                                <div class="col-sm-8">
                                	
                                    <textarea rows="10" id="editor1" name="editor1" class="form-control">${commodity.particulars }</textarea>
                                    <script>
						                // Replace the <textarea id="editor1"> with a CKEditor 4
						                // instance, using default configuration.
						                CKEDITOR.replace( 'editor1' );
						                
						            </script>
                                </div>
                                <textarea style="display: none" id="particulars" name="particulars" >${commodity.particulars }</textarea>
                            </div>
						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>

<script>
	   function addSpecification(){
			//		   alert("新增")
		   $("#ggjgDiv").append(" <input  name=\"specification\"  type=\"text\" class=\"form-control\"  placeholder=\"前面填规格，后台填价格。格式要：guige:jiage\"/> ");
		   
	   }
       function beforeSubmit(){
    	   var content = CKEDITOR.instances.editor1.getData(); //获取editor1的值
    	   $("#particulars").html(content)
    	  // alert(content)
    	   var  pictureok = $("#pictureok").val();
    	   if(pictureok != "" && pictureok!=null){
    		   $("#picture").val(pictureok);
    	   }
    	   var pictureFlag = $("#pictureFlag").val();
    	   //alert("pictureFlag:"+pictureFlag)
    	   if(pictureFlag == "1" ){
    		   //alert("商品图片有修改，请先上传再保存！");
    		   toastr.warning("商品图片有修改请先上传");
    		   return false;
    	   }
    	   return true;
       }
       
       function splitSublimted(str) {
    		var strs= new Array(); //创建一个数组
    		var ydms='';
    		strs=str.split(","); //以“|”为标志分割
    		for (i=0;i<strs.length ;i++ ) { 
    			ydms=strs[0]; //取值
    		} 
    		return ydms;
       }
   	  	 var BASE_URL = "<%=basePath%>";
   	  	 var pictureListValue = $("#picture").val();
   	     var getFileBlob = function (url, cb) {
   	     var xhr = new XMLHttpRequest();
	   	   xhr.open("GET", url);
	   	   xhr.responseType = "blob";
	   	   xhr.addEventListener('load', function() {
	   	   cb(xhr.response);
	   	   });
	   	   xhr.send();
	   	 };
	   
	   	 var blobToFile = function (blob, name) {
		   	   blob.lastModifiedDate = new Date();
		   	   blob.name = name;
		   	   return blob;
	   	 };

	   	 var getFileObject = function(filePathOrUrl, cb) {
	   	   getFileBlob(filePathOrUrl, function (blob) {
	   		  if(filePathOrUrl!= null && filePathOrUrl!=""){
		   	      cb(blobToFile(blob, 'test.jpg'));
	   		  }
	   	   });
	   	 };
   	  	 var addId = $("#id").val();
   	  	 if(addId !=null && addId != "" ){
   	  	 	//需要编辑的图片列表
   	  	 	$("#pictureFlag").val(0);
   	  		var picList = pictureListValue.split(",");
   			$.each(picList, function(index,item){
   			  	getFileObject(item, function (fileObject) {
   			    var wuFile = new WebUploader.Lib.File(WebUploader.guid('rt_'),fileObject);
   			    var file = new WebUploader.File(wuFile);
   			    uploader.addFiles(file)
   			  })
   			});
   	  		 
   	  	 }
   		function removeLongche(file){
	   		
  	    	$("#pictureFlag").val(1);
  	        
  	    
  	    }
   		//用于进行图片上传，返回地址
   		function setImg(obj){
   		    var f=$(obj).val();
   		    //alert(f);
   		    console.log(obj);
   		    if(f == null || f ==undefined || f == ''){
   		        return false;
   		    }
   		    if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
   		    {
   		        alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
   		        $(obj).val('');
   		        return false;
   		    }
   		    var data = new FormData();
   		    console.log(data);
   		    $.each($(obj)[0].files,function(i,file){
   		        data.append('file', file);
   		    });
   		    console.log(data);
   		    $.ajax({
   		        type: "POST",
   		        url: "<%=basePath%>fileUploaderController/fileupload",
   		        data: data,
   		        cache: false,
   		        contentType: false,    //不可缺
   		        processData: false,    //不可缺
   		        dataType:"json",
   		        success: function(ret) {
   		           // console.log(ret);
   		            if(ret.code==0){
   		                    $("#thumbnail").val(ret.filePath);//将地址存储好
   		                    $("#photourlShow").attr("src",ret.filePath);//显示图片   
   		                    alertOk(ret.msg);
   		            }else{
   		                alertError(ret.msg);
   		                $("#url").val("");
   		                $(obj).val('');
   		            }
   		        },
   		        error: function(XMLHttpRequest, textStatus, errorThrown) {
   		            alert("上传失败，请检查网络后重试");
   		        }
   		    });
   		}
    </script>
    <script src="<%=basePath%>static/webuploader/upload.js"></script>
</html>

