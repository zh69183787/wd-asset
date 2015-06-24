<!-- 资产租/借用 -->
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>资产大修更新台账</title>
<link href="<%=basePath%>css/default/reset.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/default/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/formalize.css" rel="stylesheet">
<link href="<%=basePath%>css/pages.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/our.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>widgets/jtable/css/jquery-ui-1.9.2.custom.min.css"	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>widgets/jtable/themes/lightcolor/blue/jtable.css"	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jay.js"></script>
<link href="<%=basePath%>widgets/datepicker/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="<%=basePath%>widgets/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=basePath%>widgets/jtable/jquery.jtable.min.js"	type="text/javascript"></script>


<script type="text/javascript">
	$(document).ready(function(){
		$(".th_medNav li:eq(5)").addClass("cur");
		$("#recordType1").show();
		showDamageJtable1();
		showAssetType("mainType",null);
		showLine();
		showDepartment();
		showOrganization();	
	});
	//显示线路 
    function showLine(){
		$.ajax({
			url:'<%=basePath%>assetLine/getAllLine.action',
			type:'post',
			dataType:'json',
			error:function(){

			},
			success:function(data){
				if(data!=null){
					var addHtml = "";
					for(var i=0; i<data.length; i++){
						if(data[i].id!=0){
							addHtml += "<option value='"+data[i].codeId+"'>"+data[i].name+"</option>";
						}
					}
					$("#assetLine").append(addHtml);
				}
			}
		});
    }
    //根据线路显示车站
    function showProject(codeId){
		$.ajax({
			url:'<%=basePath%>assetStation/findStationByLine.action',
			type:'post',
			dataType:'json',
			data:{id:codeId}, 
			error:function(){
				alert("系统繁忙，请稍后再试!");
			},
			success:function(data){
				if(data!=null){
					var addHtml = "<option value=''>请选择</option>";
					for(var i=0; i<data.length; i++){
						addHtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
					$("#station").html(addHtml);
				}
			}
		});
    }
	  function showAssetType(contentId,target){
        var parentId=null;
        if(target!=null ){
			parentId = $(target).find('option:selected').attr('class');
			if(parentId.length==2){
				$("#subType").find("option:first").attr("selected",true);
	       		$("#subType").find("option:gt(0)").remove();
			}
        }
        $("#detailType").find("option:first").attr("selected",true);
   		$("#detailType").find("option:gt(0)").remove();
        $.ajax({
			type:'post',
			url:'<%=basePath%>assetType/findAssetType.action',
					dataType : 'json',
					cache : false,
					data : {
						parentId : parentId
					},
					error : function() {
					},
					success : function(object) {
						var addHtml = "<option value=''>请选择</option>";
						if (object != null && object.length > 0) {
							for ( var i = 0, len = object.length; i < len; i++) {
								addHtml += "<option value='"+object[i].codeId+"' class='"+object[i].id+"'>"
										+ object[i].name + "</option>";
							}
						}
						$("#" + contentId + "").html(addHtml);
					}
				});
		}
	function clearForm() {
		$("#assetCode").val("");
		$("#name").val("");
		$("#projectNo").val("");
		$("#projectName").val("");
		$("#finishDateStart").val("");
		$("#finishDateEnd").val("");
		
		$("#serarchArea").find("select").find("option:first").attr("selected",
				true);
		$("#serarchArea").find("select").find("option:gt(0)").attr("selected",
				false);
	}
	//显示使用单位
    function showOrganization(){
		$.ajax({
			url:'<%=basePath%>assetOrganization/getAllOrganization.action',
			type:'post',
			dataType:'json',
			async:'false',
			error:function(){
				alert("系统繁忙，请稍后再试!");
			},
			success:function(data){
				if(data!=null){
					var addHtml = "";
					for(var i=0; i<data.length; i++){
						addHtml += "<option value='"+data[i].code+"' style='width:100px;'>"+data[i].name+"</option>";
					}
					$("#useOrganization").append(addHtml);
					$("#ownerOrganization").append(addHtml);
				}
			}
		});
    }

    //显示维护部门
    function showDepartment(){
		$.ajax({
			url:'<%=basePath%>assetDepartment/getAllDepartment.action',
			type:'post',
			dataType:'json',
			async:'false',
			error:function(){
				alert("系统繁忙，请稍后再试!");
			},
			success:function(data){
				if(data!=null){
					var addHtml = "";
					for(var i=0; i<data.length; i++){
						addHtml += "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
					}
					$("#departmentId").append(addHtml);
				}
			}
		});
    }
    
	function showInfo(type){
	    $("#childrenId").html("");
	    $("#recordNo").val("");
	    $('#childrenId').jtable('destroy');
	    if(type==1){
	        showDamageJtable1();
	        $("#recordNo").val("1");
	        document.getElementById('recordType2').style.display = 'none';
	        document.getElementById('recordType1').style.display = 'block';
	        document.getElementById('approvalScrapDateId').style.display = 'block';
	        
	    }else if(type==2){
	        showDamageJtable2();
	        $("#recordNo").val("2");
	        document.getElementById('recordType1').style.display = 'none';
	        document.getElementById('approvalScrapDateId').style.display = 'none';
	        document.getElementById('recordType2').style.display = 'block';
	        
	    }
	}
	//已大修/更新
	function showDamageJtable1(){
		
	    $("#childrenId").jtable({
	        title: '已大修/更新资产履历表',
	        paging: true, 
	        pageSize: 10, 
	        sorting: false, 
	        messages: {
	            pagingInfo:'显示 {0}-{1}条   总共{2}条',
	            gotoPageLabel: '跳转到',
	            pageSizeChangeLabel: '每页显示',
	            loadingMessage: '数据加载中...',
	            editRecord: '详细',
	            noDataAvailable: '没有数据！'
	        },
	        actions: {
	            listAction: '<%=basePath%>asset/queryAssetRecord.action?recordNo=1'
	        },
	        fields: {
	        	
	        	no : {
					title : '序号',
					width : '3%'
				},
	            'asset.assetOwnerInf.line.shortName':{
					title:'线路',
					width:'5%',
					display:function(data){
						if(data.record.asset.assetOwnerInf.line.shortName){
							return data.record.asset.assetOwnerInf.line.shortName;
						}
						return;
					}
				},
				'asset.project.projectName':{
					title:'项目',
					width:'10%',
					display:function(data){
						if(data.record.asset.project.projectName){
							return data.record.asset.project.projectName;
						}
						return;
					}
				},
				'asset.assetCode':{
					title:'资产编码',
					width:'5%',
					display:function(data){
						if(data.record.asset.assetCode){
							return data.record.asset.assetCode;
						}
						return;
					}
				},
				'asset.name':{
					title:'资产名称',
					width:'10%',
					display:function(data){
						if(data.record.asset.name){
							return data.record.asset.name;
						}
						return;
					}
				},
				'projectName':{
					title:'大修更新改造项目',
					width:'15%',
					display:function(data){
						if(data.record.projectName){
							return data.record.projectName;
						}
						return;
					}
				},
				'assetType':{
					title:'资产类型',
					width:'12%',
					display:function(data){
						if(data.record.assetType){
							return data.record.assetType;
						}
						return;
					}
				},
				'projectAppNo':{
					title:'立项批文号',
					width:'12%',
					display:function(data){
						if(data.record.projectAppNo){
							return data.record.projectAppNo;
						}
						return;
					}
				},
				'finishDate':{
					title:'竣工日期',
					width:'10%',
					display:function(data){
						if(data.record.finishDate){
							return data.record.finishDate.substring(0,10);
						}
						return;
					}
				},
				'finishPrice':{
					title:'竣工决算价',
					width:'12%',
					display:function(data){
						if(data.record.finishPrice){
							return data.record.finishPrice;
						}
						return;
					}
				},
				
	           
	            options : {
					title:'操作',
					width:'5%',
					sorting: false,
					display: function (data) {
						var btn = "<a href='<%=basePath%>asset/showView.action?id="+data.record.asset.id+"' target='_blank')>查看</a>";
				        
				        return btn;
                	}
				}
	            
	        }
	    });
	    $('#childrenId').jtable('load');
	}
	//未大修更新的
	function showDamageJtable2(){
	    $('#childrenId').jtable({
	        title: '今年计划大修更新',
	        paging: true, 
	        pageSize: 10, 
	        sorting: false, 
	        messages: {
	            pagingInfo:'显示 {0}-{1}条   总共{2}条',
	            gotoPageLabel: '跳转到',
	            pageSizeChangeLabel: '每页显示',
	            loadingMessage: '数据加载中...',
	            editRecord: '详细',
	            noDataAvailable: '没有数据！'
	        },
	        actions: {
	            listAction: '<%=basePath%>asset/queryAssetRecord.action?recordNo=2'
	        },
	        fields: {
	            no : {
					title : '序号',
					width : '3%'
				},
	            'asset.assetOwnerInf.line.shortName':{
					title:'线路',
					width:'5%',
					display:function(data){
						if(data.record.asset.assetOwnerInf.line.shortName){
							return data.record.asset.assetOwnerInf.line.shortName;
						}
						return;
					}
				},
				'asset.project.projectName':{
					title:'项目',
					width:'10%',
					display:function(data){
						if(data.record.asset.project.projectName){
							return data.record.asset.project.projectName;
						}
						return;
					}
				},
				'asset.assetCode':{
					title:'资产编码',
					width:'5%',
					display:function(data){
						if(data.record.asset.assetCode){
							return data.record.asset.assetCode;
						}
						return;
					}
				},
				'asset.name':{
					title:'资产名称',
					width:'10%',
					display:function(data){
						if(data.record.asset.name){
							return data.record.asset.name;
						}
						return;
					}
				},
				'asset.assetOwnerInf.beginUseDate':{
					title:'开始使用时间',
					width:'12%',
					display:function(data){
						if(data.record.asset.assetOwnerInf.beginUseDate){
							return data.record.asset.assetOwnerInf.beginUseDate.substring(0,10);
						}
						return;
					}
				},
				'asset.detailType.overhaulFrequencyNum':{
					title:'大修频次',
					width:'12%',
					display:function(data){
						if(data.record.asset.detailType.overhaulFrequencyNum){
							return data.record.asset.detailType.overhaulFrequencyNum;
						}
						return;
					}
				},
				options : {
					title:'操作',
					width:'5%',
					sorting: false,
					display: function (data) {
						var btn = "<a href='<%=basePath%>asset/showView.action?id="+data.record.asset.id+"' target='_blank')>查看</a>";
				        
				        
				        return btn;
                	}
				}
	        }
	    });
	    $('#childrenId').jtable('load');
	}
	
	//表单查询
	function query() {
		var assetCode = $.trim($("#assetCode").val()); 
		var name = $.trim($("#name").val());
		var lineCodeId = $("#assetLine").val(); 
		var station = $("#station").val();
		var mainType = $("#mainType").val();
		var subType = $("#subType").val();
		var detailType = $("#detailType").val();
		var projectNo = $("#projectNo").val();
		var completeTransAssetType = $("#completeTransAssetType").val();
		var departmentId = $("#departmentId").val();
		var useOrganization = $("#useOrganization").val();
		var ownerOrganization = $("#ownerOrganization").val();
		var projectName = $("#projectName").val();
		var finishDateStart = $("#finishDateStart").val();
		var finishDateEnd = $("#finishDateEnd").val();
		
		
		var recordNo = $("#recordNo").val();
		$('#childrenId').jtable('load', {
			
			name_like_filter : name,
			assetCode_like_filter : assetCode,
			lineCodeId_equal_filter : lineCodeId,
			station_equal_filter : station,
			mainType_equal_filter : mainType,
			subType_equal_filter : subType,
			detailType_equal_filter : detailType,
			projectNo_like_filter : projectNo,
			completeTransAssetType_equal_filter : completeTransAssetType,
			departmentId_equal_filter : departmentId,
			useOrganization_equal_filter : useOrganization,
			ownerOrganization_equal_filter : ownerOrganization,
			projectName_like_filter : projectName,
			finishDateStart_equal_filter: finishDateStart,
			finishDateEnd_equal_filter: finishDateEnd,
			recordNo_equal_filter : recordNo
		}); 
	}
	
	$(function() {
		$("input[name=finishDateStart]").datepicker({
			inline : true,
			changeYear : true,
			changeMonth : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
		$("input[name=finishDateEnd]").datepicker({
			inline : true,
			changeYear : true,
			changeMonth : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
	});
	
	function excelDisableAsset(){
		var recordNo = $.trim($("#recordNo").val());
		var assetCode = $.trim($("#assetCode").val()); 
		var name = $.trim($("#name").val());
		var lineCodeId = $("#assetLine").val(); 
		var station = $("#station").val();
		var mainType = $("#mainType").val();
		var subType = $("#subType").val();
		var detailType = $("#detailType").val();
		var projectNo = $("#projectNo").val();
		var completeTransAssetType = $("#completeTransAssetType").val();
		var departmentId = $("#departmentId").val();
		var useOrganization = $("#useOrganization").val();
		var ownerOrganization = $("#ownerOrganization").val();
		var projectName = $("#projectName").val();
		var finishDateStart = $("#finishDateStart").val();
		var finishDateEnd = $("#finishDateEnd").val();
		
		window.location.href='<%=basePath%>asset/excelRecordAsset.action?recordNo='+recordNo
				+'&assetCode_like_filter='+assetCode
				+'&name_like_filter='+name
				+'&lineCodeId_equal_filter='+lineCodeId
				+'&station_equal_filter='+station
				+'&mainType_equal_filter='+mainType
				+'&subType_equal_filter='+subType
				+'&detailType_equal_filter='+detailType
				+'&projectNo_like_filter='+projectNo
				+'&completeTransAssetType_equal_filter='+completeTransAssetType
				+'&useOrganization_equal_filter='+useOrganization
				+'&departmentId_equal_filter='+departmentId
				+'&ownerOrganization_equal_filter='+ownerOrganization
				+'&projectName_like_filter='+projectName
				+'&finishDateStart_equal_filter'+finishDateStart
				+'&finishDateEnd_equal_filter'+finishDateEnd;
	}
</script>
</head>
<html>
<body style="font-family:'Microsoft YaHei';">
	<div class="container">
		<!-- Everything started here -->
		<div class="tHeader">
			<div class="th_inMarginWrap">
				<div class="th_Logo"></div>
				<div class="th_minNav" id="firstMenu">
					<a href="<%=basePath%>jsp/homepage.jsp" class="minLink"><span><i>主题管理</i>
					</span>
					</a> <a href="<%=basePath%>jsp/asset/assetList.jsp"
						class="minLink cur"><span><i>资产台账</i>
					</span>
					</a> <a href="<%=basePath%>report/showReportStat.action"
						class="minLink"><span><i>统计报表</i>
					</span>
					</a> <a href="#" class="minLink"><span><i>决策分析</i>
					</span>
					</a> <a href="<%=basePath%>jsp/basecode/assetTypeList.jsp"
						class="minLink"><span><i>基础管理</i>
					</span>
					</a> <a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink"><span><i>盘点任务</i>
					</span>
					</a>
				</div>
				<jsp:include page="/jsp/navigation.jsp"></jsp:include>
			</div>
		</div>
		<div id="main" class="tc_inDataArea">
			<!--search-->
			<div class="search_1 p8" style="font-size: 12px;">
				<div class="clearfix" id="serarchArea"
					style="text-align: right;line-height: 30px;">
					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">资产编码</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="" id="assetCode" value=""
								class="input_large">
						</div>
					</div>

					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">资产名称</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="" id="name" value=""
								class="input_large">
						</div>
					</div>
					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">线路</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="assetLine"
								onchange="showProject(this.value);" class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">车站</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="station" class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="clear"></div>
					<div class="fl w25p clearfix">
                	<div class="fl p5 w30p">
	                	<label for="textfield">大类</label></div>
	                	<div class="fl p5 w65p">
	                		<select name="mainType" id="mainType" onchange="showAssetType('subType',this)" class="input_large"></select>
	                	</div>
	                </div>
	                <div class="fl w24p clearfix">
	                	<div class="fl p5 w30p"><label for="textfield">中类</label></div>
	                	<div class="fl p5 w65p">
	                		<select name="subType" id="subType" onchange="showAssetType('detailType',this)" class="input_large">
	                			<option value="">请选择</option>
	                		</select>
	                	</div>
	                </div>
	                <div class="fl w25p clearfix">
	                	<div class="fl p5 w30p"><label for="textfield">小类</label></div>
	                	<div class="fl p5 w65p">
	                		<select name="detailType" id="detailType" class="input_large">
	                			<option value="">请选择</option>
	                		</select>
	                	</div>
	                </div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">初始项目</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="" id="projectNo" value=""
								class="input_large">
						</div>
					</div>
					<div class="clear"></div>
					<div class="fl w25p clearfix">
                	<div class="fl p5 w30p">
	                	<label for="textfield">竣工移交标识</label></div>
	                	<div class="fl p5 w65p">
	                		<select name="mainType" id="completeTransAssetType" onchange="" class="input_large">
	                			<option value="">请选择</option>
	                			<option value="初始">初始</option>
	                			<option value="新增">新增</option>
	                			<option value="更新">更新</option>
	                		</select>
	                	</div>
	                </div>
	                <div class="fl w24p clearfix">
	                	<div class="fl p5 w30p"><label for="textfield">维护部门</label></div>
	                	<div class="fl p5 w65p">
	                		<select name="subType" id="departmentId" onchange="" class="input_large">
	                			<option value="">请选择</option>
	                		</select>
	                	</div>
	                </div>
	                <div class="fl w25p clearfix">
	                	<div class="fl p5 w30p"><label for="textfield">权属单位</label></div>
	                	<div class="fl p5 w65p">
	                		<select name="" id="ownerOrganization" class="input_large">
								<option value="">请选择</option>
							</select>
	                	</div>
	                </div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">使用单位</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="useOrganization" class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="clear"></div>
					<div class="fl w25p clearfix">

						<div class="fl p5 w30p">
							<label for="textfield">大修更新项目</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="" id="projectName" value=""
								class="input_large">
						</div>
					</div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">大修更新时间</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="finishDateStart" id="finishDateStart"class="input_large" style="width: 90px" />
								-
								<input type="text" name="finishDateEnd" id="finishDateEnd" class="input_large" style="width: 90px" />
						</div>
					</div>
					<div class="fl w25p clearfix" style="display:none;" id="recordType1">
						<div class="fl p5 w30p">
							<label for="textfield"></label>
						</div>
						<div class="fl p5 w65p">
							
						</div>
					</div>
					<div class="fl w25p clearfix" style="display:none;" id="recordType2">
						<div class="fl p5 w30p">
							<label for="textfield"></label>
						</div>
						<div class="fl p5 w65p">
							
						</div>
					</div>
					<div class="fl w24p clearfix" id="approvalScrapDateId">
						<div class="fl p5 w30p">
							<label for="textfield"></label>
						</div>
						<div class="fl p5 w65p">
							
						</div>
					</div>
				</div>
				<div class="t_c p5">
					<input type="button" value="查询" onclick="query();"> 
					<input type="text" value="1" id="recordNo" style="display:none;"/>
					<input type="button" value="重置" onclick="clearForm();"> 
					<input type="button" value="导出" onclick="excelDisableAsset();">
				</div>
			</div>
			<!--search end-->
			<div class="clearfix">
				<!-- <div id="content" class="fl w100p" style="width: 100%;"></div> -->
				<table class="table_1" width="100%" >
			        <thead>
				        <tr id="tabArea">
				            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(1);">已大修更新</a></td>
				            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(2);">今年计划大修更新</a></td>
				        </tr>
			        </thead>
			    </table>
			    <table id="childrenId" style="width:100%;height:120%;" height="120%;margin-bottom: 100px;"></table>
			</div>
		</div>
	</div>
	<div class="tFooter"></div>
</body>
</html>
