<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/12/18
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
%>
<html>
<head>
    <title>申告内容查询</title>
    <link type="text/css" href="<%=ctx%>/css/bootstrap.css" rel="stylesheet">
    <link type="text/css" href="<%=ctx%>/css/bootstrap-table.css" rel="stylesheet">
    <link type="text/css" href="<%=ctx%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link type="text/css" href="<%=ctx%>/css/bootstrap-editable.css" rel="stylesheet">
    <link type="text/css" href="<%=ctx%>/css/select2.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-0"></div>
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">查询条件</div>
                        <div class="panel-body">
                            <form id="formSearch" class="form-horizontal">
                                <div class="form-group" style="margin-top:15px">
                                    <label class="control-label col-sm-1" for="datetimepicker">选择日期：</label>
                                    <!--指定 date标记-->
                                    <div class='col-sm-2 date'>
                                        <input id='datetimepicker' type='text' readonly class="form-control" name="date"/>
                                    </div>
                                    <label class="control-label col-sm-1" for="sel_content_key">申告内容关键字</label>
                                    <div class="col-sm-4">
                                        <select id="sel_content_key" multiple="multiple" class="form-control">
                                            <optgroup label="系统设置">
                                                <option value="1">用户管理</option>
                                                <option value="2">角色管理</option>
                                                <option value="3">部门管理</option>
                                                <option value="4">菜单管理</option>
                                            </optgroup>
                                            <optgroup label="订单管理">
                                                <option value="5">订单查询</option>
                                                <option value="6">订单导入</option>
                                                <option value="7">订单删除</option>
                                                <option value="8">订单撤销</option>
                                            </optgroup>
                                            <optgroup label="基础数据">
                                                <option value="9">基础数据维护</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                    <div class="col-sm-4" style="text-align:left;">
                                        <button type="button" style="margin-left:50px" id="btn_query"
                                                class="btn btn-primary">查询
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <form action="" method="">
                        <div id="toolbar" class="btn-group">
                            <button id="btn_add" type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;&nbsp;导出结果
                            </button>
                        </div>
                        <table id="problem" class="table table-hover table-bordered"></table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-table.js"></script>
<script src="<%=ctx%>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=ctx%>/js/moment-with-locales.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=ctx%>/js/select2.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker').datetimepicker({
            format: 'yyyy-mm',
            language: ('zh-CN'),
            locale: moment.locale('zh-cn'),
            minView: 'year',
            startView: 'year',
            autoclose: true
        });
        $("#sel_content_key").select2({
            tags: true,
            maximumSelectionLength: 3  //最多能够选择的个数
        });
    })
    function initTable() {
        var url = "<%=ctx%>/initTable";
        $("#problem").bootstrapTable("destroy");
        $("#problem").bootstrapTable({
            url: url,
            method: "get",
            dataType: "json",
            toolbar: "#toolbar",
            cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            striped: true,//是否显示行间隔色
            queryParams: queryParams,//传递参数（*）查询方法名
            sidePagination: "server",//分页类型 server后端分页 client客户端分页（*）
//            responseHandler: responseHandler,如果后台直接传回｛total： ，rows：[]｝形式则不需要此方法
            pagination: true,//是否显示分页（*）
            pageSize: 10,//每页的记录行数（*）
            pageNumber: 1,//初始化加载第一页，默认第一页
            pageList: [10, 25, 50, 'All'],//可供选择的每页的行数（*）
            height: 500,
            width: $(window).width(),
            showColumns: false,//是否自定义显示列
            showRefresh: false,//是否显示刷新按钮
            minimumCountColumns: 2,//最少允许的列数
            //表前复选框
            clickToSelect: true,//是否启用点击选中行
            idField: "problem_id",//重要---可设置checkbox的值为指定字段
            selectItemName: "key_id",    //设置checkbox name属性，可用于遍历获取选中值
            uniqueId: "no",//每一行的唯一标识，一般为主键列
            showToggle: true,//是否显示详细视图和列表视图的切换按钮
            cardView: false,//是否显示详细视图
            detailView: false,//是否显示父子表
            columns: [
                {
                    checkbox: true
                },
                {
                    field: "problem_id",
                    title: "故障现象编号",
                    width: 10,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field: "problem",
                    title: "故障现象名称",
                    halign: "center",
                    align: "center",
                    width: "80%"
                }
            ]
        });
    }
    $("#btn_query").click(function () {
        var pid = $("#search_problem_id").val();
        var p = $("#search_problem").val();
        $("#problem").bootstrapTable('refresh', {
            url: "<%=ctx%>/searchTable",
            query: {problem_id: pid, problem: p}
        })
    });
    function queryParams(params) {//查询参数传递
        var param = {
            pageNum: this.pageNumber,
            pageSize: this.pageSize,
            limit: this.limit, // 页面大小
            offset: this.offset // 页码
        };
        return param;
    }
</script>
</body>
</html>
