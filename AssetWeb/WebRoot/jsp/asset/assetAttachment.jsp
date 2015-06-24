<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>资产附件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
		    $(".th_medNav li:eq(1)").addClass("cur");
		    initJtable();
		});
		function initJtable(){
		    $('#content').jtable({
		        title: '附件',
		        paging: true, //Enable paging
		        pageSize: 10, //Set page size (default: 10)
		        sorting: false, //Enable sorting
		        messages: {
		            pagingInfo:'显示 {0}-{1}条   总共{2}条',
		            gotoPageLabel: '跳转到',
		            pageSizeChangeLabel: '每页显示',
		            loadingMessage: '数据加载中...',
		            editRecord: '详细',
		            noDataAvailable: '没有数据！'
		        },
		        actions: {
		             listAction:'<%=basePath%>attachment/showAttachment.action?objectId_equal_filter=+<%=request.getParameter("objectId_equal_filter")%>+&type_equal_filter=+<%=request.getParameter("type_equal_filter")%>+'
		        },
		        fields: {
		            attachmentId: {
		                key: true,
		                create: false,
		                edit: false,
		                list: false
		            },
		            createTime:{
		                title:'创建日期',
		                width:'15%',
		                display:function(data){
							if(data.record.createTime!=null && data.record.createTime.length>10 ){
								return data.record.createTime.substring(0,10);
							}
							return ;   
            			}
		            },
		            creator: {
		                title: '创建人',
		                width: '15%'
		            },
		            fileTitle: {
		                title: '文件标题',
		                width: '15%'
		            },
		            fileType:{
		                title:'文件类型',
		                width:'15%'
		            },
		            fileRoute: {
		                title: '文件路径',
		                width: '20%',
		            	display:function(data){
		            		var html = "";
							if(data.record.fileRoute){
							    html+="<a href='"+data.record.fileRoute+"' target='_blank'>"+data.record.fileTitle+"</a><br/>";
								return html;
							}
							return ;   
		            	}  
			        }
			    }
		    });
		    $('#content').jtable('load');
		    $('.jtable').attr(' OVERFLOW-X','scroll');
		}
		</script>

  </head>
  
<body> 
	 <div id="main" class="tc_inDataArea">
	<div class="clearfix">
            <div id="content" class="fl w100p" style="width:1310px;;">
            </div>
        </div>
      </div>
</body>
</html>
