<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/11/15
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处理过程关键字配置</title>
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
                                <label class="control-label col-sm-1" for="search_content_key">申告内容关键字</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="search_content_key" name="content_key"
                                           placeholder="支持模糊查询">
                                </div>
                                <label class="control-label col-sm-1" for="search_process_key">处理过程关键字</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="search_process_key" name="process_key"
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
                        <button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#process-key-modal">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;新增
                        </button>
                    </div>
                    <table id="process_key_config" class="table table-hover table-bordered"></table>
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
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-editable.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-table.js"></script>
<script src="<%=ctx%>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-table-editable.min.js"></script>
<script type="text/javascript">
    $(function () {
        initTable();
        var msg = $("p#msg").text();
        if (msg != null && msg != "") {
            alert(msg);
        }
    });
    function initTable() {
        var url = "<%=ctx%>/pkConfigTable";
        $("#process_key_config").bootstrapTable("destroy");
        var bootstrapTable = $("#process_key_config").bootstrapTable({
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
            pageNumber: 1,//初始化加载第一页，默认第一页
            pageSize: 25,//每页的记录行数（*）
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
                    field: "content_key_id",
                    title: "申告内容关键字编号",
                    width: 10,
                    align: 'center',
                    valign: 'center'
                },
                {
                    field: "content_key",
                    title: "申告内容关键字",
                    halign: "center"
                },
                {
                    field: "process_key",
                    title: "处理过程关键字",
                    halign: "center",
                    editable:{
                        type: 'text',
                        title: "&：连接且关系，或：连接或关系",
                        validate: function (v) {
                            if (!$.trim(v)) return '不能为空';
                        }
                    }
                },
                {
                    field: "process_priority",
                    title: "处理过程优先级",
                    align: "center",
                    halign: "center"
                },
                {
                    field: "reason",
                    title: "故障原因",
                    halign: "center"
                },
                //2017.12.05 yjz 配置“删除”按钮
                {
                    field: "operation",
                    title: "操作",
                    align: "center",
                    valign: "middle",
                    width: '20%',
                    formatter: function (index, row) {
                        return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" " +
                            "data-toggle=\"modal\" data-target=\"#process-key-modal-del\"" +
                            "onclick='setPId(" + row["process_key_id"] + ")' " +
                            "data-row-id=\"" + row.id + "\">删除" + row["process_key_id"] +
                            "<span class=\"glyphicon glyphicon-remove\"></span></button> ";
                    }
                },
                {
                    field:"process_key_id",
                    visible:false
                }
            ],
            onEditableSave: function (field, row) {
                $.ajax({
                    type: "post",
                    url: '<%=ctx%>/editProcessKey',
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
    function queryParams(params) {//查询参数传递
        var pk = $("#search_process_key").val();
        var ck = $("#search_content_key").val();
        var param = {
            process_key: pk,
            content_key: ck,
            pageNum: this.pageNumber,
            pageSize: this.pageSize,
            limit: this.limit, // 页面大小
            offset: this.offset // 页码
        };
        return param;
    }
    $("#btn_query").click(function () {
        var pk = $("#search_process_key").val();
        var ck = $("#search_content_key").val();
        $("#process_key_config").bootstrapTable('refresh', {
            url: "<%=ctx%>/pkConfigSearchTable",
            query: {process_key: pk, content_key: ck,pageNum:this.pageNumber,pageSize: this.pageSize}
        })
    });

    /***
     * 2017.12.05 yjz 删除处理过程关键字
     * @param rowId
     */
    function deleteProcessKey(rowId) {
        $.ajax({
            type: "post",
            url: '<%=ctx%>/editCIdOfProcessKey',
            data: {process_key_id: rowId, contend_key_id: 0},
            dataType: "json",
            success: function (data) {
                if (data.status === "success") {
                    alert('处理过程关键字删除成功');
                }
                else {
                    alert('删除失败');
                }
                initTable();
            }
        })
    }

    /**
     * 2017.12.05 yjz 获取被删除行的ID
     * @param id
     */
    function setPId(id) {
        $("#btn_delete").val(id);
    }
</script>
<div class="modal fade contentKeyModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="process-key-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">配置处理过程关键字</h4>
            </div>
            <form action="<%=ctx%>/addProcessKey" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="add_content_key_id">申告内容关键字id</label>
                        <input type="text" name="content_key_id" class="form-control" id="add_content_key_id" required="required" pattern="[1-9][0-9]*" title="请输入数字id">
                    </div>
                    <div class="form-group">
                        <label for="add_process_key">处理过程关键字</label>
                        <input type="text" name="process_key" class="form-control" id="add_process_key" required="required">
                    </div>
                    <div class="form-group ">
                        <label for="add_process_priority">处理过程优先级</label>
                        <input type="text" name="process_priority" class="form-control" id="add_process_priority" required="required" pattern="[1-9][0-9]*" title="请输入整数">
                    </div>
                    <div class="form-group ">
                        <label for="add_reason">故障原因</label>
                        <input type="text" name="reason" class="form-control" id="add_reason">
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

<!--2017.12.05 yjz 删除处理过程关键字-->
<div class="modal fade contentKeyModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     id="process-key-modal-del">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <div class="modal-header">
                <h4 class="modal-title">删除过程关键字</h4>
            </div>
            <div class="modal-body">
                <label>确认删除该处理过程关键字吗？</label>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <button id="btn_delete" type="button" class="btn btn-primary" data-target="#process-key-modal-del-suc"
                        data-dismiss="modal" onclick="deleteProcessKey($('#btn_delete').val())">删除
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
