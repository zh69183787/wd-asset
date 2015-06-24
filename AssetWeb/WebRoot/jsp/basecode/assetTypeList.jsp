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
		<title>资产类型管理</title>
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
		         url : "<%=basePath%>assetType/showVersion.action",  
		         type : 'post',  
		         dataType : 'json',  
		         contentType:'application/json',  
		         async : false,  
		         success : function(data) {
		        	 var html ='';
			         if(data!=null && data.length>0){
						for(var i=0;i<data.length; i++){
							html +='<option value="'+data[i]+'">'+data[i]+'</option>';
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
		         url : '<%=basePath%>assetType/getAllAssetType.action?version='+$('#version').val(),  
		         type : 'POST',  
		         dataType : 'json',  
		         contentType:'application/json',  
		         async : false,  
		         success : function(data) {
			       if(data!=null && data.publish!=1){		//未发布
						$('#publish').html('<a href="javascript:void(0)" onclick="publish();" id="aPublish">发布</a>');
			       }else{
			    	   $('#publish').html('<a href="javascript:void(0)" id="aPublish">已发布</a>');
			       }
			       $('#hiddenPublish').val(data.publish);
		         	showTree(data);
		         	$('#name').val('');
					$('#code').val('');
					$('#useLife').val('');
					$('#overhaulFrequency').val('');
					$('#submitButton').hide();
		         },  
		         error : function(XMLHttpRequest, textStatus, errorThrown) {
			         alert(textStatus);
		         }  
		     }); 
		}
		  
		function onClick(event, treeId, treeNode, clickFlag) {
        	$('#id').val(treeNode.id);
        	$('#parentId').val(treeNode.id);
        	$('#code').val(treeNode.code);
        	$('#allCode').val(treeNode.allCode);
        	$('#name').val(treeNode.fullName);
        	$('#remark').val(treeNode.remarks);
        	$('#overhaulFrequency').val(treeNode.overhaulFrequency);
        	$('#useLife').val(treeNode.useLife);

        	$('#reference').val(treeNode.overhaulFrequency);
        	$('#referenceType').val(treeNode.useLife);
        	
        	if(treeNode.allCode==null || treeNode.allCode!='0'){
        		$('#submitButton').show();
            	$("#submitButton").val("修改");
        	}else{
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
        	var zNodes = [];
        	zNodes.push({
				name:jsonData.name,
				fullName:jsonData.name,
				id:jsonData.id,
				code:jsonData.code,
				allCode:jsonData.allCode,
				children:jsonData.children,
				useLife:jsonData.useLife,
				overhaulFrequency:jsonData.overhaulFrequency,
				open:true
			}); 
        	$.fn.zTree.init($("#jstree"), setting, zNodes);
        }

        function saveForm(){
        	if($.trim($("#name").val())==""){
        		alert("请输入类型名称！");
        		$("#name").focus();
        		return false;
        	}else if($("#name").val().length>100){
        		alert("类型名称不能超过100字！");
        		$("#name").focus();
        		return false;
        	}
        	if($.trim($("#code").val())==""){
				alert("请输入类型代码！");   
				$("#code").focus();  
				return false;   	
        	}else if($.trim($("#code").val()).length != 2 || !$.isNumeric($.trim($("#code").val()))){
				alert("类型代码必须是2位数字");
				$("#code").focus();  
				return false;
        	}
        	if($("#id").val()==""){		//新增的
				$.ajax({
					url:'<%=basePath %>assetType/saveAssetType.action',
					type:'post',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
					dataType:'text',
					data:{
						name:$('#name').val(),
						parentId:$('#parentId').val(),
						version:$('#version').val(),
						code:$('#code').val(),
						allCode:$('#allCode').val(),
						overhaulFrequency:$('#overhaulFrequency').val(),
						useLife:$('#useLife').val(),
						publish:$('#hiddenPublish').val()
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){alert(textStatus);},
					success:function(data){
						if((data!=null && data!='success')){
							alert(data);
						}else{
							alert("保存成功！");
							showTreeFirst();
							$('#name').val('');
							$('#code').val('');
							$('#useLife').val('');
							$('#overhaulFrequency').val('');
							$('#submitButton').hide();
						}
					}
				});
            	
        		$("#form1").attr("action","<%=basePath %>assetType/saveAssetType.action");
        	}else{				//修改
        		$.ajax({
					url:'<%=basePath %>assetType/updateAssetType.action',
					type:'post',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
					data:{
						id:$('#id').val(),
						code:$('#code').val(),
						name:$('#name').val(),
						overhaulFrequency:$('#overhaulFrequency').val(),
						useLife:$('#useLife').val()
					},
					dataType:'text',
					error:function(XMLHttpRequest, textStatus, errorThrown){alert(textStatus);},
					success:function(data){
						if(data=='undefined' || (data!=null && data!='success')){
							alert(data);
						}else{
							alert("保存成功！");
							showTreeFirst();
							$('#name').val('');
							$('#code').val('');
							$('#useLife').val('');
							$('#overhaulFrequency').val('');
							$('#submitButton').hide();
						}
					}
				});
				
        	}
        }
        
        function addNode(){
			if($('#allCode').val()==null || $('#allCode').val()==''){
				alert("请选择要添加子节点的节点！");
				return ;
			}
			if($('#allCode').val().length==6){
				alert('小类不能添加子节点！');
				return;
			}
			$("#id").val('');
       		$("#name").val('');
			$('#code').val('');
       		$("#submitButton").show();
       		$("#submitButton").val("添加");

       		$('#reference').val('');
       		$('#referenceType').val('');
       		$('#overhaulFrequency').val('');
       		$('#useLife').val('');
       		
        }
		
        function deleteNode(){
        	if(!$('#id').val()==null ||$('#id').val()==""){
        		alert("请选择要删除的节点！");
        	}else{
        		if(confirm("是否删除？")){
        		 $.ajax( {  
			         url : "<%=basePath%>assetType/deleteAssetType.action",  
			         type : 'GET',  
			         dataType:'text',
			         data:{id:$('#id').val(),version:$('#version').val()}, 
			         async : false,  
			         success : function(data) {
				         if(data!=null && data!='success'){
							alert('该类型下已有子类，无法删除！');
				         }else{
				        	alert('删除成功！');			
							showTreeFirst();
							$('#name').val('');
							$('#code').val('');
							$('#useLife').val('');
							$('#overhaulFrequency').val('');
							$('#submitButton').hide();
				         }
			         },  
			         error : function(a,error,c) {  
			             alert(error);  
			         }  
			     });
        		}
        	}
        }


//添加新版本的资产类型
function addNewVersion(){
	if(confirm('是否确定复制当前资产类型版本并生成最新的版本？')){
		$.ajax({
			url:'<%=basePath%>assetType/copyCurrentVersion.action',
			data:{version:$('#version').val()},
			type:'post',
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success:function(data){
				var html ='<option value="'+data+'" selected="selected">'+data+'</option>';
				$('#version').find('option').attr('selected',false);
				$('#version').html(html+$('#version').html());
				showTreeFirst();
				alert('复制成功');
				$('#name').val('');
				$('#code').val('');
				$('#useLife').val('');
				$('#overhaulFrequency').val('');
				$('#submitButton').hide();
			}
		});
	}	
}

function publish(){
	if(confirm('是否确认发布改版本?')){
		$.ajax({
			url:'<%=basePath%>assetType/publish.action',
			type:'post',
			data:{version:$('#version').val()},
			error:function(xmlHttpRequest,textStatus,errorThrown){
				alert(textStatus);
			},
			success:function(){
				alert('发布成功！');
				$('#hiddenPublish').val('1');
				$('#aPublish').html('已发布');
			}
		});
	}
}

</script>
</head>

<body style="font-family:'Microsoft YaHei';">
<div class="body">
<input type="hidden" id="hiddenPublish">
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
					<li class="cur"><span><a href="#" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>资产类型管理</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/basecode/spareTypeList.jsp"  class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>备品备件物资代码</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/basecode/useOrganization.jsp"  class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>使用单位管理</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/basecode/department.jsp" class="navLink"><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>维护部门管理</i></a></span></li>
					<li><span><a href="<%=basePath %>jsp/basecode/lineStation.jsp" class="navLink" ><i><img src="<%=basePath %>css/default/images/icons_1.gif" alt=""><br>线路车站管理</i></a></span></li>
				</ul>
				
            </div>
        </div>
	
	<div class="tc_inDataArea" id="main">
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
        			已发布
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
	      <div id="content" class="fl w70p" style="height:100%;width:70%;">
	        <form id="form1" method="post" action="<%=basePath %>assetType/updateAssetType.action">
	          <div class="clearfix b_bor">
	              <label for="textfield" class="fl p5 w120 lable_bg">类型名称</label>
	              <div class="fl p5 w70p">
	                <input type="text" name="name" id="name" class="input_xxlarge"/>
	                <input type="hidden" name="id" id="id"/>
	                <input type="hidden" name="parentId" id="parentId"/>
	                <input type="hidden" name="allCode" id="allCode"/>
	              </div>
			  </div>
			  <div class="clearfix b_bor">
	              <label for="textfield" class="fl p5 w120 lable_bg">类型代码</label>
	              <div class="fl p5 w70p">
	                <input type="text" name="code" id="code" class="input_xxlarge"/>
	              </div>
			  </div>
			   <div class="clearfix b_bor">
	              <label for="textfield" class="fl p5 w120 lable_bg">设计使用寿命（年）</label>
	              <div class="fl p5 w70p">
	                <input type="text" name="useLife" id="useLife" class="input_xxlarge"/>
	              </div>
			  </div>
			   <div class="clearfix b_bor">
	              <label for="textfield" class="fl p5 w120 lable_bg">参考依据</label>
	              <div class="fl p5 w70p">
	                <input type="text" name="" id="reference" class="input_xxlarge"/>
	              </div>
			  </div>
			   <div class="clearfix b_bor">
	              <label for="textfield" class="fl p5 w120 lable_bg">参考类别</label>
	              <div class="fl p5 w70p">
	                <input type="text" name="" id="referenceType" class="input_xxlarge"/>
	              </div>
			  </div>
			   <div class="clearfix b_bor">
	              <label for="textfield" class="fl p5 w120 lable_bg">大修频次（次/年）</label>
	              <div class="fl p5 w70p">
	                <input type="text" name="overhaulFrequency" id="overhaulFrequency" class="input_xxlarge"/>
	              </div>
			  </div>
	            <div class="t_c p5">
	                <input id="submitButton" type="button" value="修改" onclick="saveForm()" style="display:none"/>
	            </div>
	        </form>
	      </div>
	    </div>
		<!--footer-->
	    <footer class="plr8"></footer>
		<!--footer end-->
		</div>
	</div>	
</div>	
</body>
</html>
