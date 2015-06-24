function getForNum(str){
	if(str==null || str=='' || str.length<4) return str;
	str=str.split('').reverse().join('').replace(/(\d{3})/g,'$1,').replace(/\,$/,'').split('').reverse().join('');
	return str;
}

function showData(url){
	$.ajax({
		type : 'post',
		url:url,
		dataType : 'json',
		async:false,
		error:function(){alert("error");},
		success:function(data){
			if(data!=null){
				$("#data0").html(getForNum(data.projectCount));
				$("#data1").html(getForNum((data.projectPrice/10000).toFixed(0)));
				$("#data2").html(getForNum(data.assetProjectCount));
				$("#data3").html(getForNum(data.assetCount));
				$("#data4").html(getForNum((data.assetContractPrice/10000).toFixed(0)));
				
				$("#data5").html(getForNum(data.accessAssetProjectCount));
				$("#data6").html(getForNum(data.accessAssetCount));
				$("#data7").html(getForNum((data.accessAssetPrice/10000).toFixed(0)));
//				$("#data8").html((data.stopAssetPrice/10000).toFixed(0));
//				$("#data9").html(data.planScrapAssetCount);
//				$("#data10").html((data.planScrapAssetPrice/10000).toFixed(0));
				
			}
		}
	});
}

/**
 * 查询重要资产排名
 * @param url
 * @return
 */
function showImportantAssetRankType(url){
	$.ajax({
		type : 'post',
		url:url,
		dataType : 'json',
		async:false,
		error:function(){alert("error");},
		success:function(data){
			var html = '';
			if(data!=null && data.length>0){
				html += '<select onchange="drawPic6(this.value)">';
				for(var i=0; i<data.length; i++){
					html +='<option value="'+data[i]+'">'+data[i]+'</option>';
				}
				html +='</select>';
				$('#typeArea').html(html);
			}
		}
	});
}

function drawPic1(url) {
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].shortName);
					var se = [ object[i].shortName, parseInt((object[i].finalPrice/10000).toFixed(0)) ];
					series.push(se);
				}
			}
			var chartOption = {
				chart : {
					renderTo : 'pic1',
					type : 'pie',
					spacingLeft : -100,
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : ''
				},
				tooltip : {
					pointFormat : '决算价:{point.y} 万元<br/>占百分比:{point.percentage:.0f} %'
				},
				legend : {
					enabled : true,
					align : 'right',
					verticalAlign : 'top',
					//y : 5,
					layout : 'vertical',
					floating : true,
					borderColor : '#CCC',
					borderWidth : 1,
					shadow : false
				},
				credits : {
					enabled : false
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : false
						},
						showInLegend : true
					}
				},
				series : [ {
					data : series
				} ]
			}
			new Highcharts.Chart(chartOption);
		}
	});
}


function drawPic2(url) {
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].shortName);
					var se = [ object[i].shortName, parseInt((object[i].originalValue/10000).toFixed(0)) ];
					series.push(se);
				}
			}
			var chartOption = {
				chart : {
					renderTo : 'pic2',
					type : 'pie',
					spacingLeft : -100,
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : ''
				},
				tooltip : {
					pointFormat : '决算价:{point.y} 万元<br/>占百分比:{point.percentage:.0f} %'
				},
				legend : {
					enabled : true,
					align : 'right',
					verticalAlign : 'top',
					y : 5,
					layout : 'vertical',
					floating : true,
					borderColor : '#CCC',
					borderWidth : 1,
					shadow : false
				},
				credits : {
					enabled : false
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : false
						},
						showInLegend : true
					}
				},
				series : [ {
					data : series
				} ]
			}
			new Highcharts.Chart(chartOption);
		}
	});
}


function drawPic3(url) {
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].shortName);
					var se = [ object[i].shortName, parseInt((object[i].originalValue/10000).toFixed(0))];
					series.push(se);
				}
			}
			var chartOption = {
				chart : {
					renderTo : 'pic3',
					type : 'pie',
					spacingLeft : -100,
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : ''
				},
				tooltip : {
					pointFormat : '决算价:{point.y} 万元<br/>占百分比:{point.percentage:.0f} %'
				},
				legend : {
					enabled : true,
					align : 'right',
					verticalAlign : 'top',
					y : 5,
					layout : 'vertical',
					floating : true,
					borderColor : '#CCC',
					borderWidth : 1,
					shadow : false
				},
				credits : {
					enabled : false
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : false
						},
						showInLegend : true
					}
				},
				series : [ {
					data : series
				} ]
			}
			new Highcharts.Chart(chartOption);
		}
	});
}


function drawPic4(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].year);
					var se = [ object[i].year, parseInt((object[i].finalPrice/10000).toFixed(0))];
					series.push(se);
				}
			}
			$('#pic4').highcharts({
			    chart: {
			        type: 'column'
			    },
			    title: {
			        text: ''
			    },
			    xAxis: {
			        categories: categories,
			        labels:{
			    		rotation:-45
			    	}
			    },
			    yAxis:{
			    	title:{
			    		text:'决算价(万元)'
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">决算价： </td>' +
			            '<td style="padding:0"><b>{point.y:.0f} 万元</b></td></tr>',
			        footerFormat: '</table>',
			        shared: true,
			        useHTML: true
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        }
			    },
			    series: [{
			        data:series
			    }]
			});
			
		}
	});
}


function drawPic5(url,typeId,showType){
	var showType = $('#hardShowType').val();
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		data:{typeId:$('#hardTypeArea').val()},
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			var yText='';
			var showName='';
			var showUnit='';
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].shortName);
					var se ;
					if(showType==1){	//数值
						se = [ object[i].year, parseInt(object[i].count)];
						yText = '资产数值(个)';
						showName = '资产数值';
						showUnit = '个';
					}else if(showType==2){	//价值
						se = [ object[i].year, parseInt((object[i].price/10000).toFixed(0))];
						yText = '资产价值(万元)';
						showName = '资产价值';
						showUnit = '万元';
					}
					
					series.push(se);
				}
			}
			$('#pic5').highcharts({
			    chart: {
			        type: 'column'
			    },
			    title: {
			        text: ''
			    },
			    xAxis: {
			        categories: categories,
			        labels:{
			    		rotation:-45
			    	}
			    },
			    yAxis:{
			    	title:{
			    		text:yText
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">'+showName+'： </td>' +
			            '<td style="padding:0"><b>{point.y:.0f} '+showUnit+'</b></td></tr>',
			        footerFormat: '</table>',
			        shared: true,
			        useHTML: true
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        }
			    },
			    series: [{
			        data:series
			    }]
			});
		}
	});
	/*$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var data1=[],data2=[],data3=[];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].shortName);
					data1.push(parseInt((object[i].usePrice/10000).toFixed(0)));
					data2.push(parseInt((object[i].stopPrice/10000).toFixed(0)));
					data3.push(parseInt((object[i].planScrapPrice/10000).toFixed(0)));
				}
			}
			$('#pic5').highcharts({
			    chart: {
			        type: 'column'
			    },
			    title: {
			        text: ''
			    },
			    xAxis: {
			        categories: categories,
			        labels:{
			    		rotation:-45
			    	}
			    },
			    yAxis:{
			    	title:{
			    		text:'资产原值(万元)'
			    	}
			    },
			    legend: {
		            align: 'right',
		            x: -20,
		            verticalAlign: 'top',
		            layout:'horizontal',
		            y: 0,
		            floating: true,
		            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
		            borderColor: '#CCC',
		            borderWidth: 1,
		            shadow: false
		        },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
		            formatter: function() {
		                return '<b>'+ this.x +'</b><br/>'+
		                    this.series.name +': '+ this.y +'<br/>'+
		                    '原值: '+ this.point.stackTotal+' 万元';
		            }
		        },
			    plotOptions: {
		            column: {
		                stacking: 'normal'
		            }
		        },
			    series: [{
		            name: '使用',
		            data: data1
		        }, {
		            name: '停用',
		            data: data2
		        }, {
		            name: '待报废',
		            data: data3
		        }]
			});
			
		}
	});*/
}





function drawPic6(type){
	$.ajax( {
		type : 'post',
		url : pic6Url,
		dataType : 'json',
		cache : false,
		data:{type:type},
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].name);
					var value = parseInt((parseFloat(object[i].originalValue)/10000).toFixed(0));
					var se = [object[i].name,value];
					series.push(se);
				}
			}
			$('#pic6').highcharts({
			    chart: {
			        type: 'column'
			    },
			    title: {
			        text: ''
			    },
			    xAxis: {
			        categories: categories,
			        labels:{
			    		rotation:-45
			    	}
			    },
			    yAxis:{
			    	title:{
			    		text:'资产原值(万元)'
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">原值： </td>' +
			            '<td style="padding:0"><b>{point.y:.0f} 万元</b></td></tr>',
			        footerFormat: '</table>',
			        shared: true,
			        useHTML: true
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        }
			    },
			    series: [{
			        data:series
			    }]
			});
			
		}
	});
}








