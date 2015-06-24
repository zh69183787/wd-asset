function getForNum(str){
	if(str==null || str=='' || str.length<4) return str;
	str = str.split('').reverse().join('').replace(/(\d{3})/g,'$1,').replace(/\,$/,'').split('').reverse().join('');
	return str;
}

function showData(url){
	$.ajax({
		type : 'post',
		url:url,
		dataType : 'json',
		async:false,
		error:function(){alert("系统忙，请稍后再试！");},
		success:function(data){
			if(data!=null){
				$("#data0").html(getForNum(data.overhaultCount));
				$("#data01").html(getForNum((data.overhaultPrice/10000).toFixed(0)));
				$("#data02").html(getForNum(data.overhaultCountThisYear));
				$("#data03").html(getForNum((data.overhaultPriceThisYear/10000).toFixed(0)));
				
				$("#data1").html(getForNum(data.renovateCount));
				$("#data11").html(getForNum((data.renovatePrice/10000).toFixed(0)));
				$("#data12").html(getForNum(data.renovateCountThisYear));
				$("#data13").html(getForNum((data.renovatePriceThisYear/10000).toFixed(0)));
				
				$("#data10").html(getForNum(data.newAssetCount));
				$("#data101").html(getForNum((data.newAssetPrice/10000).toFixed(0)));
				$("#data102").html(getForNum(data.newAssetCountThisYear));
				$("#data103").html(getForNum((data.newAssetPriceThisYear/10000).toFixed(0)));
				
				$("#data2").html(getForNum(data.scrapAssetCount));
				$("#data21").html(getForNum((data.scrapAssetValue/10000).toFixed(0)));
				$("#data3").html(getForNum(data.useAssetCount));
				$("#data31").html(getForNum((data.useAssetValue/10000).toFixed(0)));
				$("#data4").html(getForNum(data.stopAssetCount));
				$("#data41").html(getForNum((data.stopAssetValue/10000).toFixed(0)));
				
				$("#data5").html(getForNum(data.rentAssetCount));
				$("#data51").html(getForNum((data.rentAssetValue/10000).toFixed(0)));
				$("#data6").html(getForNum(data.unusedAssetCount));
				$("#data61").html(getForNum((data.unusedAssetValue/10000).toFixed(0)));
				$("#data7").html(getForNum(data.allotAssetCount));
				$("#data71").html(getForNum((data.allotAssetValue/10000).toFixed(0)));
				
				$("#data8").html(getForNum(data.checkedAssetCount));
				$("#data9").html(getForNum(data.checkedResultCount));
				
			}
		}
	});
}

function showDwMaterialsConsume(url){
	$.ajax({
		type : 'post',
		url:url,
		dataType : 'json',
		async:false,
		error:function(){alert("系统忙，请稍后再试！");},
		success:function(data){
			var html = '';
			if(data!=null && data.length>0){
				for(var i=0,len=data.length; i<len; i++){
					html += '<tr><td style="text-align:center;">'+data[i].typeName+'</td><td style="padding-right:15px;">'+data[i].materialsPrice+'</td>'+
					'<td style="padding-right:15px;">'+data[i].materialsWorkingHours+'</td><td style="padding-right:15px;">'+data[i].maintainPrice+'</td><td style="padding-right:15px;">'+data[i].maintainWorkingHours+'</td></tr>';
				}
			}
			$('#materialsConsumeArea').html(html);
		}
	});
}

function showDwOverhaulMajorType(url){
	$.ajax({
		type : 'post',
		url:url,
		dataType : 'json',
		async:false,
		error:function(a,error,c){alert(error);},
		success:function(data){
			var html = '';
			if(data!=null && data.length>0){
				for(var i=0,len=data.length; i<len; i++){
					var overhaulPayPlan = data[i].overhaulPayPlan;
					var overhaulActualPriceGroup = data[i].overhaulActualPriceGroup;
					var overhaulActualPriceOffice = data[i].overhaulActualPriceOffice;
					var renovatePayPlan = data[i].renovatePayPlan;
					var renovateActualPriceGroup = data[i].renovateActualPriceGroup;
					var renovateActualPriceOffice = data[i].renovateActualPriceOffice;
					
					if(overhaulPayPlan!=null && overhaulPayPlan!=0){
						overhaulPayPlan = (parseFloat(overhaulPayPlan)/10000).toFixed(2);
					}
					if(overhaulActualPriceGroup!=null && overhaulActualPriceGroup!=0){
						overhaulActualPriceGroup = (parseFloat(overhaulActualPriceGroup)/10000).toFixed(2);
					}
					if(overhaulActualPriceOffice!=null && overhaulActualPriceOffice!=0){
						overhaulActualPriceOffice = (parseFloat(overhaulActualPriceOffice)/10000).toFixed(2);
					}
					if(renovatePayPlan!=null && renovatePayPlan!=0){
						renovatePayPlan = (parseFloat(renovatePayPlan)/10000).toFixed(2);
					}
					if(renovateActualPriceGroup!=null && renovateActualPriceGroup!=0){
						renovateActualPriceGroup = (parseFloat(renovateActualPriceGroup)/10000).toFixed(2);
					}
					if(renovateActualPriceOffice!=null && renovateActualPriceOffice!=0){
						renovateActualPriceOffice = (parseFloat(renovateActualPriceOffice)/10000).toFixed(2);
					}
					html+='<tr >'+
						'<td style="text-align: center;">'+data[i].typeName+'</td>'+
						'<td>'+overhaulPayPlan+'</td>'+
						'<td>'+overhaulActualPriceGroup+'</td>'+
						'<td>'+overhaulActualPriceOffice+'</td>'+
						'<td>'+data[i].overhaulOccupyRate+'</td>'+
						'<td>'+renovatePayPlan+'</td>'+
						'<td>'+renovateActualPriceGroup+'</td>'+
						'<td>'+renovateActualPriceOffice+'</td>'+
						'<td>'+data[i].renovateOccupyRate+'</td></tr>';
				}
			}
			$('#overhaulArea').html(html);
		}
	});
}



function showSubTypeName(url){
	$.ajax({
		type : 'post',
		url:url,
		dataType : 'json',
		async:false,
		error:function(){alert("系统忙，请稍后再试！");},
		success:function(data){
			var html = '';
			if(data!=null){
				for(var i=0,len=data.length; i<len; i++){
					html += '<option value="'+data[i].subTypeId+'">'+data[i].subTypeName+'</option>';
				}
				$('#subTypeId').html(html);
			}
			
		}
	});
}

function drawPic1(url){
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
			var data1 = [];
			var data2 = [];
			var data3 = [];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].year);
					data1.push(parseInt((object[i].price).toFixed(0)));
					data2.push(parseInt((object[i].contractPrice).toFixed(0)));
					data3.push(parseInt((object[i].contractPayPrice).toFixed(0)));
				}
			}
			$('#pic1').highcharts({
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
			    		text:'(万元)'
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
			    		headerFormat: '<span style="font-size:10px">{point.key}</span><table>', 
			    		pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y} 万元</b></td></tr>', 
			    		footerFormat: '</table>', 
			    		shared: true, 
			    		useHTML: true 
			    },
			  /* tooltip: {
		            formatter: function() {
		                return ('<b>'+ this.x +'</b><br/>'+
		                    this.series.name +': '+ this.y + ' (万元)<br/>');
		            }
		        },*/
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        }
			    },
			    series: [{
			    	name:'项目预算',
			        data:data1
			    },{
			    	name:'合同价',
			    	data:data2
			    },{
			    	name:'合同实际支付',
			    	data:data3
			    }]
			});
			
		}
	});
}

function drawPic2(url,type){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async : false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].name);
					var se = [];
					if(type==1){
						se = [ object[i].name, object[i].useRatio];
					}else if(type==2){
						se = [ object[i].name, object[i].intactRatio];
					}
					series.push(se);
				}
			}
			$('#pic2').highcharts({
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
			    		text:'单位：百分比'
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">占比： </td>' +
			            '<td style="padding:0"><b>{point.y:0f} %</b></td></tr>',
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

function drawPic4(url,type){
	$.ajax( {
		type : 'GET',
		url : url+'?type='+type,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var data1=[],data2=[],data3=[],data4=[],data5=[];
			if (object != null && object.length > 0) {
				
				for ( var i = 0; i < object.length; i++) {
					categories.push(object[i].name);
					data1.push(parseInt((object[i].discardPrice/10000).toFixed(0)));		//封存
					data2.push(parseInt((object[i].planScrapPrice/10000).toFixed(0)));		//闲置
					data3.push(parseInt((object[i].scrapPrice/10000).toFixed(0)));			//报废
					data4.push(parseInt((object[i].stopPrice/10000).toFixed(0)));			//停用
					data5.push(parseInt((object[i].usePrice/10000).toFixed(0)));			//使用
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
		            data: data5
		        }, {
		            name: '停用',
		            data: data4
		        }, {
		            name: '闲置',
		            data: data2
		        }, {
		            name: '报废',
		            data: data3
		        }, {
		            name: '封存',
		            data: data1
		        }]
			});
			
		}
	});
}

function drawPic5(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		data:{
			subTypeId:$('#subTypeId').val()
		},
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null) {
				categories.push("提前报废");
				categories.push("延期报废");
				categories.push("正常报废");
				series.push(["提前报废",parseInt(object.aheadOfScrapCount)]);
				series.push(["延期报废",parseInt(object.delayScrapCount)]);
				series.push(["正常报废",parseInt(object.normalScrapCount)]);
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
			    		text:'数量(个)'
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">数量： </td>' +
			            '<td style="padding:0"><b>{point.y:.0f} 个</b></td></tr>',
			        footerFormat: '<table>',
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
/*
function drawPic7(url){
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
					var se = [ object[i].year, object[i].accuracy];
					series.push(se);
				}
			}
			$('#pic7').highcharts({
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
			    		text:''
			    		//text:'资产价值(万元)'
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">准确率： </td>' +
			            '<td style="padding:0"><b>{point.y:.0f} %</b></td></tr>',
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
}*/

function showDwOverhaulLine(url,type){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			alert("系统连接失败，请稍后再试！")
		},
		success : function(data) {
			var head="";var html="";
			if(data!=null && data.length>0){
				if(type==1){
					head = '<td style="text-align: center;">线路名称</td><td style="text-align: center;">大修项目</td><td style="text-align: center;">更新改造项目</td>';
					for(var i=0,len=data.length; i<len; i++){
						html +='<tr style="padding-top: 15px;">'+
							'<td style="text-align:left;padding-left:55px;">'+data[i].name+'</td>'+
							'<td style="padding-right:130px;">'+(data[i].overhaulPrice==0?0:(data[i].overhaulPrice/10000).toFixed(2))+'</td>'+
							'<td style="padding-right:75px;">'+(data[i].renovatePrice==0?0:(data[i].renovatePrice/10000).toFixed(2))+'</td>'+
						'</tr>';
					}
					$('#materialsConsumeArea').parent().parent().attr("colspan",4);
				}else if(type==2){
					head = '<td style="text-align: left;width:25%;">线路名称</td><td style="text-align: center;width:12.5%;">车辆</td><td style="text-align: center;width:12.5%;">供电</td>'+
					'<td style="text-align: center;width:12.5%;">通号</td>'+'<td style="text-align: center;width:12.5%;">工务</td>'+
					'<td style="text-align: center;width:12.5%;">后勤</td>'+'<td style="text-align: center;width:12.5%;">车站机电</td>';
					for(var i=0,len=data.length; i<len; i++){
						var vehicle = data[i].overhaulVehicle+data[i].renovateVehicle;
						var power = data[i].overhaulPower+data[i].renovatePower;
						var signal = data[i].overhaulSignal+data[i].renovateSignal;
						var work = data[i].overhaulWork+data[i].renovateWork;
						var logistics = data[i].overhaulLogistics+data[i].renovateLogistics;
						var stationEle = data[i].overhaulStationEle+data[i].renovateStationEle;
						
						html +='<tr style="padding-top: 15px;">'+
							'<td style="text-align:left;width:25%;">'+data[i].name+'</td>'+
							'<td style="width:12.5%;">'+(vehicle==0?0:(vehicle/10000).toFixed(2))+'</td>'+
							'<td style="width:12.5%;">'+(power==0?0:(power/10000).toFixed(2))+'</td>'+
							'<td style="width:12.5%;">'+(signal==0?0:(signal/10000).toFixed(2))+'</td>'+
							'<td style="width:12.5%;">'+(work==0?0:(work/10000).toFixed(2))+'</td>'+
							'<td style="width:12.5%;">'+(logistics==0?0:(logistics/10000).toFixed(2))+'</td>'+
							'<td style="width:12.5%;">'+(stationEle==0?0:(stationEle/10000).toFixed(2))+'</td>'+
						'</tr>';
					}
					$('#materialsConsumeArea').parent().parent().attr("colspan",7);
				}else if(type==3){
					head = '<td style="text-align: left;width:20%;">线路名称</td>'+
						'<td style="text-align: center;width:10%;">运一</td><td style="text-align: center;width:10%;">运二</td>'+
					'<td style="text-align: center;width:10%;">运三</td>'+'<td style="text-align: center;width:10%;">运四</td>'+
					'<td style="text-align: center;width:10%;">运管</td>'+'<td style="text-align: center;width:10%;">信息</td>'+
					'<td style="text-align: center;width:10%;">磁浮</td>';
					for(var i=0,len=data.length; i<len; i++){
						var operation1 = data[i].overhaulOperation1+data[i].renovateOperation1;
						var operation2 = data[i].overhaulOperation2+data[i].renovateOperation2;
						var operation3 = data[i].overhaulOperation3+data[i].renovateOperation3;
						var operation4 = data[i].overhaulOperation4+data[i].renovateOperation4;
						var transportManager = data[i].overhaulTransportManager+data[i].renovateTransportManager;
						var overhaulInformation = data[i].overhaulInformation+data[i].renovateInformation;
						
						html +='<tr style="padding-top: 15px;">'+
							'<td style="text-align:left;width:20%;">'+data[i].name+'</td>'+
							'<td style="width:10%;">'+(operation1==0?0:(operation1/10000).toFixed(2))+'</td>'+
							'<td style="width:10%;">'+(operation2==0?0:(operation2/10000).toFixed(2))+'</td>'+
							'<td style="width:10%;">'+(operation3==0?0:(operation3/10000).toFixed(2))+'</td>'+
							'<td style="width:10%;">'+(operation4==0?0:(operation4/10000).toFixed(2))+'</td>'+
							'<td style="width:10%;">'+(transportManager==0?0:(transportManager/10000).toFixed(2))+'</td>'+
							'<td style="width:10%;">'+(overhaulInformation==0?0:(overhaulInformation/10000).toFixed(2))+'</td>'+
							'<td style="width:10%;"></td>'+
						'</tr>';
					}
					$('#materialsConsumeArea').parent().parent().attr("colspan",8);
				}
				
			}
			$('#overhaulLineTHead').html(head);
			$('#materialsConsumeArea').html(html);
			$("#overhaulArea").find("tr:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
			$("#materialsConsumeArea").find("tr:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
		}
	});
}
