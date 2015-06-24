﻿<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"/>
<title>资产管理首页</title>
<link rel="stylesheet" href="<%=basePath%>jsp/jeecms/css/formalize.css"/>
<link rel="stylesheet" href="<%=basePath%>jsp/jeecms/css/page.css"/>
<link rel="stylesheet" href="<%=basePath%>jsp/jeecms/css/default/imgs.css"/>
<link rel="stylesheet" href="<%=basePath%>jsp/jeecms/css/reset.css"/>
<link rel="stylesheet" href="<%=basePath%>css/flick/jquery-ui.css"/>
<script src="<%=basePath%>jsp/jeecms/js/html5.js"></script>
<script src="<%=basePath%>jsp/jeecms/js/jeecms.js"></script>
<script src="<%=basePath%>js/jquery-1.9.0.min.js"></script>
<script src="<%=basePath%>js/jquery-ui-1.9.2.min.js"></script>
<script src="<%=basePath%>widgets/highcharts/highcharts.js" type="text/javascript"></script>
<script src="<%=basePath%>js/asset/index_chart.js" type="text/javascript"></script>
<%
    Properties properties = new Properties();
    String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
    FileInputStream fis = new FileInputStream(configPath);
    properties.load(fis);
    String assetCenter = properties.getProperty("assetCenter");
    String dataShare = properties.getProperty("dataShare");
    String research = properties.getProperty("research");
    String summary = properties.getProperty("summary");
    String specification = properties.getProperty("specification");
    String centerInfo = properties.getProperty("centerInfo");
    String siteLink = properties.getProperty("siteLink");
    String jeecmsUrl = properties.getProperty("urlCms");
%>
<script>


    $(function () {
       show="<%=basePath%>report/showProjectLineValue.action";
        $("#newsCenter").width($(".news").width() - $(".focusPic").width() - 40);
        $(window).resize(function () {
            $("#newsCenter").width($(".news").width() - $(".focusPic").width() - 40);
        })


        $.ajax({
            url:'<%=basePath%>task/getCompleterate.action',
            type:'post',
            dataType:'json',
            error:function(){

            },
            success:function(data){

                $("#progressTask").html(data.taskRate>=1?data.taskRate.toFixed(0)+"%":data.taskRate.toFixed(2)+"%");
                $("#taskProgressbar").progressbar({
                    value: data.taskRate
                });

                $("#projectRate").html(data.projectRate>=1?data.projectRate.toFixed(0)+"%":data.projectRate.toFixed(2)+"%");

                $("#priceProgressbar").progressbar({
                    value: data.projectRate
                });


            }
        });


        $("#sk").find("a").click(function(){
            $(this).parents("ul").find("li").removeClass("selected");
            $(this).parent().addClass("selected");
            $("#plan").load('<%=basePath%>workPlan/inquery.action?deptId='+ ($(this).parent().index()+1));//换成action地址
        });
        $("#sk  a:eq(0)").click();

        $("#kpi").find("a").click(function () {
            $(this).parents("ul").find("li").removeClass("selected");
            $(this).parent().addClass("selected");
            $(".con[id^=chart-tab-content]").hide();

            var index = $(this).parent().index();
            var $tabContent = $("#chart-tab-content" + index);

            if (!$tabContent.hasClass("loaded")) {

                if(index == 0){
                    showAssetTransferChartAndKpi("<%=basePath%>report/showAssetTransferChartAndKpi.action");
                }
                if (index == 1) {//queryDwOverHaulhaulBudgetYear
                    showAssetUpdateChartAndKpi("<%=basePath%>report/showAssetUpdateChartAndKpi.action?detailTypeCodeId=516");
                }
                if(index == 2){
                	showAssetDamageChartAndKpi("<%=basePath%>report/showAssetDamageChartAndKpi.action");    
                }
                if(index == 3){
                	showAssetTaskChartAndKpi("<%=basePath%>report/showAssetTaskChartAndKpi.action");
                }
				if(index == 4){
					showAssetLandHouseChartAndKpi("<%=basePath%>report/showAssetLandHouseChartAndKpi.action");
				}
                
            }
            $tabContent.show();
        });
        $("#chart-tab0").click();
    });
    function showAuto() {
        var listTmp = $("#play_list li").filter(function () {
            return $(this).css('display') != 'none';
        });
        var infoTmp = $("#play_info li").filter(function () {
            return $(this).css('display') != 'none';
        });
        var textTmp = $("#play_text li.current");
        //alert(listTmp.html());
        $("#play_list li").hide();
        $("#play_info li").hide();
        $("#play_text li").removeClass("current");
        if (listTmp.next().length == 0) {
            $("#play_list li").eq(0).show();
            $("#play_info li").eq(0).show();
            $("#play_text li").eq(0).addClass("current");
        } else {
            listTmp.fadeOut(500).next().fadeIn(1000);
            infoTmp.fadeOut(500).next().fadeIn(1000);
            textTmp.next().addClass("current");
        }
    }
    setbasePath('<%=basePath%>');


    getPicNews('<%=jeecmsUrl%>', '<%=assetCenter%>');
    getHeadNews(<%=assetCenter%>);			//最上面的java代码已实现
    getTopNews(<%=assetCenter%>);


    //1638 学习资料
    getLatestNews(<%=dataShare%>, 'dataShare', 6);//频道id，显示区域的id（页面上控件的id），显示记录数
    getLatestNews(<%=specification%>, 'specification', 3);
    getLatestNews(<%=summary%>, 'summary', 3);
    getLatestNews(<%=research%>, 'research', 3);

</script>

<style>
    .ui-progressbar {
        position: relative;
    }

    .progress-label {
        position: absolute;
        left: 44%;
        top: 4px;
        font-weight: bold;
        text-shadow: 1px 1px 0 #fff;
    }

    .table_5, .table_6 {
        margin-top: -9px;
        width: 101%;
    }

    .table_5 .xh {
        width: 30px;
    }

    .table_5 th {
        background: #c9d6eb;
        text-align: center;
        font-weight: bold;
        border-right: #fff 1px solid;
    }

    .table_1 td, .table_2 td, .table_5 td, .table_5 th, .table_6 td {
        padding: 3px 5px;
    }

    .table_5 tr td {
        border-right: #fff 1px solid;
        border-bottom: #c9d6eb 1px solid;
    }

    .panel_8 .tit span {
        color: black;
    }
    
    
    #chart2{
    	float:left;
    }
    .main{
    	width:1347px;
    }
   
    .right_main mb10{
    	width:1010px;
    }
   
</style>
</head>

<body class="mw1002" style="overflow-x:auto;font-family:'Microsoft YaHei';">
<div class="main">
<!--Ctrl-->
<div class="ctrl clearfix">
    <div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
    <div class="posi fl">
        <ul>
            <li><a href="#">资产中心</a></li>
            <li class="fin">首页</li>
        </ul>
    </div>
</div>
<!--Ctrl End-->
<div class="clearfix pt45">
<div class="right_main mb10">
    <!--News-->
    <div class="news clearfix">
        <div class="news_r">
            <div class="news_l">
                <!--focusPic-->
                <div class="focusPic fl" id="play">
                    <ul class="fp_list" id="play_list"></ul>
                    <ul class="word" id="play_info"></ul>
                    <ul class="scrollnav" id="play_text"></ul>
                </div>

                <!--focusPic End-->
                <!--Txt News-->
                <div class="fl" id="newsCenter">
                    <hgroup class="clearfix mb10">
                        <h2 class="fl">资产中心</h2>
                        <h6 class="fl">News</h6>

                        <p class="t_r"><a href="<%=basePath%>jeecms/findNewsByPage.action?channelId=<%=assetCenter%>"
                                          class="more_2" target="_blank">更多</a></p>
                    </hgroup>
                    <section>
                        <a href="#" target="_blank" title="">
                            <h5></h5>

                            <p></p>
                        </a>
                    </section>
                    <ul class="txt_list">
                        <li><a href="#"></a><span></span></li>
                    </ul>
                </div>

                <!--Txt News End-->
            </div>
        </div>
    </div>
    <!--News End-->
    <!--Panel_3-->
    <div class="panel_3 mb10">
        <header class="clearfix">
            <h5 class="fl file">工作计划</h5>

            <div id="tabs" class="fr tabs_1 clearfix">
                <ul class="fl" id="sk">
                    <li class="selected"><a href="#tabs-1"><span>资产中心</span></a></li>
                    <li><a href="#tabs-2"><span>项目公司</span></a></li>
                    <li><a href="#tabs-2"><span>维保公司</span></a></li>
                    <li><a href="#tabs-3"><span>运营单位</span></a></li>
                    <li><a href="#tabs-4"><span>其他直属单位</span></a></li>
                </ul>
            </div>
        </header>
        <div class="con" style="height: 160px;">
            <div id="plan" style="height: 135px;"></div>
            <div class="clearfix">
                <div>
                    <div style="float:left;line-height: 30px;padding-right: 5px;margin-left: 10px;">盘点任务进展:</div>
                    <div id="taskProgressbar" style="width:200px;float:left;">
                        <div class="progress-label" id="progressTask">30%</div>
                    </div>
                    <div style="float:left;line-height: 30px;padding-right: 5px;padding-left:20px;">价值任务进展:</div>
                    <div id="priceProgressbar" style="width:200px;float:left;">
                        <div class="progress-label" id="projectRate">40%</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Panel_3 End-->
    <div class="panel_4 mb10 panel_8">
        <header>
            <div class="tit">
                <div class="bg clearfix">
                    <h5 class="fl stats">重点专项</h5>

                    <div class="fr tabs_1 clearfix">
                        <ul class="fl" id="kpi">
                            <li class="selected"><a href="#tabs-1" id="chart-tab0"><span>资产移交</span></a></li>
                            <li><a href="#tabs-2" id="chart-tab1"><span>资产大修更新</span></a></li>
                            <li><a href="#tabs-3"><span>资产报废</span></a></li>
                            <li><a href="#tabs-4"><span>资产盘点</span></a></li>
                            <li><a href="#tabs-4"><span>土地/房屋资源</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <div class="con" id="chart-tab-content0">

             <%@include file="/jsp/chart/asset_transfer.jsp"%>
        </div>
        <div class="con" id="chart-tab-content1">
            <%@include file="/jsp/chart/asset_update.jsp"%>
        </div>
        <div class="con" id="chart-tab-content2">
            <%@include file="/jsp/chart/asset_damage.jsp"%>
        </div>
        <div class="con" id="chart-tab-content3">
            <%@include file="/jsp/chart/asset_task.jsp"%>
        </div>
        <div class="con" id="chart-tab-content4">
            <%@include file="/jsp/chart/asset_land_house.jsp"%>
        </div>
        
    </div>
    <!--Panel_3 End-->
</div>
<!--Aside-->
<aside>
    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup>
                    <h3>业务办理</h3>
                    <a href="#" class="more_1">更 多</a>
                </hgroup>
                <div class="con">
                    <ul class="xx clearfix mb10">
                        <li>
                            <a>
                                <dl>
                                    <dt class="d5"></dt>
                                    <dd>中心简介</dd>
                                </dl>
                            </a>
                        </li>
                        <li>
                            <a>
                                <dl>
                                    <dt class="d4"></dt>
                                    <dd><a href="organization.jsp">组织机构</a></dd>
                                </dl>
                            </a>
                        </li>
                        <li>
                            <a>
                                <dl>
                                    <dt class="d1"></dt>
                                    <dd>大事记</dd>
                                </dl>
                            </a>
                        </li>
                        <li>
                            <a>
                                <dl>
                                    <dt class="d10"></dt>
                                    <dd>通讯录</dd>
                                </dl>
                            </a>
                        </li>
                    </ul>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3" style="height: 198px;">
                <hgroup>
                    <h3>制度规范</h3>
                    <%--<h6>Topic</h6>--%>
                    <a href="<%=basePath%>jeecms/findNewsByPage.action?channelId=<%=specification%>" class="more_1">更
                        多</a>
                </hgroup>
                <ul class="list" id="specification">
                </ul>
            </div>
        </div>
    </div>
    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3" style="height: 198px;">
                <hgroup>
                    <h3>研究与发展</h3>
                    <a href="<%=basePath%>jeecms/findNewsByPage.action?channelId=<%=research%>" class="more_1">更 多</a>
                </hgroup>
                <ul class="list" id="research">

                </ul>
            </div>
        </div>
    </div>
    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3" style="height: 198px;">
                <hgroup>
                    <h3>年度总结</h3>
                    <a href="<%=basePath%>jeecms/findNewsByPage.action?channelId=<%=summary%>" class="more_1">更 多</a>
                </hgroup>
                <ul class="list" id="summary">
                </ul>
            </div>
        </div>
    </div>
    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup>
                    <h3>站点链接</h3>
                    <%--<a href="#" class="more_1"></a>--%>
                </hgroup>
                <ul class="list">
                    <li><a href="<%=basePath %>jsp/homepage.jsp">资产管理系统</a></li>
                    <li><a href="http://10.35.91.3/base/login.do">EAM系统</a></li>
                    <%--<li><a href="#">Q&A</a></li>
                --%></ul>
            </div>
        </div>
    </div>
</aside>
<!--Aside End-->
</div>
</div>
</body>
</html>
