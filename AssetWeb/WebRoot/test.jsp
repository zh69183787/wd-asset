<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<! DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title> test </title>
</head>
<body>
<%--<div style="color.red"> --%>
<%--<s:fielderror /> --%>
<%--</div> --%>
<%--<s:form action ="external/test.action" method ="post" enctype ="multipart/form-data" > --%>
<%--<s:textfield name ="title" label ="文件标题" /> --%>
<%--<s:file name ="upload" label ="选择文件" />      --%>
<%--<s:submit value="解析入库" /> --%>
<%--</s:form> --%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form action="<%=basePath%>api/asset/setData.action" method="post">
    <fieldset>
        <div><label>数据类型:</label><select name="type">
            <option value="">请选择</option>
            <option value="uploadHouseAsset">房屋数据</option>
            <option value="uploadLandAsset">土地数据</option>
            <option value="uploadAssetTask">资产盘点数据</option>
            <option value="uploadDamageAsset">资产报废数据</option>
        </select></div>
        <div style="margin:5px;"><label>xml:</label><textarea name="content" cols="20" style="width: 500px"></textarea></div>
        <div><input type="submit" value="保存"/></div>
    </fieldset>
</form>
</body>
</html>