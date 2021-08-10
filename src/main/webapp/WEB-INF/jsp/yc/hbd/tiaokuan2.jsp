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
        #bt{
            margin-top: 10px;
            width: 100%;
            background-color: #1136ea
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
        <h3>中国平安财产保险股份有限公司 平安监护人责任保险条款</h3>
        <div id="div1"></div>
            <p style="text-align: left;margin-top: 10px;">
                <img src="../yucai/tiaokuan1.jpg" width="100%">
            </p>
            <p style="text-align: left;">
                <img src="../yucai/tiaokuan2.jpg" width="100%">
            </p>
            <p style="text-align: left;">
                <img src="../yucai/tiaokuan3.jpg" width="100%">
            </p>
    <%--<p style="text-align: left; ">
        &nbsp;人民币1001元至5000元部分60%；
    </p>
    <p style="text-align: left; ">
        人民币5001元至10000元部分按70%；
    </p>
    <p style="text-align: left;">
        人民币10001元至30000元部分按80%；
    </p>
    <p style="text-align: left; ">
        人民币30000元以上部分按90%给付医疗保险金。
    </p>
    <p style="text-align: left; ">
        3、对交通工具意外险，凡乘有牌有证的非营运车、摩托车、电动车出险的应按保险责任给付。
    </p>
    <div style="text-align: left; ">
        4、动物咬（抓）伤门诊费用凭发票按90%比例赔付，每次最高以600元为限。
    </div>
   <div style="text-align: left; ">
       5、连续投保学平险的学生幼儿，签约的承保公司之间相互视为续保没有等待期；骨折跨承保年度取钢板医疗费用由发生事故时的承保公司负责。
   </div>--%>
    <div>
        <button class="btn btn-primary" id="bt" onclick="cl();" type="button" >关闭</button>
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
    function cl() {
        window.location.href="home";
    }

</script>
</body>
</html>

