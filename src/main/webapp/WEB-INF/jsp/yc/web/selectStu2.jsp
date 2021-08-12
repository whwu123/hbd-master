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
            background-color: #1c84c6;
            border-color: #1c84c6;
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
            border-top: 2px solid #1c84c6;
        }
    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">
<div class="row">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <input type="hidden" id="studentCard" name="studentCard" value="${ycPaymentRecord.studentCard}">
                    <input type="hidden" id="zhifuNumber" name="zhifuNumber" value="${ycPaymentRecord.zhifuNumber}">
                    <div class="ibox-title">
                        <h5>学生信息</h5>
                    </div>
                    <div class="ibox-content">
                        <p>身份证：<span class="label ">${ycPaymentRecord.studentCard}</span></p>
                        <p>学校名称：<span class="label ">${ycPaymentRecord.studentSchool}</span></p>
                        <p>学生姓名：<span class="label ">${ycPaymentRecord.studentName}</span></p>
                        <p>年级：<span class="label ">${ycPaymentRecord.studentNianji}</span></p>
                        <p>班级：<span class="label ">${ycPaymentRecord.studentBanji}</span></p>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>监护人信息</h5>
                    </div>
                    <div class="ibox-content">
                        <p>投保人(监护人)姓名：<span class="label ">${ycPaymentRecord.toubaorenName}</span></p>
                        <p>投保人(监护人)电话：<span class="label ">${ycPaymentRecord.toubaorenPhone}</span></p>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>缴费信息</h5>
                    </div>
                    <div class="ibox-content">
                        <p>支付流水号：<span class="label label-danger "> ${ycPaymentRecord.zhifuNumber}</span></p>
                        <p>缴费时间：<span class="label " ><fmt:formatDate value="${ycPaymentRecord.createDate}" pattern="yyyy-MM-dd HH:mm"/></span></p>
                        <c:forEach items="${prList}" var="l">
                            <p> <c:out value="${l.type}" ></c:out>：<span span class="label label-danger "> ${l.payMoney}</span> 元</p>

                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <button type="button" class="btn btn-primary block full-width m-b" onclick="updateStu()">修改</button>
</div>
</body>
<script src="static/jquery/js/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/bootstrap/js/qq.js"></script>
<script src="static/validate/js/jquery.validate.min.js"></script>
<script src="static/validate/js/messages_zh.min.js"></script>
<script src="static/login/js/login.js"></script>
<script src="static/toastr/js/toastr.min.js"></script>
<script>
    function updateStu(){
        var zhifuNumber = $("#zhifuNumber").val();
        var studentCard = $("#studentCard").val();
        window.location.href="webController/updateStu?studentCard="+studentCard+"&zhifuNumber="+zhifuNumber ;
    }

</script>

</html>

