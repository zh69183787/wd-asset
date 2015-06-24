<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>建设价值统计</title>
<script type="text/javascript">
	$(function(){
		$("tr[id='dataArea']:even").attr("style","background: none repeat scroll 0 0 #F0F0F0;height:30px; padding: 2px;");
	});
	</script>		
	</head>
	<body class="" style="font-family:'Microsoft YaHei';">
	
		<div class="search_1 p8">
		
			<input type="hidden" value="${pageNo }" name="pageNo" id="pageNo"/>
        	<div class="clearfix">
            	<div class="fl w30p clearfix">
                	<div class="fl p5 w30p"><label for="textfield">年度</label></div>
                	<div class="fl p5 w65p">
                		<input type="text" name="assetCode" id="assetCode" value="" class="input_large">
                	</div>
                </div>
            	
                <div class="fl w30p clearfix">
                	<div class="fl p5 w100p">
                		<input type="button" name="assetName" id="assetName" value="查询" class="input_large" >
	                	<input type="button" name="assetName" id="assetName" value="统计" class="input_large" >
	                	<input type="button" name="assetName" id="assetName" value="导出" class="input_large" >
	                	<input type="button" name="assetName" id="assetName" value="打印" class="input_large" >
                	</div>
               	</div>
                </div>
            </div>
    </div>
	
	
		<table id="content" style="font-size: 11px;width: 100%;border: 1px solid #C8C8C8;background-color: #FFFFFF;">
			<thead>
				<tr class="trHead">
					<td class="t_c">序号</td>
					<td class="t_c">资产类别（大类）</td>
					<td class="t_c">资产项数（初始）</td>
					<td class="t_c">原值（初始）（元）</td>
				</tr>
			</thead>
			<s:if test="#request.list!=null && #request.list.size()>0">
				<s:iterator id="stat" value="#request.list" status="st">
					<tr id="dataArea" style="height:30px; padding: 2px;">
						<td width="10%;" class="t_c" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#st.index+1"/></td>
						<td width="10%;" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.name"/></td>
						<td width="15%;" class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="#stat.count " /></td>
						<td width="10%;" class="t_r" style="border-left: 1px dotted #BEBEBE;padding: 5px;"><s:property value="%{getFormattedMoney(#stat.originalValue)}"/></td>
					</tr>
				</s:iterator>
			</s:if>
			
		</table>
	</body>
</html>