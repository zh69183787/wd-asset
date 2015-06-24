<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<%-- 	<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
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
 --%>
  </head>
  
  <body style="font-family:'Microsoft YaHei';">
    <ul class="th_medNav">
		<li><span><a href="<%=basePath%>jsp/asset/assetList.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产台帐</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/assetLandList.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>土地资源</i></a></span></li>
		<li><span><a href="<%=basePath%>houseAsset/inquery.action" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>房屋资源</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/sparePartsList.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>备品备件</i></a></span></li>
		<li><span><a href="#" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产购置</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/recordAsset.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产大修更新</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/damageAsset.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产报废</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/borrowAsset.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产租/借用</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/allocateAsset.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产调拨</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/stopAsset.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产封存/启用</i></a></span></li>
		<li><span><a href="<%=basePath%>jsp/asset/disableAsset.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产停用</i></a></span></li>	
	</ul>
  </body>
</html>
