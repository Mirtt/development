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
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-0"></div>
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">月度统计查询</div>
                        <div class="panel-body">
                            <form id="formSearch" class="form-horizontal">
                                <div class="form-group" style="margin-top:15px">
                                    <label class="control-label col-sm-1" for="search_year">年</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="search_year"
                                               name="year" placeholder="年">
                                    </div>
                                    <label class="control-label col-sm-1" for="search_month">年</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="search_month"
                                               name="month" placeholder="月">
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
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="chart-bar" style="width: 800px;height:400px;margin: 0 auto;"></div>
<div id="chart-pie" style="width: 600px;height:600px;margin: 0 auto;"></div>


<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<!-- 引入 ECharts 文件 -->
<script src="<%=ctx%>/js/echarts.js"></script>
<script type="text/javascript">
    // 指定图表的配置项和数据
    var initBar = {
        title: {
            text: '故障现象数量',
            x: "center"
        },
        tooltip: {

        },
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
            itemStyle:{
                normal:{
                    color:"#3F846A",
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{c}'
                    }
                }
            },
            barWidth:70
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
    function loadBar(barChart, year, month) {
        $.getJSON('<%=ctx%>/chart?year='+year+'&month='+month, function (json) {
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
    function loadPie(pieChart, year, month) {
        $.getJSON('<%=ctx%>/chart?year='+year+'&month='+month, function (json) {
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
    function drill(param,year,month) {
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
                            pieChart.dispose();
                            backTop(year,month);
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
        $.getJSON('<%=ctx%>/drill?problem=' + encodeURI(encodeURI(param.data.name))+'&year='+year+'&month='+month, function (json) {
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
    function backTop(year,month) {
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
        loadPie(pieChart, year, month);
        pieChart.on("click", drill);
    }
    $(function () {
        var date=new Date;
        var year=date.getFullYear();
        var month=date.getMonth();//查询的记录是上一月的
        // 基于准备好的dom，初始化echarts实例
        var barChart = echarts.init(document.getElementById('chart-bar'));
        var pieChart = echarts.init(document.getElementById('chart-pie'));
        barChart.setOption(initBar);
        pieChart.setOption(initPie);
        loadBar(barChart, year, month);
        loadPie(pieChart, year, month);
    });
    $("#btn_query").click(function () {
        var barChart = echarts.init(document.getElementById('chart-bar'));
        var pieChart = echarts.init(document.getElementById('chart-pie'));
        barChart.setOption(initBar);
        pieChart.setOption(initPie);
        var year = $("#search_year").val();
        var month=$("#search_month").val();
        loadBar(barChart, year, month);
        loadPie(pieChart, year, month);
    })
</script>
</body>
</html>
