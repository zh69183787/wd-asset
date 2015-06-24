<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8" />
<link href="<%=basePath %>widgets/jtable/themes/lightcolor/orange/jtable.css" rel="stylesheet" type="text/css" />

<script src="<%=basePath %>js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery-ui-1.9.2.min.js" type="text/javascript"></script>
<script src="<%=basePath %>widgets/jtable/jquery.jtable.min.js" type="text/javascript"></script>


<script type="text/javascript">
    $(document).ready(function () {
        $('#demoContainer').jtable({
            title: 'enterprise list',
			jqueryuiTheme: false,
			paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)
            sorting: true, //Enable sorting
            defaultSorting: 'name desc', 
            actions: {
                listAction: '/AssetWeb/enterprise/inquery.action'
            },
            fields: {
                id: {
                	key: true,
                    title: '厂商编号',
                    width: '25%'
                },
                name: {
                    title: '厂商名称的描述',
                    width: '30%'
                },
                corporation: {
                    title: '法人代表',
                    width: '15%'
                },
				contactPerson: {
                    title: '联系人',
                    width: '15%'
                },
				createDate: {
                    title: '创建日期',
                    width: '15%',
                    type: 'date',
                    displayFormat: 'yy-mm-dd'
                }
            },
            messages: {
               	pagingInfo:'显示 {0}-{1}条   总共{2}条',
               	gotoPageLabel: '跳转到',
               	pageSizeChangeLabel: '每页显示',
               	loadingMessage: '数据加载中...',
               	editRecord: '详细'
            }
        });
      	//Load student list from server
        $('#demoContainer').jtable('load');
    });
</script>
</head>
<body>
<div id="demoContainer"></div>


</body>
</html>