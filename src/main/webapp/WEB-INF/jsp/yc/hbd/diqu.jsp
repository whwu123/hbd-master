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
        <h3>请您选择所需地区下订单</h3>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            温馨提示：灰色地区表示暂未开通
        </p>
        <div id="div1"></div>
    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="gx" onclick="cl();" type="button" style="width: 90px;">高校</button>
        <button class="btn btn-primary" id="cs" onclick="cl();" type="button"  style="width: 90px;">长沙市</button>
        <button class="btn btn-primary" id="hh" onclick="cl('hh');" type="button" style="width: 90px;background-color: #1c84c6;color: #ddd" >怀化市</button>


    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="hy" onclick="cl();" type="button" style="width: 90px;">衡阳市</button>
        <button class="btn btn-primary" id="sy" onclick="cl();" type="button" style="width: 90px;">邵阳市</button>
        <button class="btn btn-primary" id="yy" onclick="cl();" type="button" style="width: 90px;">岳阳市</button>

    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="cd" onclick="cl();" type="button" style="width: 90px;">常德市</button>
        <button class="btn btn-primary" id="zjj" onclick="cl();" type="button" style="width: 90px;">张家界市</button>
        <button class="btn btn-primary" id="yiy" onclick="cl();" type="button" style="width: 90px;">益阳市</button>

    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="cz" onclick="cl();" type="button" style="width: 90px;">郴州市</button>
        <button class="btn btn-primary" id="yz" onclick="cl();" type="button" style="width: 90px;">永州市</button>
        <button class="btn btn-primary" id="zz" onclick="cl();" type="button" style="width: 90px;">株洲市</button>

    </div>

    <div style="margin-top: 10px">
        <button class="btn btn-primary" id="ld" onclick="cl();" type="button" style="width: 90px;" >娄底市</button>
        <button class="btn btn-primary" id="xxzzz" onclick="cl();" type="button" style="width: 90px;">湘西自治州</button>
        <button class="btn btn-primary" id="xt" onclick="cl();" type="button" style="width: 90px;">湘潭市</button>
    </div>


</div>
<script src="../static/jquery/js/jquery.min.js"></script>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
<script src="../static/bootstrap/js/qq.js"></script>
<script src="../static/validate/js/jquery.validate.min.js"></script>
<script src="../static/validate/js/messages_zh.min.js"></script>
<script src="../static/login/js/login.js"></script>
<script src="../static/toastr/js/toastr.min.js"></script>
<script>
    function cl(str) {
       if(str=='hh'){
         //  alert('进入怀化区县');
           window.location.href="hh_quxian?diquId=e92085932471c255d56284fbd07c0611";
       }
    }

</script>
</body>
</html>

