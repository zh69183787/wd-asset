<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>索引页面</title>
<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>

<script type="text/javascript">

function quickInsert(type){

	$.ajax({
		url:'<%=basePath%>report/quickInsert.action',
		data:{
			type:type
		},
		method:'post',
		error:function(a,error,c){
			alert('error');
		},
		success:function(data){
			alert(data);
		}
	});
	
}

</script>


</head>
<body style="font-family:'Microsoft YaHei';">
	<ul>
		<li>
			资产静态首页
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(1);">1)资产概况</a>&nbsp;&nbsp;&nbsp;
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(2);">2)权属单位资产价值分布</a>&nbsp;&nbsp;&nbsp;
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(3);">3)线路资产价值分布</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(4);">4)使用单位资产价值分布</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(5);">5)资产形成年份分析</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(6);">6)重要资产线路分布</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(7);">7)重要资产专业分布</a>&nbsp;&nbsp;&nbsp;
		</li>
	</ul>
	
	
	
	<ul>
		<li>
			资产动态首页
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(11);">11)资产动态</a>&nbsp;&nbsp;&nbsp;
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(12);">12)大修更新改造项目投资年变化</a>&nbsp;&nbsp;&nbsp;
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(13);">13)资产重要指标情况</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(14);">14)报废资产实际寿命统计</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(15);">15)资产分类统计 </a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(16);">16)大修更新改造按线路分布情况</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(17);">17)大修更新改造专业分布执行情况趋势</a>&nbsp;&nbsp;&nbsp;
		</li>
	</ul>
	
	
	<ul>
		<li>
			统计报表
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(21);">21)资产实物汇总统计</a>&nbsp;&nbsp;&nbsp;
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(21);">22)资产类别价值统计（和21)资产实物汇总统计是同一张表）</a>&nbsp;&nbsp;&nbsp;
		</li>
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(23);">23)运营线路价值统计</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(24);">24)使用单位价值统计</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(25);">25)权属单位价值统计</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(26);">26)建设项目价值统计</a>&nbsp;&nbsp;&nbsp;
		</li>
		
		<li>
			<a href="javascript:void(0);" onclick="quickInsert(27);">27)大修更新项目预算</a>&nbsp;&nbsp;&nbsp;
		</li>
	</ul>
	
	
	
	
	
</body>
</html>