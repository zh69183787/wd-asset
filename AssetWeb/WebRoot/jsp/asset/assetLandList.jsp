<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>土地资源台账</title>

<!-- CSS & js -->
<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
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

<script type="text/javascript">
$(document).ready(function () {
	showBigLine();
    $(".th_medNav li:eq(1)").addClass("cur");
    showLine();
  	
    initJtable();
});


function initJtable(){
    $('#content').jtable({
        title: '土地资源台账',
        paging: true,
        pageSize: 10, 
        sorting: false, 
        messages: {
            pagingInfo:'显示 {0}-{1}条   总共{2}条',
            gotoPageLabel: '跳转到',
            pageSizeChangeLabel: '每页显示',
            loadingMessage: '数据加载中...',
            editRecord: '详细',
            noDataAvailable: '没有数据！'
        },
        actions: {
             listAction:'<%=basePath%>assetLand/inquery.action'
        },
        fields: {
            id: {
            	title:'序号',
            	width:'3%',
                key: true,
                display: function (data) {
                    return data.record.no;   
                }
            },
            lineCodeId:{
                title:'线路',
                width:'5%'
            },
            builderProject:{
                title:'建设类项目名称',
                width:'8%'
            },
            landLocation:{
                title:'土地坐落',
                width:'10%'
            },
            project: {
                title: '项目名称',
                width: '15%'
            },
            approveNo: {
                title: '用地批准文号',
                width: '10%'
            },
            landStatus:{
                title:'土地性质',
                width:'5%'
            },
            landPlaning: {
                title: '规划土地用途',
                width: '8%'
            },
            buildArea: {
                title: '建筑占地面积(平方米)',
                width: '12%'
            },
            landTotalArea:{
                title:'土地总面积(平方米)',
                width:'11%'
            },
            options:{
                title:'操作',
                width:'3%',
                sorting: false,
                display: function (data) {
                    var btn = "<a href='<%=basePath%>assetLand/showAssetLandView.action?id="+data.record.landassetId+"' target='_blank')>查看</a>";
                    return btn;
                }
            }
        }
    });
    $('#content').jtable('load');
    $('.jtable').attr(' OVERFLOW-X','scroll');
}
function numberCheck(param){
	if($(param).val()!=''){
		var res = /^[0-9]*(.[0-9]+)?$/;
		if(!res.test($(param).val())){
			alert("只能输入数字!");
			$(param).val("");
			$(param).focus();
			return ;
		}
	}
}

//表单查询
function query(){
    var project = $.trim($("#project").val());
    if(project.length > 100){
    	alert("输入的项目名称不得大于100字!");
    	return ;
    }
   
    var lineCodeId = $("#bigLineSelect").val();
    var builderProject = $("#assetLine").val();
    var landStatus = $("#landStatus").val();
    var landPlaning = $("#landPlaning").val();
    var landAreaStar=$.trim($("#landAreaStar").val());
    var landAreaEnd=$.trim($("#landAreaEnd").val());
     if(parseFloat(landAreaStar) > parseFloat(landAreaEnd)){
    	alert("土地面积范围起始值不可以大于终止值，请重新填写!");
    	$("#landAreaStar").val("");
    	$("#landAreaEnd").val("");
    	return ;
    }
    
    $('#content').jtable('load',{
        project_like_filter:project,
        builderProject_equal_filter:builderProject,
        lineCodeId_equal_filter:lineCodeId,
        landStatus_equal_filter:landStatus,
        landPlaning_like_filter:landPlaning,
        landTotalArea_before_filter:landAreaEnd,
        landTotalArea_after_filter:landAreaStar,
        publish_equal_filter:1
    });
}

//清除搜索框
function clearForm(){
    $("#project").val("");
    $("#landAreaStar").val("");
    $("#landAreaEnd").val("");
    $("#assetLine option:eq(0)").attr("selected",true);
    $("#landStatus").find("option:first").attr("selected",true);
    $("#landPlaning").find("option:first").attr("selected",true);



}

//显示线路(小线)
function showLine(){
    $.ajax({
        url:'<%=basePath%>project/findShortName.action',
        type:'post',
        dataType:'json',
        error:function(){

        },
        success:function(data){
            if(data!=null){
                var addHtml = "";
                for(var i=0; i<data.length; i++){
                    if(data[i].lineCodeId!=0){
                        addHtml += "<option value='"+data[i].shortName+"'>"+data[i].shortName+"</option>";                       
                    }
                }
                $("#assetLine").append(addHtml);
            }
        }
    });
}

//显示线路(大线)   
function showBigLine(){
    $.ajax({
        url:'<%=basePath%>assetLine/getAllLine.action',
        type:'post',
        dataType:'json',
        error:function(){

        },
        success:function(data){
            if(data!=null){
                var addHtml = "";
                for(var i=0; i<data.length; i++){
                    if(data[i].id!=0){
                        addHtml += "<option value='"+data[i].id+"'>"+data[i].shortName+"</option>";  
                    }
                }
                $("#bigLineSelect").append(addHtml);
            }
        }
    });
}



    /**
    *导出数据
     */

    function exportExcel(){
    	var project = $.trim($("#project").val());
	    if(project.length > 100){
	    	alert("输入的项目名称不得大于100字!");
	    	return ;
	    }
	   
	    var lineCodeId = $("#bigLineSelect").val();
	    var builderProject = $("#assetLine").val();
	    var landStatus = $("#landStatus").val();
	    var landPlaning = $("#landPlaning").val();
	    var landAreaStar=$.trim($("#landAreaStar").val());
	    var landAreaEnd=$.trim($("#landAreaEnd").val());
	     if(parseFloat(landAreaStar) > parseFloat(landAreaEnd)){
	    	alert("土地面积范围起始值不可以大于终止值，请重新填写!");
	    	$("#landAreaStar").val("");
	    	$("#landAreaEnd").val("");
	    	return ;
	    }
    	window.location.href = '<%=basePath%>assetLand/excelLandAsset.action?lineCodeId_equal_filter='+lineCodeId+'&project_like_filter='+project+'&builderProject_equal_filter='+builderProject
    							+'&landStatus_equal_filter='+landStatus+'&landPlaning_like_filter='+landPlaning+'&landTotalArea_before_filter='+landAreaEnd+'&landTotalArea_after_filter='+landAreaStar
    							+'&publish_equal_filter=1';
    }

    function addStyle(){

    }

</script>
<style type="text/css">
	.th_minNav{
		/*width:54%;*/
	}
</style>
</head>
<html>
<body style="font-family:'Microsoft YaHei';">
<div class="container" style="width: 100%"><br><br><!-- Everything started here -->
    <div class="tHeader" >
        <div class="th_inMarginWrap">
            <div class="th_Logo"></div>
            <div class="th_minNav" id="firstMenu">
                <a href="<%=basePath %>jsp/homepage.jsp" class="minLink" ><span><i>主题管理</i></span></a>
                <a href="#" class="minLink cur"><span><i>资产台账</i></span></a>
                <a href="<%=basePath%>report/showReportStat.action" class="minLink"><span><i>统计报表</i></span></a>
                <a href="#" class="minLink" ><span><i>决策分析</i></span></a>
                <a href="<%=basePath%>jsp/basecode/assetTypeList.jsp" class="minLink" ><span><i>基础管理</i></span></a>
                <a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink" ><span><i>盘点任务</i></span></a>
            </div>
            <jsp:include page="/jsp/navigation.jsp"></jsp:include>
        </div>
    </div>
    <div id="main" class="tc_inDataArea">
        <!--search-->
        <div class="search_1 p8" style="font-size: 12px;">

            <input type="hidden" value="${pageNo }" name="pageNo" id="pageNo"/>
            <div class="clearfix" id="serarchArea" style="text-align: right;line-height: 30px;">
            
			   <div class="fl w25p clearfix">
                    <div class="fl p5 w30p"><label for="textfield">线路(大线)</label></div>
                    <div class="fl p5 w65p">
                        <select name="" id="bigLineSelect" class="input_large">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                
                <div class="fl w24p clearfix">
                    <div class="fl p5 w30p"><label for="textfield">线路(小线)</label></div>
                    <div class="fl p5 w65p">
                        <select name="" id="assetLine" class="input_large">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="fl w25p clearfix">
                    <div class="fl p5 w30p"><label for="textfield">土地性质</label></div>
                    <div class="fl p5 w65p">
                        <select name="" id="landStatus"  class="input_large">
                            <option value="">请选择</option>
                            <option value="出租">出租</option>
                            <option value="国有">国有</option>
                            <option value="划转 ">划转 </option>
                            <option value="全民">全民</option>
                            <option value="市政">市政</option>
                            <option value="市政交通">市政交通</option>
                            <option value="市政综合">市政综合</option>
                        </select>
                    </div>
                </div>

                <div class="clear"></div>
                <div class="fl w25p clearfix">
                    <div class="fl p5 w30p"><label for="textfield">项目名称</label></div>
                    <div class="fl p5 w65p">
                        <input type="text" name="project" id="project" value="" class="input_large">
                    </div>
                </div>

                <div class="fl w24p clearfix">
                    <div class="fl p5 w30p"><label for="textfield">规划土地用途</label></div>
                    <div class="fl p5 w65p">
                        <select name="landPlaning" id="landPlaning"  class="input_large">
                            <option value="">请选择</option>
                            <option value="工程建设">工程建设</option>
                            <option value="规划道路">规划道路</option>
                            <option value="市政交通">市政交通</option>
                            <option value="防汛通道">防汛通道</option>
							<option value="市政">市政</option>
                            <option value="绿化">绿化</option>
                            <option value="道路">道路</option>
                            <option value="河道">河道</option>
                            <option value="其他">其他</option>
                            

                        </select>
                    </div>
                </div>

                <div class="fl w25p clearfix">
                    <div class="fl p5 w30p"><label for="textfield">土地面积范围</label></div>
                    <!-- <div  style="float: left;width: 90px;padding: 5px">
                        <input style="width: 90px" type="text" name="landAreaStar" id="landAreaStar" onblur="numberCheck(this);" value="" class="input_large">
                    </div>
                    <div style="float:left;width: 3px;">-</div>
                    <div  style="float: left;width: 90px;padding: 5px">
                        <input style="width: 90px" type="text" name="landAreaEnd" id="landAreaEnd" onblur="numberCheck(this);" value="" class="input_large">
                    </div> -->
                    <div class="fl p5 w65p">
						<input style="width: 90px" type="text" name="landAreaStar" id="landAreaStar" onblur="numberCheck(this);" value="" class="input_large"/>
						-
						<input style="width: 90px" type="text" name="landAreaEnd" id="landAreaEnd" onblur="numberCheck(this);" value="" class="input_large"/>
					</div>
				 </div>
            </div>
            <div class="t_c p5">
                <input type="button" value="查询" onclick="query();">
                <input type="button" value="重置" onclick="clearForm();">
                <input type="button" value="导出" onclick="exportExcel()">
            </div>

        </div>
        <!--search end-->

        <div class="clearfix">
            <!--tree
            <div class="w18p fl tree">
              <p>tree</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
            </div>-->
            <!--tree end-->
            <div id="content" class="fl w100p" style="width:1310px;;">
            </div>
        </div>
    </div>
</div>
<div class="tFooter"></div>






</body>
</html>
