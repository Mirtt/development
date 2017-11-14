<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/9/14
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ctx = request.getContextPath();
%>
<html>
<head>
    <link href="<%=ctx%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=ctx%>/css/bootstrap-table.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12"
                     style="background: url(<%=ctx%>/images/background.jpg);height: 150px;margin-bottom: 10px">
                    <button type="button" class="btn btn-default btn-link"
                            onclick="location.href= '<%=ctx%>/logout'"
                            style="float:right;">
                        退出
                    </button>
                </div>
            </div>
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">

                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                            class="icon-bar"></span><span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">·</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="<%=ctx%>/search">故障现象分析</a>
                        </li>
                        <li>
                            <a href="<%=ctx%>/ckConfig">申告内容关键字配置</a>
                        </li>
                        <li>
                            <a href="<%=ctx%>/stat">统计</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
    //动态添加active
    $(function () {
        $(".navbar-nav").find("li").each(function () {
            var a = $(this).find("a:first")[0];
            if ($(a).attr("href") === location.pathname) {
                $(this).addClass("active");
            } else {
                $(this).removeClass("active");
            }
        });
    })
</script>
</body>
</html>

