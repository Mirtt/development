<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/9/14
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>成功</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-0"></div>
                <div class="col-md-12">
                    <div id="toolbar" class="btn-group">
                        <button id="btn_add" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                        </button>
                        <button id="btn_delete" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                        </button>
                    </div>
                    <form action="<%=ctx%>/report" method="post">
                        <button type="submit" class="btn btn-primary" style="margin-bottom: 10px">
                            导出结果
                        </button>
                        <table id="problem" class="table table-hover table-bordered"></table>
                    </form>
                    <a href="<%=ctx%>/testChart">测试后台</a>
                    <c:if test="${msg != null}">
                        <p id="msg" style="display: none">${msg}</p>
                    </c:if>
                </div>
                <div class="col-md-0"></div>
            </div>
        </div>
    </div>
</div>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-table.js"></script>
<script type="text/javascript">
    //checkbox 全选/取消全选
    $("#checkAll").click(function () {
        $("[name=key_id]:checkbox").prop('checked', this.checked);
    });
    //取消全选时同时取消全选框的checked状态
    $("[name=key_id]:checkbox").click(function () {
        var flag = true;
        $("[name=key_id]:checkbox").each(function () {
            if (!this.checked) {
                flag = false;
            }
        });
        $("#checkAll").prop("checked", flag);
    });
    //前台判断不要传空值
    $("button[type=submit]").click(function () {
        if ($("[name=key_id]:checkbox:checked").length < 1) {
            alert("请选择关键字");
            return false;
        } else {
            this.submit();
        }
    });
    //打出后台的msg错误信息
    $(function () {
        var msg = $("p#msg").text();
        if (msg != null && msg != "") {
            alert(msg);
        }
    });
    //初始化table（使用bootstrap table插件）
    $(function () {
        initTable();
    });

    function initTable() {
        var url="<%=ctx%>/problem";
        $("#problem").bootstrapTable({
            url:url,
            method:"get",
            dataType:"json",
            toolbar:"#toolbar",
            cache:false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            striped: true,//是否显示行间隔色
            queryParams: queryParams,//传递参数（*）查询方法名
            sidePagination:"server",//分页类型 server后端分页 client客户端分页（*）
//            responseHandler: responseHandler,如果后台直接传回｛total： ，rows：[]｝形式则不需要此方法
            pagination:true,//是否显示分页（*）
            pageNumber: 1,//初始化加载第一页，默认第一页
            pageSize: 10,//每页的记录行数（*）
            pageList: [10, 25, 50, 100],//可供选择的每页的行数（*）
            height: 500,
            width:$(window).width(),
            showColumns: false,//是否自定义显示列
            showRefresh: true,//是否显示刷新按钮
            minimumCountColumns: 2,//最少允许的列数
            //表前复选框
            clickToSelect: true,//是否启用点击选中行
            idField: "problem_id",//重要---可设置checkbox的值为指定字段
            selectItemName:"key_id",    //设置checkbox name属性，可用于遍历获取选中值
            uniqueId: "no",//每一行的唯一标识，一般为主键列
            showToggle: true,//是否显示详细视图和列表视图的切换按钮
            cardView: false,//是否显示详细视图
            detailView: false,//是否显示父子表
            columns:[
                {
                  checkbox:true
                },
                {
                    field:"problem_id",
                    title:"故障现象编号",
                    width:10,
                    align : 'center',
                    valign : 'middle'
                },
                {
                    field:"problem",
                    title:"故障现象名称"
                },
                {
                    field:"change",
                    title:"操作"
                }
            ]
        });
    }
    function queryParams(params) {//查询参数传递
        var param={
            pageNum : this.pageNumber,
            pageSize : this.pageSize,
            limit : this.limit, // 页面大小
            offset : this.offset // 页码
        }
        return param;
    }
    var $remove=$("#btn_delete");
    $remove.click(function () {
        $.POST({

        });
    })
</script>
</body>
</html>

<%--<thead>--%>
<%--<tr>--%>
    <%--<th>--%>
        <%--<input type="checkbox" id="checkAll">--%>
    <%--</th>--%>
    <%--<th>--%>
        <%--故障现象--%>
    <%--</th>--%>
    <%--<th>--%>
        <%--申告内容关键字--%>
    <%--</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach items="${pList}" var="p">--%>
    <%--<tr>--%>
        <%--<td>--%>
            <%--<input type="checkbox" name="key_id" value="${p.problem_id}">--%>
        <%--</td>--%>
        <%--<td>--%>
                <%--${p.problem}--%>
        <%--</td>--%>
        <%--<td>--%>
                <%--${p.contentKeyList}--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
