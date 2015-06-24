<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资产动态管理首页</title>
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
		<script src="<%=basePath %>js/asset/homepageDynamic.js" type="text/javascript"></script>
    
<STYLE type="text/css">
.table_border td
   {
       padding-top: 5px;
       padding-right: 5px;
       text-align: right;
       margin-right: 4px;
   }
</STYLE>    
    
    
<script type="text/javascript">
	$(function(){
		$(function(){
			$("#overhaulArea").find("tr:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
			$("#materialsConsumeArea").find("tr:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
		});

		showData('<%=basePath%>report/queryDwOverhaulProjectStat.action');
		drawPic1('<%=basePath%>report/queryDwOverHaulhaulBudgetYear.action');
		//drawPic2('<'%=basePath%>report/queryStopStateAssetValue.action',1);
		drawPic2('<%=basePath%>report/queryAssetImportantRatio.action',1);
		//drawPic4('</%=basePath%>report/queryScrapStateAssetValue.action');
		drawPic4('<%=basePath%>report/queryDwAssetTypeState.action',"assetType");
		showSubTypeName('<%=basePath%>report/queryDwScrapAssetUseLifeSubType.action');
		drawPic5('<%=basePath%>report/queryDwScrapAssetUseLife.action');
		//drawPic7('</%=basePath%>report/queryDwCheckAssetAccuracyYear.action');

		
		showDwOverhaulLine('<%=basePath%>report/queryDwOverhaulLine.action',1);
		
		//showDwMaterialsConsume('</%=basePath%>report/queryDwMaterialsConsume.action');
		showDwOverhaulMajorType('<%=basePath%>report/queryDwOverhaulMajorType.action');

		
	});
</script>        
</head>
<body style="font-family:'Microsoft YaHei';">
 <div class="container"><!-- Everything started here -->
        <div class="tHeader">
            <div class="th_inMarginWrap">
            <!-- <div style="margin:0 auto;height:113px;"> -->	
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
					<li><span><a href="<%=basePath %>jsp/homepage.jsp" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产基础管理</i></a></span></li>
					<li class="cur"><span><a href="#" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产动态管理</i></a></span></li>
				</ul>
            </div>
        </div>
        <div class="" id="main">
			<div class="tContain clearfix">
			<div class="tc_inDataArea"> 
			<!-- <div style="margin:0 auto;">-->
				<div style="padding-top:10px;width: 100%;">
					<div class="">
						<div class="radiuBox padBot noBorder" ><!-- 圆角盒子模型 -->
							<div class="radiuBoxTopR">
								<div class="radiuBoxTopL"></div>
							</div>
							<div class="radiuBoxCen ">
								<div class="rBoxTitle_1"><i class="rBtb"></i>资产动态</div>
								<div class="rBoxContain">
									<table width="100%" class="tabele_sty_1" cellpadding="0" cellspacing="0">
										<tr style="font-size: 14px;">
											<th></th>
											<th>大修项目</th>
											<th>更新改造项目</th>
											<th>新增资产</th>
											<th>使用资产</th>
											<th>停用资产</th>
											<th>报废资产</th>
											<th>租赁资产</th>
											<th>闲置资产</th>
											<th>调拨资产</th>
											<th>本年度盘点资产/结果</th>
										</tr>
										<tr class="fixheight_2" id="dataArea2">
											<td width="4%;">
												<span class="bigFont_1"  style="font-size:18px;">当年</span><span class="smallFont_1"></span><br/>
											</td>
											<td width="9%;">
												<span class="bigFont_1"  id="data02" style="font-size:18px;"></span><span class="smallFont_1">个</span><br/>
												<span class="bigFont_1"  id="data03" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;">
												<span class="bigFont_1"  id="data12" style="font-size:18px;"></span><span class="smallFont_1">个</span><br/>
												<span class="bigFont_1"  id="data13" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;">
												<span class="bigFont_1"  id="data102" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/>
												<span class="bigFont_1"  id="data103" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;" rowspan="2">
												<span class="bigFont_1" id="data3" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/><br/>
												<span class="bigFont_1" id="data31" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;" rowspan="2">
												<span class="bigFont_1" id="data4" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/><br/>
												<span class="bigFont_1" id="data41" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;" rowspan="2">
												<span class="bigFont_1" id="data2" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/><br/>
												<span class="bigFont_1" id="data21" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;" rowspan="2">
												<span class="bigFont_1" id="data5" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/><br/>
												<span class="bigFont_1" id="data51" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;" rowspan="2">
												<span class="bigFont_1" id="data6" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/><br/>
												<span class="bigFont_1" id="data61" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;" rowspan="2">
												<span class="bigFont_1" id="data7" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/><br/>
												<span class="bigFont_1" id="data71" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;" rowspan="2">
												<span class="bigFont_1" id="data8" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/><br/>
												<span class="bigFont_1" id="data9" style="font-size:18px;"></span><span class="smallFont_1">项</span>
											</td>
										</tr>
										<tr class="fixheight_2" id="dataArea">
											<td width="4%;">
												<span class="bigFont_1" id="" style="font-size:18px;">累计</span><span class="smallFont_1"></span><br/>
											</td>
											<td width="9%;">
												<span class="bigFont_1" id="data0" style="font-size:18px;"></span><span class="smallFont_1">个</span><br/>
												<span class="bigFont_1" id="data01" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;">
												<span class="bigFont_1" id="data1" style="font-size:18px;"></span><span class="smallFont_1">个</span><br/>
												<span class="bigFont_1" id="data11" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
											<td width="9%;">
												<span class="bigFont_1" id="data10" style="font-size:18px;"></span><span class="smallFont_1">项</span><br/>
												<span class="bigFont_1" id="data101" style="font-size:18px;"></span><span class="smallFont_1">万元</span>
											</td>
										</tr>
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
									<div class="rBoxTitle_1"><i class="rBtb"></i>大修更新改造项目投资年变化</div>
									<div class="rBoxContain">
										<div class="chatrWrap_1" id="pic1"></div>
									</div>
								</div>
								<div class="radiuBoxBotR">
									<div class="radiuBoxBotL"></div>
								</div>
							</div>
							
							<div class="radiuBox padBot halfbox_2" style="width: 838px;padding-left: 10px;"><!-- 圆角盒子模型 -->
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<!-- datahere -->
									<div class="rBoxTitle_1"><i class="rBtb"></i>资产重要指标情况
										<span style="float:right"> 
											<select name="importantType" onchange="drawPic2('<%=basePath%>report/queryAssetImportantRatio.action',this.value);">
												<option value="1">资产利用率</option>
												<option value="2">资产完好率</option>
											</select>
										</span>
									</div>
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
							 <div class="radiuBox padBot halfbox_2" style="float: left;width: 422px;">
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<div class="rBoxTitle_1">
										<i class="rBtb"></i>报废资产实际寿命统计
										<span style="float:right">
										<select name="subTypeName" id="subTypeId" onchange="drawPic5('<%=basePath%>report/queryDwScrapAssetUseLife.action')">
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
							 
							 
							<div class="radiuBox padBot halfbox_2" style="float: left;padding-left: 20px;width: 838px;">
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<div class="rBoxTitle_1"><i class="rBtb"></i>资产分类统计
										<span style="float:right">
											<select name="" onchange="drawPic4('<%=basePath%>report/queryDwAssetTypeState.action',this.value);">
												<option value="assetType">资产大类</option>
												<option value="org">使用单位</option>
												<option value="line">线路</option>
											</select>
										</span>
									</div>
									<div class="rBoxContain">
										<div class="chatrWrap_1" id="pic4"></div>
									</div>
								</div>
								<div class="radiuBoxBotR">
									<div class="radiuBoxBotL"></div>
								</div>
							</div>
						</div>
						
						
						
						<div class="ip_conLeftInw clearfix">
							<div class="radiuBox padBot halfbox_1" style="height: 285px;width: 630px;">
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen " style="height: 100%;">
									<div class="rBoxTitle_1"><i class="rBtb"></i>大修更新改造按线路分布情况(万元)
									<span style="float:right">
											<select onchange="showDwOverhaulLine('<%=basePath%>report/queryDwOverhaulLine.action',this.value);">
												<option value="1">项目类型</option>
												<option value="2">专业分类</option>
												<option value="3">执行单位</option>
											</select>
										</span>
									</div>
									<div class="rBoxContain" id="assetLineValue">
										<div class="chatrWrap_1" id="pic6" style="padding: 0px 5px;">
											<table width="100%;" height="90%;" class="table_border NewTableStyle" style="font-size: 14px;border-color:#CDE0F5;cellspacing:0;border:1;cellpadding:0;">
												<thead>
													<tr class="trHead" style="height: 12%; padding-top: 5px;" id="overhaulLineTHead">
														<td style="text-align: center;">线路名称</td>
														<td style="text-align: center;">大修项目</td>
														<td style="text-align: center;">更新改造项目</td>
													</tr>
												</thead>
												<tbody class="t_r" style="border: 1px solid: 1px solid;width: 100%;" >
													<tr><td colspan="3">
													<div style="height: 205px;overflow:auto;width: 100%;" >
														<table width="100%;" id="materialsConsumeArea">
															
															
														</table>
													</div>
													</td></tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="radiuBoxBotR">
									<div class="radiuBoxBotL"></div>
								</div>
							</div>
							
							<div class="radiuBox padBot halfbox_2" style="width: 630px;padding-left: 10px;height: 285px;"><!-- 圆角盒子模型 -->
								<div class="radiuBoxTopR">
									<div class="radiuBoxTopL"></div>
								</div>
								<div class="radiuBoxCen ">
									<!-- datahere -->
									<div class="rBoxTitle_1"><i class="rBtb"></i>大修更新改造专业分布执行情况趋势(万元)
										
									</div>
									<div class="rBoxContain">
										<div class="chatrWrap_1" id="pic7" style="height: 100%;">
											<!-- <div style="text-align: center;padding: 5px 5px;">2013年大修更新改造专业分布执行情况趋势（万元）</div> -->
											<table width="100%;" height="100%;" class="table_border NewTableStyle" style="font-size: 14px;border-color:#CDE0F5;cellspacing:0;border:1;cellpadding:0;">
												<thead >
													<tr class="trHead" style="height: 12%; padding-top: 5px;">
														<td style="text-align: center;" colspan="1"></td>
														<td style="text-align: center;" colspan="4">大修</td>
														<td style="text-align: center;" colspan="4">更新改造</td>
													</tr>
													<tr style="height: 12%; padding-top: 5px;">
														<td style="text-align: center;border-right: 0.5px solid;border-color:#CDE0F5;" colspan="1"></td>
														<td style="text-align: center;border-right: 0.5px solid;border-color:#CDE0F5;" colspan="1">用款计划</td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="2">实际支付</td>
														<td style="text-align: center;border-right: 0.5px solid;border-color:#CDE0F5;border-color:#CDE0F5;" colspan="1">占比</td>
														<td style="text-align: center;border-right: 0.5px solid;border-color:#CDE0F5;border-color:#CDE0F5;" colspan="1">用款计划</td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="2">实际支付</td>
														<td style="text-align: center;border-right: 0.5px solid;border-color:#CDE0F5;" colspan="1">占比</td>
													</tr>
													<tr style="height: 12%; padding-top: 5px;">
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1"></td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1"></td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1">集团</td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1">交港局</td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1"></td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1"></td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1">集团</td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1">交港局</td>
														<td style="text-align: center;border-right: 0.5px solid;border-bottom: 0.5px solid;border-color:#CDE0F5;" colspan="1"></td>
													</tr>
												</thead>
												<tbody class="t_r" style="border: 1px solid: 1px solid;" id="overhaulArea">
													<tr style="padding-top: 15px;">
														<td style="text-align: center;">车辆</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td style="text-align: center;">供电</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td style="text-align: center;">通号</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td style="text-align: center;">工务</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td style="text-align: center;">基地</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td style="text-align: center;">车站机电</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td style="text-align: center;">磁浮</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
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