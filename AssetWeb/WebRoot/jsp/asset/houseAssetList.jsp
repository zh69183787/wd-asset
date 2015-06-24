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
<head>
	<meta charset=utf-8 />
	<title>房屋资源台帐</title>

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
	$(document).ready(function() {
		
		$("#houseType>option[value='${searchMap.houseType}']").prop("selected",true);
		
		showBigLine();
		$(".th_medNav li:eq(2)").addClass("cur");
		
		$("span[class='jtable-page-number']").click(
			function(){
				var num = $(this).text();
				goPage(num);
			}	
		);
		$("span[class='jtable-page-number-first ']").click(
			function(){
				goPage(1);
			}	
		);
		$("span[class='jtable-page-number-previous ']").click(
			function(){
				goPage(${pageInfo.pageNo-1});
			}	
		);
		$("span[class='jtable-page-number-next ']").click(function(){
				goPage(${pageInfo.pageNo+1});
			}	
		);
		$("span[class='jtable-page-number-last ']").click(
			function(){
				goPage(${pageInfo.totalPages});
			}	
		);
		
	});

//表单查询
function formInit() {
	var line = $.trim($("#line").val());
	if(line == '请选择'){
		$("#line").val("");
	}
	var station = $.trim($("#station").val());
	if(station == '请选择'){
		$("#station").val("");
	}
	
	//输入过长字符串处理
	$("#takeOverDep").val($("#takeOverDep").val().substr(0,100));
	$("#assetName").val($("#assetName").val().substr(0,100));
	$("#useType").val($("#useType").val().substr(0,100));
}

function numberCheck(param){
	if($(param).val()!=''){
		var res = /^[0-9]*(.[0-9]+)?$/;
		if(!res.test($(param).val())){
			alert("只能输入数字!");
			$(param).val("");
			$(param).focus();
			return ;
		}
	}
}

//清除搜索框
function clearForm() {
	$("#smallLine").find("option:gt(0)").attr("selected", false);
	$("#smallLine").find("option:first").attr("selected", true);
	$("#stationSelect").find("option:first").attr("selected", true);
	$("#stationSelect").find("option:gt(0)").remove();

	$("#serarchArea").find("select").find("option:first")
			.attr("selected", true);
	$("#serarchArea").find("select").find("option:gt(0)").attr("selected",
			false);

	$("input[name='line_filter']").val("");
	$("input[name='station_filter']").val("");
	$("input[name='useType_filter']").val("");
	$("input[name='takeOverDep_filter']").val("");
	$("input[name='assetName_filter']").val("");
	$("input[name='buildAreaStart_filter']").val("");
	$("input[name='buildAreaEnd_filter']").val("");
	$("input[name='houseType_filter']").val("");
}

//显示大线
function showBigLine(){
	$.ajax( {
		url : '<%=basePath%>assetLine/getAllLine.action',
		type : 'get',
		dataType : 'json',
		error : function() {
		},
		success : function(data) {
			var tmp;
			if (data != null) {
				var addHtml = "";
				var flag;
				for ( var i = 0; i < data.length; i++) {
					if (data[i].id != 0) {
						if(data[i].id==$('#bigLine').val()){
							flag = 'selected';
							tmp = data[i].id;
						}else{
							flag = '';
						}
						addHtml += "<option value='" + data[i].id
								+ "' "+flag+">" + data[i].shortName + "</option>";
					}
				}
				$("#bigLineSelect").append(addHtml);
				
			}
			showLine();
		}
	});
}

//显示线路 
function showLine() {
	$.ajax( {
		url : '<%=basePath%>project/findShortName.action',
		type : 'post',
		dataType : 'json',
		error : function() {

		},
		success : function(data) {
			var tmp;
			if (data != null) {
				var addHtml = "";
				var flag;
				for ( var i = 0; i < data.length; i++) {
					if (data[i].id != 0) {
						if(data[i].shortName==$('#line').val()){
							flag = 'selected';
							tmp = data[i].lineCodeId;
						}else{
							flag = '';
						}
						addHtml += "<option value='" + data[i].lineCodeId
								+ "' "+flag+">" + data[i].shortName + "</option>";
					}
				}
				$("#smallLine").append(addHtml);
				
			}
			showStation(tmp);
		}
	});
}

//显示车站
function showStation(lineCodeId) {
	$("#line").val($("#smallLine option:selected").text());
	$("#station option:gt(0)").remove("");
	$.ajax( {
		url : '<%=basePath%>assetLine/findStationById.action?lineCodeId=' + lineCodeId,
		type : 'post',
		dataType : 'json',
		error : function() {

		},
		success : function(data) {
			if (data != null) {
				var addHtml = "";
				var flag;
				for ( var i = 0; i < data.length; i++) {
					if(data[i].name==$('#station').val()){
							flag = 'selected';
						}else{
							flag = '';
						}
					if (data[i].id != 0) {
						addHtml += "<option value='" + data[i].id
								+ "' "+flag+">" + data[i].name + "</option>";
					}
				}
				$("#stationSelect").append(addHtml);
			}
		}
	});
}

function goPage(no){
	$("#pageNo").val(no);
	formInit();
	$("#searchForm").submit();
}

function changeSize(size){
	$("#pageSize").val(size);
	formInit();
	$("#searchForm").submit();
}

//保存车站到隐藏域中
function saveStation() {
	$("#station").val($("#stationSelect option:selected").text());
}

//导出房屋数据报表
function exportDataReport() {
	<%-- window.location.href = '<%=basePath%>houseAsset/exportDataReport.action'; --%>
	var houseType = $("#houseType").val(); 
	var bigLineSelect = $("#bigLineSelect").val();
	var line = $("#smallLine").val();
	var station = $("#station").val();
	var useType = $("#useType").val();
	var assetName = $("#assetName").val();
	var takeOverDep = $("#takeOverDep").val();
	var buildAreaStart = $("#buildAreaStart").val();
	var buildAreaEnd = $("#buildAreaEnd").val();
	window.location.href = '<%=basePath%>houseAsset/exportDataReport.action?houseType_filter='+houseType
	 																		+'&bigLine_filter='+bigLineSelect
	 																		+'&line_filter='+line
	 																		+'&station_filter='+station
	 																		+'&useType_filter='+useType
	 																		+'&assetName_filter='+assetName
	 																		+'&takeOverDep_filter='+takeOverDep
	 																		+'&buildAreaStart_filter='+buildAreaStart
	 																		+'&buildAreaEnd_filter='+buildAreaEnd; 
}

function setPageInfo(){
	$("#pSize option[value="+$('#pageSize').val()+"]").attr("selected",true);
}
</script>
</head>
<html>
	<body onload="setPageInfo()" style="font-family:'Microsoft YaHei';">
		<div class="container">
			<!-- Everything started here -->
			<div class="tHeader">
				<div class="th_inMarginWrap">
					<div class="th_Logo"></div>
					<div class="th_minNav" id="firstMenu">
						<a href="<%=basePath%>jsp/homepage.jsp" class="minLink"><span><i>主题管理</i>
						</span>
						</a>
						<a href="#" class="minLink cur"><span><i>资产台账</i>
						</span>
						</a>
						<a href="<%=basePath%>report/showReportStat.action"
							class="minLink"><span><i>统计报表</i>
						</span>
						</a>
						<a href="#" class="minLink"><span><i>决策分析</i>
						</span>
						</a>
						<a href="<%=basePath%>jsp/basecode/assetTypeList.jsp"
							class="minLink"><span><i>基础管理</i>
						</span>
						</a>
						<a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink"><span><i>盘点任务</i>
						</span>
						</a>
					</div>
					<jsp:include page="/jsp/navigation.jsp"></jsp:include>
				</div>
			</div>
			<div id="main" class="tc_inDataArea">
				<!--search-->
				<form action="<%=basePath%>houseAsset/inquery.action" method="post" id="searchForm">
				<div class="search_1 p8" style="font-size: 12px;">
				
					<input type="hidden" value="" name="pageNo" id="pageNo" />
					<input type="hidden" value="${pageInfo.pageSize}" name="pageSize" id="pageSize" />
					<div class="clearfix" id="serarchArea"
						style="text-align: right; line-height: 30px;">
						<div class="fl w25p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									线路(大线)
								</label>
							</div>
							<input type="hidden" name="" id="bigLine" value="${searchMap.bigLine}" />
							<div class="fl p5 w65p">
								<select name="bigLine_filter" id="bigLineSelect"
									onchange="" class="input_large">
									<option value="" selected>请选择</option>
								</select>
							</div>
						</div>
						
						<div class="fl w25p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									线路(小线)
								</label>
							</div>
							<input type="hidden" name="line_filter" id="line" value="${searchMap.line}" />
							<div class="fl p5 w65p">
								<select name="smallLine" id="smallLine"
									onchange="showStation(this.value)" class="input_large">
									<option value="" selected>
										请选择
									</option>
								</select>
							</div>
						</div>

						<div class="fl w24p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									车站
								</label>
							</div>
							<input type="hidden" name="station_filter" id="station" value="${searchMap.station}"/>
							<div class="fl p5 w65p">
								<select name="stationSelect" id="stationSelect"
									onchange="saveStation()" class="input_large">
									<option value="">
										请选择
									</option>
								</select>
							</div>
						</div>
						
						<div class="fl w25p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									房屋用途
								</label>
							</div>
							<div class="fl p5 w65p">
								<input type="text" name="useType_filter" id="useType" value="${searchMap.useType}"
									placeholder="请输入房屋用途" class="input_large" />
							</div>
						</div>

						<div class="clear"></div>
						
						<div class="fl w25p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									房屋建筑名称
								</label>
							</div>
							<div class="fl p5 w65p">
								<input type="text" name="assetName_filter" id="assetName" value="${searchMap.assetName}"
									placeholder="请输入房屋建筑名称" class="input_large" />
							</div>
						</div>
						<div class="fl w25p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									房屋分类
								</label>
							</div>
							<div class="fl p5 w65p">
								<select name="houseType_filter" id="houseType"
									onchange="" class="input_large">
									<option value="">请选择</option>
									<option value="车站">车站</option>
									<option value="停车场（车辆段）">停车场（车辆段）</option>
									<option value="控制中心">控制中心</option>
									<option value="培训中心">培训中心</option>
									<option value="主变、风井和端头井等">主变、风井和端头井等</option>
									<option value="其他">其他</option>
								</select>
							</div>
						</div>
						
						<div class="fl w24p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									使用单位
								</label>
							</div>
							<div class="fl p5 w65p">
								<input type="text" name="takeOverDep_filter" id="takeOverDep" value="${searchMap.takeOverDep}"
									placeholder="请输入使用单位" class="input_large" />
							</div>
						</div>
						
						
						<div class="fl w25p clearfix">
							<div class="fl p5 w30p">
								<label for="textfield">
									建筑面积范围
								</label>
							</div>
							<div class="fl p5 w65p">
								<input type="text" name="buildAreaStart_filter" id="buildAreaStart"
									class="input_large" style="width: 90px" onblur="numberCheck(this);" value="${searchMap.buildAreaStart}"/>
								-
								<input type="text" name="buildAreaEnd_filter" id="buildAreaEnd"
									class="input_large" style="width: 90px" onblur="numberCheck(this);" value="${searchMap.buildAreaEnd}"/>
							</div>
						</div>

					</div>
					<div class="t_c p5">
						<input type="submit" value="查询" onclick="formInit();">
						<input type="button" value="重置" onclick="clearForm();">
						<input type="button" value="导出"
							onclick="exportDataReport();">
					</div>

				</div>
				</form>
				<!--search end-->

				<div class="clearfix" style="width: 1280px; overflow: auto;">
					<div id="content" class="fl w100p" style="display:block;width:1280px;">
						<div class="jtable-main-container">
							<div class="jtable-busy-panel-background"
								style="display: none; width: 2400px; height: 123px;"></div>
							<div class="jtable-busy-message" style="display: none;"></div>
							<div class="jtable-title">
								<div class="jtable-title-text">
									房屋资源
								</div>
								<div class="jtable-toolbar"></div>
							</div>
							<table class="jtable">
								<thead>
									<tr>
										<th class="jtable-column-header"
											style="width: 30px">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">序号</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 60px">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">线路</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 90px">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">建设类项目名称</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 130px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">车站、基地、变电所等</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 80px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">房屋建筑名称</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<!-- <th class="jtable-column-header"
											style="width: 100px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">地址</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th> -->
										<th class="jtable-column-header"
											style="width: 60px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">房屋分类</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 120px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">建筑面积（平方米）</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 110px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">层数（地上/地下）</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<!-- <th class="jtable-column-header"
											style="width: 195px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">产权证或竣工证号或规划许可证号</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th> -->
										<th class="jtable-column-header"
											style="width: 50px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">房屋用途</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 130px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">使用单位</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: 150px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">实际用房面积（平方米）</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<!-- <th class="jtable-column-header"
											style="width: ">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">所在楼层</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th>
										<th class="jtable-column-header"
											style="width: ">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">备注</span>
												<div class="jtable-column-resize-handler"></div>
											</div>
										</th> -->
										<th class="jtable-column-header"
											style="width: 30px;">
											<div class="jtable-column-header-container">
												<span class="jtable-column-header-text">操作</span>
											</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="#request.records" var="record" status="re">
										<s:set name="spanNum" value="#request.areaInfos.size()"/>
										<s:if test="#spanNum == 0">
											<tr class="jtable-data-row <s:if test='#re.index%2 == 0'>jtable-row-even</s:if>" data-record-key="">
													<td><s:property value="#re.index+1"/></td>
													<td><s:property value="#record.line"/></td>
													<td><s:property value="#record.builderProject"/></td>
													<td>
														<s:property value='#record.station'/>
													</td>
													<td><s:property value='#record.assetName'/></td>
													
													<!-- <td><s:property value='#record.locationNo'/></td> -->
													<td>
														<s:property value='#request.houseType'/>
													</td>
													<td><s:property value='#record.buildArea'/></td>
													<td>
														<s:property value='#record.groundFloor'/>
														/<s:property value='#record.undergroundFloor'/>
													</td>
													<td>
														<s:if test="#request.propertyRightNo != null">
															<s:property value="#record.propertyRightNo"/>
														</s:if>
														<s:elseif test="#record.completedLicense != null">
															<s:property value="#record.completedLicense"/>
														</s:elseif>
														<s:else>
															<s:property value="#record.planLicense"/>
														</s:else>
													</td>
												
													<td>
														<s:property value=""/>
													</td>
													<td>
														<s:property value=""/>
													</td>
													<td>
														<s:property value=""/>
													</td>
													
													<td>
													<a
														href="<%=basePath%>houseAsset/showView.action?id=<s:property value="#record.houseAssetId"/>"
														target="_blank")="">详情</a>
													</td>
											</tr>
										</s:if>
										<s:else>
											<s:iterator value="#record.areaInfos" var="areaInfo" status="st">
												<tr class="jtable-data-row <s:if test='#re.index%2 == 0'>jtable-row-even</s:if>" data-record-key="">
													<s:if test="#st.index == 0">
														<td rowspan="<s:property value='#spanNum'/>">
															<s:property value="#re.index+1"/>
														</td>
														<td rowspan="<s:property value='#spanNum'/>">
															<s:property value="#record.line"/>
														</td>
														<td rowspan="<s:property value='#spanNum'/>"><s:property value="#record.builderProject"/></td>
														<td rowspan="<s:property value='#spanNum'/>">
															<s:property value='#record.station'/>
														</td>
														<td rowspan="<s:property value='#spanNum'/>"><s:property value='#record.assetName'/></td>
														<%-- <td rowspan="<s:property value='#spanNum'/>"><s:property value='#record.locationNo'/></td> --%>
														<td rowspan="<s:property value='#spanNum'/>">
																<s:property value='#request.houseType'/>
														</td>
														
														<td rowspan="<s:property value='#spanNum'/>"><s:property value='#record.buildArea'/></td>
														<td rowspan="<s:property value='#spanNum'/>">
															<s:property value='#record.groundFloor'/>
															/<s:property value='#record.undergroundFloor'/>
														</td>
														<%-- <td rowspan="<s:property value='#spanNum'/>">
															<s:if test="#request.propertyRightNo != null">
																<s:property value="#record.propertyRightNo"/>
															</s:if>
															<s:elseif test="#record.completedLicense != null">
																<s:property value="#record.completedLicense"/>
															</s:elseif>
															<s:else>
																<s:property value="#record.planLicense"/>
															</s:else>
														</td> --%>
													</s:if>
													
														<td>
															<s:property value="#areaInfo.useType"/>
														</td>
														<td>
															<s:property value="#areaInfo.takeOverDep"/>
														</td>
														<td>
															<s:property value="#areaInfo.reallyArea"/>
														</td>
														<!-- <td>
															<s:property value="#areaInfo.inFloor"/>
														</td>
														<td><s:property value="#areaInfo.note"/></td>-->
													<s:if test="#st.index == 0"> 
														<td rowspan="<s:property value='#spanNum'/>">
														<a
															href="<%=basePath%>houseAsset/showView.action?id=<s:property value="#record.houseAssetId"/>"
															target="_blank")="">详情</a>
														</td>
													</s:if>
												</tr>
											</s:iterator>
										</s:else>
									</s:iterator>
										
								</tbody>
							</table>
							<div class="jtable-bottom-panel">
								<div class="jtable-left-area">
									<span class="jtable-page-list">
										<span class="jtable-page-number-first <c:if test="${pageInfo.pageNo==1}">jtable-page-number-disabled</c:if>">&lt;&lt;</span>
										<span class="jtable-page-number-previous <c:if test="${pageInfo.pageNo==1}">jtable-page-number-disabled</c:if>">&lt;</span>
										<c:if test="${pageInfo.totalPages<5}">
											<c:forEach begin="1" end="${pageInfo.totalPages}" var="s">
												<span class="jtable-page-number <c:if test="${pageInfo.pageNo==s}">jtable-page-number-active</c:if>">${s}</span>
											</c:forEach>
										</c:if>
										<c:if test="${pageInfo.totalPages>=5}">
											<c:if test="${pageInfo.pageNo<5}">
												<c:forEach begin="1" end="${pageInfo.pageNo}" var="s">
													<span class="jtable-page-number <c:if test="${pageInfo.pageNo==s}">jtable-page-number-active</c:if>">${s}</span>
												</c:forEach>
												<span class="jtable-page-number-space">...</span>
												<span class="jtable-page-number">${pageInfo.totalPages-1}</span>
												<span class="jtable-page-number">${pageInfo.totalPages}</span>
											</c:if>
											<c:if test="${pageInfo.pageNo>=5 && pageInfo.pageNo<=pageInfo.totalPages-4}">
												<span class="jtable-page-number">1</span>
												<span class="jtable-page-number">2</span>
												<span class="jtable-page-number-space">...</span>
												<span class="jtable-page-number">${pageInfo.pageNo-1}</span>
												<span class="jtable-page-number jtable-page-number-active">${pageInfo.pageNo}</span>
												<span class="jtable-page-number">${pageInfo.pageNo+1}</span>
												<span class="jtable-page-number-space">...</span>
												<span class="jtable-page-number">${pageInfo.totalPages-1}</span>
												<span class="jtable-page-number">${pageInfo.totalPages}</span>
											</c:if>
											<c:if test="${pageInfo.pageNo > pageInfo.totalPages-4}">
												<span class="jtable-page-number">1</span>
												<span class="jtable-page-number">2</span>
												<span class="jtable-page-number-space">...</span>
												<c:forEach begin="${pageInfo.pageNo}" end="${pageInfo.totalPages}" var="s">
													<span class="jtable-page-number <c:if test="${pageInfo.pageNo==s}">jtable-page-number-active</c:if>">${s}</span>
												</c:forEach>
											</c:if>
										</c:if>
										<span class="jtable-page-number-next <c:if test="${pageInfo.pageNo==pageInfo.totalPages}">jtable-page-number-disabled</c:if>">&gt;</span>
										<span class="jtable-page-number-last <c:if test="${pageInfo.pageNo==pageInfo.totalPages}">jtable-page-number-disabled</c:if>">&gt;&gt;</span>
									</span>
									<span class="jtable-goto-page"><span>跳转到: </span>
									<select onchange="goPage(this.value)">
										<c:forEach begin="1" end="${pageInfo.totalPages}" var="s">
											<option value="${s}" <c:if test="${pageInfo.pageNo == s}">selected</c:if>>
												${s}
											</option>
										</c:forEach>
										</select>
									</span><span class="jtable-page-size-change"><span>每页显示:
									</span>
									<select id="pSize" onchange="changeSize(this.value)">
											<option value="10">
												10
											</option>
											<option value="25">
												25
											</option>
											<option value="50">
												50
											</option>
											<option value="100">
												100
											</option>
											<option value="250">
												250
											</option>
											<option value="500">
												500
											</option>
										</select>
									</span>
								</div>
								<div class="jtable-right-area">
									<span class="jtable-page-info">显示 <c:if test="${pageInfo.totalCount==0}">0-</c:if>
									<c:if test="${pageInfo.totalCount>0}">${(pageInfo.pageNo-1)*pageInfo.pageSize+1}-</c:if>
									<c:if test="${(pageInfo.pageNo)*pageInfo.pageSize < pageInfo.totalCount}">${(pageInfo.pageNo)*pageInfo.pageSize}</c:if>
									<c:if test="${(pageInfo.pageNo)*pageInfo.pageSize >= pageInfo.totalCount}">${pageInfo.totalCount}</c:if>条 总共${pageInfo.totalCount}条</span>
								</div>
							</div>
							<div class="jtable-column-resize-bar" style="display: none;"></div>
							<div class="jtable-column-selection-container"></div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="tFooter"></div>






	</body>
</html>
