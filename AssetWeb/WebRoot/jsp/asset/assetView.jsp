<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta charset=utf-8 />
<title>资产详细</title>
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
<script src="<%=basePath %>widgets/jtable/jquery.jtable.js" type="text/javascript"></script>
<script src="<%=basePath %>js/html5.js" type="text/javascript"></script>



<style type="text/css">
    body {
        font: 12px/1.5 'Helvetica Neue',Arial,'Liberation Sans',FreeSans,sans-serif;
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


//显示设备列表
function showEquipmentJtable(){
    //$('#childrenId').jtable('destroy',{});
    $('#childrenId').jtable({
        title: '设备履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>equipment/inquery.action'
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            name:{
                title:'设备名称',
                width:'25%'
            },
            type: {
                title: '设备类型',
                width: '25%'
            },
            serviceLife:{
                title:'使用期限',
                width:'20%'
            },
            price:{
                title:'价格',
                width:'20%'
            },
            createDate:{
                title:'创建时间',
                width:'10%',
                display:function(data){
					if(data.record.createDate!=null && data.record.createDate.length>10 ){
						return data.record.createDate.substring(0,10);
					}
					return ;     
            	}
            
            }
        }
    });
    $('#childrenId').jtable('load',{'asset.id_equal_filter':'<%=request.getParameter("id")%>'});
}

//大修更新项目履历表
function showAssetRecord(){
    $('#childrenId').jtable({
        title: '大修更新项目履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>asset/showProjectRecord.action'
            //listAction: '<%=basePath %>assetRecord/inquery.action'
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            'projectName':{
                title:'项目名称',
                width:'10%'
            },
            'maintainContent':{
                title:'维修内容',
                width:'10%'
            },
            'maintainCost':{
                title:'维修费用',
                width:'10%'
            },
            'assetType':{
                title:'资产类型',
                width:'10%'
            },
            'projectAppNo':{
                title:'立项批文号',
                width:'10%'
            },
            'finishDate':{
                title:'竣工日期',
                width:'10%',
                display:function(data){
					if(data.record.finishDate!=null && data.record.finishDate.length>10 ){
						return data.record.finishDate.substring(0,10);
					}
					return ;     
            	} 
            },
            'finishPrice':{
                title:'竣工决算价',
                width:'10%'
            }
        }
    });
    $('#childrenId').jtable('load',{'asset.id_equal_filter':'<%=request.getParameter("id")%>'});
}


//显示资产折旧
function showAssetPriceJtable(){
    $('#childrenId').jtable({
        title: '资产折旧履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>assetPrice/inquery.action'
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            originalValue:{
                title:'原值',
                width:'25%'
            },
            finalPrice: {
                title: '决算价',
                width: '25%'
            },
            periodDepreciation:{
                title:'本期折旧',
                width:'20%'
            },
            netValue:{
                title:'净值',
                width:'20%'
            },
            createDate:{
                title:'创建时间',
                width:'10%'
            }
        }
    });
    $('#childrenId').jtable('load',{'asset.id_equal_filter':'<%=request.getParameter("id")%>'});
}

//显示日常维护
function showAssetMaintenanceCost(){
    $('#childrenId').jtable({
        title: '日常维护履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>maintenanceCost/inquery.action'
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            costTime:{
                title:'结算月度',
                width:'25%',
                display:function(data){
					if(data.record.costTime!=null && data.record.costTime.length>10 ){
						return data.record.costTime.substring(0,10);
					}
					return ;     
            	}
            },
            maintenanceCost: {
                title: '消耗金额',
                width: '25%'
            },
            costHour:{
                title:'工时总和',
                width:'20%'
            },
            createDate:{
                title:'创建时间',
                width:'10%',
                display:function(data){
					if(data.record.createDate!=null && data.record.createDate.length>10 ){
						return data.record.createDate.substring(0,10);
					}
					return ;     
            	}
            }
        }
    });
    $('#childrenId').jtable('load',{'assetNo_equal_filter':$.trim($("#assetCode").text())});
}

//显示大修更新改造
function showAssetOverhaul(){
    $('#childrenId').jtable({
        title: '大修更新改造履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>assetOverhaul/inquery.action'
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            costTime:{
                title:'结算月度',
                width:'25%',
                display:function(data){
					if(data.record.costTime!=null && data.record.costTime.length>10 ){
						return data.record.costTime.substring(0,10);
					}
					return ;     
            	}
             
            },
            maintenanceCost: {
                title: '消耗金额',
                width: '25%'
            },
            costHour:{
                title:'工时总和',
                width:'20%'
            },
            createDate:{
                title:'创建时间',
                width:'10%',
                display:function(data){
					if(data.record.createDate!=null && data.record.createDate.length>10 ){
						return data.record.createDate.substring(0,10);
					}
					return ;     
            	}
            }
        }
    });
    $('#childrenId').jtable('load',{'assetNo_equal_filter':$.trim($("#assetCode").text())});            				
}

//显示资产停用信息
function showAssetStop(){
    $('#childrenId').jtable({
        title: '资产封存/启用履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>stopAsset/inquery.action?jtSorting=stopDate desc'
        },
        fields: {
            stopAssetId: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            leixing:{
            	title: '类型',
                width: '10%',
                display: function (data) {
					if(data.record.state=="0"){
						return "停用";
					}else{
						return "启用";  
					}
				    return ;
				}   
            },
            organ: {
                title: '申请单位',
                width: '10%'
            },
            stopDate:{
                title:'封存日期',
                width:'10%',
                display:function(data){
					if(data.record.stopDate!=null && data.record.stopDate.length>10 ){
						return data.record.stopDate.substring(0,10);
					}
					return ;     
            	}
            },
            stopReason:{
                title:'封存原因',   
                width:'15%'
            },
            startDate:{
                title:'启用日期',
                width:'10%',
                display:function(data){
					if(data.record.startDate!=null && data.record.startDate.length>10 ){
						return data.record.startDate.substring(0,10);
					}
					return ;     
            	} 
            },
            startReason:{
                title:'启用原因',
                width:'20%'
            },
            state:{
            	title:'状态',
				width:'10%',
				display: function (data) {
					if(data.record.state=="1"){
						return "是";
					}else{
						return "否";
					}
				    return ;
				} 
            },
            note:{
            	title:'备注',
                width:'15%'
            }
        }
    });
    $('#childrenId').jtable('load',{'assetNo_equal_filter':$.trim($("#assetCode").text()),'publish_equal_filter':1});
}

//o
function showAssetAllocate(){
    $('#childrenId').jtable({
        title: '资产调拨履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>allocateAsset/inquery.action?jtSorting=allotDate desc'
        },
        fields: {
            allocateAssetId: {  
                key: true,
                create: false,
                edit: false,
                list: false
            },
            outOrg: {
                title: '调出单位',
                width: '15%'
            },
            inOrg:{
                title:'调入单位',
                width:'15%'
            },
            allotReason:{
                title:'调拨原因',
                width:'15%'
            },
            allotDate:{
                title:'调拨日期',
                width:'15%',
                display:function(data){
					if(data.record.allotDate!=null && data.record.allotDate.length>10 ){
						return data.record.allotDate.substring(0,10);
					}
					return ;     
            	}
            },
            note:{
                title:'备注',
                width:'15%'
            }
        }
    });
    $('#childrenId').jtable('load',{'assetNo_equal_filter':$.trim($("#assetCode").text()),'publish_equal_filter':1});
}  

//显示资产报废信息
function showAssetDamage(){
    $('#childrenId').jtable({
        title: '资产报废履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>damageAsset/inquery.action'
        },
        fields: {
            damageAssetId: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            scrapDate:{
                title:'报废日期',
                width:'15%',
                display:function(data){
					if(data.record.scrapDate!=null && data.record.scrapDate.length>10 ){
						return data.record.scrapDate.substring(0,10);
					}
					return ;     
            	}
            },
            accumulatedDepreciation: {
                title: '累计折旧价值',
                width: '10%'
            },
            netValue:{
                title:'净值',
                width:'15%'  
            },
            //计提折旧
            userYear:{
                title:'已用年限',
                width:'10%'
            },
            
            scrapReason:{
                title:'报废理由',
                width:'20%'
            },
            subAsset:{
                title:'附属设备',
                width:'10%'
            },
            note:{
            	title:'备注 ',
                width:'20%'
            }  
        }
    });
    $('#childrenId').jtable('load',{'assetNo_equal_filter':$.trim($("#assetCode").text()),'publish_equal_filter':1});
}


//显示资产借用信息
function showBorrowAsset(){
    $('#childrenId').jtable({
        title: '资产租/借用履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>borrowAsset/inquery.action?jtSorting=loanDate desc'
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            businessType:{
            	title: '类型',
                width: '6%',
                display: function (data) {
                	if(data.record.businessType){
                		if(data.record.businessType=="0")     
							return "租用";
						else
							return "借用";
                	}				    
				    return ;
				}
            },
            loanOrg: {
                title: '租/借出单位',
                width: '12%'
            },
            borrOrg:{
                title:'租/借入单位',
                width:'12%'
            },
            loanDate:{
                title:'租/借出日期',
                width:'10%',
                display:function(data){
					if(data.record.loanDate!=null && data.record.loanDate.length>10 ){
						return data.record.loanDate.substring(0,10);
					}
					return ;   
            	}
            },
            loanReason:{
                title:'租/借用原因',
                width:'15%'
            },
            returnBack:{
                title:'归还状态',   
				width:'10%',
				display: function (data) {
				    if(data.record.returnBack=="1"){      
						return "已归还";
					}else{
						return "未归还";
					}
				    return ;
				}
            },
            returnDate:{
                title:'归还日期',
                width:'10%',
                display:function(data){
					if(data.record.returnDate!=null && data.record.returnDate.length>10 ){
						return data.record.returnDate.substring(0,10);
					}
					return ;   
            	}
            },
            note:{
            	title:'备注',
                width:'10%'
            },
            'attachmentList':{
            	/*  title:'租/借用协议附件',   
            	width:'15%'  ,
            	display:function(data){
            	var attachmentList = data.record.attachmentList;
            	var html = "";
					if(attachmentList!=null){
					    $.each(attachmentList,function(i,n){
					        html+="<a href='"+n.fileRoute+"' target='_blank'>"+n.fileTitle+"</a><br/>";
					    });
						return html;
					}
					return ;   
            	}  */
            	title:'租/借用协议附件',
            	width:'15%',
            	display:function(data){
            		btn = "<a href='<%=basePath%>jsp/asset/assetAttachment.jsp?objectId_equal_filter="+data.record.loanNo+"&type_equal_filter=3' target='_blank')>查看附件</a>";
                    return btn;
            	}
            }
        }
    });
    $('#childrenId').jtable('load',{'assetNo_equal_filter':$.trim($("#assetCode").text()),'publish_equal_filter':'1'});
}

function showDepreciation(){
	$('#childrenId').jtable({
        title: '资产折旧履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: ''
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            assetNo:{
                title:'时间',
                width:'10%'
            },
            loanOrg: {
                title: '折旧值',
                width: '10%'
            }
        }
    });
}

//停用DisableAsset
function showDisableAsset(){
	$('#childrenId').jtable({
        title: '资产停用履历表',
        paging: true, //Enable paging
        pageSize: 5, //Set page size (default: 10)
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
            listAction: '<%=basePath %>disableAsset/inquery.action'
        },
        fields: {
            id: {
                key: true,
                create: false,
                edit: false,
                list: false
            },
            organ:{
                title:'申请单位',
                width:'10%'
            },
            stopDate: {
                title: '停用日期',
                width: '10%',
                display:function(data){
					if(data.record.stopDate!=null && data.record.stopDate.length>10 ){
						return data.record.stopDate.substring(0,10);
					}
					return ;   
            	}
            },
            stopReason: {
            	title: '停用原因',
                width: '10%'
            },
            state: {
            	title: '状态',
                width: '10%',
                display: function (data) {
					if(data.state=="0"){
						return "停用";
					}else{
						return "启用";
					}
				    return ;
				}
            },
            note: {
            	title: '备注',
                width: '10%'
            }
        }
    });
	$('#childrenId').jtable('load',{'assetNo_equal_filter':$.trim($("#assetCode").text()),'publish_equal_filter':'1'});
}

function showInfo(type){
    $("#childrenId").html("");
    $('#childrenId').jtable('destroy');
    if(type==1){
        showEquipmentJtable();
    }else if(type==2){
        showAssetRecord();
    }else if(type==3){
        //showAssetPriceJtable();
    }else if(type==5){
        showAssetMaintenanceCost();
    }else if(type==6){
        showAssetOverhaul();
    }else if(type==7){
        showAssetStop();
    }else if(type==8){
        showAssetAllocate();
    }else if(type==9){
        showAssetDamage();
    }else if(type==10){
        showBorrowAsset();
    }else if(type==11){
    	showDepreciation();
    }else if(type==12){
    	showDisableAsset();
    }

}

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
<body>

<div class="bar clearfix t_r">
    <input type="button" value="关 闭" onclick="window.close();">
</div>
<!--bar end-->

<div id="content" >
<div class="clearfix">
<div class="clearfix">
    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">资产基本信息</h4>  
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产编码</label>
    <div class="fl p5 w65p" id="assetCode">
        <s:property value="#request.asset.assetCode"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产名称</label>
    <div class="fl p5 w65p" id="assetName">
        <s:property value="#request.asset.name"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">数量</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.count"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产单位</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.unit.name"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">型号</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.type"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">产地</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.manufactureCountry"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">生产厂商</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.manufacturer.name"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">供应商</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.supplier.name"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">出厂日期</label>
    <div class="fl p5 w65p">
        <s:date name="#request.asset.madeDate" format="yyyy-MM-dd"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">供应日期</label>
    <div class="fl p5 w65p">
        <s:date name="#request.asset.useDate" format="yyyy-MM-dd"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">设计使用年限（年）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.useLife"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">预期使用寿命（年）</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.expectancyLife"/>
    </div>
</div>



<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">大类</label>
    <div class="fl p5 w65p">
        <s:property value="#request.main.name"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">保修期</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.warrantyPeriod"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">中类</label>
    <div class="fl p5 w65p">
        <s:property value="#request.sub.name"/>
    </div>
</div>


<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">小类</label>
    <div class="fl p5 w65p">
        <s:property value="#request.detail.name"/>
    </div>
</div>

<!--
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield"> 资产权属信息</label>
    <div class="fl p5 w65p">
           --------------资产权属信息-----------
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产价值信息</label>
    <div class="fl p5 w65p">
        bbbbbbbbbbbbbbbbbbb
    </div>
</div>
 -->

<div class="clearfix">
    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">资产所属信息</h4>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">使用责任人</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.assetOwnerInf.user"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">权属责任人</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.assetOwnerInf.owner"/>
    </div>
</div>



<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">开始使用日期</label>
    <div class="fl p5 w65p">
        <s:date name="#request.asset.assetOwnerInf.beginUseDate" format="yyyy-MM-dd"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">停止使用日期</label>
    <div class="fl p5 w65p">
        <s:date name="#request.asset.assetOwnerInf.stopUseDate" format="yyyy-MM-dd"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">移交时间</label>
    <div class="fl p5 w65p">
        <s:date name="#request.asset.assetOwnerInf.receiveDate" format="yyyy-MM-dd"/>
    </div>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">维护部门</label>
    <div class="fl p5 w65p">
        <s:property value="#request.department.name"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">使用单位</label>
    <div class="fl p5 w65p">
        <s:property value="#request.useOrganization.name"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">权属单位</label>
    <div class="fl p5 w65p">
        <s:property value="#request.ownerOrganization.name"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">线路</label>
    <div class="fl p5 w65p">
        <s:property value="#request.line.name"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">车站</label>
    <div class="fl p5 w65p">
        <s:property value="#request.station.name"/>
    </div>
</div>

<div class="clearfix">
    <h4 class="w120 p5 t_r" for="textfield" style="background-color: #F7F9FC;">资产价值信息</h4>
</div>

<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">出厂价格（万元）</label>
    <div class="fl p5 w65p">
        <s:property value="%{getFormattedMoney(#request.asset.factoryPrice)}"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">合同价格（万元）</label>
    <div class="fl p5 w65p">
        <s:property value="%{getFormattedMoney(#request.asset.contractPrice)}"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">原值(万元)</label>
    <div class="fl p5 w65p">
        <s:property value="%{getFormattedMoney(#request.asset.originalValue)}"/>
    </div>
</div>





<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">累计折旧（万元）</label>
    <div class="fl p5 w65p">
        <s:property value="%{getFormattedMoney(#request.asset.accumulatedDepreciation)}"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">净值（万元）</label>
    <div class="fl p5 w65p">
        <s:property value="%{getFormattedMoney(#request.asset.netValue)}"/>
    </div>
</div>

<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">资产使用状态</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.state.useState"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">移交标识</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.completeTransAssetType"/>
    </div>
</div>
<div class="fl w50p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">折旧方法</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.depreciationMethod"/>
    </div>
</div>
<div class="fl w49p clearfix b_bor">
    <label class="fl p5 w120 lable_bg t_r" for="textfield">铭牌张贴位置</label>
    <div class="fl p5 w65p">
        <s:property value="#request.asset.state.nameplateSite"/>
    </div>
</div>


</div>

</div>


<div class="bar clearfix">
</div>


<!--table-->
<div>
    <table class="table_1" width="100%" >
        <thead>
        <tr id="tabArea">
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(1);">资产设备情况</a></td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(2);">大修更新项目履历</a></td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(5);">日常维修费用</a></td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(6);">大修更新改造(物资消耗)</a></td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(7);">资产封存/启用</a> </td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(12);">资产停用</a> </td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(8);">资产调拨</a> </td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(9);">资产报废</a> </td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(10);">资产租/借用</a> </td>
            <td class="t_c"><a href="javascript:void(0);" onclick="showInfo(11);">资产折旧</a> </td>
        </tr>
        </thead>
    </table>
    <table id="childrenId" width="100%;" height="120%;" style="margin-bottom: 100px;"></table>
</div>
<!--table end-->




</body>
</html>
