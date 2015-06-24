<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>盘点任务</title>
	
	<!-- CSS & js -->
		<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/formalize.css" rel="stylesheet">
		<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">
		
		<link href="<%=basePath %>widgets/jtable/css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>widgets/jtable/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>widgets/datepicker/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<%=basePath %>js/jay.js"></script>
		<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>		
		<script src="<%=basePath %>widgets/jtable/jquery.jtable.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>widgets/datepicker/jquery.ui.datepicker-zh-CN.js" type="text/javascript"></script>

	<script type="text/javascript">
        $(document).ready(function () {
            initJtable();
            $("input[name=start1]").datepicker({
        		inline: true,
        		changeYear:true,
        		changeMonth:true,
        		showOtherMonths: true,
        		selectOtherMonths: true,
        		onSelect: function( selectedDate ) {
        			$("input[name='start2']").datepicker( "option", "minDate", selectedDate );
        		}
        	});
        	$("input[name=start2]").datepicker({
        		inline: true,
        		changeYear:true,
        		changeMonth:true,
        		showOtherMonths: true,
        		selectOtherMonths: true,
        		onSelect: function( selectedDate ) {
        			$("input[name='start1']").datepicker( "option", "maxDate", selectedDate );
        		}
        	});
        	$("input[name=end1]").datepicker({
        		inline: true,
        		changeYear:true,
        		changeMonth:true,
        		showOtherMonths: true,
        		selectOtherMonths: true,
        		onSelect: function( selectedDate ) {
        			$("input[name='end2']").datepicker( "option", "minDate", selectedDate );
        		}
        	});
        	$("input[name=end2]").datepicker({
        		inline: true,
        		changeYear:true,
        		changeMonth:true,
        		showOtherMonths: true,
        		selectOtherMonths: true,
        		onSelect: function( selectedDate ) {
        			$("input[name='end1']").datepicker( "option", "maxDate", selectedDate );
        		}
        	});
        });

        function initJtable(){
        	$('#content').jtable({
                title: '任务列表',
                paging: true, // Enable paging
                pageSize: 10, // Set page size (default: 10)
               	sorting: false, // Enable sorting
                messages: {
                	pagingInfo:'显示 {0}-{1}条   总共{2}条',
                	gotoPageLabel: '跳转到',
                	pageSizeChangeLabel: '每页显示',
                	loadingMessage: '数据加载中...',
                	editRecord: '详细',
                	noDataAvailable: '没有数据！'
                },
                actions: {
                    listAction: '<%=basePath %>task/inquery.action'
                },
                fields: {
                    id: {
                        key: true,
                        create: false,
                        edit: false,
                        list: false
                    },
                    taskname:{
        				title:'任务名称',
        				width:'6%'
                    },
                    starttime: {
                        title: '开始时间',
                        width: '10%'
                    },
                   	endtime:{
                    	title:'结束时间',
        				width:'10%'
                    },
                    taskstatus:{
                    	title:'任务状态',
        				width:'10%',
        				display:function(data){
							if(data.record.taskstatus==0){
								return '进行中';
							}
							return '已过期';
                    	}
                    },
                    completerate:{
        				title:'完成率',
        				width:'10%'
                	},
                	options:{
                      	title:'操作',
                      	width:'5%',
                      	sorting: false,
            			display: function (data) {
            				var btn = '<a href="<%=basePath%>task/showView.action?id='+data.record.id+'" target="_blank")>任务详情</a>';
            		        return btn;
            		    }
                   }
               }
        	});
        	$('#content').jtable('load');
        }
	
   

    //表单查询
	function query(){
		var taskname = $.trim($("#taskname").val());
		var start1 = $.trim($("#start1").val());
		var start2 = $.trim($("#start2").val());
		var end1 = $.trim($("#end1").val());
		var end2 = $.trim($("#end2").val());
		
		if(taskname.length>100){
			alert("输入长度不能超过100");
			return false;
		}
		$('#content').jtable('load',{
			taskname_like_filter:taskname,
			starttime_after_date_filter:start1,
			starttime_before_date_filter:start2,
			endtime_after_date_filter:end1,
			endtime_before_date_filter:end2
		});
	}
    	
    //清除搜索框
    function clearForm(){
		$("#taskname").val('');
		$("#start1").val('');
		$("#start2").val('');
		$("#end1").val('');
		$("#end2").val('');
    }
   
    </script>
</head>
<html>
	<body style="font-family:'Microsoft YaHei';">
	<div class="container"><!-- Everything started here -->
        <div class="tHeader">
            <div class="th_inMarginWrap">
            	<div class="th_Logo"></div>
            	<div class="th_minNav" id="firstMenu">
					<a href="<%=basePath %>jsp/homepage.jsp" class="minLink" ><span><i>主题管理</i></span></a>
					<a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink"><span><i>资产台账</i></span></a>
					<a href="<%=basePath%>report/showReportStat.action" class="minLink"><span><i>统计报表</i></span></a>
					<a href="#" class="minLink" ><span><i>决策分析</i></span></a>
					<a href="<%=basePath%>jsp/basecode/assetTypeList.jsp" class="minLink" ><span><i>基础管理</i></span></a>
					<a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink cur" ><span><i>盘点任务</i></span></a>
				</div>
            	<ul class="th_medNav">
					<li class="cur"><span><a href="#" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>盘点任务</i></a></span></li>
				</ul>
            </div>
        </div>
        <div id="main" class="tc_inDataArea">
			<!--search-->
   		<div class="search_1 p8" style="font-size: 12px;">
		
			<input type="hidden" value="${pageNo }" name="pageNo" id="pageNo"/>
        	<div class="clearfix" id="serarchArea" style="text-align: right;line-height: 30px;">
            	<div class="fl clearfix" style="width: 30%;">
                	<div class="fl p5 w30p"><label for="textfield">任务名称</label></div>
                	<div class="fl p5 w65p">
                		<input type="text" name="taskname" id="taskname" value="" class="input_large">
                	</div>
                </div>
                
            	<div class="fl clearfix" style="width: 35%;">
                	<div class="fl p5 w30p"><label for="textfield">任务开始时间</label></div>
                	<div class="fl p5 w65p">
                		<input type="text" id="start1" name="start1" style="width: 100px;" value="<s:property value='#request.start1'/>" > 至
						<input type="text" id="start2" name="start2" style="width: 100px;" value="<s:property value='#request.start2'/>" >
                	</div>
                </div>
                <div class="fl w50p clearfix" style="width: 35%;">
                	<div class="fl p5 w30p"><label for="textfield">任务结束时间</label></div>
                	<div class="fl p5 w65p">
                		<input type="text" id="end1" name="end1" style="width: 100px;" value="<s:property value='#request.end1'/>" > 至
						<input type="text" id="end2" name="end2" style="width: 100px;" value="<s:property value='#request.end2'/>" >
                	</div>
                </div>
            </div>
            <div class="t_c p5">
            	<input type="button" value="查询" onclick="query();">
            	<input type="button" value="重置" onclick="clearForm();">
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
          <div id="content" class="fl w100p" style="width: 100%;">
          </div>
        </div>
        </div>
    </div>
    <div class="tFooter"></div>
	
	
	
	
	
    	
	</body>
</html>
