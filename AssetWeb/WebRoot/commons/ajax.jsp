<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script src="${ctx}/styles/smy/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
	
	/**
	 *	参数说明：
	 *		url:发送请求的地址
	 *		date:发送到服务器的数据。格式举例：{name1:"value1",
	 									  name2:["value1","value2","value3"],
	 									  name3:[["value311","value312"],"value321",["value331","value332"]]}
	 *		successFn:请求完成后回调函数
	 *      dataType:预期服务器返回的数据类型
	 *			"xml": 返回 XML 文档，可用 jQuery 处理。 
	 *			"html": 返回纯文本 HTML 信息；包含 script 元素。 
	 *			"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。 
	 *			"json": 返回 JSON 数据 。 
	 *			"jsonp": JSONP 格式
	**/
   
   function ajaxRemote (url,date,successFn,dataType) {
       $.ajax({
	       type:"POST",						
	       url:url,
	       dataType:dataType,
	       data:date,
	       async:true,						
	       contentType:"application/x-www-form-urlencoded",   //发送到服务器的编码类型
	       beforeSend:function(XMLHttpRequest) {
	           		maskDocAllWithMessage("Wait...");
	           },
	       success:successFn,				
	       complete:function(XMLHttpRequest,textStatus) {
	           		unmaskDocAll();
	           },
	       error:function(msg) {
	       		alert("调用失败！"+msg);
	          }
       });
   }

</script>