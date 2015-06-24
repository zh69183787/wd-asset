<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>房屋数据详细</title>
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
<script src="<%=basePath %>js/html5.js" type="text/javascript"></script>



<style type="text/css">
    body {
        font: 12px/1.5 'Helvetica Neue',Arial,'Liberation Sans',FreeSans,sans-serif;
    }
    .w49p{
    	width: 50%;
    }
    .w120{
    	width: 140px;
    }
</style>
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
    //$("#content").width($(window).width()-210);
});

$(function(){
    showEquipmentJtable();
});



function returnParent(){
    $.ajax({
        url: "<%=basePath%>jsp/asset/assetList.jsp",
        cache: false,
        success: function(html){
            $("#main").html(html);
        }
    });
}

</script>
</head>

<html>
<body style="font-family:'Microsoft YaHei';">

<div class="bar clearfix t_r">
    <input type="button" value="关 闭" onclick="window.close();">
</div>
<!--bar end-->

<div id="content" >
	<div class="clearfix">
		<div class="clearfix">
		    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">房屋资源基本信息</h4>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产编码</label>
		    <div class="fl p5 w65p" id="assetNo">
		        <s:property value="#request.houseAsset.assetNo"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产名称</label>
		    <div class="fl p5 w65p" id="assetName">
		        <s:property value="#request.houseAsset.assetName"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产类型</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.assetType"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">位置</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.locationNo"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">数量</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.quantity"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">竣工日期</label>
		    <div class="fl p5 w65p">
		        <s:date name="#request.houseAsset.completionDate" format="yyyy-MM-dd"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">使用年限</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.assetLifeYear"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">房产证编号</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.certificateNo"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">附属设施</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.ancillaryFacilities"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">土地权属证编号</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.warrantsNo"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">分类</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.classificationName"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">建筑面积（平方米）</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.buildArea"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">地上层数</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.groundFloor"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">地下层数</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.undergroundFloor"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">竣工证号</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.completedLicense"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">规划许可证号</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.planLicense"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">产权证号</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.propertyRightNo"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">位置信息</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.locationName"/>
		    </div>
		</div>
		
		
		<div class="clearfix">
		    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">房屋所属信息</h4>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">合同编号</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.contractNo"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">卡片号码</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.cardId"/>
		    </div>
		</div>
		
		
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">项目名称</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.project"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">单位</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.unit"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">总包单位</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.contractUnit"/>
		    </div>
		</div>
		
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">设计单位</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.designUnit"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">承建单位</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.constructionUnit"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">监理单位</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.supervisingCompany"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">权属单位</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.ownershipUnit"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">移交时间</label>
		    <div class="fl p5 w65p">
		        <s:date name="#request.houseAsset.transferTime" format="yyyy-MM-dd"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">线路</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.line"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">建设类项目名称</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.builderProject"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">车站</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.station"/>
		    </div>
		</div>
		
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px;height:18px;"></label>
		    <div class="fl p5 w65p">
		        <s:property value=""/>
		    </div>
		</div>
		
		
		<div class="clearfix">
		    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">房屋资源价值信息</h4>
		</div>
		
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">工程造价(万元)</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.projectValue"/>
		    </div>
		</div>
		
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产价值</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.assetValue"/>
		    </div>
		</div>
		<div class="fl w49p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">状态</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.state"/>
		    </div>
		</div>
		<div class="fl w50p clearfix b_bor">
		    <label class="fl p5 w120 lable_bg t_r" for="textfield">备注</label>
		    <div class="fl p5 w65p">
		        <s:property value="#request.houseAsset.note"/>
		    </div>
		</div>
	</div>
</div>

<div id="content" class="fl w100p" style="width: 100%;">
		<div class="jtable-main-container">
			<div class="jtable-busy-panel-background"
				style="display: none; width: 100%; height: 123px;"></div>
			<div class="jtable-busy-message" style="display: none;"></div>
			<div class="jtable-title">
				<div class="jtable-title-text">
					资产列表
				</div>
				<div class="jtable-toolbar"></div>
			</div>
			<table class="jtable">
				<thead>
					<tr>
						<th class="jtable-column-header"
							style="width: 7.5041689827682%;">
							<div class="jtable-column-header-container">
								<span class="jtable-column-header-text">房屋用途</span>
								<div class="jtable-column-resize-handler"></div>
							</div>
						</th>
						<th class="jtable-column-header"
							style="width: 7.5597554196776%;">
							<div class="jtable-column-header-container">
								<span class="jtable-column-header-text">使用单位</span>
								<div class="jtable-column-resize-handler"></div>
							</div>
						</th>
						<th class="jtable-column-header"
							style="width: 7.5597554196776%;">
							<div class="jtable-column-header-container">
								<span class="jtable-column-header-text">实际用房面积（平方米）</span>
								<div class="jtable-column-resize-handler"></div>
							</div>
						</th>
						<th class="jtable-column-header"
							style="width: 7.5597554196776%;">
							<div class="jtable-column-header-container">
								<span class="jtable-column-header-text">所在楼层</span>
								<div class="jtable-column-resize-handler"></div>
							</div>
						</th>
						<th class="jtable-column-header"
							style="width: 7.5597554196776%;">
							<div class="jtable-column-header-container">
								<span class="jtable-column-header-text">其中轨道公安/消防部门/其他</span>
								<div class="jtable-column-resize-handler"></div>
							</div>
						</th>
						<th class="jtable-column-header"
							style="width: 7.5597554196776%;">
							<div class="jtable-column-header-container">
								<span class="jtable-column-header-text">备注</span>
								<div class="jtable-column-resize-handler"></div>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.houseAsset.areaInfos" var="areaInfo" status="st">
						<tr class="jtable-data-row <s:if test='#st.index%2 == 0'>jtable-row-even</s:if>" data-record-key="">
							<td>
								<s:property value="#areaInfo.useType"/>
							</td>
							<td>
								<s:property value="#areaInfo.takeOverDep"/>
							</td>
							<td>
								<s:property value="#areaInfo.reallyArea"/>
							</td>
							<td>
								<s:property value="#areaInfo.inFloor"/>
							</td>
							<td>
								<s:property value="#areaInfo.detail"/>
							</td>
							<td>
								<s:property value="#areaInfo.note"/>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="jtable-column-resize-bar" style="display: none;"></div>
			<div class="jtable-column-selection-container"></div>
		</div>
	</div>
</body>
</html>
