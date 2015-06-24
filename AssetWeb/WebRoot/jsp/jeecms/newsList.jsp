<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>列表页面</title>
<link rel="stylesheet" href="<%=basePath %>jsp/jeecms/css/formalize.css" />

<link rel="stylesheet" href="<%=basePath %>jsp/jeecms/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>jsp/jeecms/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>jsp/jeecms/css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       <script src="<%=basePath %>js/jquery-1.9.0.min.js"></script>
        <script src="<%=basePath %>js/jquery-ui-1.9.2.min.js"></script>
        <script src="<%=basePath %>jsp/jeecms/js/show.js"></script>
        <script src="<%=basePath %>/js/html5.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		
		<script type="text/javascript">
		$(document).ready(function(){
			$("#subList").height($("#newList").height() - $("#relativeSub").height() - 4);
			loadShow();
		});
		
		$(window).resize(function(){
		 	$("#subList").height($("#newList").height() - $("#relativeSub").height() - 4);
		});
		
		function goPage(page,totalPage){
		    if((page<=totalPage)&&(page>0)){
			$("#page").val(page);
			$("#form").submit();
			}
		}
		
		function goPageRedirect(totalPage){
			if(!$("#redirectPage").val().match(/^[0-9]*$/)){
       			alert("请输入数字");
       			$("#redirectPage").val("");
       			$("#redirectPage").focus();
       			return;
       		}
	       	if(	($("#redirectPage").val()<=totalPage) &&($("#redirectPage").val()>0)){
				$("#page").val($("#redirectPage").val());			
			}else if($("#redirectPage").val()>totalPage){
				$("#page").val(totalPage);
			}else if($("#redirectPage").val()<1){
				$("#page").val(1);
			}
			$("#form").submit();
		}
		
		//点击栏目，显示信息
		$(function(){
			//alert($("#urlist").children("li").length);
			$("#urlist").children("li").each(function(){
				var channelId = $(this).attr("id");
				if($(this).attr("id")==$("#channelId").val()){
					$(this).addClass("selected");
				}
				$(this).click(function(){
				
					$("#page").val("1");			//点击标签后，设置页码为1
					$("#searchWord").val("");		//点击标签后，设置所搜条件为空
					
					$("#urlist").children("li").removeClass("selected");
					$(this).addClass("selected");
					
					//根据channelId查询
					$.ajax({
						url:"<%=basePath%>/jeecms/findDateByPage.action?random="+Math.random(),
						type:"post",
						dataType:'json',
						cache : false,
						data:{		
							channelId:channelId					
						},
						error:function(){alert('系统连接失败，请稍后再试！')},
						success: function(obj){
							var list = obj.list;
							var dataHtml ="";
							var pageCountHtml ="记录总数：";
							
							//该栏目下有数据
							if(obj!="undefined" && list!=null && list.length>0){	
							
								for(var i=0; i<list.length; i++){
									if(parseInt(list[i].isrecommend)==-1){
										dataHtml+= "<li><dl>"+
													"<dt class='clearfix'>"+
														"<span class='type'><font style='float: left;color: red;'>[置顶]</font>["+list[i].channelName+"]</span> <a target='_blank' href='../jeecms/contentDetail.jsp?content="+list[i].contentId+"'>"+list[i].title+"</a>"+
				                            			"<span>"+list[i].releaseDate+"</span>"+
			                                		"</dt>"+
			                                		"<dd>"+list[i].description+"</dd>"+    
												"</dl></li>";
									}else{
									
									      dataHtml+= "<li><dl>"+
													"<dt class='clearfix'>"+
														"<span class='type'>["+list[i].channelName+"]</span> <a target='_blank' href='../jeecms/contentDetail.jsp?content="+list[i].contentId+"'>"+list[i].title+"</a>"+
				                            			"<span>"+list[i].releaseDate+"</span>"+
			                                		"</dt>"+
			                                		"<dd>"+list[i].description+"</dd>"+    
												"</dl></li>";
											
												
									}
								
										
								}
								pageCountHtml += obj.pageInfo.totalRow;
								$("#redirectPage").val("1");	//有数据则设置直接跳转的页码为1
								
							
								var  pageNumber= "<input value='1' type='number' id='redirectPage' name='redirectPage' class='input_tiny' />/"+obj.pageInfo.totalPage+"<a style='display:inline;' href='javascript:goPageRedirect();'>转到</a>";
								$("#pageNumber").html(pageNumber);
								//$("#headPage").attr("href","javascript:goPage('1','"+obj.pageInfo.totalPage+"')");		//首页
								$("#previousPage").attr("href","javascript:goPage('1','"+obj.pageInfo.totalPage+"')");		//上一页
								$("#nextPage").attr("href","javascript:goPage('2','"+obj.pageInfo.totalPage+"')");		//下一页
								$("#endPage").attr("href","javascript:goPage('"+obj.pageInfo.totalPage+"','"+obj.pageInfo.totalPage+"')");		//尾页
								
								
							}else{
								$("#redirectPage").val("0");	//没有数据则设置直接跳转的页码为0
							  pageNumber= "<input value='0' type='number' id='redirectPage' name='redirectPage' class='input_tiny' />/0<a style='display:inline;' href='javascript:goPageRedirect();'>转到</a>";
								$("#pageNumber").html(pageNumber);
								$("#headPage").attr("href","javascript:void(0);");		//首页
								$("#previousPage").attr("href","javascript:void(0);");	//上一页
								$("#nextPage").attr("href","javascript:void(0);");		//下一页
								$("#endPage").attr("href","javascript:void(0);");		//尾页
								
								
								
					            pageCountHtml += "0";
					            //dataHtml+= "<li><dl><dt class='clearfix'><span class='type'></span><span></span></dt><h4 style='color:black;'>无相关信息</h4></li></dl>";
					            dataHtml += "<h4 style='color:black;'>无相关信息</h4>";
							}
							$("#channelId").val(channelId);			//切换栏目，设置channelId
							$("#dataList").html(dataHtml);
							$("#pageCount").html(pageCountHtml);
							
						}	  
					});	
					
				});
			});
		});
		
		$(function(){
			if($("#asideInfo").text()=="无相关信息。"){
				$("#aside").hide();
				$("#newList").attr("style","width:99%;");
			}
		});
	</script>

</head>

<body style="font-family:'Microsoft YaHei';">
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>jsp/jeecms/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">信息发布</li>
                </ul>
            </div>
             <div style="display:none;" class="fr lit_nav nwarp">
            	<ul>
                    <li class="selected"><a class="print" href="#">打印</a></li>
                    <li><a class="express" href="#">导出数据</a></li>
                    <li class="selected"><a class="table" href="#">表格模式</a></li>
                    <li><a class="treeOpen" href="#">打开树</a></li>
                    <li><a class="filterClose" href="#">关闭过滤</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <div class="clearfix pt45">
        	<!--News_list-->
            <div id="newList" class="mb10 right_main News_list" style="width:99%">
            	<header class="clearfix mb10">
            		<s:if test="#request.channelList!=null && #request.channelList.size()!=0">
		                <div class="tabs_2" style="height: 35px;">
		                  		<div style="height: 10px;"></div>
		                  	<ul id="urlist" >
					        	<s:iterator value="#request.channelList" id="subject" status="st">
					        		<li id="<s:property value='#subject.channelId'/>">
					        			<a href="#" onclick="javascript:void(0);">
					        				<span>
					        					<s:property value="#subject.channelName"/>
					        				</span>
					        			</a>
					        		</li>
					        	</s:iterator>
				            </ul>
				            <form action="<%=basePath %>/jeecms/findNewsByPage.action" id="form" method="post">
				                    <div class="fr p10" style="padding-top: 1px;float: right;">
				                      	<input type="text" name="searchWord" id="searchWord" value="<s:property value="#request.infoSearchVo.searchWord"/>" />
				                      	<input type="submit" value="检索" />
				                      	<input type="hidden" name="page" id="page" value="<s:property value="#request.pageResultSet.pageInfo.currentPage"/>"/>
										<input type="hidden" name="channelId" id="channelId" value="<s:property value='#request.channelId'/>"/>
										<input type="hidden" name="parentId" id="parentId" value="<s:property value='#request.parentId'/>"/>
				                    </div>
			               		</form>
				        </div>
            		 </s:if>
            		 <s:else>
            		 	<h4 class="fl">
		                	<s:if test='infoSearchVo.channelId.indexOf(",")>0'>
		                	
		                	</s:if>
		                	<s:elseif test="pageResultSet.list.get(0).channelName!=null">
		                		<s:property value="pageResultSet.list.get(0).channelName" />
		                	</s:elseif>
		                	<s:else>
			                	无相关信息。
		                	</s:else>
	                	</h4>
	                	<form action="<%=basePath %>jeecms/findNewsByPage.action" id="form" method="post">
			                    <div class="fr p10">
			                      <input type="text" name="searchWord" id="searchWord" value="<s:property value="#request.infoSearchVo.searchWord"/>" />
			                      <input type="submit" value="检索" />
			                      	<input type="hidden" name="page" id="page" value="<s:property value="#request.infoSearchVo.page"/>"/>
									<input type="hidden" name="channelId" id="channelId" value="<s:property value="#request.infoSearchVo.channelId"/>"/>
			                    </div>
		               </form>
            		 </s:else>
                </header>   
                <div class="list" >   
                    <ul id="dataList">
                    <s:if test="#request.pageResultSet.list.size==0">
                    	<h4 style='color:black;'>无相关信息</h4>
                    </s:if>
                    <s:else>
                    	<s:iterator value="#request.pageResultSet.list" id="news">
	                    	 <li>
	                            <dl>
	                                <dt class="clearfix">
	                                    <span class="type">
	                                    	<s:if test="#news.isrecommend=='-1'"><font style="float: left;color: red;">[置顶]</font></s:if>
	                                    	[<s:property value="#news.channelName" />]
	                                    </span> 
	                                    <a target="_blank" href="<%=basePath %>jsp/jeecms/contentDetail.jsp?content=<s:property value="#news.contentId" />"><s:property escape="0" value="#news.title" /></a>
	                                    <span><s:property value="#news.releaseDate" /></span>
	                                </dt>
	                                <dd><s:property escape="0" value="#news.description" /></dd>
	                            </dl>
	                        </li>
	                      </s:iterator>
                    </s:else>
                    </ul>
                </div>
                
                
                <div class="pager mb10 clearfix">
                	<span class="fl" id="pageCount">记录总数：<s:property value="pageResultSet.pageInfo.totalRow"/></span>
                    <div class="fr mr5" id="pageNumber">
                    	<input value="<s:property value="pageResultSet.pageInfo.currentPage"/>" type="number" id="redirectPage" name="redirectPage" class="input_tiny" />/<s:property value="pageResultSet.pageInfo.totalPage"/>
                		<a style="display:inline;" href="javascript:goPageRedirect('<s:property value="pageResultSet.pageInfo.totalPage"/>');">转到</a>
                	</div>
                    <ul class="fr clearfix mr5">
                    	<li><a href="javascript:goPage('1','<s:property value="pageResultSet.pageInfo.totalPage"/>');" id="headPage">首页</a></li>
                    	<li><a href="javascript:goPage('<s:property value="pageResultSet.pageInfo.currentPage-1"/>','<s:property value="pageResultSet.pageInfo.totalPage"/>');" id="previousPage">上一页</a></li>
                    	<li><a href="javascript:goPage('<s:property value="pageResultSet.pageInfo.currentPage+1"/>','<s:property value="pageResultSet.pageInfo.totalPage"/>');" id="nextPage">下一页</a></li>
                    	<li><a href="javascript:goPage('<s:property value="pageResultSet.pageInfo.totalPage"/>','<s:property value="pageResultSet.pageInfo.totalPage"/>');" id="endPage">尾页</a></li>
                    </ul>
                </div>
            </div>
            <!--News_list End-->
            <!--News_list right-->
             <!-- 
            <aside class="mb10 related panel_5" id="aside">
            	<div id="relativeSub" class="tit"><h4>相关栏目</h4></div>
                <div id="subList" class="con">
                	<ul class="list">
                    	<li><a href="#" id="asideInfo">无相关信息。</a></li>
                    </ul>
                </div>
            </aside>
              -->
            <!--News_list right End-->
        </div>
	</div>

</body>
</html>
