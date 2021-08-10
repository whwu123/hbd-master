<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>怀保代</title>
    <meta name="keywords" content="怀保代">
    <meta name="description" content="怀保代">
    <link rel="shortcut icon" href="static/bootstrap/image/favicon.ico">
    <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/style.min.css" rel="stylesheet">
    <link href="static/toastr/css/toastr.min.css" rel="stylesheet">
    <style>
        #div1{
            border-bottom: 2px solid #1136ea;
        }
        .btn{
            background-color: red;
            border-color: red;
            color:  white;

        }
        .hr_new{
            margin-top: 10px;
            margin-bottom: 10px;
            border: 0;
            border-top: 2px solid #ddd;
        }
        .hr_new_2{
            margin-top: 10px;
            margin-bottom: 10px;
            border: 0;
            border-top: 2px solid red;
        }
    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">

        <form class="form-horizontal m-t" id="signupForm" action="webController/doUpdateStu" method="post">

            <h3 style="text-align: left;">学生信息</h3>
            <hr class="hr_new"/>
            <input  type="hidden" class="form-control" id="quxianDepartment"  name="quxianDepartment" value="${ycPaymentRecord.quxianDepartment}" />
            <div class="form-group">
                <label class="col-sm-4 control-label">身份证号：</label>
                <div class="col-sm-8">
                    <input   type="text" class="form-control" id="studentCard" name="studentCard" value="${ycPaymentRecord.studentCard}" placeholder="请输入有效的学生身份证"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">学校名称：</label>
                <div class="col-sm-8">
                    <input   type="text" class="form-control" id="studentSchool" name="studentSchool" value="${ycPaymentRecord.studentSchool}"  placeholder="请输入学校名称" readonly/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">学生姓名：</label>
                <div class="col-sm-8">
                    <input  type="text" class="form-control" id="studentName" name="studentName" value="${ycPaymentRecord.studentName}"  placeholder="请输入学生真实姓名"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">年级：</label>
                <div class="col-sm-8">

                    <select class="form-control help-block m-b-none" name="studentNianji">
                        <option value="幼儿园一" <c:if test="${ycPaymentRecord.studentNianji=='幼儿园一'}">selected</c:if>>幼儿园一</option>
                        <option value="幼儿园二" <c:if test="${ycPaymentRecord.studentNianji=='幼儿园二'}">selected</c:if>>幼儿园二</option>
                        <option value="幼儿园三" <c:if test="${ycPaymentRecord.studentNianji=='幼儿园三'}">selected</c:if>>幼儿园三</option>
                        <option value="一年级" <c:if test="${ycPaymentRecord.studentNianji=='一年级'}">selected</c:if>>一年级</option>
                        <option value="二年级" <c:if test="${ycPaymentRecord.studentNianji=='二年级'}">selected</c:if>>二年级</option>
                        <option value="三年级" <c:if test="${ycPaymentRecord.studentNianji=='三年级'}">selected</c:if>>三年级</option>
                        <option value="四年级" <c:if test="${ycPaymentRecord.studentNianji=='四年级'}">selected</c:if>>四年级</option>
                        <option value="五年级" <c:if test="${ycPaymentRecord.studentNianji=='五年级'}">selected</c:if>>五年级</option>
                        <option value="六年级" <c:if test="${ycPaymentRecord.studentNianji=='六年级'}">selected</c:if>>六年级</option>
                        <option value="初一" <c:if test="${ycPaymentRecord.studentNianji=='初一'}">selected</c:if>>初一</option>
                        <option value="初二" <c:if test="${ycPaymentRecord.studentNianji=='初二'}">selected</c:if>>初二</option>
                        <option value="初三" <c:if test="${ycPaymentRecord.studentNianji=='初三'}">selected</c:if>>初三</option>
                        <option value="高一/中专一" <c:if test="${ycPaymentRecord.studentNianji=='高一/中专一'}">selected</c:if>>高一/中专一</option>
                        <option value="高二/中专二" <c:if test="${ycPaymentRecord.studentNianji=='高二/中专二'}">selected</c:if>>高二/中专二</option>
                        <option value="高三/中专三" <c:if test="${ycPaymentRecord.studentNianji=='高三/中专三'}">selected</c:if>>高三/中专三</option>
                    </select>


                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">班级：</label>
                <div class="col-sm-8">


                    <input   type="text" class="form-control" id="studentBanji" name="studentBanji" value="${ycPaymentRecord.studentBanji}"  placeholder="请输入班级"/>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-4 control-label">投保人姓名：</label>
                <div class="col-sm-8">
                    <input  type="text" class="form-control" id="toubaorenName"  name="toubaorenName" value="${ycPaymentRecord.toubaorenName}" placeholder="请输入真实姓名"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">投保人电话：</label>
                <div class="col-sm-8">
                    <input  type="text" class="form-control" id="toubaorenPhone" name="toubaorenPhone" value="${ycPaymentRecord.toubaorenPhone}" placeholder="请输入真实电话号码"/>
                </div>
            </div>

            <button type="submit" class="btn btn-primary block full-width m-b" >提交</button>


        </form>


</div>
<script src="static/jquery/js/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/bootstrap/js/qq.js"></script>
<script src="static/validate/js/jquery.validate.min.js"></script>
<script src="static/validate/js/messages_zh.min.js"></script>
<script src="static/login/js/login.js"></script>
<script src="static/toastr/js/toastr.min.js"></script>
<script>


    function doNxet() {
       // window.location.href="hh_quxian";
        if(!$("#signupForm").valid()) {
            return;
        }

        var quxianId = $("#quxianId").val();
        var diquId = $("#diquId").val();
        var xiangzhengId = $("#xiangzhengId").val();
        var xuexiaoId = $("#xuexiaoValue").val();
        var xuexiaoName = $("#xuexiaoName").val();
        var xueshengCard = $("#xueshengCard").val();
        var xueshengName = $("#xueshengName").val();
        var nianjiName =$("#nianjiOption option:selected").text();
        var nianjiId = $("#nianjiOption option:selected").val();
        var banji = $("#banji").val();
        var parentsGuanxi = $("#parentsGuanxi option:selected").val();
        var parentsName = $("#parentsName").val();
        var parentsPhone = $("#parentsPhone").val();
        var baoxianStr = $("#baoxianStr").val();
        var baoxianTotalmoney = $("#baoxianTotalmoney").val();

        //登录
        $.post("ychome/saveOrders",
            {
                xuexiaoName:xuexiaoName,
                studentCard:xueshengCard,
                studentName:xueshengName,
                nianjiName:nianjiName,
                nianjiId:nianjiId,
                banJi:banji,
                parentsGuanxi:parentsGuanxi,
                parentsName:parentsName,
                parentsPhone:parentsPhone,
                xiangzhenId:xiangzhengId,
                xuexiaoId:xuexiaoId,
                quxianId:quxianId,
                diquId:diquId,
                baoxianType:baoxianStr,
                baoxianMoney:baoxianTotalmoney,
            },
            function(data) {
            if(data.success) {
                toastr.success('登录成功');
                location.href = 'index'; //后台主页
            }else {
                toastr.warning(data.msg);
            }
        });
    }
    
    function checkboxOnclick() {
        var chk_value =[];//定义一个数组
        var total = 0
        $('input[name="chk"]:checked').each(function(){//遍历每一个名字为nodes的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中
        });
        //var groups = chk_value.join(",");
        for(var i = 0; i < chk_value.length; i++) {
            total += Number(chk_value[i])
        }
        alert(total);
        $("#totalmoney").html(total);
        $("#totalmoney2").html(total);


    }


</script>
</body>
</html>

