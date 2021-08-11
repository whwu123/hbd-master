<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <t:base type="default,select2,icheck,laydate,webuploader"></t:base>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
                            <div class="form-group">
                                <%--<label class="col-sm-2 control-label m-b">上传文件:</label>--%>
                                <div class="col-sm-2">
                                    <div id="filePicker2">上传缴费</div>
                                </div>
                                <div class="col-sm-4">
                                    <div id="fileList2" class="uploader-list"></div>
                                </div>
                            </div>

                        <%--<div class="form-group">
                            <button class="btn btn-info" id="xiazaiMoban" onclick ="xiazaiMoban()" style="margin-left: 15px;">下载模板</button>

                        </div>--%>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

    $(function() {

        //初始化Web Uploader
        var uploader2 = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto : true,
            // swf文件路径
            swf : 'static/webuploader/Uploader.swf',
            // 文件接收服务端。
            server : 'exportChargeController/exportPayMentRecord',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick : {
                id : '#filePicker2',
            },
            accept: {
                extensions: 'xls,xlsx',
                mimeTypes: 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            },
            allows: ".xls,.xlsx"
        });
        // 文件上传过程中创建进度条实时显示。
        uploader2.on('uploadProgress', function(file, percentage) {
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader2.on('uploadSuccess', function(file, data) {
           // var filePath = data.attributes.filePath;
            qhTipSuccess('上传完成....filePath='+file.name);
            $("#fileList2").html(file.name);

        });

        // 文件上传失败，显示上传出错。
        uploader2.on('uploadError', function(file) {

        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader2.on('uploadComplete', function(file) {
            qhTipSuccess('上传完成....');
        });

    });

</script>
</html>

