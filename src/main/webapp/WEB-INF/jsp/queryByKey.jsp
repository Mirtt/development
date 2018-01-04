<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/12/18
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                    <label class="control-label col-sm-1" for="datetimepicker">选择月份：</label>
                                    <!--指定 date标记-->
                                    <div class='col-sm-2 date'>
                                        <div class="input-daterange input-group" id="datetimepicker">
                                            <input type="text" class="form-control" name="start" id="qBeginTime"/>
                                            <span class="input-group-addon">至</span>
                                            <input type="text" class="form-control" name="end" id="qEndTime"/>
                                        </div>
                                    </div>
                                    <label class="control-label col-sm-1" for="sel_content_key">申告内容关键字</label>
                                    <div class="col-sm-3">
                                        <select id="sel_content_key" multiple="multiple" class="form-control">
                                        </select>
                                    </div>
                                    <label class="control-label col-sm-1" for="sel_process_key">处理过程关键字</label>
                                    <div class="col-sm-3">
                                        <select id="sel_process_key" multiple="multiple" class="form-control">
                                        </select>
                                    </div>
                                    <div class="col-sm-1" style="text-align:left;">
                                        <button type="button" style="margin-left:50px" id="btn_query"
                                                class="btn btn-primary">查询
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div id="toolbar" class="btn-group">
                        <button id="btn_download" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;&nbsp;导出结果
                        </button>
                    </div>
                    <table id="result" class="table table-hover table-bordered"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-table.js"></script>
<script src="<%=ctx%>/js/locales/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=ctx%>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=ctx%>/js/moment-with-locales.min.js"></script>
<script src="<%=ctx%>/js/select2.min.js"></script>
<script src="<%=ctx%>/js/locales/zh-CN.js"></script>
<script type="text/javascript">
    $(function () {
        $('#qBeginTime').datetimepicker({
            format: 'yyyy-mm',
            language: 'zh-CN',
            locale: moment.locale('zh-cn'),
            minView: 'year',
            startView: 'year',
            autoclose: true
        }).on('changeDate', function (e) {
            var startTime = e.date;
            $('#qEndTime').datetimepicker('setStartDate', startTime);
        });
        $('#qEndTime').datetimepicker({
            format: 'yyyy-mm',
            language: 'zh-CN',
            locale: moment.locale('zh-cn'),
            minView: 'year',
            startView: 'year',
            autoclose: true,
            todayBtn: true
        }).on('changeDate', function (e) {
            var endTime = e.date;
            $('#qBeginTime').datetimepicker('setEndDate', endTime);
        });
        $("#sel_content_key").select2({
            placeholder: '请选择',//默认文字提示
            tags: false,//允许手动添加
            allowClear: true,//允许清空
            language: "zh-CN",
            ajax: {
                url: "<%=ctx%>/query/select2ContentKey",
                type: "GET",
                delay: 250,
                dataType: "json",
                data: function (params) {
                    return {contentKey: params.term};
                },
                processResults: function (data) {
                    return {
                        results: data
                    };
                }
            }
        });
        $("#sel_process_key").select2({
            placeholder: '请选择',//默认文字提示
            tags: false,//允许手动添加
            allowClear: true,//允许清空
            language: "zh-CN",
            ajax: {
                url: "<%=ctx%>/query/select2ProcessKey",
                type: "GET",
                delay: 250,
                dataType: "json",
                data: function (params) {
                    var ck = new String();
                    var p = $("#sel_content_key").select2("data");
                    $.each(p, function (i, item) {
                        ck += item.id + ",";
                    });
                    return {
                        contentKey: ck,
                        processKey: params.term
                    };
                },
                processResults: function (data) {
                    return {
                        results: data
                    };
                }
            }
        })
    });
    $("#btn_download").click(function () {
        var balk_no_list=[];
        var t=document.getElementsByName("balk_no_list");
        for(var i=0;i<t.length;i++){
            if (t[i].checked)
                balk_no_list.push(t[i].value);
        }
        if (balk_no_list.length<1){
            alert("请至少选择一行");
        }else {
            var url = "<%=ctx%>/query/report";
            var form=$("<form>");//定义一个form表单
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("method","post");//请求类型
            form.attr("action",url);//请求地址
            $("body").append(form);//将表单放置在web中
            for (var i=0;i<balk_no_list.length;i++){
                var input=$("<input>");
                input.attr("type","hidden");
                input.attr("name","balkNoList");
                input.attr("value",balk_no_list[i]);
                form.append(input);
            }
            form.submit();//表单提交
        }
    });

    $("#btn_query").click(function () {
        $("#result").bootstrapTable("destroy");
        $("#result").bootstrapTable({
            url: "<%=ctx%>/query/key",
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
            idField: "balk_no",//重要---可设置checkbox的值为指定字段
            selectItemName: "balk_no_list",    //设置checkbox name属性，可用于遍历获取选中值
            uniqueId: "no",//每一行的唯一标识，一般为主键列
            showToggle: true,//是否显示详细视图和列表视图的切换按钮
            cardView: false,//是否显示详细视图
            detailView: false,//是否显示父子表
            columns: [
                {
                    checkbox: true
                },
                {
                    field: "type",
                    title: "类型",
                    width: 10,
                    halign: 'center',
                    align: 'center'
                },
                {
                    field: "write_time",
                    title: "时间",
                    width: 10,
                    halign: "center",
                    align: "center"
                },
                {
                    field: "balk_no",
                    title: "受理单号",
                    width: 10,
                    halign: 'center',
                    align: 'center'
                },
                {
                    field: "content_key",
                    title: "申告内容关键字",
                    width: 10,
                    halign: 'center'
                },
                {
                    field: "proc_key",
                    title: "处理过程关键字",
                    width: 10,
                    halign: 'center'
                },
                {
                    field: "problem",
                    title: "故障现象",
                    width: 10,
                    halign: 'center'
                },
                {
                    field: "reason",
                    title: "故障原因",
                    width: 10,
                    halign: 'center'
                }
            ]
        });
    });
    function queryParams(params) {//查询参数传递
        var start = $('#qBeginTime').val();
        var end = $('#qEndTime').val();
        if (start == null || start == "" || end == null || end == "") {
            alert("请输入起止时间");
            return null;
        }
        var ck = new String();
        var c = $("#sel_content_key").select2("data");
        $.each(c, function (i, item) {
            ck += item.text + ",";
        });
        var pk = new String();
        var p = $("#sel_process_key").select2("data");
        $.each(p, function (i, item) {
            pk += item.text + ",";
        });
        var param = {
            pageNum: this.pageNumber,
            pageSize: this.pageSize,
            limit: this.limit, // 页面大小
            offset: this.offset, // 页码
            start: start,
            end: end,
            contentKey: ck,
            processKey: pk
        };
        return param;
    }
</script>
</body>
</html>
