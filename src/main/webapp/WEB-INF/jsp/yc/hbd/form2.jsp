<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/context/mytags.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>财信育才投保系统</title>
    <meta name="keywords" content="财信育才投保系统">
    <meta name="description" content="财信育才投保系统">
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

        <form class="form-horizontal m-t" id="signupForm" action="login" method="post">
            <input type="hidden" value="${quxianId}" id="quxianId"/>
            <input type="hidden" value="${xueshengCard}" id="xueshengCard"/>
            <input type="hidden" value="${xueshengName}" id="xueshengName"/>
            <input type="hidden" value="${xuexiaoValue}" id="xuexiaoValue"/>
            <input type="hidden" value="${jieduanId}" id="jieduanId"/>
            <input type="hidden" value="${xuexiaoName}" id="xuexiaoName"/>
            <input type="hidden" value="${nianji}" id="nianji"/>
            <input type="hidden" value="${banji}" id="banji"/>
            <input type="hidden" value="${diquId}" id="diquId"/>
            <input type="hidden" value="${xiangzhengId}" id="xiangzhengId"/>
            <input type="hidden" value="" id="baoxianStr"/>
            <input type="hidden" value="" id="baoxianTotalmoney"/>
            <h3 style="text-align: left;">学生保险总保费共<span id="totalmoney2">70</span>元</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d" id="fruit">
                学平险
                <span style="float: right;color: red;font-weight: bold">
                   40 <input type="checkbox" id="xpx" text = "1_学平险" checked value="40"  name="chk"  onclick="checkboxOnclick('ywx')" />
                </span>
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                交通意外重疾险
                <span style="float: right;color: red;font-weight: bold">
                    20 <input type="checkbox" id="ywx" text = "2_交通意外重疾险" checked value="20" name="chk"  onclick="checkboxOnclick('ywx')" />
                </span>
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                监护人责任险
                <span style="float: right;color: red;font-weight: bold">
                   10 <input type="checkbox" id="zrx" text = "3_护人责任险" checked value="10" name="chk"  onclick="checkboxOnclick('zrx')" />
                </span>
            </p>


            <h3 style="text-align: left;margin-top: 20px;">保险期间</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                起保时间：2021年09月01日零时起
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                止保时间：2022年02月28日二十四时止
            </p>
            <hr class="hr_new"/>

            <h3 style="margin-top: 20px;color: red">学平险</h3>
            <hr class="hr_new_2"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                学生意外伤害保险（通常简称为“学平险”或“学生平安保险”）是一种专门针对在校学生及幼儿园幼儿的活动特点和易发意外伤害及患病而设计的带有公益性质的保险产品，它可以对学生及幼儿园幼儿因意外导致死亡、伤残、医疗和疾病住院等事故提供综合性的保障，是未成年人及大学生社会保障制度的有益补充。其保障范围通常包括“学生、幼儿意外伤害保险”、“学生、幼儿定期保险”和“学生、幼儿意外伤害医疗保险”和“学生、幼儿住院医疗保险”四个部分。
            </p>
            <h3 style="text-align: left;margin-top: 20px;">学平险</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                疾病住院1万元
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                意外门诊3千元
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                意外伤残4万元
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                意外、疾病身故4万元
            </p>

            <h3 style="margin-top: 10px;color: red">交通意外重疾险</h3>
            <hr class="hr_new_2"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                学生交通意外保险是指因搭乘飞机、火车、有牌有证轮船、汽车、摩托车、电动车、三轮车、农用车等交通工具发生的交通事故或遇交通事故导致的意外身故或伤残、医院医疗的，保险人根据保险条款、合同负责理赔。
            </p>
            <h3 style="text-align: left;margin-top: 20px;">交通意外重疾险</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                交通意外（身故、伤残）35万元
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                交通意外住院医疗3.5万元
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                25种重大疾病10万元
            </p>

            <h3 style="margin-top: 10px;color: red">监护人责任险</h3>
            <hr class="hr_new_2"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                监护人责任险是专门为在校学生设计的一种带有公益性质的险种，保险责任涵盖了意外伤害、意外伤害医疗以及住院医疗在内的多项保障;监护人责任险;被誉为;校园生命工程;具有保费低、保障高、责任广的特点。投保;监护人责任保险;，可以在很大程度上减轻学生、幼儿因意外伤害给家庭带来的沉重经济负担，有效化解和转嫁家庭的经济风险。
            </p>
            <h3 style="text-align: left;margin-top: 20px;">监护人责任险</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                被监护人的人身伤亡赔偿 10万元
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                第三者的人身伤亡赔偿 15万元
            </p>

            <h3 style="text-align: left;margin-top: 20px;">保险条款</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                <a>我已阅读并了解《学生保险服务告知书》、《授权委托书》、 《致学生家长的一封信》</a>
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                <a>中国平安财产保险股份有限公司 平安监护人责任保险条款</a>
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                <input type="radio" name="yuedu" value="1"/> <span style="font-size: 15px;color: #888;">我已经阅读并同意以上条款及协议</span>
            </p>

            <button type="button" class="btn btn-primary block full-width m-b" onclick="doNxet()">金额：￥ <span id="totalmoney">70</span> 元</button>


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
    function cl(str) {
       if(str=='hh'){
           alert('进入怀化区县');
           window.location.href="hh_quxian";
       }
    }

    function doNxet() {
        //alert('进入怀化区县');
        //判读阅读框是否选中
        var val=$('input:radio[name="yuedu"]:checked').val();
        if(val==null){
            //alert("什么也没选中!");
            toastr.error('请选中我已经阅读并同意以上条款及协议');
            return false;
        }
        else{
            checkboxOnclick();
            var quxianId = $("#quxianId").val();
            var xueshengCard = $("#xueshengCard").val();
            var xueshengName = $("#xueshengName").val();
            var jieduanId = $("#jieduanId").val();
            var xuexiaoName =$("#xuexiaoName").val();
            var xuexiaoValue =$("#xuexiaoValue").val();
            var nianji =$("#nianji").val();
            var banji = $("#banji").val();
            var diquId = $("#diquId").val();
            var xiangzhengId = $("#xiangzhengId").val();
            var baoxianStr = $("#baoxianStr").val();
            var baoxianTotalmoney = $("#baoxianTotalmoney").val();
            window.location.href="ychome/form3?quxianId="+quxianId+"&xueshengCard="+xueshengCard+ "&xueshengName="+xueshengName+"&jieduanId="+jieduanId+"&xuexiaoName="+xuexiaoName+"&nianji="+nianji+"&banji="+banji+"&xuexiaoValue="+xuexiaoValue+"&diquId="+diquId+"&xiangzhengId="+xiangzhengId+"&baoxianStr="+baoxianStr+"&baoxianTotalmoney="+baoxianTotalmoney;

        }

    }
    
    function checkboxOnclick() {
        var chk_value =[];//定义一个数组
        var total = 0
        var baoxianStr = [];
        $('input[name="chk"]:checked').each(function(){//遍历每一个名字为nodes的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中

            var text = $(this).attr("text");
            var money = $(this).val();
            var str = text+"_"+money;
            baoxianStr.push(str);
        });
        //var groups = chk_value.join(",");
        for(var i = 0; i < chk_value.length; i++) {
            total += Number(chk_value[i])
        }

        $("#totalmoney").html(total);
        $("#totalmoney2").html(total);
        $("#baoxianStr").val(baoxianStr);
        $("#baoxianTotalmoney").val(total);

    }


</script>
</body>
</html>

