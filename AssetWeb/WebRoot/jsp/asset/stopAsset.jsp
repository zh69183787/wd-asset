<!--封存/启用  -->
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
<title>封存/启用台账</title>
<link href="<%=basePath%>css/default/reset.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/default/style.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/formalize.css" rel="stylesheet">
<link href="<%=basePath%>css/pages.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/our.css" rel="stylesheet" type="text/css">
<link
	href="<%=basePath%>widgets/jtable/css/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>widgets/jtable/themes/lightcolor/blue/jtable.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jay.js"></script>
<link href="http://localhost:8080/AssetWeb/widgets/datepicker/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" />

<script src="<%=basePath%>js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery-ui-1.9.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>widgets/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=basePath%>widgets/jtable/jquery.jtable.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$(".th_medNav li:eq(9)").addClass("cur");
			initJtable();
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
	
	    
		function initJtable(){
			$("#content").jtable({
				title:'封存/启用列表',
				paging:true,
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
                listAction: '<%=basePath%>stopAsset/inqueryAsset.action'
			},
			fields : {
				no : {
					title : '序号',
					width : '3%'
				},
				'assetOwner.lineCodeId':{
					title:'线路',
					width:'5%',
					display:function(data){
						if(data.record.assetOwner.lineCodeId){
							return data.record.assetOwner.lineCodeId;
						}
						return;
					}
				},
				'asset.projectNo':{
					title:'项目',
					width:'10%',
					display:function(data){
						if(data.record.asset.projectNo){
							return data.record.asset.projectNo;
						}
						return;
					}
				},
				'asset.assetCode':{
					title:'资产编码',
					width:'10%',
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
				organ : {
					title:'申请单位',
					width:'10%'
				},
				stopDate : {
					title:'封存日期',
					width:'10%',
					display:function(data){
						if(data.record.stopDate.length>10 && data.record.stopDate!=null){
							return data.record.stopDate.substring(0,10);
						}
						return;
					}
				},
				stopReason : {
					title:'封存原因',
					width:'10%'
				},
				startReason : {
					title:'启用原因',
					width:'10%'
				},
				note : {
					title:'备注',
					width:'10%'
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
		$("#content").jtable('load');
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
		var useOrganization = $("#useOrganization").val();
		var ownerOrganization = $("#ownerOrganization").val();
		var assetDepartment = $("#assetDepartment").val();
		var stopDateStart = $("#stopDateStart").val();
		var stopDateEnd = $("#stopDateEnd").val();
		$('#content').jtable('load', {
			'asset.assetCode_like_filter' : assetCode,
			'asset.name_like_filter' : name,
			lineCodeId_equal_filter : lineCodeId,
			station_equal_filter : station,
			mainType_equal_filter : mainType,
			subType_equal_filter : subType,
			detailType_equal_filter : detailType,
			projectNo_like_filter : projectNo,
			useOrganization_equal_filter : useOrganization,
			ownerOrganization_equal_filter : ownerOrganization,
			assetDepartment_equal_filter : assetDepartment,
			stopDateStart_equal_filter : stopDateStart,
			stopDateEnd_equal_filter : stopDateEnd	
		}); 
		
	}

	function clearForm() {
		$("#assetCode").val("");
		$("#name").val("");
		$("#projectNo").val("");
		$("#stopDateStart").val("");
		$("#stopDateEnd").val("");
		$("#serarchArea").find("select").find("option:first").attr("selected",
				true);
		$("#serarchArea").find("select").find("option:gt(0)").attr("selected",
				false);
	}
	
	//显示使用单位、权属单位
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
					$("#assetDepartment").append(addHtml);
				}
			}
		});
    }
    
    function excelStopAsset(){
    	var assetCode = $.trim($("#assetCode").val());
		var name = $.trim($("#name").val());
		var lineCodeId = $("#assetLine").val(); 
		var station = $("#station").val();
		var mainType = $("#mainType").val();
		var subType = $("#subType").val();
		var detailType = $("#detailType").val();
		var projectNo = $("#projectNo").val();
		var useOrganization = $("#useOrganization").val();
		var ownerOrganization = $("#ownerOrganization").val();
		var assetDepartment = $("#assetDepartment").val();
		var stopDateStart = $("#stopDateStart").val();
		var stopDateEnd = $("#stopDateEnd").val();
    	window.location.href = '<%=basePath%>stopAsset/excelStopAsset.action?asset.assetCode_like_filter='+assetCode
																				+'&asset.name_like_filter='+name
																				+'&lineCodeId_equal_filter='+lineCodeId
																				+'&station_equal_filter='+station
																				+'&mainType_equal_filter='+mainType
																				+'&subType_equal_filter='+subType
																				+'&detailType_equal_filter='+detailType
																				+'&projectNo_like_filter='+projectNo
																				+'&useOrganization_equal_filter='+useOrganization
																				+'&ownerOrganization_equal_filter='+ownerOrganization
																				+'&assetDepartment_equal_filter='+assetDepartment
																				+'&stopDateStart_equal_filter='+stopDateStart
																				+'&stopDateEnd_equal_filter='+stopDateEnd;
    }
    
      $(function() {
		$("input[name=stopDateStart]").datepicker({
			inline : true,
			changeYear : true,
			changeMonth : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
		$("input[name=stopDateEnd]").datepicker({
			inline : true,
			changeYear : true,
			changeMonth : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
	});
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
					<!-- <div class="fl w25p clearfix">
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
							<select name="" id="station" onchange="showProject(this.value);"
								class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div> -->
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
							<label for="textfield">项目</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="" id="projectNo" value=""
								class="input_large">
						</div>
					</div>
					<div class="clear"></div>
					<div class="fl w25p clearfix">
                		<div class="fl p5 w30p"><label for="textfield">维护部门</label></div>
	                	<div class="fl p5 w65p">
	                		<select name="" id="assetDepartment" class="input_large">
	                			<option value="">请选择</option>
	                		</select>
	                	</div>
                </div>
                
                
                
                <div class="fl w24p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">权属单位</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="ownerOrganization" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">使用单位</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="useOrganization" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">封存时间</label>
						</div>
						<div class="fl p5 w65p">
								<input type="text" name="stopDateStart" id="stopDateStart"
									class="input_large" style="width: 90px" />
								-
								<input type="text" name="stopDateEnd" id="stopDateEnd"
									class="input_large" style="width: 90px" />
							</div>
					</div>
				</div>
				<div class="t_c p5">
					<input type="button" value="查询" onclick="query();"> 
					<input type="button" value="重置" onclick="clearForm();"> 
					<input type="button" value="导出" onclick="excelStopAsset();">
				</div>
			</div>
			<!--search end-->
			<div class="clearfix">
				<div id="content" class="fl w100p" style="width: 100%;"></div>
			</div>
		</div>
	</div>
	<div class="tFooter"></div>
</body>
</html>
