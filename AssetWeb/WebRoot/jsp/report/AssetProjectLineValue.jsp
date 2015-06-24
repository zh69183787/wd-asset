<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
</head>

<script type="text/javascript">
    $(document).ready(function() {


        $(".th_medNav li:eq(5)").addClass("cur");
    });
    $(function () {
        $("tr[id='dataArea']:even").attr("style", "background: none repeat scroll 0 0 #F0F0F0; height:30px; padding: 2px;");
    });
</script>

<title>轨道交通建设项目资产价值统计表</title>
</head>
<body class="body"  style="font-family:'Microsoft YaHei';">
<div class="container"><!-- Everything started here -->
    <div class="tHeader">
        <div class="th_inMarginWrap">
            <div class="th_Logo"></div>
            <div class="th_minNav" id="firstMenu">
                <a href="<%=basePath %>jsp/homepage.jsp" class="minLink"><span><i>主题管理</i></span></a>
                <a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink"><span><i>资产台账</i></span></a>
                <a href="#" class="minLink cur"
                   onclick="setMenu(2);turnToDiv('<%=basePath%>report/showReportStat.action')"><span><i>统计报表</i></span></a>
                <a href="#" class="minLink"><span><i>决策分析</i></span></a>
                <a href="<%=basePath%>jsp/basecode/assetTypeList.jsp" class="minLink"><span><i>基础管理</i></span></a>
                <a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink"><span><i>盘点任务</i></span></a>
            </div>
            <jsp:include page="/jsp/reportNavigation.jsp"></jsp:include>

        </div>
    </div>
    <div class="tc_inDataArea" id="main">
        <form action="<%=basePath %>report/showProjectLineValue.action" method="post">
            <div class="search_1 p8">

                <input type="hidden" value="${pageNo }" name="pageNo" id="pageNo"/>

                <div class="clearfix">
                    <div class="fl w30p clearfix">
                        <div class="fl p5 w30p"><label for="textfield">年度</label></div>
                        <div class="fl p5 w65p">
                            <select name="year">
                                <s:iterator var="yearnow" value="#request.yearList">
                                    <s:if test="#request.year==#request.yearnow">
                                        <option value="<s:property value="#request.yearnow"/>" selected="selected">
                                            <s:property value="#request.yearnow"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="#request.yearnow"/>"><s:property
                                                value="#request.yearnow"/></option>
                                    </s:else>
                                </s:iterator>
                            </select>
                        </div>
                    </div>

                    <div class="fl w40p clearfix">
                        <div class="fl p5 w100p">
                            <input type="submit" value="查询" class="input_large">
                            <!--  <input type="button" name="assetName" id="assetName" value="导出" class="input_large" >-->
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <table style="font-size: 11px;width: 100%;border: 1px solid #C8C8C8;background-color: #FFFFFF;">
            <thead>
            <tr class="trHead">
                <td class="t_c" style="width:40px;">序号</td>
                <td class="t_c" style="width:220px;">项目名称</td>
                <td class="t_c" style="width:65px;">资产项数</td>
                <td class="t_c" style="width:140px;">项目决算价(万元)<br/>1</td>
				<td class="t_c" style="width:140px;">增加投资（万元）<br/>2</td>

                
                <td class="t_c" style="width:140px;">预留预估（万元）<br/>3</td>
                <td class="t_c" style="width:140px;">增值税及其他（万元）<br/>4</td>
                <td class="t_c" style="width:140px;">应计资产清册价值<br/>1+2-3-4</td>  
                <td class="t_c" style="width:140px;">流动资产（万元）<br/>5</td>
                <td class="t_c" style="width:140px;">固定资产价值（万元）<br/>6</td>
                <td class="t_c" style="width:140px;">总计（万元）<br/>5+6</td>
            </tr>
            </thead>
            <s:if test="#request.list!=null && #request.list.size()>0">
                <s:iterator id="stat" value="#request.list" status="st">
                    <tr id="dataArea" style="height:30px; padding: 2px;">
                        <td  class="t_c" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="#st.index+1"/></td>
                                
<!--                                 项目名称 -->
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;">
                            <s:if test="#stat.shortName==null || #stat.shortName==''">
                                <s:property value="#stat.projectName"/>
                            </s:if>
                            <s:else>
                                <s:property value="#stat.shortName"/>
                            </s:else>
                        </td>
                        
<!--                         资产项数 -->    
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="#stat.count "/></td>
                                
<!--                                 项目决算价(元) -->
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="%{getFormattedMoney(#stat.finalPrice)}"/></td>
                                
                                <!--                                  增加投资(万元) -->
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="%{getFormattedMoney(#stat.investAdded)}"/></td>
                                


<!--                                  预留预估(万元) -->
                        <td  class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="%{getFormattedMoney(#stat.reserveEstimation)}"/></td>
<!--                                 增值税及其他(万元) -->
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="%{getFormattedMoney(#stat.tax)}"/></td>
                                
<!--                                 应计资产清册价值 -->
                        <s:set var="val" value="%{getFormattedMoney(#stat.finalPrice+#stat.investAdded-#stat.reserveEstimation-#stat.tax)}"/>
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="#val"/></td>
                                
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="%{getFormattedMoney(#stat.spareParts)}"/></td>
                                                  
                         					<!-- 原值（初始）(万元) -->
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property
                                value="%{getFormattedMoney(#stat.originalValue)}"/></td>                                       
<!--                                  总计 -->
                        <s:set var="total" value="%{getFormattedMoney(#stat.spareParts+#stat.originalValue)}"/>
                        <td class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;
                        	<s:if test="#val != #total">color: red;</s:if>"><s:property
                                value="#total"/></td>
                        
                    </tr>
                </s:iterator>
            </s:if>

        </table>
    </div>
</div>
<div class="tFooter"></div>


</body>
</html>