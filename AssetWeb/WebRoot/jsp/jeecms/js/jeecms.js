
var basePath="";
function setbasePath(path){
	basePath=path;
}
function setShortTitle(shortTitle,Title){
	if(shortTitle=="null"){
		return shortTitle;
	}else{
		return Title;
	}
}

//显示图片新闻
function getPicNews(cmsUrl,channelID){
	$.ajax({
		url:basePath+"jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'3',
			rownum:'5'
		},
		error:function(){
			alert("系统连接失败，请稍后再试!");
		},
		success:function(obj){
			var playList = "";
			var playText = "";
			var playInfo = "";
			if(obj!=null && obj!="undefined"){
				for(var i=0;i<obj.length;i++){
					playList += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='"+basePath+"jsp/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>" +
							"<img src='"+cmsUrl.substring(0,cmsUrl.length-6)+obj[i].titleImg+"' title='"+obj[i].title+"'></img></a></li>";
					playInfo += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='"+basePath+"jsp/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"
					+ obj[i].title+"</a></li>";
					playText += "<li>"+(i+1)+"</li>";
				}
				$("#play_list").html(playList);
				$("#play_info").html(playInfo);
				$("#play_text").html(playText);
				$("#play_list li").hide();
				$("#play_info li").hide();
				$("#play_list li").eq(0).show();
				$("#play_info li").eq(0).show();
				$("#play_text li").eq(0).addClass("current");
				
				$("#play_text li").bind("click",function(){
					
					var pos = $(this).html();
					$("#play_list li").hide();
					$("#play_info li").hide();
					$("#play_text li").removeClass("current");
					$("#play_list li").eq(pos-1).show();
					$("#play_info li").eq(pos-1).show();
					$(this).addClass("current");
				});
			}
		}
	});
}



//头条新闻
function getHeadNews(channelID){
$.ajax({
		url:basePath+"jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'1'
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			if(obj){
				for(var i=0;i<obj.length;i++){
					$("#newsCenter hgroup a").attr("href",basePath+"jeecms/findNewsByPage.action?channelId="+channelID);
					$("#newsCenter section a").attr("href",basePath+"jsp/jeecms/contentDetail.jsp?content="+obj[i].contentId);
					$("#newsCenter section a h5").html(obj[i].title);
					$("#newsCenter section a p").html(obj[i].description);
					$("#newsCenter section a").attr("title",obj[i].description);
				}
				
			}
		}	  
	});	
}

//顶部新闻
function getTopNews(channelID){
	$.ajax({
		url:basePath+"jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'6'
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			if(obj){
				for(var i=1;i<obj.length;i++){
					newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"' href='"+basePath+"jsp/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a><span>"+obj[i].releaseDate+"</span></li>";
				}
				$("#newsCenter ul").html(newsLi);
			}
		}	  
	});	
}


//最新消息
function getLatestNews(channelID,areaId,rownum){
	$.ajax({
		url:basePath+"jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:rownum
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
//			var newsLi = "";
//			var newsP = "javascript:void(0)";
			var html ="";
			
			if(obj){
				//html+= "<ul class='columns clearfix mb10'>";
				for(var i=0;i<obj.length;i++){
					html +="<li class='clearfix'><a href='"+basePath+"jsp/jeecms/contentDetail.jsp?content="+obj[i].contentId+"' target='_blank'>"+setShortTitle(obj[i].shortTitle,obj[i].title)+"</a><span></span></li></ul>";
					
					
//					newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
//					newsLi += " href='jsp/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a></li>";
//					newsP =basePath+ "/jeecms/findStfbNewsByPage.action?channelId="+obj[i].channelId;
				}
				//html +="</ul>";
				$('#'+areaId).html(html);
			}
		}	  
	});	
}


//最新消息
function getAssideLatestNews(channelID,areaId,rownum){
	$.ajax({
		url:basePath+"jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:rownum
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			var newsP = "javascript:void(0)";
			var html ="";
			
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
					newsLi += " href='jsp/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a></li>";
					newsP =basePath+ "/jeecms/findNewsByPage.action?channelId="+obj[i].channelId;
				}
				$('#'+areaId).html(newsLi);
			}
		}	  
	});	
}