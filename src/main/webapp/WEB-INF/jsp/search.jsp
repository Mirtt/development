<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/9/14
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
%>
<html>
<head>
    <title>成功</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <form action="/getResult" method="post">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>
                            #&nbsp<input type="checkbox" onclick="checkAll()">
                        </th>
                        <th>
                            申告内容
                        </th>
                        <th>
                            处理过程
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dictList}" var="dict">
                        <tr>
                            <td>
                                <input type="checkbox" name="key_id" value="${dict.id}">
                            </td>
                            <td>
                                    ${dict.content_key}
                            </td>
                            <td>
                                    ${dict.proc_key}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-primary">
                    导出结果
                </button>
            </form>
        </div>
    </div>
</div>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script type="text/javascript">
    //checkbox 全选/取消全选
    var isCheckAll = false;
    function checkAll() {
        if (isCheckAll) {
            $("input[type='checkbox']").each(function () {
                this.checked = false;
            });
            isCheckAll = false;
        } else {
            $("input[type='checkbox']").each(function () {
                this.checked = true;
            });
            isCheckAll = true;
        }
    }
</script>
</body>
</html>
