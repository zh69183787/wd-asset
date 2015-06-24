<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- CSS & js -->
		<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/formalize.css" rel="stylesheet">
		<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath %>js/jay.js"></script>
		<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>		
<style type="text/css">
#thead tr td{
	text-align: center;
	border-right: 0.5px solid;
	border-top: 0.5px solid;
	border-bottom: 0.5px solid;
	border-color:#CDE0F5;
}
#dataArea td{
	padding-right:5px;
	text-align: right;
}

</style>				
	<script type="text/javascript">
        $(document).ready(function() {


            $(".th_medNav li:eq(6)").addClass("cur");
        });
	$(function(){
		$("tr[id='dataArea']:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
	});
	</script>	
	
		<title>大修/更新改造项目预算</title>
	</head>
	<body class="body" style="font-family:'Microsoft YaHei';">

<div class="container"><!-- Everything started here -->
        <div class="tHeader">
            <div class="th_inMarginWrap">
            	<div class="th_Logo"></div>
            	<div class="th_minNav" id="firstMenu">
					<a href="<%=basePath %>jsp/homepage.jsp" class="minLink"><span><i>主题管理</i></span></a>
					<a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink"><span><i>资产台账</i></span></a>
					<a href="#" class="minLink cur" onclick="setMenu(2);turnToDiv('<%=basePath%>report/showReportStat.action')"><span><i>统计报表</i></span></a>
					<a href="#" class="minLink" ><span><i>决策分析</i></span></a>
					<a href="<%=basePath%>jsp/basecode/assetTypeList.jsp" class="minLink" ><span><i>基础管理</i></span></a>
					<a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink" ><span><i>盘点任务</i></span></a>
				</div>
                <jsp:include page="/jsp/reportNavigation.jsp"></jsp:include>
				
            </div>
        </div>
        <div class="tc_inDataArea" id="main">
			<div class="search_1 p8">
			
			
        	<div class="clearfix">
        		<form action="<%=basePath %>report/showDwOverhaulLine.action" method="post">
            	<div class="fl w10p clearfix">
                	<div class="fl p5 w30p" style="text-align: right;"><label for="textfield">年度</label></div>
                	<div class="fl p5 w65p">
                		<select name="year">
                			<s:iterator var="yearnow" value="#request.yearList">
                				<s:if test="#request.year==#request.yearnow">
	                				<option value="<s:property value="#request.yearnow"/>" selected="selected"><s:property value="#request.yearnow"/></option>
	                			</s:if>
	                			<s:else>
	                				<option value="<s:property value="#request.yearnow"/>"><s:property value="#request.yearnow"/></option>
	                			</s:else>
                			</s:iterator>
                		</select>
                	</div>
                </div>
                <div class="fl w30p clearfix" style="width: 20%;">
                	<div class="fl p5 w30p" style="text-align: right;"><label for="textfield">项目类型</label></div>
                	<div class="fl p5 w65p">
                		<select name="showType">
                			<s:if test="#request.showType==2">
                				<option value="1">大修项目</option>
                				<option value="2" selected="selected">更新项目</option>
                			</s:if>
                			<s:else>
                				<option value="1">大修项目</option>
                				<option value="2">更新项目</option>
                			</s:else>
                		</select>
                	</div>
                </div>
            	
                <div class="fl w40p clearfix">
                	<div class="fl p5 w100p">
                		<input type="submit" name="" id="" value="查询" class="input_large" >
                	</div>
               	</div>
               	</form>
               	<div class="fl w40p clearfix" style="float: right;">
                	单位（万元）
               	</div>
               </div>
            </div>
	
		<table style="width: 100%;border: 1px solid #C8C8C8;background-color: #FFFFFF;">
			<thead id="thead">
				<tr class="">
					<td class="t_c" width="10%;" style="border-bottom: none;" rowspan="2">线路</td>
					<td class="t_c" width="36%;" colspan="6" style="border-bottom: 0.5px solid;border-bottom-color: #CDE0F5;">维保中心</td>
					<td class="t_c" width="6%;">运一</td>
					<td class="t_c" width="6%;">运二</td>
					<td class="t_c" width="6%;">运三</td>
					<td class="t_c" width="6%;">运四</td>
					<td class="t_c" width="6%;">运管</td>
					<td class="t_c" width="6%;">信息</td>
					<td class="t_c" width="9%;" style="border-bottom: none;">磁浮</td>
					<td class="t_c" width="9%;" style="border-bottom: none;" rowspan="2">合计</td>
				</tr>
				<tr >
					<td class="t_c" width="6%;">车辆</td>
					<td class="t_c" width="6%;">供电</td>
					<td class="t_c" width="6%;">通号</td>
					<td class="t_c" width="6%;">工务</td>
					<td class="t_c" width="6%;">后勤</td>
					<td class="t_c" width="6%;">维保小计</td>
					<td class="t_c" width="36%;" colspan="6" style="border-top: 0.5px solid;border-top-color: #CDE0F5;">车站机电</td>
					<td class="t_c" width="9%;" style="border-top: none;"></td>
					<td class="t_c" width="9%;" style="border-top: none;"></td>
				</tr>
			</thead>
			<s:if test="#request.list!=null && #request.list.size()>0">
				<s:if test="#request.showType==1">
					<s:iterator id="list" value="#request.list" status="st">
						<tr id="dataArea" style=" height:30px; padding: 2px;">
							<td class="t_c" style="text-align: left;"><s:property value="#list.name"/></td>
							<td class="t_c"><s:property value="#list.overhaulVehicle"/></td>
							<td class="t_c"><s:property value="#list.overhaulPower"/></td>
							<td class="t_c"><s:property value="#list.overhaulSignal"/></td>
							<td class="t_c"><s:property value="#list.overhaulWork"/></td>
							<td class="t_c"><s:property value="#list.overhaulLogistics"/></td>
							<td class="t_c"><s:property value="#list.overhaulTotal"/></td>
							<td class="t_c"><s:property value="#list.overhaulOperation1"/></td>
							<td class="t_c"><s:property value="#list.overhaulOperation2"/></td>
							<td class="t_c"><s:property value="#list.overhaulOperation3"/></td>
							<td class="t_c"><s:property value="#list.overhaulOperation4"/></td>
							<td class="t_c"><s:property value="#list.overhaulTransportManager"/></td>
							<td class="t_c"><s:property value="#list.overhaulInformation"/></td>
							<td class="t_c"><s:property value="#list.overhaulMaglev"/></td>
							<td class="t_c"></td>
						</tr>
					</s:iterator>
				</s:if>
				<s:elseif test="#request.showType==2">
					<s:iterator id="list" value="#request.list" status="st">
						<tr id="dataArea" style=" height:30px; padding: 2px;text-align: right;">
							<td class="t_c" style="text-align: left;"><s:property value="#list.name"/></td>
							<td class="t_c"><s:property value="#list.renovateVehicle"/></td>
							<td class="t_c"><s:property value="#list.renovatePower"/></td>
							<td class="t_c"><s:property value="#list.renovateSignal"/></td>
							<td class="t_c"><s:property value="#list.renovateWork"/></td>
							<td class="t_c"><s:property value="#list.renovateLogistics"/></td>
							<td class="t_c"><s:property value="#list.renovateTotal"/></td>
							<td class="t_c"><s:property value="#list.renovateOperation1"/></td>
							<td class="t_c"><s:property value="#list.renovateOperation2"/></td>
							<td class="t_c"><s:property value="#list.renovateOperation3"/></td>
							<td class="t_c"><s:property value="#list.renovateOperation4"/></td>
							<td class="t_c"><s:property value="#list.renovateTransportManager"/></td>
							<td class="t_c"><s:property value="#list.renovateInformation"/></td>
							<td class="t_c"><s:property value="#list.renovateMaglev"/></td>
							<td class="t_c"></td>
						</tr>
					</s:iterator>
				</s:elseif>
				<s:else>
					<s:property value="#request.showType"/>showType
				</s:else>
			</s:if>
			<!--  
			<tr id="dataArea" style=" height:30px; padding: 2px;">
				<td class="t_c">1</td>
				<td class="t_c">2</td>
				<td class="t_c">3</td>
				<td class="t_c">4</td>
				<td class="t_c">5</td>
				<td class="t_c">6</td>
				<td class="t_c">7</td>
				<td class="t_c">8</td>
				<td class="t_c">9</td>
				<td class="t_c">10</td>
				<td class="t_c">11</td>
				<td class="t_c">12</td>
				<td class="t_c">13</td>
				<td class="t_c">14</td>
				<td class="t_c">15</td>
			</tr>
			-->
			<!-- 
			<s:if test="#request.list!=null && #request.list.size()>0">
				<s:iterator id="stat" value="#request.list" status="st">
					<tr id="dataArea" style=" height:30px; padding: 2px;">
						<td class="t_c" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#st.index+1"/></td>
						<td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.name"/></td>
						<td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.assetCount"/></td>
						<td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="%{getFormattedMoney(#stat.originalValue)}"/></td>
					</tr>
				</s:iterator>
			</s:if>
			 -->
		</table>
        </div>
    </div>
    <div class="tFooter"></div>	
	
	</body>
</html>