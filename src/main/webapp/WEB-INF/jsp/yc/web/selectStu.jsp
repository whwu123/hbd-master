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
<div class="middle-box text-center loginscreen  animated fadeInDown">

        <form class="form-horizontal m-t" id="signupForm" action="webController/selectStuContent" method="post" onsubmit="return toVaild()">
            <h3 style="text-align: left;">缴费学生信息查询</h3>
            <p style="color: red;">输入其中任何一条信息即可查询</p>
            <hr class="hr_new"/>

            <div class="form-group">
                <label class="col-sm-4 control-label">身份证号：</label>
                <div class="col-sm-8">
                    <input   type="text" class="form-control" id="xueshengCard" name="xueshengCard"  placeholder="缴过费的学生身份证"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">支付流水号：</label>
                <div class="col-sm-8">
                    <input  type="text" class="form-control" id="payNum" name="payNum"  placeholder="订单流水号"/>
                </div>
            </div>
            <%--<div class="form-group">
                <label class="col-sm-4 control-label">投保人电话：</label>
                <div class="col-sm-8">
                    <input  type="text" class="form-control" id="toubaorenPhone" name="toubaorenPhone"  placeholder="投保人（监护人）电话"/>
                </div>
            </div>--%>

            <button type="submit" class="btn btn-primary block full-width m-b" >查询</button>
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

    $(function(){
        $('#xueshengCard').blur(function(){
            var num = $("#xueshengCard").val();
            if(isCardID(num)){
                //  alert("正确")
                //进入后台去进行检验

            }else{
                toastr.error('你输入的身份证长度或格式错误');
                //$("#card").focus();
            }

        });

    })

    function toVaild(){
        var xueshengCard = $("#xueshengCard").val();
        var payNum = $("#payNum").val();
      //  var toubaorenPhone = $("#toubaorenPhone").val();
        if(xueshengCard != '' || payNum != '' ){
            return true
        }else{
            toastr.warning('请填写身份证或者支付流水号');
            return false
        }
    }


    function doNxet() {
       // window.location.href="hh_quxian";
        if(!$("#signupForm").valid()) {
            return;
        }

        var xueshengCard = $("#xueshengCard").val();
        var payNum = $("#payNum").val();


        //登录
        $.post("webController/selectStuContent",
            {
                xueshengCard:xueshengCard,
                payNum:payNum
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


    function isCardID(sId) {
        if (!/(^\d{15}$)|(^\d{17}(\d|X|x)$)/.test(sId)) {
            console.log('你输入的身份证长度或格式错误')
            return false
        }
        //身份证城市
        var aCity = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外"
        };
        if (!aCity[parseInt(sId.substr(0, 2))]) {
            console.log('你的身份证地区非法')
            return false
        }

        // 出生日期验证
        var sBirthday = (sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" +
            Number(sId.substr(12, 2))).replace(/-/g, "/"),
            d = new Date(sBirthday)
        if (sBirthday != (d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate())) {
            console.log('身份证上的出生日期非法')
            return false
        }

        // 身份证号码校验
        var sum = 0,
            weights = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2],
            codes = "10X98765432"
        for (var i = 0; i < sId.length - 1; i++) {
            sum += sId[i] * weights[i];
        }
        console.log(sum);
        var last = codes[sum % 11]; //计算出来的最后一位身份证号码
        if (sId[sId.length - 1] != last) {
            console.log('你输入的身份证号非法')
            return false
        }

        return true
    }

</script>
</body>
</html>

