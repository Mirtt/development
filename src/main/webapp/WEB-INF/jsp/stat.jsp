<%--
  Created by IntelliJ IDEA.
  User: Zyq
  Date: 2017/11/6
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计图形</title>
    <meta charset="utf-8">
</head>
<body>
<%@include file="common/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="form-group">
                <form id="download" action="<%=ctx%>/reportMonth" method="post">
                    <label>选择日期：</label>
                    <!--指定 date标记-->
                    <div class='input-group date'>
                        <input id='datetimepicker' type='text' readonly class="form-control" name="date"/>
                    </div>
                    <button id="btn_download" type="submit" class="btn btn-default" style="margin-top: 5px">
                        <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;&nbsp;导出故障单
                    </button>
                </form>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="chart-bar" style="width: 800px;height:400px;margin: 0 auto;"></div>
<div id="chart-pie" style="width: 600px;height:600px;margin: 0 auto;"></div>

<button id="test">alert</button>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=ctx%>/js/moment-with-locales.min.js"></script>
<script src="<%=ctx%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- 引入 ECharts 文件 -->
<script src="<%=ctx%>/js/echarts.js"></script>
<script type="text/javascript">
    // 指定图表的配置项和数据
    var initBar = {
        title: {
            text: '故障现象数量',
            x: "center"
        },
        tooltip: {},
        legend: {
            data: []
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data: [],
            itemStyle: {
                normal: {
                    color: "#3F846A",
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{c}'
                    }
                }
            },
            barWidth: 70
        }]
    };
    var initPie = {
        title: {
            text: '故障现象占比',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a}： <br/>{b}:{c}条 ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: []
        },
        series: [
            {
                name: '占比',
                type: 'pie',
                radius: '55%',
                center: ['40%', '50%'],
                data: [],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    function loadBar(barChart, dateTime) {
        $.getJSON('<%=ctx%>/chart?date=' + dateTime, function (json) {
            barChart.setOption({
                legend: {
                    data: json["problems"]
                },
                xAxis: {
                    data: json["problems"]
                },
                series: {
                    data: json["problemMapArray"]
                }
            });
        });
    }
    function loadPie(pieChart, dateTime) {
        $.getJSON('<%=ctx%>/chart?date=' + dateTime, function (json) {
            pieChart.setOption({
                legend: {
                    data: json["problems"]
                },
                series: {
                    data: json["problemMapArray"]
                }
            });
        });
        pieChart.on("click", drill);
    }
    function drill(param) {
        var dateTime = $("#datetimepicker").val();
        var pieChart = echarts.init(document.getElementById('chart-pie'));
        var initDrillPie = {
            title: {
                text: '故障原因占比',
                x: 'center'
            },
            toolbox: {
                show: true,
                left: "5%",
                itemSize: 30,
                feature: {
                    dataView: {show: true, readOnly: false},
                    myTool: {
                        show: true,
                        title: '返回',
                        icon: 'image://<%=ctx%>/images/back.png',
                        onclick: function () {
                            var dateTime = $("#datetimepicker").val();
                            pieChart.dispose();
                            backTop(dateTime);
                        }
                    }
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a}： <br/>{b}:{c}条 ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 10,
                top: 20,
                bottom: 20,
                data: []
            },
            series: [
                {
                    name: '占比',
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: [],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        pieChart.setOption(initDrillPie);
        $.getJSON('<%=ctx%>/drill?date=' + dateTime + '&problem=' + encodeURI(encodeURI(param.data.name)), function (json) {
            pieChart.setOption({
                legend: {
                    data: json["reasons"]
                },
                series: {
                    data: json["reasonMapArray"]
                }
            });
        });
        pieChart.off("click");
    }
    function backTop(dateTime) {
        var pieChart = echarts.init(document.getElementById('chart-pie'));
        var initPie = {
            title: {
                text: '故障现象占比',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a}： <br/>{b}:{c}条 ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 10,
                top: 20,
                bottom: 20,
                data: []
            },
            series: [
                {
                    name: '占比',
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: [],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        pieChart.setOption(initPie);
        loadPie(pieChart, dateTime);
        pieChart.on("click", drill);
    }
    $(function () {
        $('#datetimepicker').datetimepicker({
            format: 'yyyy-mm',
            language: ('zh-CN'),
            locale: moment.locale('zh-cn'),
            minView: 'year',
            startView: 'year',
            autoclose: true
        });
        $("#datetimepicker").change(function () {
            var barChart = echarts.init(document.getElementById('chart-bar'));
            var pieChart = echarts.init(document.getElementById('chart-pie'));
            barChart.setOption(initBar);
            pieChart.setOption(initPie);
            var dateTime = $('#datetimepicker').val();
            loadBar(barChart, dateTime);
            loadPie(pieChart, dateTime);
        })
    });
</script>
</body>
</html>
