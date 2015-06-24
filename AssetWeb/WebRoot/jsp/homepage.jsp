<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资产管理首页</title>
 <!-- Basic Page Needs
  ================================================== -->
        <meta name="description" content="">
        <meta name="author" content="AuthorName">
        <meta name="Keywords" content="website">

        <!-- Mobile Specific Metas
  ================================================== -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

       <!-- CSS & js -->
		<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/formalize.css rel="stylesheet" ">
		<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath %>js/jay.js"></script>
		<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>		
	<script src="<%=basePath %>widgets/highcharts/highcharts.js" type="text/javascript"></script>
        
    <!-- current page needs -->
    <script src="<%=basePath %>js/asset/homepage.js" type="text/javascript"></script>
<script type="text/javascript">
	var pic6Url = '<%=basePath%>report/queryImportantAssetRank.action';
	$(function(){

		showImportantAssetRankType('<%=basePath%>report/queryImportantAssetRankType.action');
		showData('<%=basePath%>report/queryDwHomePageStat.action');
		drawPic1('<%=basePath%>report/queryProjectCompanyAssetValue.action');
		drawPic2('<%=basePath%>report/queryAssetLineValue.action');
		drawPic3('<%=basePath%>report/queryUseOrgUnit.action');
		drawPic4('<%=basePath%>report/queryAssetFormYear.action');
		//drawPic5('</%=basePath%>report/queryAssetStateLine.action');
		drawPic5('<%=basePath%>report/queryDwImportantAssetLine.action');
		drawPic6($('#typeArea select').val());

		
	});

	
</script>     
<style type="text/css">
	a:hover {text-decoration:underline ; color:black;}
</style>   
</head>
<body style="font-family:'Microsoft YaHei';">
 <div class="container"><!-- Everything started here -->
        <div class="tHeader">
            <div class="th_inMarginWrap">
            	<div class="th_Logo"></div>
            	<div class="th_minNav" id="firstMenu">
					<a href="#" class="minLink cur""><span><i>主题管理</i></span></a>
					<a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink" ><span><i>资产台账</i></span></a>
					<a href="<%=basePath%>report/showReportStat.action" class="minLink" ><span><i>统计报表</i></span></a>
					<a href="#" class="minLink" ><span><i>决策分析</i></span></a>
					<a href="<%=basePath%>jsp/basecode/assetTypeList.jsp" class="minLink" ><span><i>基础管理</i></span></a>
					<a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink" ><span><i>盘点任务</i></span></a>
				</div>
				<ul class="th_medNav" >
					<li class="cur"><span><a href="#" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产静态管理</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/homepageDynamic.jsp" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产动态管理</i></a></span></li>
				</ul>
            </div>
        </div>
        <div class="" id="main">
			<div class="tContain clearfix">
			<div class="tc_inDataArea">
				<div class="ip_conRight" style="padding-top: 157px;">
					<div class="radiuBox padBot"><!-- 圆角盒子模型 -->
						<div class="radiuBoxTopR">
							<div class="radiuBoxTopL"></div>
						</div>
						<div class="radiuBoxCen">
							<!-- datahere -->
							<div class="rBoxTitle_1"><i class="rBtb"></i>使用单位资产价值分布</div>
							<div class="rBoxContain">
								<div class="chatrWrap_1" id="pic3"></div>
							</div>
						</div>
						<div class="radiuBoxBotR">
							<div class="radiuBoxBotL"></div>
						</div>
					</div>
					
					<div class="radiuBox padBot"><!-- 圆角盒子模型 -->
						<div class="radiuBoxTopR">
							<div class="radiuBoxTopL"></div>
						</div>
						<div class="radiuBoxCen">
							<!-- datahere -->
							<div class="rBoxTitle_1">
								<i class="rBtb"></i>重要资产专业分布
								<span style="float:right;" id="typeArea">
									<select>
										<option>运营公司</option>
										<option>运营公司</option>
									</select>
								</span>
							</div>
							<div class="rBoxContain">
								<div class="chatrWrap_1" id="pic6">
								</div>
							</div>
						</div>
						<div class="radiuBoxBotR">
							<div class="radiuBoxBotL"></div>
						</div>
					</div>
					
				</div>
				<div class="ip_conLeftWrap">
					<div class="ip_conLeft">
					
					
						<div class="radiuBox padBot noBorder" style="margin-right: -422px"><!-- 圆角盒子模型 -->
							<div class="radiuBoxTopR">
								<div class="radiuBoxTopL"></div>
							</div>
							<div class="radiuBoxCen ">
								<div class="rBoxTitle_1"><i class="rBtb"></i>资产概况</div>
								<div class="rBoxContain">
									<table width="100%" class="tabele_sty_1" cellpadding="0" cellspacing="0" >
										<tr>
											<th>建设项目总数</th>
											<th>建设项目总概算</th>
											<th>实物资产入册项目数</th>
											<th>实物资产入册项数</th>
											<th>实物资产入册合同价</th>
											<th>价值资产入册项目数</th>
											<th>价值资产入册项数</th>
											<th>价值资产入册原值</th>
										</tr>
										<tr class="fixheight_2" id="dataArea">
											<td width="12.5%;"><a href="<%=basePath %>report/showProjectLineValue.action" target="_blank"><span class="bigFont_1" id="data0" style="font-size:18px;"></span></a><span class="smallFont_1">项</span></td>
											<td width="12.5%;"><a href="<%=basePath %>report/showProjectLineValue.action" target="_blank"><span class="bigFont_1" id="data1" style="font-size:18px;"></span></a><span class="smallFont_1">万元</span></td>
											<td width="12.5%;"><span class="bigFont_1" id="data2" style="font-size:18px;"></span></a><span class="smallFont_1">项</span></td>
											<td width="12.5%;"><a href="<%=basePath%>report/showReportStat.action" target="_blank"><span class="bigFont_1" id="data3" style="font-size:18px;"></span><span class="smallFont_1">项</span></td>
											<td width="12.5%;"><span class="bigFont_1" id="data4" style="font-size:18px;"></span><span class="smallFont_1">万元</span></td>
											<td width="12.5%;"><span class="bigFont_1" id="data5" style="font-size:18px;"></span><span class="smallFont_1">项</span></td>
											<td width="12.5%;"><span class="bigFont_1" id="data6" style="font-size:18px;"></span><span class="smallFont_1">项</span></td>
											<td width="12.5%;"><span class="bigFont_1" id="data7" style="font-size:18px;"></span><span class="smallFont_1">万元</span></td>
										</tr>
										<!-- 
										<tr class="fixheight_3">
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
											<td><span class="bigFont_2"></span><span class="smallFont_2"></span></td>
										</tr>
										 -->
									</table>
								</div>
							</div>
							<div class="radiuBoxBotR">
								<div class="radiuBoxBotL"></div>
							</div>
						</div>
						
						<div class="ip_conLeftInw clearfix">
						
							<div class="radiuBox padBot halfbox_1"><!-- 圆角盒子模型 -->
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<!-- datahere -->
									<div class="rBoxTitle_1"><i class="rBtb"></i>权属单位资产价值分布</div>
									<div class="rBoxContain">
										<div class="chatrWrap_1" id="pic1"></div>
									</div>
								</div>
								<div class="radiuBoxBotR">
									<div class="radiuBoxBotL"></div>
								</div>
							</div>
							
							<div class="radiuBox padBot halfbox_2"><!-- 圆角盒子模型 -->
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<!-- datahere -->
									<div class="rBoxTitle_1"><i class="rBtb"></i>线路资产价值分布</div>
									<div class="rBoxContain">
										<div class="chatrWrap_1" id="pic2"></div>
									</div>
								</div>
								<div class="radiuBoxBotR">
									<div class="radiuBoxBotL"></div>
								</div>
							</div>
						
						</div>
						
						<div class="ip_conLeftInw clearfix">
						
							<div class="radiuBox padBot halfbox_1"><!-- 圆角盒子模型 -->
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<!-- datahere -->
									<div class="rBoxTitle_1"><i class="rBtb"></i>资产形成年份分析</div>
									<div class="rBoxContain" id="assetLineValue">
										<div class="chatrWrap_1" id="pic4"></div>
									</div>
								</div>
								<div class="radiuBoxBotR">
									<div class="radiuBoxBotL"></div>
								</div>
							</div>
							
							<div class="radiuBox padBot halfbox_2"><!-- 圆角盒子模型 -->
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<!-- datahere -->
									<div class="rBoxTitle_1"><i class="rBtb"></i>重要资产线路分布
										<span style="float:right">
											<select onchange="drawPic5('<%=basePath%>report/queryDwImportantAssetLine.action')" id="hardTypeArea">
												<option value="2#01">线路</option>
												<option value="32#02">房屋建筑</option>
												<option value="190#0401">电客列车</option>
											</select>
											<select onchange="drawPic5('<%=basePath%>report/queryDwImportantAssetLine.action')" id="hardShowType">
												<option value="1">资产数值</option>
												<option value="2">资产价值</option>
											</select>
										</span>
									</div>
									<div class="rBoxContain">
										<div class="chatrWrap_1" id="pic5"></div>
									</div>
								</div>
								<div class="radiuBoxBotR">
									<div class="radiuBoxBotL"></div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
        </div>
    </div>
    <div class="tFooter"></div>







</body>
</html>