<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>财信育才管理系统</title>
    <meta name="keywords" content="active4j-oa演示系统">
    <meta name="description" content="active4j-oa演示系统">
    <link rel="shortcut icon" href="../static/bootstrap/image/favicon.ico">
    <link href="../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/font-awesome.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/style.min.css" rel="stylesheet">
    <link href="../static/toastr/css/toastr.min.css" rel="stylesheet">
    <style>
        #div1{
            border-bottom: 2px solid #1136ea;
        }
        .btn{
            background-color: #ddd;
            border-color: #ddd;
            color: #1c84c6;
            margin-left: 6px
        }
    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">
        <%--<div>
            <h3 class="logo-name">YC2</h3>
        </div>--%>
        <h3>请您选择所需区县下订单</h3>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            温馨提示：灰色地区表示暂未开通
        </p>
        <div id="div1"></div>
    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="sz" onclick="cl();" type="button" style="width: 90px;" >市直</button>
        <button class="btn btn-primary" id="hc" onclick="cl();" type="button"  style="width: 90px;">鹤城区</button>
        <button class="btn btn-primary" id="jk" onclick="cl();" type="button" style="width: 90px;">经开区</button>

    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="xp" onclick="cl();" type="button" style="width: 90px;">溆浦县</button>
        <button class="btn btn-primary" id="xh" onclick="cl();" type="button" style="width: 90px;">新晃县</button>
        <button class="btn btn-primary" id="zj" onclick="cl();" type="button" style="width: 90px;">芷江县</button>

    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="jz" onclick="cl();" type="button" style="width: 90px;">靖州县</button>
        <button class="btn btn-primary" id="yl" onclick="cl('8b7c901190e5f5c1e7040252f44cfc7e');" type="button" style="width: 90px;background-color: #1c84c6;color: #ddd">沅陵县</button>
        <button class="btn btn-primary" id="cx" onclick="cl();" type="button" style="width: 90px;">辰溪县</button>

    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="td" onclick="cl();" type="button" style="width: 90px;">通道县</button>
        <button class="btn btn-primary" id="my" onclick="cl();" type="button" style="width: 90px;">麻阳县</button>
        <button class="btn btn-primary" id="ht" onclick="cl();" type="button" style="width: 90px;">会同县</button>

    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="zf" onclick="cl('80735fa22a7d4d2901c8ca9919a8f9f3');" type="button" style="width: 90px;background-color: #1c84c6;color: #ddd" >中方县</button>
        <button class="btn btn-primary" id="hjs" onclick="cl();" type="button" style="width: 90px;" >洪江市</button>
        <button class="btn btn-primary" id="hjq" onclick="cl();" type="button" style="width: 90px;">洪江区</button>

    </div>
    <p style="margin-top: 50px;">
        湖南财信育才保险代理有限公司
    </p>

    <p style="color:red;font-size: 16px;">
        客户服务电话:0731-88310000
    </p>

    <p>
        湖南财信育才保险代理有限公司湖南省怀化营业部
    </p>
    <p style="color:red;font-size: 16px;">
        客户服务电话:0747-88310001
    </p>
    <input type="hidden" id="diquId" value="${diquId}"/>

</div>
<script src="../static/jquery/js/jquery.min.js"></script>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
<script src="../static/bootstrap/js/qq.js"></script>
<script src="../static/validate/js/jquery.validate.min.js"></script>
<script src="../static/validate/js/messages_zh.min.js"></script>
<script src="../static/login/js/login.js"></script>
<script src="../static/toastr/js/toastr.min.js"></script>
<script>
    function cl(id) {
       // alert(id);
        var diquId = $("#diquId").val();
       window.location.href="form1?quxianId="+id+"&diquId="+diquId;
    }

</script>
</body>
</html>

