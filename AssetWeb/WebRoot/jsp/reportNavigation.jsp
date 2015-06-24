<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
</head>
<body style="font-family:'Microsoft YaHei';">
            <ul class="th_medNav" id="reportArea">
                <li ><span><a href="<%=basePath%>report/showReportStat.action" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产实物汇总统计</i></a></span></li>
                <li><span><a href="<%=basePath %>report/showReportMain.action"  class="navLink"  onclick="changeClass(this);"><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产类别价值统计</i></a></span></li>
                <li><span><a href="<%=basePath %>report/showLineAssetValue.action" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>运营线路价值统计</i></a></span></li>
                <li><span><a href="<%=basePath %>report/showUseOrganization.action" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>使用单位价值统计</i></a></span></li>
                <li><span><a href="<%=basePath %>report/showOwnerOrganization.action" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>权属单位价值统计</i></a></span></li>
                <li><span><a href="<%=basePath %>report/showProjectLineValue.action" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>建设项目价值统计</i></a></span></li>
                <li><span><a href="<%=basePath %>report/showDwOverhaulLine.action" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>大修/更新项目预算统计表</i></a></span></li>
                <li><span><a href="<%=basePath %>assetLand/findReport.action" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>土地报表</i></a></span></li>
                <li><span><a href="<%=basePath %>report/houseAsset.action" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>房屋报表</i></a></span></li>
            </ul>


   </body>
 </html>