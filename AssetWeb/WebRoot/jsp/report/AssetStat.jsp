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
		<link href="<%=basePath %>widgets/treeTable/jquery.treetable.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>widgets/treeTable/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css">	
		<title>资产实物汇总统计</title>
<!-- CSS & js -->
		<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/formalize.css" rel="stylesheet">
		<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath %>js/jay.js"></script>
		<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>		
		<script src="<%=basePath %>widgets/treeTable/jquery.treetable.js" type="text/javascript"></script>
		
		<script type="text/javascript">
            $(document).ready(function() {


                $(".th_medNav li:eq(0)").addClass("cur");
            });
	$(function() {
		$("#content").treetable({ expandable: true });
	});

	function expandAll(){
		$("#content").treetable("expandAll");
	}

	function collapseAll(){
		$("#content").treetable("collapseAll");
	}

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
        <div id="main" class="tc_inDataArea">
        	<form action="<%=basePath %>report/showReportStat.action" method="post">
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
		                	<!-- <input type="button" name="" id="" value="导出" class="input_large" > -->
	                	</div>
	               	</div>
	                </div>
	            </div>
			</form>
	
	
		<div align="left">
			<button onclick="expandAll();">展开</button>
			<button onclick="collapseAll();">收缩</button>
		</div>
		<table id="content" style="font-size: 11px;width: 100%;border: 1px solid #C8C8C8;">
			<thead>
				<tr class="trHead" style="font-size: 11px;width: 100%;">
					<td>大类</td>
					<td>中类</td>
					<td>小类</td>
					<td>大类代码</td>
					<td>中类代码</td>
					<td>小类代码</td>
					<td>项数</td>
				</tr>
			</thead>
			<s:if test="#request.list!=null && #request.list.size()>0">
				<s:iterator id="stat" value="#request.list" status="st">
					<s:if test="#stat.subTypeCode==null || #stat.subTypeCode==''">
						<tr data-tt-id="<s:property value='#stat.code'/>" style="height: 30px;">
							<td width="10%;" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.name"/></td>
							<td width="10%;" style="border-left: 1px dotted #BEBEBE;padding: 5px;"></td>
							<td width="15%;" style="border-left: 1px dotted #BEBEBE;padding: 5px;"></td>
					</s:if>
					<s:elseif test="#stat.detailTypeCode==null || #stat.detailTypeCode==''">
						<tr data-tt-id="<s:property value='#stat.code'/>" data-tt-parent-id="<s:property value='#stat.pcode'/>" style="height: 30px;">
							<td style="border-left: 1px dotted #BEBEBE;padding: 5px;"></td>
							<td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.name"/></td>
							<td style="border-left: 1px dotted #BEBEBE;padding: 5px;"></td>
					</s:elseif>
					<s:else>
						<tr data-tt-id="<s:property value='#stat.code'/>" data-tt-parent-id="<s:property value='#stat.pcode'/>" style="height: 30px;"> 
							<td style="border-left: 1px dotted #BEBEBE;padding: 5px;"></td>
							<td style="border-left: 1px dotted #BEBEBE;padding: 5px;"></td>
							<td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.name"/></td>
					</s:else>
						<td width="10%;" style="text-align: center;border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.mainTypeCode"/></td>
						<td width="10%;" style="text-align: center;border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.subTypeCode"/></td>
						<td width="10%;" style="text-align: center;border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.detailTypeCode"/></td>
						<td width="10%;" style="text-align: right;border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.count"/></td>
					</tr>
				</s:iterator>
			</s:if>
			
		</table>
        </div>
    </div>
    <div class="tFooter"></div>	
	
		
	</body>
</html>