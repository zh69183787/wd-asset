<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>备品备件详细</title>

<link href="<%=basePath%>css/default/reset.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/default/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/our.css" rel="stylesheet" type="text/css">
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon" href="<%=basePath%>css/default/images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=basePath%>css/default/images/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=basePath%>css/default/images/apple-touch-icon-114x114.png">
<script type="text/javascript" src="<%=basePath%>js/jay.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/formalize.css">
<link href="<%=basePath%>css/pages.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>widgets/jtable/css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>widgets/jtable/themes/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>
<script src="<%=basePath%>widgets/jtable/jquery.jtable.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/html5.js" type="text/javascript"></script>
<style type="text/css">
body {
	font: 12px/1.5 'Helvetica Neue', Arial, 'Liberation Sans', FreeSans,
		sans-serif;
}

.w120 {
	width: 170px;
}
</style>
<script type="text/javascript">
</script>
</head>

<html>
<body style="font-family:'Microsoft YaHei';">
	<div class="bar clearfix t_r">
		<input type="button" value="关 闭" onclick="window.close();">
	</div>
	<!--bar end-->

	<div id="content">
		<div class="clearfix">
			<div class="clearfix">
				<h4 class="w120 p5 t_r" for="textfield"
					style="background-color: #F7F9FC;">备品备件基本信息</h4>
			</div>

			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">备品备件资产编码</label>
				<div class="fl p5 w65p" id="spareAssetNo">
					<s:property value="#request.spareParts.spareAssetNo" />
				</div>
			</div>
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">备品备件名称</label> 
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.spareAssetDescription" />
				</div>
			</div>
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">数量</label>  
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.assetQty" />
				</div>
			</div>

			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">单位</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.unitCode" />
				</div>
			</div>
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">规格型号</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.finGzxh" />
				</div>
			</div>

			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">产地</label> 
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.productArea" />
				</div>
			</div>
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">生产厂商</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.manufacturer" />
				</div>
			</div>

			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">供应商</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.supplier" />
				</div>
			</div>
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px;">出厂日期</label> 
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.leaveFactory" />
				</div>
			</div>
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">供应日期</label>  
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.supplyDate" />
				</div>
			</div>
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">设计使用年限（年）</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.designUseLife" />
				</div>
			</div>



			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">保修期至</label>  
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.warrantyPeriod" />
				</div>
			</div>
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">大类</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.at1" />
				</div>
			</div>

			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">中类</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.at2" />
				</div>
			</div>


			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">小类</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.at3" />
				</div>
			</div>
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">竣工移交资产类型标示</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.completeTransAssetType" />
				</div>
			</div>


			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">技术、规格、操作资料及清单</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.haveList" />
				</div>
			</div>

			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">设备清单</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.equipList" />
				</div>
			</div>
			
			<div class="clearfix">
				<h4 class="w120 p5 t_r" for="textfield"
					style="background-color: #F7F9FC;">备品备件所属信息</h4>
			</div>
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">项目名称</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.projectName" />
				</div>
			</div>
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">立项或可研批复文号</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.projectAppDocNo" />
				</div>
			</div>



			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">项目合同编号</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.projectContractNo" />
				</div>
			</div>
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">移交时间</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.transferDate.toString().substring(0,10)" />
				</div>
			</div>

			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">线路</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.locLine" />
				</div>
			</div>

			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">车站</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.locationCode" />
				</div>
			</div>

			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">权属单位</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.ownershipUnitName" />
				</div>
			</div>
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">使用单位</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.useUnitName" />
				</div>
			</div>

			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">维护部门</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.maintainDepartName" />
				</div>
			</div>
			
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px; height:18px;"></label>
				<div class="fl p5 w65p" style="height:18px;">
					<s:property value="" />
				</div>
			</div>

			<div class="clearfix">
				<h4 class="w120 p5 t_r" for="textfield"
					style="background-color: #F7F9FC;">备品备件价值信息</h4>
			</div>

			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">出厂价(元)</label>
				<div class="fl p5 w65p">
					<s:property
						value="%{getFormattedMoney(#request.spareParts.factoryPrice)}"></s:property>
				</div>
			</div>

			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">合同价(元)</label>
				<div class="fl p5 w65p">
					<s:property
						value="%{getFormattedMoney(#request.spareParts.contractPrice)}"></s:property>
				</div>
			</div>
			<div class="fl w49p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">原值(元)</label>
				<div class="fl p5 w65p">
					<s:property
						value="%{getFormattedMoney(#request.spareParts.originalValue)}"></s:property>
				</div>
			</div>
			
			<div class="fl w50p clearfix b_bor">
				<label class="fl p5 w120 lable_bg t_r" for="textfield"
					style="width: 170px">备注</label>
				<div class="fl p5 w65p">
					<s:property value="#request.spareParts.remarks" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
