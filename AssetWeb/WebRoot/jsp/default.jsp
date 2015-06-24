<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>

        <!-- Basic Page Needs
  ================================================== -->
        <meta charset="utf-8">
        <title>资产管理系统</title>
        <meta name="description" content="">
        <meta name="author" content="AuthorName">
        <meta name="Keywords" content="website">

        <!-- Mobile Specific Metas
  ================================================== -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

        <!-- CSS
  ================================================== -->
		<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">

        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicons
        ================================================== -->
		<link rel="shortcut icon" href="favicon.ico" >
        <link rel="apple-touch-icon" href="<%=basePath %>css/default/images/apple-touch-icon.png">
        <link rel="apple-touch-icon" sizes="72x72" href="<%=basePath %>css/default/images/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="<%=basePath %>css/default/images/apple-touch-icon-114x114.png">
		<!-- Javascript
        ================================================== -->
		<script type="text/javascript" src="<%=basePath %>js/jay.js"></script>
		
		
	<!-- ours css and js -->
	<link rel="stylesheet" href="<%=basePath %>css/formalize.css">
	<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>widgets/jtable/css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>widgets/jtable/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
	
	<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>widgets/jtable/jquery.jtable.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>widgets/highcharts/highcharts.js" type="text/javascript"></script>
	
	
	<style type="text/css">
		.w30p {
		    width: 25%;
		}
		.w50p {
		    width: 50%;
		}
		.w49p {
		    width: 49%;
		}
		.w120 {
		    width: 120px;
		}
		.bar {
		    height: 27px;
		    line-height: 27px;
		    padding: 6px 8px;
		    border-top: 1px solid #BFCFDA;
		    border-bottom: 1px solid #BFCFDA;
		    background-image: url("../css/default/images/bar_bg.png");
    		background-repeat: repeat-x;
		}
		.b_bor {
		    border-bottom: 1px solid #D1D1D1;
		}
		.p5 {
		    padding: 5px;
		}
		.p8 {
		    padding: 8px;
		}
		.fl {
		    float: left;
		}
		.t_c {
		    text-align: center;
		}
		.t_r {
		    text-align: right;
		}
		.clearfix:before, .clearfix:after {
		    content: ".";
		    display: block;
		    font-size: 0;
		    height: 0;
		    line-height: 0;
		    overflow: hidden;
		    visibility: hidden;
		    width: 0;
		}
		.clearfix:after {
		    clear: both;
		}
		a {
		    color: #605F5F;
		    text-decoration: none;
		}
		
		.lable_bg {
		    background: none repeat scroll 0 0 #F7F9FC;
		}
		.table_1, #content, .tree {
		    border-top: 1px solid #BFCFDA;
		}
		.table_1, .tree, #content, .bor {
		    background-color: #FFFFFF;
		}
		.table_1 thead td {
		    border-left: 1px solid #F6F7FA;
		    border-right: 1px solid #BFCFDA;
		    box-shadow: 0 2px 0 #ECECEC;
		    color: #595F81;
		    text-shadow: 0 1px 0 #FFFFFF;
		}
		h4 {
		    font-size: 15px;
		}
	</style>
	
	<script type="text/javascript">
        $(document).ready(function () {
        	//turnToDiv('</%=basePath%>jsp/asset/assetList.jsp');
        	
        	//一级菜单样式
        	$("#firstMenu a").each(function(index){
				$(this).bind("click",function(){
					$(this).siblings().removeClass("cur");
					$(this).addClass("cur");
					$("#firstMenu").siblings("ul").hide();
					$("#firstMenu").siblings("ul:eq("+index+")").show()
				});
            });
			//二级菜单样式
			$("#firstMenu ").siblings("ul").find("a").each(function(){
				$(this).bind("click",function(){
					$(this).parents("li").siblings().removeClass("cur");
					$(this).parents("li").addClass("cur");
	            });
			});
        	
        	<%
        		String type = request.getParameter("type");
        		if(type!=null && !"".equals(request.getParameter(type))){
					%>
						$("#firstMenu a:eq(2)").click();
						$("#reportArea").find("a:eq("+<%=type%>+")").click();
					<%        			
        		}else{
        		%>
        			turnToDiv('body.jsp');
        		<%
        		}
        	%>
        	

			
            
        });
		
		function turnToDiv(url){
			$.ajax({
			  url: url,
			  cache: false,
			  success: function(html){
			    $("#main").html(html);
			  }
			});
		}

		function setMenu(num){
			$('#firstMenu').siblings('ul:eq('+num+')').find("li:eq(0)").addClass("cur");
			$('#firstMenu').siblings('ul:eq('+num+')').find("li:gt(0)").removeClass("cur");
		}
    </script>
</head>
<body style="font-family:'Microsoft YaHei';">
    <div class="container"><!-- Everything started here -->
        <div class="tHeader">
            <div class="th_inMarginWrap">
            	<div class="th_Logo"></div>
            	<div class="th_minNav" id="firstMenu">
					<a href="#" class="minLink cur" onclick="turnToDiv('body.jsp')"><span><i>主页</i></span></a>
					<a href="#" class="minLink" onclick="setMenu(1);turnToDiv('<%=basePath%>jsp/asset/assetList.jsp');"><span><i>资产台账</i></span></a>
					<a href="#" class="minLink" onclick="setMenu(2);turnToDiv('<%=basePath%>report/showReportStat.action')"><span><i>报表</i></span></a>
					<a href="#" class="minLink"><span><i>其它</i></span></a>
				</div>
				<ul class="th_medNav" >
					<li class="cur"><span><a href="#" class="navLink" onclick="turnToDiv('body.jsp')"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产首页</i></a></span></li>
				</ul>
            	<ul class="th_medNav" style="display: none;">
					<li class="cur"><span><a href="#" class="navLink" onclick="turnToDiv('<%=basePath%>jsp/asset/assetList.jsp')"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产管理</i></a></span></li>
					<li><span><a href="#" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产价值</i></a></span></li>
					<li><span><a href="#" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>单位资源</i></a></span></li>
					<li><span><a href="#" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产分布</i></a></span></li>
				</ul>
				<ul class="th_medNav" style="display: none;" id="reportArea">
					<li class="cur"><span><a href="#" class="navLink" onclick="turnToDiv('<%=basePath%>report/showReportStat.action')"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产实物汇总统计</i></a></span></li>
					<li><span><a href="#" class="navLink" onclick="turnToDiv('<%=basePath %>report/showReportMain.action')"><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产类别价值统计</i></a></span></li>
					<li><span><a href="#" class="navLink" onclick="turnToDiv('<%=basePath %>report/showLineAssetValue.action')"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>轨道线路价值统计</i></a></span></li>
					<li><span><a href="#" class="navLink" onclick="turnToDiv('<%=basePath %>report/showUseOrganization.action')"><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产使用单位价值统计</i></a></span></li>
					<li><span><a href="#" class="navLink" onclick="turnToDiv('<%=basePath %>report/showOwnerOrganization.action')"><i><img src="<%=basePath %>css/default/images/icons_2.gif" alt=""><br>资产权属单位价值统计</i></a></span></li>
					<li><span><a href="#" class="navLink" onclick="turnToDiv('<%=basePath %>report/showProjectLineValue.action')" id="projectValue"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>轨道交通建设项目资产价值统计表</i></a></span></li>
				</ul>
				
            </div>
        </div>
        <div class="" id="main">
			
        </div>
    </div>
    <div class="tFooter"></div>
</body>
</html>
