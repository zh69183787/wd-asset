<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>索引页面</title>
<script type="text/javascript">
	window.location.href = 'jsp/jeecms/index.jsp';
</script>

</head>
<body style="font-family:'Microsoft YaHei';">
	<ul>
		<li>
			<a href="jsp/asset/assetList.jsp" target="_blank">资产列表</a>&nbsp;&nbsp;&nbsp;jsp/asset/assetList.jsp
		</li>
		<li>
			<a href="report/showReportStat.action" target="_blank">资产实物汇总统计</a>&nbsp;&nbsp;&nbsp;report/showReportStat.action
		</li>
		<li>
			<a href="report/showReportMain.action" target="_blank">资产类别价值统计</a>&nbsp;&nbsp;&nbsp;report/showReportMain.action
		</li>
		<li>
			<a href="report/showLineAssetValue.action" target="_blank">轨道线路价值统计</a>&nbsp;&nbsp;&nbsp;report/showLineAssetValue.action
		</li>
		
		<li>
			<a href="report/showUseOrganization.action" target="_blank">资产使用单位价值统计</a>&nbsp;&nbsp;&nbsp;report/showUseOrganization.action
		</li>
		
		<li>
			<a href="report/showProjectLineValue.action" target="_blank">轨道交通建设项目资产价值统计表</a>&nbsp;&nbsp;&nbsp;report/showProjectLineValue.action
		</li>
	</ul>
</body>
</html>