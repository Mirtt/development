<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ctx = request.getContextPath();
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>xxxx系统</title>
    <link href="<%=ctx%>/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container-fluid">
    <div class="row" style="height: 15%">
        <div class="col-md-12">
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4" style="margin-top: 10%">
                    <div class="row">
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-8">
                            <h3 class="text-center">
                                xxxx系统
                            </h3>
                            <c:if test="${msg != null}">
                                <p style="color: #c12e2a;text-align: center">${msg}</p>
                            </c:if>
                            <form class="form-horizontal" role="form" action="<%=ctx%>/login" method="post"
                                  style="margin-top: 10%">
                                <div class="form-group">

                                    <label for="user_name" class="col-sm-1 control-label">

                                    </label>
                                    <div class="col-sm-10">
                                        <input class="form-control" id="user_name" type="text" name="user_name" required="required" placeholder="用户名">
                                    </div>
                                </div>
                                <div class="form-group">

                                    <label for="password" class="col-sm-1 control-label">

                                    </label>
                                    <div class="col-sm-10">
                                        <input class="form-control" id="password" type="password" name="password" required="required" placeholder="密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-1 col-sm-10">
                                        <div class="checkbox">

                                            <label>
                                                <input type="checkbox"> 记住用户名
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-1 col-sm-10">

                                        <button type="submit" class="btn btn-default">
                                            登录
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-2">
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap.min.js"></script>
</body>
</html>
