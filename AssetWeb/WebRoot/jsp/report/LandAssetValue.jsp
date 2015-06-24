<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=basePath %>widgets/treeTable/jquery.treetable.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>widgets/treeTable/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css">
    <title>土地报表</title>
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


            $(".th_medNav li:eq(7)").addClass("cur");
        });
        $(function(){
            $("tr[id='dataArea']:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
        });
        function excelReport(){
        	location.href = '<%=basePath %>report/excelLandAsset.action';
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

                    <div class="fl w40p clearfix">
                        <div class="fl p5 w100p">
                            <input type="button" value="导出" class="input_large"  onclick="excelReport();">
                            <!-- <input type="button" name="" id="" value="导出" class="input_large" > -->
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <table id="content" style="font-size: 11px;width: 100%;border: 1px solid #C8C8C8;">
            <thead>
            <tr class="trHead" style="font-size: 11px;width: 100%;">
                <td>序号</td>
                <td>线路</td>
                <td>建设类项目名称</td>
                <td>建筑占地面积（平方米）</td>
                <td>土地总面积（平方米）</td>
                <td>其中地面征地（平方米）</td>
                <td>其中地下征地或征用道路（平方米）</td>
                <td>其中带征地（平方米）</td>
                <td>征地动迁总费用（元）</td>
                <td>征地动迁费用（元）</td>
                <td>带征地费用（元）</td>
            </tr>
            </thead>
            <s:if test="#request.list!=null && #request.list.size()>0">
                <s:iterator id="stat" value="#request.list" status="st">
                    <tr id="dataArea" style="height:30px; padding: 2px;">
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#st.index+1"/></td>
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.line"/></td>
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.builderProject"/></td>
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.totalBuildArea"/></td>
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.totalLandArea"/></td>
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.totalLandRequisitionArea"/></td>
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.undergroundRequisitionArea"/></td>
                        <td style="border-left: 1px dotted #b2beb6;padding: 5px;"><s:property value="#stat.totalInclandRequisitionArea"/></td>
                        <td style="border-left: 1px dotted #b2beb6;padding: 5px;"><s:property value="#stat.totalLandTotalFee"/></td><!-- new -->
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.totalLandRequisitionTotalfee"/></td>
                        <td style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.totalInlandRequisitionTotalfee"/></td>
                    </tr>
                </s:iterator>
            </s:if>

        </table>
    </div>
</div>
<div class="tFooter"></div>


</body>
</html>