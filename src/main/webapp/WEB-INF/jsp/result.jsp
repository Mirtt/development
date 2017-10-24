<%--
  Created by IntelliJ IDEA.
  User: 99689
  Date: 2017/9/15
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果</title>
</head>
<body>
<%@include file="common/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>
                        文件名
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${searchList}" var="item">
                    <tr>
                        <td>
                            <a href="/download?search_time=${item}">${item}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap.min.js"></script>
</body>
</html>
