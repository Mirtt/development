<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/11/9
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申告内容关键字配置</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
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
                                <label class="control-label col-sm-1" for="search_problem">故障现象</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="search_problem" name="problem"
                                           placeholder="支持模糊查询">
                                </div>
                                <label class="control-label col-sm-1" for="search_content_key">申告内容关键字</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="search_content_key" name="content_key"
                                           placeholder="支持模糊查询">
                                </div>
                                <div class="col-sm-2" style="text-align:left;">
                                    <button type="button" style="margin-left:50px" id="btn_query"
                                            class="btn btn-primary">查询
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div id="toolbar" class="btn-group">
                        <button id="btn_refresh" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;&nbsp;刷新
                        </button>
                        <button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#content-key-modal">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;新增
                        </button>
                    </div>
                    <table id="content_key_config" class="table table-hover table-bordered"></table>
                    <a href="<%=ctx%>/searchTable?problem_id=&problem='2323' ">测试后台</a>
                    <c:if test="${msg != null}">
                        <p id="msg" style="display: none">${msg}</p>
                    </c:if>
                </div>
                <div class="col-md-0"></div>
            </div>
        </div>
    </div>
</div>
<script src="<%=ctx%>/js/bootstrap-editable.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-table.js"></script>
<script src="<%=ctx%>/js/locales/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-table-editable.min.js"></script>
<script type="text/javascript">
    //打出后台的msg错误信息
    $(function () {
        var msg = $("p#msg").text();
        if (msg != null && msg != "") {
            alert(msg);
        }
    });

    $(function () {
        initTable();
    });
    function initTable() {
        var url = "<%=ctx%>/ckConfigTable";
        $("#content_key_config").bootstrapTable("destroy");
        var bootstrapTable = $("#content_key_config").bootstrapTable({
            url: url,
            method: "get",
            dataType: "json",
            toolbar: "#toolbar",
            cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            striped: true,//是否显示行间隔色
            queryParams: function () {
                var p = $("#search_problem").val();
                var ck = $("#search_content_key").val();
                var param = {
                    problem: p,
                    content_key: ck,
                    pageNum: this.pageNumber,
                    pageSize: this.pageSize,
                    limit: this.limit, // 页面大小
                    offset: this.offset // 页码
                };
                return param;
            },//传递参数（*）查询方法名
            sidePagination: "server",//分页类型 server后端分页 client客户端分页（*）
//            responseHandler: responseHandler,如果后台直接传回｛total： ，rows：[]｝形式则不需要此方法
            pagination: true,//是否显示分页（*）
            pageNumber: 1,//初始化加载第一页，默认第一页
            pageSize: 10,//每页的记录行数（*）
            pageList: [10, 25, 50, 'All'],//可供选择的每页的行数（*）
            height: 500,
            width: $(window).width(),
            showColumns: false,//是否自定义显示列
            showRefresh: false,//是否显示刷新按钮
            minimumCountColumns: 2,//最少允许的列数
            //表前复选框
            clickToSelect: false,//是否启用点击选中行
//            idField: "problem_id",//重要---可设置checkbox的值为指定字段
//            selectItemName: "key_id",    //设置checkbox name属性，可用于遍历获取选中值
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
                    valign: 'center'
                },
                {
                    field: "problem",
                    title: "故障现象名称",
                    halign: "center"
                },
                {
                    field: "content_key",
                    title: "申告内容关键字",
                    halign: "center",
                    editable:{
                        type: 'text',
                        title: '&：连接且关系，或：连接或关系',
                        validate: function (v) {
                            if (!$.trim(v)) return '不能为空';
                        }
                    }
                },
                {
                    field: "content_priority",
                    title: "申告内容优先级",
                    align: "center",
                    halign: "center",
                    sortalbe:true
                },
                {
                    field: "operation",
                    title: "操作",
                    align: "center",
                    halign: "center",
                    formatter: function (index, row) {
                        return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\">编辑" + row["content_key_id"] + "<span class=\"glyphicon glyphicon-pencil\"></span></button> ";
                    }
                },
                {
                    field:"content_key_id",
                    visible:false
                }
            ],
            onEditableSave: function (field, row) {
                $.ajax({
                    type: "post",
                    url: '<%=ctx%>/editContentKey?cid='+row["content_key_id"]+'&ck='+row["content_key"],
                    data:row,
                    dataType: "json",
                    success: function (data) {
                        if (data.status === "success") {
                            alert('编辑成功');
                        }
                        initTable();
                    }
                })
            }
        });
    }
    $("#btn_refresh").click(function () {
        initTable();
    });
    $("#btn_query").click(function () {
        var p = $("#search_problem").val();
        var ck = $("#search_content_key").val();
        $("#content_key_config").bootstrapTable('refresh', {
            url: "<%=ctx%>/ckConfigSearchTable",
            query: {problem: p, content_key: ck,pageNum:this.pageNumber,pageSize:this.pageSize}
        })
    });
</script>
<div class="modal fade contentKeyModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="content-key-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">配置申告内容关键字</h4>
            </div>
            <form action="<%=ctx%>/addContentKey" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="add_problem">故障现象</label>
                        <input type="text" name="problem" class="form-control" id="add_problem" required="required">
                    </div>
                    <div class="form-group">
                        <label for="add_content_key">申告内容关键字</label>
                        <input type="text" name="content_key" class="form-control" id="add_content_key">
                    </div>
                    <div class="form-group ">
                        <label for="add_content_priority">申告内容优先级</label>
                        <input type="text" name="content_priority" class="form-control" id="add_content_priority" pattern="^[1-9]\d*$" title="请输入整数">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
