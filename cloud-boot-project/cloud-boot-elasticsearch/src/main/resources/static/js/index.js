layui.use(["jquery", "table","layer"], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table;
    //第一个实例
    table.render({
        elem: "#demo_hash"
        , height: 500
        ,title: '异常信息表'
        , url: "/monitor"
        , page: true
        , toolbar: '#toolbarDemo'
        , defaultToolbar: ['filter', 'print', 'exports']
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: "retCode", title: "retCode", width: 120, fixed: "left"}
            , {field: "transDate", title: "transDate", width: 120, sort: true}
            , {field: "sofaTraceId", title: "sofaTraceId", width: 250}
            , {field: "bankSrlNo", title: "bankSrlNo", width: 250, sort: true}
            , {field: "channelId", title: "channelId", width: 100}
            , {field: "cardNo", title: "cardNo", width: 120}
            , {field: "messageType", title: "messageType", width: 120, sort: true}
            , {field: "messageCode", title: "messageCode", width: 120, sort: true}
            , {field: "messageDesc", title: "messageDesc", width: 120, color: "red"}
            , {field: "retMsg", title: "retMsg", width: 120}
            , {field: "hostName", title: "hostName", width: 200}
            , {field: "ip", title: "ip", width: 120}
            , {field: "spendTime", title: "spendTime", width: 120}
            , {fixed: "right", title: "操作", toolbar: "#barDemo", width: 150}
        ]]
    });
    //头工具栏事件
    table.on('toolbar(test_hash)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        var getCheckData = checkStatus.data;
        if(obj.event=="updateAll"||obj.event=="deleteAll"){
            if(getCheckData.length<1){
                layer.alert("请选择一条数据");
            }else {
                if(obj.event=="updateAll"){
                    layer.confirm('确认处理所选数据?', function (index) {
                        var array = new Array();
                        getCheckData.forEach(function (value,i) {
                            array[i] = value.id;
                        });
                        layer.close(index);
                        var result = layui.updeteMonitors(JSON.stringify(array));
                        if(result.code==0){
                            table.reload('demo_hash', {
                                url: '/update/reload/monitor',
                                page: true,
                                where:{
                                    ids: array
                                }
                            });
                            layer.msg(result.msg,{timeout:2000});
                        }
                    });
                };
                if(obj.event=="deleteAll"){
                    layer.confirm('确认删除所选数据?', function (index) {
                        var array = new Array();
                        getCheckData.forEach(function (value,i) {
                            array[i] = value.id;
                        });
                        layer.close(index);
                        var result = layui.deleteMonitors(JSON.stringify(array));
                        if(result.code==0){
                            table.reload('demo_hash', {
                                url: '/delete/reload/monitor',
                                page: true,
                                where:{
                                    ids: array
                                }
                            });
                            layer.msg(result.msg,{timeout:2000});
                        }
                    });
                }
        }
        }
/*        switch(obj.event){
            case 'getCheckData':
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;
        };*/
    });
    //监听行工具事件
    table.on('tool(test_hash)', function(obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'del') {
            layer.confirm('确认删除?', function (index) {
                var array = new Array();
                array[0] = data.id
                var data1 = layui.deleteMonitors(JSON.stringify(array));
                layer.close(index);
                if(data1.code==0){
                    table.reload('demo_hash', {
                        url: '/delete/reload/monitor',
                        page: true,
                        where : {
                            ids:array
                        }
                    });
                    layer.msg(data1.msg,{timeout:2000});
                }
            });
        } else if (obj.event === 'edit') {
            var array = new Array();
            array[0] = data.id
            var data = layui.updeteMonitors(JSON.stringify(array));
            if(data.code==0){
                table.reload('demo_hash', {
                    url: '/update/reload/monitor',
                    page: true ,
                    where : {
                        ids:array
                    }
                });
                layer.msg(data.msg,{timeout:2000}); 
            }
            /*        layer.prompt({
                      formType: 2
                      , value: data.sofaTraceId
                    }, function (value, index) {
                      obj.update({
                        sofaTraceId: value
                      });
                      layer.close(index);
                    });*/
        }
    });
    var intervalIndex = setInterval(function() {
        if (echarts === undefined) {
            return;
        }
        // 如果eacharts加载完成，则清除循环
        clearInterval(intervalIndex);
        //echarts
        // 基于准备好的dom，初始化echarts实例
/*        var myChart = echarts.init(document.getElementById("main"));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: "近一周异常交易量"
            },
            tooltip: {},
            legend: {
                data: ["异常交易量"]
            },
            xAxis: {
                data: ["周一", "周二", "周三", "周四", "周五", "周六", "周天"]
            },
            yAxis: {},
            series: [{
                name: "异常交易量",
                type: "bar",
                data: [49, 100, 55, 46, 29, 12, 8]
            }]
        };*/
        // 使用刚指定的配置项和数据显示图表。
        //myChart.setOption(option);

        // echarts 1
/*        var myChart1 = echarts.init(document.getElementById("container1"));
        var app1 = {};
        option1 = null;
        app1.title = "异常接口占比统计图";

        option1 = {
            title: {
                text: "异常接口占比",
                //subtext: "纯属虚构",
                x: "center"
            },
            tooltip: {
                trigger: "item",
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: "vertical",
                left: "left",
                data: ["1400-1301", "1200-1001", "1400-3001", "1000-0001"]
            },
            series: [{
                name: "异常接口",
                type: "pie",
                radius: "55%",
                center: ["50%", "60%"],
                data: [{
                    value: 335,
                    name: "1400-1301"
                }, {
                    value: 310,
                    name: "1200-1001"
                }, {
                    value: 234,
                    name: "1400-3001"
                }, {
                    value: 135,
                    name: "1000-0001"
                }],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.5)"
                    }
                }
            }]
        };
        if (option1 && typeof option1 === "object") {
          //  myChart1.setOption(option1, true);
        }*/

        // echart2
        var myChart2 = echarts.init(document.getElementById("main1"));
        var app2 = {};

        var groupdate  = layui.groupDate();
        var xAxisData = new Array();
        var groupbydate = new Array();
        var handle = new Array();
        var nohandle = new Array();
        var Alldata = groupdate.data.ALL;
        var handledata = groupdate.data.HANDLE;
        var nohandledata = groupdate.data.NOHANDLE;
        Alldata.forEach(function (value,i) {
            xAxisData[i] = value.key;
            handle[i] = 0;
            nohandle[i] = 0;
            handledata.forEach(function (value1,i1) {
                if(value.key == value1.key){
                    handle[i] = value1.value;
                }
            });
            nohandledata.forEach(function (value2,i2) {
                if(value.key == value2.key){
                    nohandle[i] = value2.value;
                }
            })
            groupbydate[i] = value.value
        })
        option2 = null;
        option2 = {
            title: {
                text: "业务数据统计图"
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "cross",
                    label: {
                        backgroundColor: "#6a7985"
                    }
                }
            },
            legend: {
                data: ["交易量", "已处理", "未处理"]
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: "3%",
                right: "4%",
                containLabel: true
            },
            xAxis: [{
                type: "category",
                boundaryGap: false,
                data: xAxisData
            }],
            yAxis: [{
                type: "value"
            }],
            dataZoom: [{
                type: 'inside',
                startValue: xAxisData[xAxisData.length-7] ,
                end: 100
            }, {
                type: 'slider'
            }],
            series: [{
                name: "未处理",
                type: "line",
                stack: "总量",
                areaStyle: {
                    normal: {}
                },
                data: nohandle
            }, {
                name: "已处理",
                type: "line",
                stack: "总量",
                areaStyle: {
                    normal: {}
                },
                data: handle
            }, {
                name: "交易量",
                type: "line",
                stack: "总量",
                areaStyle: {
                    normal: {}
                },
                data: groupbydate
            }]
        };
        if (option2 && typeof option2 === "object") {
            myChart2.setOption(option2, true);
        };

        $(window).on("resize", function() {
/*            myChart.resize();
            myChart1.resize();*/
            myChart2.resize();
        });
    }, 50);
});
