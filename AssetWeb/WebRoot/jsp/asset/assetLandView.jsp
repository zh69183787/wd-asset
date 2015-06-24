<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>土地资源详细</title>
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
    .w120{
    	width: 170px;
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
<div class="clearfix" >
    <h4 class="w120 p5 t_r"  for="textfield" style="background-color: #F7F9FC;">土地资源基本信息</h4>
</div>


<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">资产名称</label>
    <div class="fl p5 w65p" id="assetName">
        <s:property value="#request.landAsset.assetName"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">资产类型</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.assetType"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">位置码</label>
    <div class="fl p5 w65p">
        <s:property value="#request.location"/>
    </div>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">面积（平方米）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.area"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">权属证编码</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.warrantsNo"/>
    </div>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">土地使用证</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landUsecertificate"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">房产证编码</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.ownCertificate"/>
    </div>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">地块性质</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landStatus" />
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px;">土地坐落位置</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landLocation" />
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">地块代号</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landDh"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">用地批准文号</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.approveNo"/>
    </div>
</div>



<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">用地批准书号或土地决定书文号</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.approveNoOrDecide"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">规划土地用途</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landPlaning"/>
    </div>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">建筑占地面积（平方米）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.buildArea"/>
    </div>
</div>


<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">土地总面积（平方米）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landTotalArea"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">其中地面征地（平方米）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landRequisitionArea"/>
    </div>
</div>


<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">其中带征地面积（平方米）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.inclandRequisitionArea"/>
    </div>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px;">其中地下征地或征用道路</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.undergroundRequisitionArea"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">有无闲置或出租场地</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.hasOpenspace"/>
    </div>
</div>


<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">闲置或出租场地（平方米）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.openSpacearea"/>
    </div>
</div>


<div class="fl w50p clearfix b_bor" style="display:none;">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">资产编码</label>
    <div class="fl p5 w65p" id="assetCode">
        <s:property value="#request.landAsset.assetNo"/>
    </div>
</div> 


<div class="clearfix">
    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">土地资源所属信息</h4>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">合同编号</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.contractNo"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">卡片号码</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.cardId"/>
    </div>
</div>



<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">合同名称</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.contractName" />
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">项目名称</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.project" />
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">权属单位</label>
    <div class="fl p5 w65p">
        <s:property value="#request.organizatioName"/>
    </div>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">土地坐落位置</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.landLocation"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">移交部门</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.transDep"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">接管部门</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.takeOverDep"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">分类</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.classificationName"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">单位</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.unitCode"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">建设类项目名称</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.builderProject"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px;">线路</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.lineCodeId"/>
    </div>
</div>

<div class="clearfix">
    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">土地资源价值信息</h4>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">账面价值(元)</label>
    <div class="fl p5 w65p">
    	<s:property value="%{getFormattedMoney(#request.landAsset.bookValue)}"></s:property>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">资产价值(元)</label>
    <div class="fl p5 w65p">
        	<s:property value="%{getFormattedMoney(#request.landAsset.assetValue)}"></s:property>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"style="width: 170px">状态</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.state"/>
    </div>
</div>





<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">征地动迁总费用(元)</label>
    <div class="fl p5 w65p">
    	<s:property value="%{getFormattedMoney(#request.landAsset.landTotalFee)}"></s:property>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">征地动迁费用(元)</label>
    <div class="fl p5 w65p">
    	<s:property value="%{getFormattedMoney(#request.landAsset.landRequisitionTotalfee)}"></s:property>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">带征地费用(元)</label>
    <div class="fl p5 w65p">
    	<s:property value="%{getFormattedMoney(#request.landAsset.inlandRequisitionTotalfee)}"></s:property>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield" style="width: 170px">备注</label>
    <div class="fl p5 w65p">
        <s:property value="#request.landAsset.note"/>
    </div>
</div>



</div>

</div>
</body>
</html>
