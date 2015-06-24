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
		<title>资产使用单位价值统计</title>
		<!-- CSS & js -->
		<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/formalize.css" rel="stylesheet" >
		<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath %>js/jay.js"></script>
		<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>		
<script type="text/javascript">

    $(document).ready(function() {

        $(".th_medNav li:eq(3)").addClass("cur");
    });
	$(function(){
		$("tr[id='dataArea']:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
	});
</script>		
	</head>
	<body style="font-family:'Microsoft YaHei';">
	
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
        	<form action="<%=basePath %>report/showUseOrganization.action" method="post">
				<div class="search_1 p8">
			
				<input type="hidden" value="${pageNo }" name="pageNo" id="pageNo"/>
	        	<div class="clearfix">
	            	<div class="fl w30p clearfix">
	                	<div class="fl p5 w30p"><label for="textfield">年度</label></div>
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
	            	
	                <div class="fl w40p clearfix">
	                	<div class="fl p5 w100p">
	                		<input type="submit" value="查询" class="input_large" >
		                	<!-- <input type="button" name="assetName" id="assetName" value="导出" class="input_large" > -->
	                	</div>
	               	</div>
	                </div>
	            </div>
	    	</form>
		<table style="font-size: 11px;width: 100%;border: 1px solid #C8C8C8;background-color: #FFFFFF;">
			<thead>
				<tr class="trHead">
					<td class="t_c" width="10%;">序号</td>
					<td class="t_c" width="40%;">使用单位</td>
					<td class="t_c" width="35%;">资产项数</td>
					<td class="t_c" width="15%;">原值（初始）（元）</td>
				</tr>
			</thead>
			<s:if test="#request.list!=null && #request.list.size()>0">
				<s:iterator id="stat" value="#request.list" status="st">
					<tr id="dataArea" style="height:30px; padding: 2px;">
						<td  class="t_c" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#st.index+1"/></td>
						<td  style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.name"/></td>
						<td  class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.count " /></td>
						<td  class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="%{getFormattedMoney(#stat.originalValue)}"/></td>
					</tr>
				</s:iterator>
			</s:if>
			
		</table>
        </div>
    </div>
    <div class="tFooter"></div>		
	
		
	</body>
</html>