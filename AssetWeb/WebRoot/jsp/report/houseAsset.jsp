<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset=utf-8 />
	<title>房屋报表</title>

	<!-- CSS & js -->
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
	<script type="text/javascript" src="<%=basePath%>js/jay.js">
</script>
	<script src="<%=basePath%>js/jquery-1.9.0.min.js"
		type="text/javascript">
</script>
	<script src="<%=basePath%>js/jquery-ui-1.9.2.min.js"
		type="text/javascript">
</script>
	<script src="<%=basePath%>widgets/jtable/jquery.jtable.min.js"
		type="text/javascript">
</script>

	<script type="text/javascript">

//清除搜索框
function clearForm() {
	$("#reportType").find("option:gt(0)").attr("selected", false);
	$("#reportType").find("option:first").attr("selected", true);
}

function query(value, line) {
	if(value<3){
		$("#form").prop("action",
				"<%=basePath%>report/houseAsset.action?reportType=" + value);
		$("#form").submit();
	} else if(value==3){
		if(line == 0){
			$("#form").prop("action",
					"<%=basePath%>report/useTypeReport.action?option=query&lineType=0&reportType=" + value);
		}else if(line == 1){
			$("#form").prop("action",
					"<%=basePath%>report/useTypeReport.action?option=query&lineType=1&reportType=" + value);
		}
		$("#form").submit();
	}
}

function getLineReport() {
	$("#content").html("");
$('#content').jtable({
            title: '线路报表',
            messages: {
            	loadingMessage: '数据加载中...',
            	editRecord: '详细',
            	noDataAvailable: '没有数据！'
            },
            actions: {
                listAction: '<%=basePath%>report/lineReport.action?option=query'
            },
            fields: {
                id: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                index:{
                	title:'序号',
                	width:'3%'
                },
                line:{
					title:'线路',  
					width:'10%'   
                },
                builderProject:{
                	title:'建设类项目',
                	width:'8%'
                },
                station: {
                    title: '车站、基地、变电所、控制中心等',
                    align:'right',
                    width: '16%'
                },
                area:{
                	title:'建筑面积(适用整栋楼或整座车站)(平方米)',
					width:'20%'
                },
                useType:{
                	title:'房屋用途类别',  
					width:'10%'
                },
                takeOverDep:{
                	title:'使用单位',
					width:'15%'
                },
                reallyArea:{
                	title:'实际用房面积(平方米)',
					width:'12%'
                }
            }
        });
		$('#content').jtable('load');
}

function getLineBuildAreaReport(){
	$("#content").html("");
	$('#content').jtable({
            title: '线路商业用地报表',
            messages: {
            	loadingMessage: '数据加载中...',
            	editRecord: '详细',
            	noDataAvailable: '没有数据！'
            },
            actions: {
                listAction: '<%=basePath%>report/lineBuildAreaReport.action?option=query'
            },
            fields: {
                id: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                index:{
                	title: '序号',
                	width:'6%'
                },
                line:{
					title:'线路',
					width:'6%'
                },
                area: {
                    title: '用于商业经营开发的面积(平米)',
                    width: '10%'
                }
            }
        });
		$('#content').jtable('load');
}
function exportReport(value, lineType){
	if(value == '1'){
		exportLineReport();
	}
	if(value == '2'){
		exportLineBuildAreaReport();
	}
	if(value == '3'){
		exportUseTypeReport(lineType);
	}
}

//导出线路报表
function exportLineReport() {
	window.location.href = '<%=basePath%>report/lineReport.action?option=export';
}

//导出线路商业用地报表
function exportLineBuildAreaReport() {
	window.location.href = '<%=basePath%>report/lineBuildAreaReport.action?option=export';
}

//导出使用单位报表
function exportUseTypeReport(lineType) {
	if(lineType == 0){
		window.location.href = '<%=basePath%>report/useTypeReport.action?lineType=0&option=export';	
	} else if(lineType == 1){
		window.location.href = '<%=basePath%>report/useTypeReport.action?lineType=1&option=export';
	}
}

$(function(){
	<s:if test="#request.reportType == 1">
		getLineReport();
	</s:if>
	<s:if test="#request.reportType == 2">
		getLineBuildAreaReport();
	</s:if>
	<s:if test="#request.reportType == 3">
		$("#lineDiv").attr("style", "display:block");
	</s:if>
	
	$("#reportArea li:eq(8)").addClass("cur");
	
	<s:if test="#request.lineType == 0">
		$("#useTypeContent").attr("style","display:block;width:9300px;");
	</s:if>
	<s:if test="#request.lineType == 1">
		$("#useTypeContent").attr("style","display:block;width:2500px;");
	</s:if>
	
});

function childOption(value){
	if(value == 3){
		$("#lineDiv").attr("style", "display:block");
	}else{
		$("#lineDiv").attr("style", "display:none");
	}
}
</script>
</head>
	<body style="font-family:'Microsoft YaHei';">
		<div class="container">
			<!-- Everything started here -->
			<div class="tHeader">
				<div class="th_inMarginWrap">
					<div class="th_Logo"></div>
					<div class="th_minNav" id="firstMenu">
						<a href="<%=basePath%>jsp/homepage.jsp" class="minLink"><span><i>主题管理</i>
						</span> </a>
						<a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink"><span><i>资产台账</i> </span> </a>
						<a href="<%=basePath%>report/showReportStat.action"
							class="minLink cur"><span><i>统计报表</i> </span> </a>
						<a href="#" class="minLink"><span><i>决策分析</i> </span> </a>
						<a href="<%=basePath%>jsp/basecode/assetTypeList.jsp"
							class="minLink"><span><i>基础管理</i> </span> </a>
						<a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink"><span><i>盘点任务</i>
						</span> </a>
					</div>
					
					<jsp:include page="/jsp/reportNavigation.jsp"></jsp:include>
				</div>
			</div>
			<div id="main" class="tc_inDataArea">
				<!--search-->
				<form action="" method="post" id="form">
					<div class="search_1 p8"
						style="font-size: 12px; text-align: center;">
						<div class="">
							<div class="fl p5" style="text-align: right">
								<label for="textfield">
									报表类型
								</label>
							</div>
							<div class="fl p5 w65p" style="text-align: left">
								<select name="reportType" id="reportType" onchange="childOption(this.value)"
									class="input_large">
									<option value="0" selected>
										请选择
									</option>
									<option value="1"
										<s:if test="#request.reportType==1">selected</s:if>>
										线路报表
									</option>
									<option value="2"
										<s:if test="#request.reportType==2">selected</s:if>>
										线路商业开发用地报表
									</option>
									<option value="3"
										<s:if test="#request.reportType==3">selected</s:if>>
										使用单位报表
									</option>
								</select>
							</div>
						</div>

						<div id="lineDiv" class="" style="display: none;">
							<div class="fl p5 w30p" style="text-align: right">
								<label for="textfield">
									线路类型
								</label>
							</div>
							<div class="fl p5 w65p" style="text-align: left">
								<select name="lineType" id="lineType" onchange=""
									class="input_large">
									<option value="0" selected>
										小线
									</option>
									<option value="1"
										<s:if test="#request.lineType==1">selected</s:if>>
										大线
									</option>
								</select>
							</div>
						</div>

						<div class="t_c p5" style="text-align: right">
							<input type="button" value="查询"
								onclick="query($('#reportType').val(), $('#lineType').val())">
							<input type="button" value="导出"
								onclick="exportReport($('#reportType').val(), $('#lineType').val());">
						</div>

					</div>
				</form>
				<!--search end-->

				<div id="content" class="fl w100p">

				</div>

				<div class="clearfix" style="width: 1280px; overflow: auto;">
					<div id="useTypeContent" class="fl w100p" style="display:none;"><!-- width: 3150px; -->
						<div class="jtable-main-container">
							<div
								class="jtable-busy-panel-background jtable-busy-panel-background-invisible"
								style="display: none; width: 1280px; height: 96px;"></div>
							<div class="jtable-busy-message" style="display: none;"></div>
							<div class="jtable-title">
								<div class="jtable-title-text">
									使用单位报表
								</div>
								<div class="jtable-toolbar"></div>
							</div>
							<table class="jtable">
								<thead>
									<tr>
										<s:iterator value="#request.title" var="ti" status="s">
											<th class="jtable-column-header"
												style="width: 180px;">
												<div class="jtable-column-header-container">
													<span class="jtable-column-header-text">
														<s:property value="#ti"/>
													</span>
													<div class="jtable-column-resize-handler"></div>
												</div>
											</th>
										</s:iterator>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="#request.result" var="result" status="s">
										<tr class="jtable-data-row <s:if test="#s.index%2==1">jtable-row-even</s:if>">
                                            <td><s:property value="#s.index+1"/></td>
                                            <td><s:property value="#result.company"/></td>
                                            <td><s:property value="#result.houseType"/></td>
											<s:iterator var="line" value="#result.line">
											<td>
												<s:property value="#line"/>
											</td>
											</s:iterator>
                                            <td>
                                                <s:property value="#result.xj"/>
                                            </td>
										</tr>	
									</s:iterator>
								</tbody>
							</table>
							<div class="jtable-column-resize-bar" style="display: none;"></div>
							<div class="jtable-column-selection-container"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="tFooter"></div>






	</body>
</html>
