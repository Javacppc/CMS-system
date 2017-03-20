<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>錯誤發生</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<img src="<%=request.getContextPath() %>/images/common/error.jpg" />
  	<br/>
  	<s:if test="exception.errorMsg != null && exception.errorMsg != ''">
  		自定義錯誤發生！<s:property value="exception.errorMsg"/>
  	</s:if>
  	<s:else>
  		操作失敗！（Java錯誤）<s:property value="exception.message"/>
  	</s:else>
  </body>
</html>
