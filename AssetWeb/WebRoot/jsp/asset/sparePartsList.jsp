<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>备品备件台账</title>
	<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/formalize.css" rel="stylesheet">
	<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>widgets/jtable/css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>widgets/jtable/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath %>js/jay.js"></script>
	<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>		
	<script src="<%=basePath %>widgets/jtable/jquery.jtable.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".th_medNav li:eq(3)").addClass("cur");
			initJtable();
			showLine();
			showDepartment();
			showOrganization();
			showAt('8a81abc64becc844014becd102b4000d');
		    $("#at1,#at2").change(function(){
			    var index = $(this).attr("id").replace("at","");
			    var target ;
			    if(index == "1"){
			       target=$($(".at")[1]);
			    }else{
			       target=$($(".at")[2]);
			    }
				showAt($(this).val(),target);
			}); 
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
						$("#locLine").append(addHtml);
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
						$("#maintainDepartName").append(addHtml);
					}
				}
			});
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
						$("#useUnitName").append(addHtml);
						$("#ownershipUnitName").append(addHtml);
					}
				}
			});
	    }
	    
	   
	    function showAt(v,$o){
	    	$o = $o || $("#at1");
			$.ajax({
				url:'<%=basePath%>spareParts/findSparePartsType.action?parentId='+v,
				type:'post',
				dataType:'json',
				async:'false',
				error:function(){
					alert("系统繁忙，请稍后再试!");
				},
				success:function(data){
					if(data!=null){
						var addHtml = "";
						for(var i=0; i<data.Records.length; i++){
							addHtml += "<option value='"+data.Records[i].id+"'>"+data.Records[i].name+"</option>";//' style='width:100px;'
						}
						$o.append(addHtml);
					}
				}
			});
	    }
	    
	    
		function initJtable(){
			$("#content").jtable({
				title:'备品备件列表',
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
                listAction: '<%=basePath %>spareParts/inquery.action'
            },
            fields: {
                id: {
                	title:'序号',
                	width:'3%',
                	display:function(data){
                		return data.record.no;
                	}
                },
                spareAssetNo:{
					title:'备品备件编码',
					width:'10%'
                },
                spareAssetDescription: {
                    title: '备品备件名称',
                    width: '10%'
                },
                locLine:{
                	title:'线路',
					width:'10%'
                },
                locationCode:{
                	title:'车站',
					width:'10%'
                },
                projectName:{
					title:'项目',
					width:'10%'
                },
                transferDate:{
					title:'移交时间',
					width:'10%',
					display:function(data){
						if(data.record.transferDate!=null && data.record.transferDate.length>10){
							return data.record.transferDate.substring(0,10);
						}
						return ;
					}
                },
                options:{
                	title:'操作',
                	width:'5%',
                	sorting: false,
					display: function (data) {
						var btn = "<a href='<%=basePath%>spareParts/showSparePartsView.action?id="+data.record.id+"' target='_blank')>查看</a>";
				        return btn;
				    }
                	}
            	}
        		});
			$("#content").jtable('load');
		}

		//表单查询
		function query() {
			var spareAssetNo = $.trim($("#spareAssetNo").val());  //备品备件编码
			var spareAssetDescription = $.trim($("#spareAssetDescription").val());  //备品备件名称
			var locLine = $.trim($("#locLine").val());  //线路
			var projectName = $.trim($("#projectName").val());  //项目
			var at1 = "";
			var at2 = "";
			var at3 = "";
			if($("select[name=at1]").find("option:selected").text()!="请选择")
				at1 = $("select[name=at1]").find("option:selected").text();
			if($("select[name=at2]").find("option:selected").text()!="请选择")
				at2 = $("select[name=at2]").find("option:selected").text();
			if($("select[name=at3]").find("option:selected").text()!="请选择")	
			    at3 = $.trim($("#at3").val()); 
			var maintainDepart = $.trim($("#maintainDepartName").val());  //维护部门
			var ownershipUnit = $.trim($("#ownershipUnitName").val());  //权属单位 
			var useUnit = $.trim($("#useUnitName").val());  // 使用单位
			var completeTransAssetType = $.trim($("#completeTransAssetType").val());  //移交资产类型
			$('#content')
					.jtable(
							'load',
							{
								spareAssetNo_like_filter : spareAssetNo,
								spareAssetDescription_like_filter : spareAssetDescription,
								locLine_equal_filter : locLine,
								projectName_equal_filter : projectName,
								at1_equal_filter : at1,
								at2_equal_filter : at2,
								at3_equal_filter : at3,
								maintainDepart_equal_filter : maintainDepart,
								ownershipUnit_equal_filter : ownershipUnit,
								useUnit_equal_filter : useUnit,
								completeTransAssetType_equal_filter : completeTransAssetType
							});
		}

		function clearForm() {
			$("#spareAssetNo").val("");
			$("#spareAssetDescription").val("");
			$("#projectName").val("");
			$("#at1").find("option:gt(0)").attr("selected", false);
			$("#at1").find("option:first").attr("selected", true);
			$("#at2").find("option:first").attr("selected", true);
			$("#at2").find("option:gt(0)").remove();
			$("#at3").find("option:first").attr("selected", true);
			$("#at3").find("option:gt(0)").remove();
			$("#serarchArea").find("select").find("option:first").attr(
					"selected", true);
			$("#serarchArea").find("select").find("option:gt(0)").attr(
					"selected", false);
		}
		
		function excelSpareParts(){
			var spareAssetNo = $.trim($("#spareAssetNo").val());  //备品备件编码
			var spareAssetDescription = $.trim($("#spareAssetDescription").val());  //备品备件名称
			var locLine = $.trim($("#locLine").val());  //线路
			var projectName = $.trim($("#projectName").val());  //项目
			var at1 = "";
			var at2 = "";
			var at3 = "";
			if($("select[name=at1]").find("option:selected").text()!="请选择")
				at1 = $("select[name=at1]").find("option:selected").text();
			if($("select[name=at2]").find("option:selected").text()!="请选择")
				at2 = $("select[name=at2]").find("option:selected").text();
			if($("select[name=at3]").find("option:selected").text()!="请选择")	
			    at3 = $.trim($("#at3").val());
			    
			var maintainDepart = $.trim($("#maintainDepartName").val());  //维护部门
			var ownershipUnit = $.trim($("#ownershipUnitName").val());  //权属单位 
			var useUnit = $.trim($("#useUnitName").val());  // 使用单位
			var completeTransAssetType = $.trim($("#completeTransAssetType").val());  //移交资产类型
			window.location.href = '<%=basePath%>spareParts/excelSpareParts.action?spareAssetNo_like_filter='+spareAssetNo
																					+'&spareAssetDescription_like_filter='+spareAssetDescription
																					+'&locLine_equal_filter='+locLine
																					+'&projectName_equal_filter='+projectName
																					+'&at1_equal_filter='+at1
																					+'&at2_equal_filter='+at2
																					+'&at3_equal_filter='+at3
																					+'&maintainDepart_equal_filter='+maintainDepart
																					+'&ownershipUnit_equal_filter='+ownershipUnit
																					+'&useUnit_equal_filter='+useUnit
																					+'&completeTransAssetType_equal_filter='+completeTransAssetType;																			
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
					<a href="<%=basePath %>jsp/homepage.jsp" class="minLink"><span><i>主题管理</i></span></a> 
					<a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink cur"><span><i>资产台账</i></span></a> 
					<a href="<%=basePath%>report/showReportStat.action" class="minLink"><span><i>统计报表</i></span></a> 
					<a href="#" class="minLink"><span><i>决策分析</i></span></a> 
					<a href="<%=basePath%>jsp/basecode/assetTypeList.jsp" class="minLink"><span><i>基础管理</i></span></a> 
					<a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink"><span><i>盘点任务</i></span></a>
				</div>
				<jsp:include page="/jsp/navigation.jsp"></jsp:include>
			</div>
		</div>
		<div id="main" class="tc_inDataArea">
			<!--search-->
			<div class="search_1 p8" style="font-size: 12px;">
				<input type="hidden" value="${pageNo }" name="pageNo" id="pageNo" />
				<div class="clearfix" id="serarchArea" style="text-align: right;line-height: 30px;">
					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">备品备件编码</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="spareAssetNo" id="spareAssetNo" value="" class="input_large">
						</div>
					</div>

					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">备品备件名称</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" name="spareAssetDescription" id="spareAssetDescription" value="" class="input_large">
						</div>
					</div>
					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">线路</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="locLine"
								onchange="showLine();" class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">项目</label>
						</div>
						<div class="fl p5 w65p">
							<input type="text" id="projectName" value="" class="input_large">
						</div>
					</div>

					<div class="clear"></div>
					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">大类</label>
						</div>
						<div class="fl p5 w65p">
							<select class="at" name="at1" id="at1" style="height:23px;width:160px;">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">中类</label>
						</div>
						<div class="fl p5 w65p">
							<select class="at" name="at2" id="at2" style="height:23px;width:160px;">
								<option value="">请选择</option>
							</select>
						</div>
					</div>

					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">小类</label>
						</div>
						<div class="fl p5 w65p">
							<select class="at" name="at3" id="at3" style="height:23px;width:160px;">
								<option value="">请选择</option>
							</select>
						</div>
					</div>

					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">维护部门</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="maintainDepartName" class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">权属单位</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="ownershipUnitName" class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="fl w24p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">使用单位</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="useUnitName" class="input_large">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="fl w25p clearfix">
						<div class="fl p5 w30p">
							<label for="textfield">移交资产类型</label>
						</div>
						<div class="fl p5 w65p">
							<select name="" id="completeTransAssetType" class="input_large">
								<option value="">请选择</option>
								<option value="初始">初始</option>
								<option value="新增">新增</option>
								<option value="更新">更新</option>
							</select>
						</div>
					</div>
				</div>
				<div class="t_c p5">
					<input type="button" value="查询" onclick="query();"> 
					<input type="button" value="重置" onclick="clearForm();">
					<input type="button" value="导出" onclick="excelSpareParts();">
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
