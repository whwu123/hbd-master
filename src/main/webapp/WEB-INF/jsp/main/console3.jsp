<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default"></t:base>
<title>查询管理系统</title>
</head>

<body class="gray-bg">
<div class="row">

    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-5">
            <h2>Hello！</h2>
            <small>欢迎扫描以下二维码进行缴费：</small>
            <br>
            <br>
            <img src="hbd/1628036816.png" width="100%" style="max-width:264px;">
            <br>
        </div>
        <div class="col-sm-5">
            <h2>Hello！</h2>
            <small>学生信息修改二维码</small>
            <br>
            <br>
            <img src="hbd/123.png" width="100%" style="max-width:264px;">
            <br>
        </div>
        <%--<div class="col-sm-4">
            <h4>项目特点：</h4>
            <ol>
                <li>开箱即用，节省开发时间，提高开发效率</li>
                <li>代码全部开源，持续更新，共同维护</li>
                <li>支持分布式部署，session统一由redis进行管理</li>
                <li>基于SpringBoot，简化了大量项目配置和maven依赖，让您更专注于业务开发</li>
                <li>使用分层设计，分为dao，service，Controller，view层，层次清楚，低耦合，高内聚</li>
                <li>提供了诸多的UI组件</li>
                <li>友好的代码结构及注释，便于阅读及二次开发</li>
                <li>灵活的权限控制, 整合shiro，可控制到页面或按钮，满足绝大部分的权限需求,优化权限注解方便权限配置</li>
                <li>日志记录采用aop(LogAop类)方式，可对用户所有操作进行记录</li>
                <li>引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能</li>
                <li>数据统计报表：丰富的报表统计功能</li>
                <li>集成jsp页面，采用标准JSTL标签库对常用组件进行封装，便于将传统项目过度到springboot</li>
                <li>组件库丰富，对常用页面组件进行了代码封装，提高开发效率</li>
                <li>前端页面简洁优美，支持移动端</li>
                <li>更多……</li>
            </ol>
        </div>--%>

    </div>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>联系信息</h5>

                    </div>
                    <div class="ibox-content">
                        <p><i class="fa fa-send-o"></i> 联系电话：<a href="http://www.active4j.com/" target="_blank">0745-2289827</a>
                        </p>
                        <p><i class="fa fa-qq"></i> QQ：<a href="javascript:;" target="_blank">472997069</a>
                        </p>
                        <p><i class="fa fa-weixin"></i> 微信号：<a href="javascript:;">wuchunhui</a>
                        </p>
                       <%-- <p><i class="fa fa-credit-card"></i> qq群：<a href="javascript:;">203802692</a>
                        </p>--%>
                    </div>
                </div>
            </div>
            <%--<div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>更新日志</h5>
                    </div>
                    <div class="ibox-content no-padding">
                        <div class="panel-body">
                            <div class="panel-group" id="version">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.0</a><code class="pull-right">2021.8.4</code>
                                            </h5>
                                    </div>
                                    <div id="v1.0" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="alert alert-warning">项目发布并开源</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>--%>
        </div>
    </div>
</div>
</body>


</html>

