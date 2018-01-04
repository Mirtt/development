<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/12/18
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工单查询导出</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-1">
                    <div class="tabbable" id="tabs-958427">
                        <ul class="nav nav-tabs nav-pills nav-stacked">
                            <li class="active">
                                <a href="#panel-924198" data-toggle="tab">按关键字查询</a>
                            </li>
                            <li>
                                <a href="#panel-1" data-toggle="tab">自定义查询</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-11">
                    <div class="tabbable" id="tabs-145046">
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="panel-924198">
                                <jsp:include page="queryByKey.jsp"/>
                            </div>
                            <div class="tab-pane fade" id="panel-1">
                                <jsp:include page="queryFree.jsp"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
