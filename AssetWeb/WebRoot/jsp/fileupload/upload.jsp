<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>文件上传</title>

<link rel="stylesheet" href="<%=basePath %>widgets/uploadify/uploadify.css" />
<script src="<%=basePath %>js/jquery-1.9.0.min.js"></script>
<script src="<%=basePath %>widgets/uploadify/jquery.uploadify-3.1.js"></script>

<script type="text/javascript">
$(function(){
	$("#uploadify").uploadify({
       'auto' : false,
       'method' : "post",
       'height' : 20,
       'width' : 100,
       'swf' : '<%=basePath%>widgets/uploadify/uploadify.swf', 
       'uploader' : '<%=basePath%>asset/assetBatchUpload.action',
       'fileTypeDesc' : '格式:xls',		//描述
       'fileTypeExts' : '*.xls',			//文件类型
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
       		alert(data);
    	}
	});
});

</script>

</head>

<body style="font-family:'Microsoft YaHei';">
<div>

<input type="file" name="uploadify" id="uploadify" />
<input type="button" value="上传" onclick="$('#uploadify').uploadify('upload','*');">
<input type="button" value="取消" onclick="$('#uploadify').uploadify('stop');">

</div>
<!--Table End-->
</body>
</html>
