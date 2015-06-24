<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.wonders.jeecms.ShowJeecmsInfo"%>
<%@ page import="com.wonders.jeecms.utils.StringUtil"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.FileInputStream"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); 

String downDate = request.getParameter("downDate");
if(downDate!=null){
	downDate = "&nbsp;&nbsp;下线日期:"+downDate;
}else{
	downDate = "";
}

String jeecmsUrl="";
Properties properties = new Properties();
String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
try {
	FileInputStream fis = new FileInputStream(configPath);
	properties.load(fis);
	jeecmsUrl = properties.getProperty("urlCms");			
} catch (Exception e) {
	//e.printStackTrace();
	jeecmsUrl=basePath;
}

String contentId=request.getParameter("content");
ShowJeecmsInfo dwgk=new ShowJeecmsInfo(request,response);

dwgk.getInfoList("JEECMS.ADD_CONTENT_VIEWER","{'contentId':'"+contentId+"'}");
	
JSONObject contentObj=null;
JSONArray contentList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'contentId':'"+contentId+"'}");
String pageTitle=null;
String channelId = "";
if(contentList!=null){
	contentObj=contentList.getJSONObject(0);
	channelId = contentObj.getString("channelId");
	pageTitle=contentObj.getString("title");
  	if(!contentObj.getString("shortTitle").equals("null")){
  		pageTitle= contentObj.getString("shortTitle");
  	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息内容</title>
<link rel="stylesheet" href="css/formalize.css" />
<link rel="stylesheet" href="css/page.css" />
<link rel="stylesheet" href="css/default/imgs.css" />
<link rel="stylesheet" href="css/reset.css" />
<!-- link href="../css/jeecms/newsStyle-blue.css" rel="stylesheet" type="text/css" id="cssLink"></link-->
<!--[if IE 6.0]>
         <script src="js/iepng.js" type="text/javascript"></script>
         <script type="text/javascript">
              EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
         </script>
     <![endif]-->
<script src="<%=basePath %>js/jquery-1.9.0.min.js"></script>

<script src="<%=basePath %>js/jquery.formalize.js"></script>
<script type="text/javascript">
	
	
	function winopen() { 
		window.open("viewerList.jsp?content=<%=contentId%>","_blank","location=no,width=400,height=500");
	}
	
	<% if("1712".equals(channelId)||"1713".equals(channelId)||"1714".equals(channelId)||"1732".equals(channelId)||"1733".equals(channelId)){%>
	$(function(){
		$.ajax({
			type: 'POST',
			url: '/portal/jeecms/findIfWsLeader.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){
				if(!obj.ifLeader){
					$("#log").hide();
				}
			}
		});
	})
	<%}%>
</script>
</head>

<body style="font-family:'Microsoft YaHei';">
        <div class="main"> 
          <!--Ctrl-->
          <div class="ctrl clearfix">
            <div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>		
            <div class="posi fl">
              <ul>
                <li class="fin">信息发布</li>
              </ul>
            </div>
            <div class="fr lit_nav">
              <ul>
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>
                <li><a class="query" href="#">查询</a></li>
              </ul>
            </div>
          </div>
          <!--Ctrl End-->
          <div class="clearfix pt45"> 
          	<!--News_list-->
            <div class="mb10 right_main News_list" style="width:99%">
              <header class="clearfix mb10">
              	<h4 class="fl"><%=contentObj.getString("channelName") %></h4>
                <!-- div class="fr p10">
                  <input type="text" id="datepicker" />
                  <input type="text" id="text_inline" name="text_inline" />
                  <input type="button" value="检索" />
                </div-->
              </header>
              <div class="p10 view">
              	<h3 class="t_c"><%=contentObj.getString("title") %></h3>
              	<h5 class="t_c"></h5>
                <h6 class="t_r p10 b_dash">
<%
if(!contentObj.getString("origin").equals("null")){
%>
						 <span class="mr8">From:<%=contentObj.getString("origin") %></span>
<%
}
%>
                <span class="mr5">发布日期:<%=contentObj.getString("releaseDate") %><%=downDate %></span></h6>
                <div class="b_dash p10 mb10">
                	<h6 class="zhaiyao p10">
<%
if(!contentObj.getString("description").equals("null")){
%>
		     		摘要：<%= contentObj.getString("description")%>
<%
}else if(!contentObj.getString("txt").equals("null")){
%>
		     		摘要：<%= StringUtil.splitAndFilterString(contentObj.getString("txt"),150)%>
<%
}
%>
					</h6></div>
                <div class="p10">
<%
if(contentObj.getString("hasTitleImg").equals("1")){
%>
		     		<div class="t_c mb10"><img name="" src="<%= contentObj.getString("titleImg").replaceAll("/jeecms/u/",jeecmsUrl+"/u/").replaceAll("/attach",jeecmsUrl+"/attach")%>" width="500" alt=""/></div>
<%
}
%>

<%
if(!contentObj.getString("txt").equals("null")){
%>		     
	     			 <p class="mb10"> <%= contentObj.getString("txt").replaceAll("/jeecms/u/",jeecmsUrl+"/u/").replaceAll("href=\"/","href=\""+jeecmsUrl+"/").replaceAll("src=\"/","src=\""+jeecmsUrl+"/")%></p>
<% 
}
%>

<% 
JSONArray fileList=null;
JSONObject fileObj=null;
String filePath="";
String imgType="";
fileList=dwgk.getInfoList("JEECMS.GET_FILE_LIST","{'contentId':'"+contentId+"'}");
if(fileList!=null){
%>
                    <div class="attach mb10">
                        <dl class="clearfix">
                        <dt class="fl">附件：</dt>	
                        <dd class="fl">     
<%
	for(int i=0;i<fileList.size();i++){
		fileObj=fileList.getJSONObject(i);
		filePath=fileObj.getString("filePath");
		if(filePath.indexOf(".bmp")!=-1||filePath.indexOf(".jpg")!=-1||filePath.indexOf(".gif")!=-1||filePath.indexOf(".png")!=-1){
			imgType="jpg";
		}else if(filePath.indexOf(".pdf")!=-1){
			imgType="pdf";
		}else if(filePath.indexOf(".xls")!=-1){
			imgType="excel";
		}else if(filePath.indexOf(".ppt")!=-1){
			imgType="powerpoint";
		}else if(filePath.indexOf(".rm")!=-1||filePath.indexOf(".avi")!=-1){
			imgType="mpeg";
		}else{
			imgType="word";
		}
   %>
   
   							<a href="<%=jeecmsUrl%><%=filePath.replaceAll("/jeecms/u/","/u/") %>" target="_blank"><!-- img height="28px"   alt="<%=fileObj.getString("fileName") %>" src="../css/jeecms/images/<%=imgType%>.gif" align="absMiddle" border="0"/--><%=fileObj.getString("fileName") %></a> 
  <%
	}
	%>
	                    </dd>
                        </dl>
                    </div>
	<%
	
}
  %>

<%

JSONObject viewerObj=null;
int nViewerNum=0;
JSONArray viewerList=dwgk.getInfoList("JEECMS.GET_VIEWER_NUM","{'contentId':'"+contentId+"'}");
if(viewerList!=null){
	viewerObj=viewerList.getJSONObject(0);
	nViewerNum=viewerObj.getInt("viewerNum");
}	   
	
 %>  

                    <div class="zhaiyao" align="right" id="log">已有<a href="#" onclick="winopen()" class="mr5"><%=nViewerNum%></a>人浏览</div>
                    <!-- div class="zhaiyao">关键字：<a href="#" class="mr5">123</a><a href="#" class="mr5">123</a><a href="#" class="mr5">123</a></div-->
                </div>
              </div>
              <div class="mb10">
              </div>
            </div>
            <!--News_list End--> 
            <!--News_list right-->
            <!-- 
            <aside class="mb10 related panel_5">
              <div class="tit">
                <h4>相关栏目</h4>
              </div>
              <div class="con">
                <ul class="list">
                  <li><a href="#">暂无相关信息</a></li>
                  <li><a href="#"></a></li>
                  
                </ul>
              </div>
            </aside>
             -->
            <!--News_list right End--> 
          </div>
        </div>
</body>

</html>
<% 
}else{
	response.sendRedirect(basePath+path);
}
%>