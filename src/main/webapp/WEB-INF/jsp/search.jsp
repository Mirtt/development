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
                            <input type="checkbox" id="checkAll">
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
            <c:if test="${msg != null}">
                <p id="msg" style="display: none">${msg}</p>
            </c:if>
        </div>
    </div>
</div>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script type="text/javascript">
    //checkbox 全选/取消全选
    $("#checkAll").click(function () {
        $("[name=key_id]:checkbox").prop('checked',this.checked);
    });
    //取消全选时同时取消全选框的checked状态
    $("[name=key_id]:checkbox").click(function () {
        var flag = true;
        $("[name=key_id]:checkbox").each(function () {
            if (!this.checked){
                flag = false;
            }
        });
        $("#checkAll").prop("checked", flag);
    });
    //前台判断不要传空值
    $("button[type=submit]").click(function () {
        if ($("[name=key_id]:checkbox:checked").length < 1){
            alert("请选择关键字");
            return false;
        }else {
            this.submit();
        }
    });
    //打出后台的msg错误信息
    $().ready(function () {
        var msg=$("p#msg").text();
        if (msg != null && msg != ""){
            alert(msg);
        }
    });
</script>
</body>
</html>
