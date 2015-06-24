<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>资产台帐</title>
	
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
            initJtable();
            showAssetType("mainType",null);
            showLine();
            showOrganization();
            showDepartment();	
            $(".th_medNav li:eq(0)").addClass("cur");
        });

	function initJtable(){
		$('#content').jtable({
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
                listAction: '<%=basePath %>asset/inquery.action'
            },
            fields: {
                id: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                assetCode:{
					title:'资产编码',
					width:'6%'
                },
                name: {
                    title: '资产名称',
                    width: '10%'
                },
                'assetOwnerInf.line':{
                	title:'线路',
					width:'10%',
					display:function(data){
						if(data.record.assetOwnerInf.line){
							return data.record.assetOwnerInf.line.name;
						}
						return ;
                	}
                },
                'assetOwnerInf.station':{
                	title:'车站',
					width:'10%',
					display:function(data){
						if(data.record.assetOwnerInf.station){
							return data.record.assetOwnerInf.station.name;
						}
						return ;
                	}
                },
                'assetOwnerInf.beginUseDate':{
					title:'使用时间',
					width:'10%',
					display:function(data){
						if(data.record.assetOwnerInf.beginUseDate!=null && data.record.assetOwnerInf.beginUseDate.length>10 ){
							return data.record.assetOwnerInf.beginUseDate.substring(0,10);
						}
						return ;
            		}
                },
                options:{
                	title:'操作',
                	width:'5%',
                	sorting: false,
					display: function (data) {
						var btn = "<a href='<%=basePath%>asset/showView.action?id="+data.record.id+"' target='_blank')>查看</a>";
				        return btn;
				    }
                }
            }
        });
		$('#content').jtable('load');
	}
	
    function showAssetType(contentId,target){
        var parentId=null;
        if(target!=null ){
			parentId = $(target).find('option:selected').attr('class');
			if(parentId.length==2){
				$("#subType").find("option:first").attr("selected",true);
	       		$("#subType").find("option:gt(0)").remove();
			}
        }
        $("#detailType").find("option:first").attr("selected",true);
   		$("#detailType").find("option:gt(0)").remove();
        $.ajax({
			type:'post',
			url:'<%=basePath%>assetType/findAssetType.action',
			dataType:'json',
			cache:false,
			data:{
				parentId:parentId
			},
			error:function(){},
			success:function(object){
				var addHtml="<option value=''>请选择</option>";
				if(object!=null && object.length>0){
					for(var i=0,len=object.length; i<len; i++){
						addHtml +="<option value='"+object[i].codeId+"' class='"+object[i].id+"'>"+object[i].name+"</option>"
					}
				}
				$("#"+contentId+"").html(addHtml);
			}
        });
    }	

    //表单查询
	function query(){
		var assetName = $.trim($("#assetName").val());
		var assetCode = $.trim($("#assetCode").val());
		var mainType = $.trim($("#mainType").val());
		var subType = $.trim($("#subType").val());
		var detailType = $.trim($("#detailType").val());
		//var lineId = $("#assetLine").val();
		var codeId = $("#assetLine").val();
		var ownerOrgId = $("#ownerOrganization").val();
		var useOrgId = $("#useOrganization").val();
		var departmentId = $("#assetDepartment").val();
		//var verifyState = $("#verifyState").val();
		var assetState = $("#assetState").val();
		var projectId = $("#projectId").val();
		var completeTransAssetType = $("#completeTransAssetType").val();
		$('#content').jtable('load',{
			name_like_filter:assetName,
			assetCode_like_filter:assetCode,
			mainTypeCodeId_equal_filter:mainType,
			subTypeCodeId_equal_filter:subType,
			detailTypeCodeId_equal_filter:detailType,
			completeTransAssetType_equal_filter:completeTransAssetType, 
			//'assetOwnerInf.line.id_equal_filter':lineId,
			'assetOwnerInf.lineCodeId_equal_filter':codeId,
			'assetOwnerInf.useOrganizationCodeId_equal_filter':useOrgId,
			'assetOwnerInf.ownerOrganizationCodeId_equal_filter':ownerOrgId,
			'assetOwnerInf.departmentCodeId_equal_filter':departmentId,
			// verifyState_equal_filter: verifyState,
			'state.state_equal_filter':assetState,
			'project.id_equal_filter':projectId
		});
	}
    	
    //清除搜索框
    function clearForm(){
		$("#assetName").val("");
		$("#assetCode").val("");
		$("#mainType").find("option:gt(0)").attr("selected",false);
		$("#mainType").find("option:first").attr("selected",true);
		$("#subType").find("option:first").attr("selected",true);
			$("#subType").find("option:gt(0)").remove();
		$("#detailType").find("option:first").attr("selected",true);
			$("#detailType").find("option:gt(0)").remove();

		$("#serarchArea").find("select").find("option:first").attr("selected",true);
		$("#serarchArea").find("select").find("option:gt(0)").attr("selected",false);
    }

    //显示线路 
    function showLine(){
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
							addHtml += "<option value='"+data[i].codeId+"'>"+data[i].name+"</option>";
						}
					}
					$("#assetLine").append(addHtml);
				}
			}
		});
    }

  	//显示使用单位
    function showOrganization(){
		$.ajax({
			url:'<%=basePath%>assetOrganization/getAllOrganization.action',
			type:'post',
			dataType:'json',
			async:'false',
			error:function(){
				alert("系统繁忙，请稍后再试!");
			},
			success:function(data){
				if(data!=null){
					var addHtml = "";
					for(var i=0; i<data.length; i++){
						addHtml += "<option value='"+data[i].code+"' style='width:100px;'>"+data[i].name+"</option>";
					}
					$("#useOrganization").append(addHtml);
					$("#ownerOrganization").append(addHtml);
				}
			}
		});
    }

    //显示维护部门
    function showDepartment(){
		$.ajax({
			url:'<%=basePath%>assetDepartment/getAllDepartment.action',
			type:'post',
			dataType:'json',
			async:'false',
			error:function(){
				alert("系统繁忙，请稍后再试!");
			},
			success:function(data){
				if(data!=null){
					var addHtml = "";
					for(var i=0; i<data.length; i++){
						addHtml += "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
					}
					$("#assetDepartment").append(addHtml);
				}
			}
		});
    }

    //根据线路显示项目
    function showProject(codeId){
		$.ajax({
			url:'<%=basePath%>project/getProjectByLine.action',
			type:'post',
			dataType:'json',
			data:{codeId:codeId},
			error:function(){
				alert("系统繁忙，请稍后再试!");
			},
			success:function(data){
				if(data!=null){
					var addHtml = "<option value=''>请选择</option>";
					for(var i=0; i<data.length; i++){
						addHtml += "<option value='"+data[i].id+"'>"+data[i].projectName+"</option>";
					}
					$("#projectId").html(addHtml);
				}
			}
		});
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
					<a href="<%=basePath %>jsp/asset/assetList.jsp" class="minLink cur"><span><i>资产台账</i></span></a>
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
                	<div class="fl p5 w30p"><label for="textfield">资产编码</label></div>
                	<div class="fl p5 w65p">
                		<input type="text" name="assetCode" id="assetCode" value="" class="input_large">
                	</div>
                </div>
                
            	<div class="fl w24p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">资产名称</label></div>
                	<div class="fl p5 w65p">
                		<input type="text" name="assetName" id="assetName" value="" class="input_large">
                	</div>
                </div>
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">线路</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="assetLine" onchange="showProject(this.value);" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                 <div class="fl w24p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">项目</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="projectId" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                
                <div class="clear"></div>
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">大类</label></div>
                	<div class="fl p5 w65p">
                		<select name="mainType" id="mainType" onchange="showAssetType('subType',this)" class="input_large"></select>
                	</div>
                </div>
                <div class="fl w24p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">中类</label></div>
                	<div class="fl p5 w65p">
                		<select name="subType" id="subType" onchange="showAssetType('detailType',this)" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">小类</label></div>
                	<div class="fl p5 w65p">
                		<select name="detailType" id="detailType" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                
                <div class="fl w24p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">维护部门</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="assetDepartment" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                
                
                
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">权属单位</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="ownerOrganization" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                <div class="fl w24p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">使用单位</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="useOrganization" class="input_large">
                			<option value="">请选择</option>
                		</select>
                	</div>
                </div>
                <!-- 
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">审核状态</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="verifyState" class="input_large">
                			<option value="">请选择</option>
                			<option value="1">待审核</option>
                			<option value="2">已入册</option>
                		</select>
                	</div>
                </div>
                 -->
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">资产使用状态</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="assetState" class="input_large">
                			<option value="">请选择</option>
                			<option value="1">使用</option>
                			<option value="2">停用</option>
                			<option value="3">报废</option>
                			<option value="4">待报废</option>
                			<option value="5">封存</option>
                		</select>
                	</div>
                </div>
                <div class="fl w25p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">移交资产类型</label></div>
                	<div class="fl p5 w65p">
                		<select name="" id="completeTransAssetType" class="input_large">
                			<option value="">请选择</option>
                			<option value="初始">初始</option>
                			<option value="新增">新增</option>
                			<option value="更新">更新</option>
                		</select>
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
