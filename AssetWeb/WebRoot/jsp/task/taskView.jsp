<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
	<meta charset=utf-8 />
	<title>任务详情</title>
	<!-- CSS
  ================================================== -->
	<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">

	<!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

	<!-- Favicons
        ================================================== -->
	
	<!-- Javascript
        ================================================== -->
	<link rel="stylesheet" href="<%=basePath %>widgets/uploadify/uploadify.css" />

	<!-- ours css and js -->
	<link rel="stylesheet" href="<%=basePath %>css/formalize.css">
	<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>widgets/jtable/css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>widgets/jtable/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />

	<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>js/html5.js" type="text/javascript"></script>
	<script src="<%=basePath %>widgets/uploadify/jquery.uploadify-3.1.js"></script>
	<script src="<%=basePath %>widgets/jtable/jquery.jtable.min.js" type="text/javascript"></script>


<style type="text/css">
body {
	font: 12px/ 1.5 'Helvetica Neue', Arial, 'Liberation Sans', FreeSans,
		sans-serif;
}
.table_1 td{
	border: 1px solid #D0D0D3;
	white-space: nowrap;
}
</style>
	<script type="text/javascript">
	$(document).ready(function() {
		var $tbInfo = $(".search_1 input:text");
		$tbInfo.each(function() {
			$(this).focus(function() {
				$(this).attr("placeholder", "");
			});
		});

		var $tblAlterRow = $(".table_1 tbody tr:odd");
		if ($tblAlterRow.length > 0)
			$tblAlterRow.css("background", "#f7f9fc");
		
		initJtable();
	});


	$(function(){
		$("#uploadify").uploadify({
	       'auto' : false,
	       'method' : "post",
	       'height' : 20,
	       'width' : 100,
	       'swf' : '<%=basePath%>widgets/uploadify/uploadify.swf', 
	       'uploader' : '<%=basePath%>task/uploadAttach.action?id='+$('#id').val(),
	       'fileTypeDesc' : '格式:xls',		//描述
	       'fileTypeExts' : '*.xls;*.txt;*.pdf;*.xlsx;*.doc;*.docx;',			//文件类型
	       'fileSizeLimit' : '30000KB',			//文件大小
	       'buttonText' : '选择文件',			//按钮名称
	       'fileObjName'	:'uploadify',
	       'successTimeout' : 5,
	       'requeueErrors' : false,
	       'removeTimeout' : 1,
	       'removeCompleted' : true,
	       'onSelectError' : function(file,errorCode,errorMsg) {
	       		if(errorCode==-110){
	       			this.queueData.errorMsg = "文件太大，无法上传！";
	       		}
	        }, 
	       'onUploadSuccess' : function(file, data, response){
	 	       alert("上传成功！");
	 	       window.location.reload();
	    	}
		});
	});

	function deleteAttach(id){
		if(confirm('是否删除？')){
			$.ajax({
				url:'deleteAttach.action?id='+id,
				method:'get',
				error:function(){},
				success:function(){
					alert('删除成功！');
					window.location.reload();
				}
			});
		}
	}
	
	function initJtable(){
		$('#contentInfo').jtable({
            title: '资产列表',
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
                listAction: '<%=basePath %>task/findInfo.action?id='+$("#id").val()
            },
            fields: {
                id: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                assetNo:{
					title:'资产编码',
					width:'12%',
                },
                assetName: {
                    title: '资产名称',
                    width: '12%'
                },
                line:{
                	title:'线路',
					width:'12%',
                },
                station:{
                	title:'车站',
					width:'12%',
                },
                beginUseDate:{
					title:'使用时间',
					width:'12%',
                },
                checkInfo:{
                	title:'盘点结果',
                	width:'12%',
                },
                checkDate:{
                	title:'实际盘点时间',
                	width:'12%',
                },
                checkPerson:{
                	title:'盘点人',
                	width:'12%',
                }
            }
        });
		$('#contentInfo').jtable('load');
	}
</script>
</head>

<html>
<input type="hidden" id="id" value="<s:property value='#request.assetTask.id'/>">
	<body style="font-family:'Microsoft YaHei';">
		<div class="bar clearfix t_r">
			<input type="button" value="关 闭" onclick="window.close();">
		</div>
		<!--bar end-->

		<div id="content">
			<div class="clearfix">
				<div class="clearfix">
					<h4 class="w120 p5 t_r" for="textfield"
						style="background-color: #F7F9FC;">
						任务基本信息
					</h4>
				</div>

				<div class="fl clearfix b_bor" style="width: 100%;">
					<label class="fl p5 w120 lable_bg t_r" for="textfield">
						任务名称
					</label>
					<div class="fl p5">
						<s:property value="#request.assetTask.taskname" />
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="fl w50p clearfix b_bor">
					<label class="fl p5 w120 lable_bg t_r" for="textfield">
						任务开始时间
					</label>
					<div class="fl p5 w65p">
						<s:property value="#request.assetTask.starttime" />
					</div>
				</div>
				<div class="fl w49p clearfix b_bor">
					<label class="fl p5 w120 lable_bg t_r" for="textfield">
						任务结束时间
					</label>
					<div class="fl p5 w65p">
						<s:property value="#request.assetTask.endtime" />
					</div>
				</div>

				<div class="fl clearfix b_bor" style="width: 100%;">
					<label class="fl p5 w120 lable_bg t_r" for="textfield">
						任务范围
					</label>
					<div class="fl p5 w65p">
						<s:property value="#request.assetTask.taskmemo" />
					</div>
				</div>
				<div class="fl clearfix b_bor" style="width: 100%;">
					<label class="fl p5 w120 lable_bg t_r" for="textfield">
						附件列表
					</label>
					<div class="fl p5 w65p" style="width: auto;">
						<s:if test="#request.attachList!=null && #request.attachList.size()>0">
							<table class="table_1">
								<thead>
									<td>文件名</td>
									<!-- <td>大小</td> -->
									<td colspan="2">操作</td>
								</thead>
								<tbody>
									<s:iterator var="attach" value="#request.attachList">
										<tr>
											<td><s:property value="#attach.filename"/>.<s:property value="#attach.fileextname"/></td>
											<!-- <td><s:property value="#attach.filesize"/></td> -->
											<td><a href="downloadAttach.action?id=<s:property value="#attach.id"/>">下载</a></td>
											<td><a href="javascript:void(0);" onclick="deleteAttach('<s:property value="#attach.id"/>')">删除</a></td>
										</tr>
									</s:iterator>
								</tbody>	
							</table>
						</s:if>
						<s:else>
							无
						</s:else>
					</div>
				</div>
				
				<div class="fl clearfix b_bor" style="width: 100%;">
					<label class="fl p5 w120 lable_bg t_r" for="textfield">
						上传附件
					</label>
					<div class="fl p5 w65p" style="width: auto;">
						<input type="file" name="uploadify" id="uploadify" />
						<input type="button" value="上传" onclick="$('#uploadify').uploadify('upload','*');">
						<input type="button" value="取消" onclick="$('#uploadify').uploadify('stop');">
					</div>
				</div>
			</div>
		</div>

		<div id="contentInfo" class="fl w100p" style="width: 100%;">
        </div>
	</body>
</html>
