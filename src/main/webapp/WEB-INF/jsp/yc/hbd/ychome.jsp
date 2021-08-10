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
        .hr_new{
            margin-top: 10px;
            margin-bottom: 10px;
            border: 0;
            border-top: 2px solid #1136ea;
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
        <h3>致学生家长的一封信</h3>
            <hr class="hr_new"/>
       <%-- <div id="div1"></div>--%>
            <p style="text-align: left;margin-top: 10px;">
                尊敬的学生家长、监护人:
            </p>
            <p style="text-align: left;">
                &nbsp;&nbsp;&nbsp;&nbsp;您好!
            </p>
            <p style="text-align: left;">
                &nbsp;&nbsp;&nbsp;&nbsp;孩子是家庭的未来和希望，是祖国的未来，为他们提供一个安全的生活和学习环境，让孩子能够健康、快乐的成长是家长和我们共同的责任。孩子正处于长身体、长知识的时期，自我防护意识和保护能力都比较弱，受到各种疾病、意外事故的侵害相对较多，意外伤害已成为威胁青少年的“头号杀手”，昂贵的医疗费往往给学生家庭带来沉重的经济负担，直接影响到家庭生活的稳定、孩子的健康和学业。
            </p>
    <p style="text-align: left; ">
        &nbsp;&nbsp;&nbsp;&nbsp;学生平安保险是专门针对学生风险开发的产品，保险责任涵盖了意外事故的门诊和住院、疾病住院、保费低、保障高。为您的孩子提供全方位的风险保障，有效化解和转移家庭的经济风险(具体的保险责任、理赔要求和理赔程序以保险公司相关规定为准)。
    </p>
    <p style="text-align: left; ">
        &nbsp;&nbsp;&nbsp;&nbsp;xxxxxxxx有限公司愿与您共同关心、呵护孩子的长。如需要为您的孩子投保字生平安保险，请您仔细阅读此一封信，保险公司的<a href="tiaokuan" style="color: #1237ea;">《相关条款与保障范围》</a>和<a href="tebie" style="color: #1237ea;">《特别约定》</a>，按照自愿的原则办理网上投保手续。
    </p>
    <p style="text-align: left;">
        &nbsp;&nbsp;&nbsp;&nbsp;报案、服务咨询电话:0745-xxxxxxx
    </p>
    <p style="text-align: left; ">
        &nbsp;&nbsp;&nbsp;&nbsp;微信支付交费程序
    </p>
    <p style="text-align: left; ">
        &nbsp;&nbsp;&nbsp;&nbsp;请学生家长(或监护人)签署好自愿购买保险项目的委托后，通过手机扫描学生保险微信二维码进入“怀化市校园安全风险管理”系统，按照系统提示完成交费。不具备条件的家长(或监护人)可通过他人代交保险费。
    </p>
    <div>
        <img src="../yucai/home.jpg" width="100px" height="100px">
    </div>
   <div style="text-align: left; ">
       <input type="checkbox" id="checkbox1"/>
       我已仔细阅读本信的内容、<a href="tiaokuan" style="color: #1237ea;">《相关条款与保障范围》</a> 和<a href="tebie" style="color: #1237ea;">《特别约定》</a>
   </div>
    <div>
        <button class="btn btn-primary" id="bt" onclick="queding()" type="button" >确定</button>
    </div>
        <%--<form class="form-horizontal m-t" id="signupForm" action="login" method="post">
            <div class="form-group">
                <input type="text" name="userName" id="userName" value="admin" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" name="password" id="password" value="123456" class="form-control" placeholder="密码" required="">
            </div>
            <!-- <div class="form-group">
                <input id="randCode" name="randCode" type="text" class="form-control" placeholder="验证码" required="" style="width: 60%">
                <div style="float: right; margin-top: -3.4rem;">
                     <img id="randCodeImage" src="verCode" />
                </div>
            </div> -->
            <button type="button" class="btn btn-primary block full-width m-b" onclick="doSubmit()">登 录</button>

            <!-- <p class="text-muted text-center"> <a href="https://github.com/yunchaoyun/active4j-oa">github</a> | <a href="https://gitee.com/active4j/active4j-oa">码云</a>
            </p> -->
        </form>--%>

</div>
<script src="../static/jquery/js/jquery.min.js"></script>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
<script src="../static/bootstrap/js/qq.js"></script>
<script src="../static/validate/js/jquery.validate.min.js"></script>
<script src="../static/validate/js/messages_zh.min.js"></script>
<script src="../static/login/js/login.js"></script>
<script src="../static/toastr/js/toastr.min.js"></script>
<script>
    function queding() {

        var isChecked = $('#checkbox1').prop('checked');
        //alert(isChecked)
        if(isChecked){
            //toastr.success('进入下一个页面');

            window.location.href="diqu";
        }else {

            toastr.error('请先阅读《相关条款与保障范围》和《特别约定》');
        }

    }

</script>
</body>
</html>

