<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.huateng.com.cn/tags-dl" prefix="dl" %>
<%@ taglib uri="http://www.huateng.com.cn/tags-securityDisplay" prefix="display" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
