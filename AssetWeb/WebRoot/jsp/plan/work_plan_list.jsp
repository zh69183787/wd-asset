<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/11/19
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body style="font-family:'Microsoft YaHei';">
    <table class="table_5 mb10">
        <tbody><tr>
            <th class="xh" style="width: 40px;">序号</th>
            <th style="width:236px; ">工作内容</th>
            <th style="width: 261px;">计划完成时间</th>
            <th style="width: 261px;">实际完成时间</th>
            <th style="width: 100px;">备注</th>
        </tr>
        <c:forEach items="${workPlans}" var="w" varStatus="status">
        <tr>
            <td class="t_c">${status.index+1}</td>
            <td><p style="width: 170px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">${w.content}</p></td>
            <td class="t_c">${w.planEndDate}</td>
            <td class="t_c">${w.realEndDate}</td>
            <td ><p style="width: 200px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">${w.note}</p></td>
        </tr>
        </c:forEach>
        </tbody></table>
</body>
</html>
