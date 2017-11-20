<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/11/20
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<select class="js-example-basic-single"   align="left">
    <option></option>
    <option value="123">1</option>
    <option value="1234">1234</option>
    <option value="12345">12345</option>
    <option value="23456">23456</option>
    <option value="8888">8888</option>
    <option value="fan">范围</option>
</select>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/select2.min.js"></script>
<script type="text/javascript">
    $(".js-example-basic-single").select2({
        placeholder : '请选择界面字段',
        language: "zh-CN"
    });
</script>
</body>
</html>
