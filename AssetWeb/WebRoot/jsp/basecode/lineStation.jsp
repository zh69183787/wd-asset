<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>线路车站管理</title>
		<link href="<%=basePath %>css/default/reset.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/default/style.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/formalize.css" rel="stylesheet">
		<link href="<%=basePath %>css/pages.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>css/our.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath%>css/imgs.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath%>widgets/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
		<script src="<%=basePath%>js/jquery-1.9.0.min.js"></script>
		
		<script src="<%=basePath%>widgets/ztree/jquery.ztree.core-3.5.min.js"></script>
		
        <script type="text/javascript">
        
        $(document).ready(function () {
            var $tbInfo = $(".search_1 input:text");
            $tbInfo.each(function () {
                $(this).focus(function () {
                    $(this).attr("placeholder", "");
                });
            });
			var $tblAlterRow = $(".table_1 tbody tr:odd");
			if ($tblAlterRow.length > 0)
				$tblAlterRow.css("background","#f7f9fc");	
        });
        
        $(function(){
			showVersions();
        	showTreeFirst();
        });

        function showVersions(){
			$.ajax( {  
		         url : "<%=basePath%>assetLine/findAllVersion.action",  
		         type : 'post',  
		         dataType : 'json',  
		         contentType:'application/json',  
		         async : false,  
		         success : function(data) {
		        	 var html ='';
			         if(data!=null && data.length>0){
						for(var i=0;i<data.length; i++){
							if(<%=request.getParameter("version")%>==data[i]){
								html +='<option value="'+data[i]+'" selected="selected">'+data[i]+'</option>';
							}else{
								html +='<option value="'+data[i]+'">'+data[i]+'</option>';
							}
						}						
			         }
		         	$('#version').html(html);
		         },  
		         error : function(XMLHttpRequest, textStatus, errorThrown) {
			         alert(textStatus);
		         }  
		     }); 
		}
        
		function showTreeFirst(){
			$.ajax( {  
		         url : '<%=basePath%>assetLine/findByVersion.action?version='+$('#version').val(),  
		         type : 'POST',  
		         dataType : 'json',  
		         data:{
					version:$('#version').val()
			     },
		         contentType:'application/json',  
		         async : false,  
		         success : function(data) {
			       if(data!=null && data.length>0 && data[0].publish!=1){		//未发布
						$('#publish').html('<a href="javascript:void(0)" onclick="publish();" id="aPublish">发布</a>');
						$('#publish').val(0);
			       }else{
			    	   $('#publish').html('<a href="javascript:void(0)" id="aPublish">已发布</a>');
			    	   $('#publish').val(1);
			       }
		         	showTree(data);
		         },  
		         error : function(XMLHttpRequest, textStatus, errorThrown) {
			         alert(textStatus);
		         }  
		     }); 
		}
        
		function onClick(event, treeId, treeNode, clickFlag) {
			if(treeNode.id!=null && treeNode.id!=''){
				$('#id').val(treeNode.id);
	        	$('#code').val(treeNode.code);
	        	$('#name').val(treeNode.fullName);
	        	$('#remark').val(treeNode.remarks);
				if(treeNode.shortName!=null && treeNode.shortName!=''){		//有简称的是线路
					$('#nowType').val('line');
					$('#shortName').parent().parent().show();
					$('#shortName').val(treeNode.shortName);
					$('#lineId').val(treeNode.id);
				}else{
					$('#lineId').val('');
					$('#nowType').val('station');
					$('#shortName').parent().parent().hide();			//车站
					$('#shortName').val('');
					$('#name').parent().parent().find('label').html('车站名称');
				}
	        	$('#submitButton').show();
			}else{
				$('#nowType').val('top');
				$('#shortName').parent().parent().show();
				$('#shortName').val(treeNode.shortName);
				$('#submitButton').hide();
			}
		}	
		
        function showTree(jsonData){
        	$.fn.zTree.destroy("jstree");
        	var setting = {
        		callback: {
					onClick: onClick
				}
            };
        	var zNodes = [],allNodes = [];
			if(jsonData!=null && jsonData.length>0){
				for(var i=0,len=jsonData.length; i<len; i++){
					var children = [];
					if(jsonData[i].stations!=null && jsonData[i].stations.length>0){
						for(var j=0;j<jsonData[i].stations.length; j++){
							children.push({
								name:getFormatedText(jsonData[i].stations[j].name),
								fullName:jsonData[i].stations[j].name,
								id:jsonData[i].stations[j].id,
								remarks:jsonData[i].stations[j].remarks,
								code:jsonData[i].stations[j].code
							});
						}
					}
					zNodes.push({
						name:getFormatedText(jsonData[i].name),
						fullName:jsonData[i].name,
						id:jsonData[i].id,
						remarks:jsonData[i].remarks,
						code:jsonData[i].code,
						shortName:jsonData[i].shortName,
						children:children
					}); 
				}
			}
			allNodes={
				name:'线路车站',
				children:zNodes,
				open:true
			}
        	$.fn.zTree.init($("#jstree"), setting, allNodes);
        }

        function saveForm(){
			if($('#nowType').val()=='line'){
				if($.trim($("#name").val())==""){
	        		alert("请输入线路名称！");
	        		$("#name").focus();
	        		return false;
	        	}else if($("#name").val().length>100){
	        		alert("线路名称不能超过100字！");
	        		$("#name").focus();
	        		return false;
	        	}
	        	if($.trim($("#shortName").val())==""){
	        		alert("请输入线路简称！");
	        		$("#shortName").focus();
	        		return false;
	        	}else if($("#shortName").val().length>10){
	        		alert("线路简称不能超过10个字符！");
	        		$("#shortName").focus();
	        		return false;
	        	}
	        	if($.trim($("#code").val())==""){
					alert("请输入代码！");   
					$("#code").focus();  
					return false;   	
	        	}else if($.trim($("#code").val()).length != 2 || !$.isNumeric($.trim($("#code").val()))){
					alert("代码必须是2位数字");
					$("#code").focus();  
					return false;
	        	}
	        	$.ajax({
					url:'<%=basePath %>assetLine/checkUniqueness.action',
					type:'post',
					data:{
						id:$('#id').val(),
						code:$('#code').val(),
						name:$('#name').val(),
						version:$('#version').val()
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){alert(textStatus);},
					success:function(data){
						if(data!="success"){
							alert(data);
						}else{
							$.ajax({
								url:'<%=basePath %>assetLine/saveLine.action',
								type:'post',
								contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
								data:{
									id:$('#id').val(),
									code:$('#code').val(),
									name:$('#name').val(),
									shortName:$('#shortName').val(),
									version:$('#version').val(),
									remark:$('#remark').val(),
									publish:$('#aPublish').html()=='已发布'?1:0
								},
								error:function(XMLHttpRequest, textStatus, errorThrown){alert(textStatus);},
								success:function(){
									alert('保存成功！');
									window.location.href='<%=basePath%>jsp/basecode/lineStation.jsp?version='+$('#version').val();	
								}
							});
						}
					}
				});
			}else if($('#nowType').val()=='station'){
				if($.trim($("#name").val())==""){
	        		alert("请输入车站名称！");
	        		$("#name").focus();
	        		return false;
	        	}else if($("#name").val().length>100){
	        		alert("线路名称不能超过100字！");
	        		$("#name").focus();
	        		return false;
	        	}
	        	if($.trim($("#code").val())==""){
					alert("请输入代码！");   
					$("#code").focus();  
					return false;   	
	        	}else if($.trim($("#code").val()).length != 2 || !$.isNumeric($.trim($("#code").val()))){
					alert("代码必须是2位数字");
					$("#code").focus();  
					return false;
	        	}
	        	$.ajax({
					url:'<%=basePath %>assetStation/checkUniqueness.action',
					type:'post',
					data:{
						id:$('#id').val(),
						lineId:$('#lineId').val(),
						code:$('#code').val(),
						name:$('#name').val(),
						shortName:$('#shortName').val(),
						version:$('#version').val(),
						remark:$('#remark').val(),
						publish:$('#publish').val()
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){alert(textStatus);},
					success:function(data){
						if(data!='success'){
							alert(data);
						}else{
							$.ajax({
								url:'<%=basePath %>assetStation/saveStation.action',
								type:'post',
								contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
								data:{
									lineId:$('#lineId').val(),
									id:$('#id').val(),
									code:$('#code').val(),
									name:$('#name').val(),
									version:$('#version').val(),
									remark:$('#remark').val(),
									publish:$('#aPublish').html()=='已发布'?1:0
								},
								error:function(XMLHttpRequest, textStatus, errorThrown){alert(textStatus);},
								success:function(){
									alert('保存成功！');
									window.location.href='<%=basePath%>jsp/basecode/lineStation.jsp?version='+$('#version').val();	
								}
							});
						}
					}
				});
			}
        }
        
        function addNode(){
            if($('#nowType').val()==null || $('#nowType').val()==''){
				alert('请选择要添加子节点的节点！');
				return false;
            }
            if($('#nowType').val()=='station'){
            	alert('车站类型无法添加子节点！');
				return false;
            }
			if($('#nowType').val()=='top'){
				$('#name').parent().parent().find('label').html('线路名称');
				$('#nowType').val('line');
				$('#shortName').parent().parent().show();
				$('#shortName').val('');
			}else if($('#nowType').val()=='line'){
				$('#name').parent().parent().find('label').html('车站名称');
				$('#nowType').val('station');
				$('#shortName').parent().parent().hide();
				$('#shortName').val('');
			}
        	$("#id").val("");
        	$("#code").val("");
       		$("#name").val("");
       		$('#shortName').val('');
			$('#remark').val('');
       		$("#submitButton").show();
       		$("#submitButton").val("添加");
        }
		
        function deleteNode(){
        	if(!$('#id').val()==null ||$('#id').val()==""){
        		alert("请选择要删除的节点！");
        		return false;
        	}
			if($('#nowType').val()=='top'){
				alert('根节点无法删除！');
				return false;
			}else if($('#nowType').val()=='line'){
				if(confirm("是否删除？")){
	        		 $.ajax( {  
				        url : "<%=basePath%>assetLine/deleteLine.action",
						type : 'post',
						data : {
							id : $('#id').val()
						},
						async : false,
						dataType:'text',
						success : function(data) {
							if(data!=null && data!=''){
								alert(data);
							}else{
								alert('删除成功！');
								window.location.href = '<%=basePath%>jsp/basecode/lineStation.jsp?version='+$('#version').val();
							}
						},
						error : function(request,errorinfo,error) {
							alert(errorinfo);
						}
					});
				}
			} else if ($('#nowType').val() == 'station') {
				if(confirm("是否删除？")){
	        		 $.ajax( {  
				        url : "<%=basePath%>assetStation/deleteStation.action",
						type : 'post',
						data : {
							id : $('#id').val()
						},
						async : false,
						success : function() {
							alert('删除成功！');
							window.location.href = '<%=basePath%>jsp/basecode/lineStation.jsp?version='+$('#version').val();
						},
						error : function() {
							alert("ajax error");
						}
					});
				}
			}

	}

	//添加新版本的资产类型
	function addNewVersion() {
		if (confirm('是否确定复制当前资产类型版本并生成最新的版本？')) {
			$.ajax( {
				url : '<%=basePath%>assetLine/copyCurrentVersion.action',
				data:{version:$('#version').val()},
				type:'post',
				error:function(XMLHttpRequest, textStatus, errorThrown){
						alert(textStatus);
				},
				success:function(data){
					var html ='<option value="'+data+'" selected="selected">'+data+'</option>';
					$('#version').find('option').attr('selected',false);
					$('#version').html(html+$('#version').html());
					$('#version').find('option:eq(0)').attr('selected',true);
					showTreeFirst();
					alert('复制成功！');
				}
		});
	}	
}

function publish(){
	if(confirm('是否确认发布改版本?')){
		$.ajax({
			url:'<%=basePath%>assetLine/publish.action',
			type:'post',
			data:{version:$('#version').val()},
			error:function(xmlHttpRequest,textStatus,errorThrown){
				alert(textStatus);
			},
			success:function(){
				alert('发布成功！');
				$('#aPublish').html('已发布');
			}
		});
	}
}

function getFormatedText(context){
	if(context==null || ""==context) return "";
	if(context.length>18){
		return (context.substring(0,18)+'...');
	}else{
		return context;
	}
}

</script>
</head>

<body style="font-family:'Microsoft YaHei';">
<div class="body">
<div class="container">

	<div class="tHeader">
            <div class="th_inMarginWrap">
            	<div class="th_Logo"></div>
            	<div class="th_minNav" id="firstMenu">
					<a href="<%=basePath %>jsp/homepage.jsp" class="minLink"><span><i>主题管理</i></span></a>
					<a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink"><span><i>资产台账</i></span></a>
					<a href="<%=basePath%>report/showReportStat.action" class="minLink" ><span><i>统计报表</i></span></a>
					<a href="#" class="minLink" ><span><i>决策分析</i></span></a>
					<a href="#" class="minLink cur" class="minLink"><span><i>基础管理</i></span></a>
					<a href="<%=basePath%>jsp/task/taskList.jsp" class="minLink" ><span><i>盘点任务</i></span></a>
				</div>
				<ul class="th_medNav" id="reportArea">
					<li><span><a href="<%=basePath%>jsp/basecode/assetTypeList.jsp" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产类型管理</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/basecode/spareTypeList.jsp"  class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>备品备件物资代码</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/basecode/useOrganization.jsp"  class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>使用单位管理</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/basecode/department.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>维护部门管理</i></a></span></li>
					<li class="cur"><span><a href="#" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>线路车站管理</i></a></span></li>
				</ul>
				
            </div>
        </div>

	<input type="hidden" id="openId" value="${openId}"/>
	<!--bar-->
	<div class="bar clearfix">
    	<ul class="fl select clearfix">
    		<li class="selected"><a href="javascript:void(0)">当前版本：</a>
    			<select style="margin-top: -2.5px;" name="version" id="version" onchange="showTreeFirst();">
    			</select>
    		</li>
    		<li class="selected">
    			<a href="javascript:void(0)" onclick="addNewVersion();">复制并添加新版本</a>
    		</li>    			
        	<li class="selected"><a href="javascript:void(0)" onclick="addNode()">添加子节点</a></li>
        	<li class="selected" id="deleteLi"><a href="javascript:void(0)" onclick="deleteNode()">删除</a></li>
        	<li class="selected" id="publish">
        		<a href="javascript:void(0)" id="aPublish">已发布</a>
        	</li>
        </ul>
    </div>
	<!--bar end-->
    <div class="clearfix">
    	<!--tree-->
        <div class="w30p fl tree">
          <ul id="jstree" class="ztree"></ul>
        </div>
        <!--tree end-->
      <div id="content" class="fl w70p" >
        <form id="form1" method="post" action="<%=basePath %>assetType/updateAssetType.action">
        	<input type="hidden" id="pbStatus" name="pbStatus">
        	<input type="hidden" id="nowType" name="nowType">
        	<input type="hidden" id="lineId" name="lineId">
        	<input type="hidden" id="id" name="id">
        	<input type="hidden" id="publish" name="publish">
          <div class="clearfix b_bor">
              <label for="textfield" class="fl p5 w120 lable_bg">线路名称</label>
              <div class="fl p5 w70p">
                <input type="text" name="name" id="name" class="input_xxlarge"/>
              </div>
		  </div>
		   <div class="clearfix b_bor">
              <label for="textfield" class="fl p5 w120 lable_bg">线路简称</label>
              <div class="fl p5 w70p">
                <input type="text" name="shortName" id="shortName" class="input_xxlarge" maxlength="10"/>
              </div>
		  </div>
		  <div class="clearfix b_bor">
              <label for="textfield" class="fl p5 w120 lable_bg">代码</label>
              <div class="fl p5 w70p">
                <input type="text" name="code" id="code" class="input_xxlarge"/>
              </div>
		  </div>
		   <div class="clearfix b_bor">
              <label for="textfield" class="fl p5 w120 lable_bg">备注</label>
              <div class="fl p5 w70p">
                <input type="text" name="remark" id="remark" class="input_xxlarge" maxlength="50"/>
              </div>
		  </div>
            <div class="t_c p5">
                <input id="submitButton" type="button" value="修改" onclick="saveForm();" style="display:none"/>
            </div>
        </form>
      </div>
    </div>
	<!--footer-->
    <footer class="plr8"></footer>
	<!--footer end-->
	</div>
</div>	
</body>
</html>
