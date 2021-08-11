<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <t:base type="default"></t:base>
    <title>查询管理系统</title>
</head>
<body class="gray-bg">
<!-- 页面部分 -->
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>缴费统计</h5>
                </div>

                <div class="ibox-content">
                    <button class="btn btn-primary" style="    margin-bottom: 5px" type="button" onclick="daochuIndex('${xuexiaoName}');"><i class="fa fa-level-up"></i>导出</button>
                    <table class="table table-hover">

                        <thead>
                        <tr class="info" style="font-weight: bold">
                            <td>序号</td>
                            <td>名称</td>
                            <td>学生平安保险</td>
                            <td>交通意外、重大疾病险</td>
                            <td>监护人责任险</td>
                            <td>合计</td>
                        </tr>
                        </thead>
                        <thead>
                        <tr class="warning" style="font-weight: bold">
                            <td>#</td>
                            <td style="color: #337ab7">${xuexiaoModel.schoolName}</td>
                            <td style="color: #148cf3">${xuexiaoModel.xuepingxian}</td>
                            <td style="color: #148cf3">${xuexiaoModel.yiwaixian}</td>
                            <td style="color: #148cf3">${xuexiaoModel.jianhurenxian}</td>
                            <td style="color: #148cf3">${xuexiaoModel.total}</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="im" items="${indexmodelSchoolList}" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td>${im.nianjiName}</td>
                                <td>${im.xuepingxian}</td>
                                <td>${im.yiwaixian}</td>
                                <td>${im.jianhurenxian}</td>
                                <td>${im.total}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
<script>

    function daochuIndex(xuexiaoName){
        window.location.href="reportController/exportXuexiao?name="+xuexiaoName;
    }

</script>

</html>

