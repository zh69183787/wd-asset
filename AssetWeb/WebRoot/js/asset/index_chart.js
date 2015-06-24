var  show;
function showAssetTransferChartAndKpi(url) {

    $.ajax({
        type: 'post',
        url: url,
        dataType: 'json',
        cache: false,
        async: false,
        error: function () {
            alert("系统连接失败，请稍后再试！")
        },
        success: function (object) {
//            drawAssetValueChart1(object.values);
          //  drawAssetValueChart2(object.transfer);
            drawAssetValueChart3(object.lists);
            drawAssetValueChart4(object.alists);
            drawAssetValueKpi(object.kpi);
            $("#chart-tab-content0").addClass("loaded");
        }
    });
}

function drawAssetValueKpi(kpi) {
    $("#assetTransferKpi").find("li:eq(0) h1").html(kpi.assetCount);
    $("#assetTransferKpi").find("li:eq(1) h1").html(kpi.accessAssetProjectCount);
    $("#assetTransferKpi").find("li:eq(2) h1").html((kpi.accessAssetPrice / 10000).toFixed(6));
    $("#assetTransferKpi").find("li:eq(3) h1").html(kpi.transfer);
}

/*function drawAssetValueChart1(result) {
    var categories = [];
    var data1 = [];
    var data2 = [];
    var data3 = [];
    if (result != null && result.length > 0) {
        for (var i = 0; i < result.length; i++) {
            categories.push(result[i].shortName);
            data1.push(parseInt((result[i].count)));
            data2.push(parseFloat((result[i].originalValue / 10000).toFixed(4)));
            data3.push(parseFloat((result[i].finalPrice / 10000).toFixed(4)));

        }

        $("#chart1").highcharts({
            chart: {
                zoomType: 'xy', width: "480",
                events: {
                    click: function (e) {
                        location.href = show;
                    }
                }

//            height:200
            }, credits: {
                enabled: false
            },
            title: {
                text: '建设项目价值统计',
                style: {"font-weight": "bold", color: "#000000"}
            },
            xAxis: [
                {
                    categories: categories
                    , labels: {rotation: 290,style:{
//                    backgroundColor: '#6D869F'
                }}
                }
            ],
            yAxis: [
                { // Primary yAxis
                    labels: {
                        format: '{value} 个',
                        style: {
                            color: '#4572A7'
                        }
                    },
                    title: {
                        text: '资产项数',
                        style: {
                            color: '#4572A7'
                        }
                    }
                },
                { // Secondary yAxis
                    title: {
                        text: '价值',
                        style: {
                            color: '#4572A7'
                        }
                    },
                    labels: {
                        format: '{value} 万元',
                        style: {
                            color: '#4572A7'
                        }

                    },
                    opposite: true
                }
            ],
            tooltip: {
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 80,
                verticalAlign: 'top',
//                y: 60,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [
                {
                    name: '资产项数',
                    color: '#4897F1',
                    type: 'column',
                    data: data1,
                    tooltip: {
                        valueSuffix: ' 个'
                    }, point: {
                    events: {
                        click: function (e) {
                            location.href =show;
                        }
                    }
                }

                },
                {
                    name: '资产原值',
                    color: '#901010',
                    type: 'spline',
                    yAxis: 1,
                    data: data2,
                    tooltip: {
                        valueSuffix: ' 万元'
                    }, point: {
                    events: {
                        click: function (e) {
                            location.href = show;
                        }
                    }}

                },
                {
                    name: '决算价',
                    color: '#89A54E',
                    yAxis: 1,
                    type: 'spline',
                    data: data3,
                    tooltip: {
                        valueSuffix: '万元'
                    }, point: {
                    events: {
                        click: function (e) {
                            location.href = show;
                        }
                    }}
                }
            ]
        });
    }
}*/
function drawAssetValueChart2(result) {
    var categories = [];
    var data = [];
    if (result != null && result.length > 0) {
        for (var i = 0; i < result.length; i++) {
            categories.push(result[i].shortName);
            data.push(parseInt((result[i].percent)));
        }

        $('#chart2').highcharts({
            chart: {
                width: 450, zoomType: 'xy', events: {
                    click: function (e) {
                        location.href = show;
                    }
                }
            }, credits: {  
                enabled: false
            },
            title: {
                text: '建设项目价值移交',
                style: {"font-weight": "bold", color: "#000000"}
            },
            xAxis: [
                {
                    categories: categories
                    , labels: {rotation: 290}
                }
            ],
            yAxis: [
                { // Primary yAxis
                    labels: {
                        format: '{value} %',
                        style: {
                            color: '#4572A7'
                        }
                    },
                    title: {
                        text: '移交比例',
                        style: {
                            color: '#4572A7'
                        }
                    }
                }
            ],
            tooltip: {
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                x: 120,
                verticalAlign: 'top',
//                y: 60,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [
                {
                    name: '移交比例',
                    color: '#4897F1',
                    type: 'column',
                    data: data,
                    tooltip: {
                        valueSuffix: ' %'
                    }, point: {
                    events: {
                        click: function (e) {
                            location.href =show;
                        }
                    }}

                }

            ]
        });
    }
}

function drawAssetValueChart3(result) {
    var categories = [];
    var data = [];
	var data1 = [];
	var data2 = [];
	if (result != null && result.length > 0) {
        for (var i = 0; i < result.length; i++) {   
            categories.push(result[i].shortName);
            data.push(parseInt((result[i].zone)));
			data1.push(parseInt((result[i].plan))-parseInt((result[i].actual)));
			data2.push(parseInt((result[i].actual)));   
        }
        /*Highcharts.setOptions({
            colors: ['#ff0000','#DDDF00'] //'#FF9655','#50B432'  ,'#ED561B' ,    '#24CBE5', '#64E572', , '#FFF263' , '#6AF9C4'
        });*/
        $('#chart2').highcharts({
        	chart: {
        		width: 900,
        		type: 'column'
            },
            title: {
                text: '建设项目实物移交',
            	style: {"font-weight": "bold", color: "#000000","font-family":"Microsoft YaHei"}
            },
            xAxis: [
            	{
                    categories: categories
                    , labels: {rotation: 290}  
                }
            ],

            yAxis: {
                allowDecimals: false,
                min: 0,
                title: {
                    text: '资产实物项数'
                }
            },
            credits: {
                enabled: false
            },
          legend: {
                align: 'right',
                x: -70,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function() {
                    /*return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +': '+ this.y ;*/
            		var i = this.point.x;
                    if(this.series.name=='计划')
                        return '<b>'+ this.x +'</b><br/>'+
                        '实际: '+(data2[i])+'<br/>'+this.series.name +': '+ this.point.stackTotal +'<br/>'+'百分比: '+((this.point.stackTotal-this.y)/this.point.stackTotal*100).toString().substr(0,5)+'%';  
                    if(this.series.name=='实际')
                        return '<b>'+ this.x +'</b><br/>'+
                            this.series.name +': '+ this.y +'<br/>'+'计划: '+(data1[i]+data2[i])+'<br/>'+'百分比: '+((this.y)/this.point.stackTotal*100).toString().substr(0,5)+'%';  
               
                }  
            },
            plotOptions: {
                column: {
                    stacking: 'normal'
                },
                series: {
                    cursor: 'pointer',
                    events: {
                        click: function(e) {
                        	window.open("../../report/showProjectLineValue.action");  
                        }
                    }
                }
            },
            series: [{
                name: '计划',
                data: data1,
                color:"#8BBC21",
                stack: 'male'
                },
				{
                name: '实际',
                data: data2,
              //  color:'#ff0000',
                stack: 'male'
            }]
        });
    }
}
function drawAssetValueChart4(result) {
    var categories = [];
    var data = [];
	var data1 = [];
	var data2 = [];
	if (result != null && result.length > 0) {
        for (var i = 0; i < result.length; i++) {   
            categories.push(result[i].shortName);
            data.push(parseInt((result[i].percent)));
            data1.push(result[i].plan-result[i].actual);
//			data1.push(((10000*(result[i].plan)-10000*(result[i].actual))/10000));
			data2.push(((result[i].actual)));   
        }
        $('#chart1').highcharts({
        	chart: {
        		width: 900,
        		type: 'column'
            },
            title: {
                text: '建设项目价值移交',
            	style: {"font-weight": "bold", color: "#000000"}
            },
            xAxis: [
            	{
                    categories: categories
                    , labels: {rotation: 290}
                }
            ],
            yAxis: {
                allowDecimals: false,
                min: 0,
                title: {
                    text: '资产价值(万元)'
                }
            },
            credits: {
                enabled: false
            },
           legend: {
                align: 'right',
                x: -70,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: true
            },
            tooltip: {
                formatter: function() {
            		var i = this.point.x;
                	if(this.series.name=='计划')
                    return '<b>'+ this.x +'</b><br/>'+
                    '实际: '+(this.point.stackTotal-this.y).toFixed(6)+'<br/>'+this.series.name +': '+ (this.point.stackTotal).toFixed(6) +'<br/>'+'百分比: '+(((this.point.stackTotal-this.y)/(this.point.stackTotal))*100).toString().substr(0,5)+'%';  
                	if(this.series.name=='实际')
                    return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +': '+ (this.y).toFixed(6) +'<br/>'+'计划: '+(data1[i]+data2[i]).toFixed(6)+'<br/>'+'百分比: '+(((this.y)/(data1[i]+data2[i]))*100).toString().substr(0,5)+'%';  
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal'
                },
                series: {
                    cursor: 'pointer',
                    events: {
                        click: function(e) {
                        	window.open("../../report/showProjectLineValue.action");  
                        }
                    }
                }
            },
            series: [{
                name: '计划',
                data: data1,
                color:"#8BBC21",
                stack: 'male'
                },
				{
                name: '实际',
                data: data2,
              //  color:'#ff0000',
                stack: 'male'
            }]
        }); 
    }
}
function showAssetUpdateChartAndKpi(url) {
    $.ajax({
        type: 'post',
        url: url,
        dataType: 'json',
        cache: false,
        async: false,
        error: function () {
            alert("系统连接失败，请稍后再试！")
        },
        success: function (object) {
            drawBudgeYearChart(object.charts.budgetYearChart);
            drawPieChart("chart4", "专业分布图", object.charts.assetTypeChart);
            drawPieChart("chart5", "线路分布图", object.charts.lineChart);
            drawPieChart("chart6", "公司分布图", object.charts.departmentChart);
            drawAssetUpdateKpi(object.kpi);
            $("#chart-tab-content1").addClass("loaded");
        }
    });
}

function drawPieChart(id, title, result) {
    var series = [];
    for (var i = 0; i < result.length; i++) {
        series.push([result[i].name, parseInt((result[i].originalValue / 10000).toFixed(0))]);
    }
    $("#" + id).highcharts({
        chart: {
            type: 'pie',width:470,height:300,
//            spacingLeft: -100,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title,style: {"font-weight": "bold", color: "#000000"}
        },
        tooltip: {
            pointFormat: '原值:{point.y} 万元'
        },
        legend: {
            enabled: true,
            align: 'right',width:165,itemWidth:130,
//            labelFormatter:function () {
//                return "<span style='overflow: hidden;width: 110px;display: inline;'>"+this.name + '</span>';
//            },
//            useHtml:true,
            verticalAlign: 'top',
            //y : 5,
            layout: 'vertical',
            floating: true,
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [
            {
                data: series
            }
        ]
    });
}

function drawBudgeYearChart(result) {
    var categories = [];
    var data = [];
    if (result != null) {
        for (var i = 0; i < result.length; i++) {
            categories.push(result[i].year);
            data.push(parseInt((result[i].price).toFixed(0)));
        }
    }
    $('#chart3').highcharts({
        chart: {
            type: 'column', width: 470, height: 300
        },
        title: {
            text: '项目投资情况', style: {"font-weight": "bold", color: "#000000","font-family":"Microsoft YaHei"}
        },
        xAxis: {
            categories: categories,
            labels: {
                rotation: -45,
                style:{
                	'font-family' : 'Microsoft YaHei'
                }
            }
        },
        yAxis: {
            title: {
                text: '(万元)',
                style:{
                	'font-family' : 'Microsoft YaHei'
                }
            }
        },
        legend: {  
            enabled: false,
            itemStyle : {
                'font-family' : 'Microsoft YaHei'
            }
        },
        credits: {
            enabled: false
        }, tooltip: {
            valueSuffix: ' 万元',
            style:{
            	'font-family' : 'Microsoft YaHei'
            }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
            {
                data: data,
                name: '投资金额',
                style:{
                	'font-family' : 'Microsoft YaHei'
                }
            }
        ]
    });
}

function drawAssetUpdateKpi(kpi) {
    $("#assetUpdateKpi").find("li:eq(0) h1").html((kpi.yearInvest).toFixed(6));
    $("#assetUpdateKpi").find("li:eq(1) h1").html((kpi.allInvest).toFixed(6));
    $("#assetUpdateKpi").find("li:eq(2) h1").html(kpi.pInvestChange);
//  $("#assetUpdateKpi").find("li:eq(2) h1").html("pInvestChange");
  //大修资产占比
    $("#assetUpdateKpi").find("li:eq(3) h1").html(kpi.pOverhaulAsset);

    
    $("#assetUpdateKpi").find("li:eq(4) h1").html(kpi.pNewAsset);

    $("#assetUpdateKpi").find("li:eq(5) h1").html(kpi.pUpdateAsset);

    $("#assetUpdateKpi").find("li:eq(6) h1").html(kpi.pNormal); //正常
    $("#assetUpdateKpi").find("li:eq(7) h1").html(kpi.pInAdvance);//提前
    $("#assetUpdateKpi").find("li:eq(8) h1").html(kpi.pLate);//延时
}

function showAssetDamageChartAndKpi(url){
    $.ajax({
        type: 'post',
        url: url,
        dataType: 'json',
        cache: false,
        async: false,
        error: function () {
            alert("系统连接失败，请稍后再试！damage")
        },
        success: function (object) {
        	//资产报废项目年变化情况
        	drawAssetDamageChangeChart(object.charts.assetDamageChangeChart);
        	drawAssetDamagePieChart("chart8", "专业分布", object.charts.assetDamageProfessionConChart);
        	drawAssetDamagePieChart("chart10", "公司分布", object.charts.assetDamageDepartmentChart);
        	drawAssetDamagePieChart("chart9", "线路分布", object.charts.assetDamageLineChart);
        	drawAssetDamageKpi(object.kpi);
        	$("#chart-tab-content2").addClass("loaded");
        	
        }
    });
    
//	var test1 = [{name:"车辆", value:"10"},{name:"线路",value:"20"},{name:"供电",value:"70"},
//		{name:"信号", value:"10"},{name:"管理工具",value:"40"}];
//	var test2 = [{name:"运一公司", value:10},{name:"运二公司",value:20},{name:"运三公司",value:70},
//		{name:"运四公司", value:50},{name:"运五公司",value:20}];
//	var test3 = [{name:"网络", value:10},{name:"一号线",value:20},{name:"二号线",value:70},
//		{name:"四号线", value:40},{name:"五号线",value:28},{name:"六号线",value:60},
//		{name:"七号线", value:35},{name:"八号线",value:56},{name:"九号线",value:59}];

}

function drawAssetDamageChangeChart(result){

	var categories = [];
    var data = [];
    if (result != null) {
        for (var i = 0; i < result.length; i++) {
            categories.push(result[i].year);
            data.push(((result[i].price)));
        }
    }
    $('#chart7').highcharts({
        chart: {
            type: 'column', width: 470, height: 300
        },
        title: {
            text: '报废资产值年变化', style: {"font-weight": "bold", color: "#000000"}
        },
        xAxis: {
            categories: categories,
            labels: {
                rotation: -45
            }
        },
        yAxis: {
            title: {
                text: ''
            }
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        }, tooltip: {
            valueSuffix: ' '
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
            {
                name: '金额(万元)',
                data: data
            }
        ]
    });
}

function drawAssetDamagePieChart(id, title, result) {
    var series = [];
    for (var i = 0; i < result.length; i++) {
        series.push([result[i].name, ((result[i].value))]);
    }
    $("#" + id).highcharts({
        chart: {
            type: 'pie',width:470,height:300,
//            spacingLeft: -100,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title,style: {"font-weight": "bold", color: "#000000"}
        },
        tooltip: {
        	formatter: function () {
                return '原值: ' +  this.y.toFixed(6) + ' 万元';
            }
        },
        legend: {
            enabled: true,
            align: 'right',width:130,itemWidth:110,
//            labelFormatter:function () {
//                return "<span style='overflow: hidden;width: 110px;display: inline;'>"+this.name + '</span>';
//            },
//            useHtml:true,
            verticalAlign: 'top',
            //y : 5,
            layout: 'vertical',
            floating: true,
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [
            {
                data: series
            }
        ]
    });
}

//资产报废
function drawAssetDamageKpi(kpi){

	$("#assetDamageKpi").find("li:eq(0) h1").html(kpi.kDamage);
	$("#assetDamageKpi").find("li:eq(1) h1").html(kpi.kOutOfService);
	$("#assetDamageKpi").find("li:eq(4) h1").html(kpi.kDInAdvance);
	$("#assetDamageKpi").find("li:eq(2) h1").html(kpi.kDNormal);
	$("#assetDamageKpi").find("li:eq(3) h1").html(kpi.kDLate);
}


/**
 * 资产盘点
 * @param url
 */
function showAssetTaskChartAndKpi(url){
	//数据格式
//	var result = [{"task":"盘点任务1","percent":20},{"task":"盘点任务2", "percent":30},
//	      		{"task":"盘点任务3", "percent":50}];
    $.ajax({
        type: 'post',
        url: url,
        dataType: 'json',
        cache: false,
        async: false,
        error: function () {
            alert("系统连接失败，请稍后再试！");
        },
        success: function (object) {
        	drawAssetTaskChart(object.result);
            
            drawAssetTaskKpi(object.kpi);
        	$("#chart-tab-content3").addClass("loaded");
        }
    });

}

function drawAssetTaskChart(result){

	var categories = [];
    var data = [];
    if (result != null) {
        for (var i = 0; i < result.length; i++) {
            categories.push(result[i].task);
            data.push(parseInt((result[i].percent)));
        }
    }
    $('#chart11').highcharts({
        chart: {
            type: 'column', width: 930, height: 300
        },
        title: {
            text: '资产盘点年度完成情况', style: {"font-weight": "bold", color: "#000000"}
        },
        xAxis: {
            categories: categories,
            labels: {
                rotation: 0
            }
        },
        yAxis: {
        	labels: {
                format: '{value} %',
                style: {
                    color: '#4572A7'
                }
            },
            title: {
                text: '(百分比)'
            },
            max: 100
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        }, tooltip: {
            valueSuffix: ' '
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
            {
            	name: '百分比',
            	tooltip: {
                    valueSuffix: ' %'
                }, 
                data: data
            }
        ]
    });
}

function drawAssetTaskKpi(kpi){
	$("#assetTaskKpi").find("li:eq(0) h1").html(kpi.accuracyRate);
	$("#assetTaskKpi").find("li:eq(1) h1").html(kpi.planCompleteRate);
}

//  土地/房屋资源
function showAssetLandHouseChartAndKpi(url){
	//以下是数据格式示例
//	var land = [{line:"一号线",area:143.2},{line:"二号线",area:153.2},{line:"三号线",area:213.2}];
//	var house = [{line:"一号线",area:123.2},{line:"二号线",area:93.2},{line:"三号线",area:213.2}];

    $.ajax({
        type: 'post',
        url: url,
        dataType: 'json',
        cache: false,
        async: false,
        error: function () {
            alert("系统连接失败，请稍后再试！");
        },
        success: function (object) {
        	drawAssetLandHouseChart("chart12","土地线路分布",'土地面积',object.land);
        	drawAssetLandHouseChart("chart13","房屋线路分布",'房屋面积',object.house);
        	drawAssetLandHouseKpi(object.kpi);
        	$("#chart-tab-content4").addClass("loaded");

        }
    });

}

function drawAssetLandHouseChart(id, title, ytitle,result){
	var categories = [];
    var data = [];
    if (result != null) {
        for (var i = 0; i < result.length; i++) {
            categories.push(result[i].line);
            data.push(((result[i].area)));
        }
    }
    $('#'+id).highcharts({
        chart: {
            type: 'column', width: 470, height: 300
        },
        title: {
            text: title, style: {"font-weight": "bold", color: "#000000"}
        },
        xAxis: {
            categories: categories,
            labels: {
                rotation: 0
            }
        },
        yAxis: {
            labels: {
                format: '{value} ㎡',
                style: {
                    color: '#4572A7'
                }
            },
            title: {
                text: ytitle
            }
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        }, 
        tooltip: {
            formatter: function () {
                return ytitle+': ' +  this.y.toFixed(2) + ' ㎡';
            }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
            {
                
                    name: ytitle,
//                    color: '#901010',
//                    type: 'spline',
//                    yAxis: 1,
                    data: data,
                    tooltip: {
                        valueSuffix: ' ㎡'
                    }
//                    point: {
//                    events: {
//                        click: function (e) {
//                            location.href = show;
//                        }
//                    }
//                    	}

                
                
             
            }
        ]
    });
}

function drawAssetLandHouseKpi(kpi){
	$("#assetLandHouseKpi").find("li:eq(0) h1").html(kpi.lSumAsset);
	$("#assetLandHouseKpi").find("li:eq(1) h1").html(kpi.lSumArea);
	$("#assetLandHouseKpi").find("li:eq(2) h1").html(kpi.hSumAsset);
	$("#assetLandHouseKpi").find("li:eq(3) h1").html(kpi.hSumArea);
}