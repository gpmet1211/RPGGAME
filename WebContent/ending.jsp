<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Clear,java.util.List"%>

<% long gameClearTime = (long) session.getAttribute("gameClearTime"); %>
<% List<Clear> clearList = (List<Clear>) session.getAttribute("clearList");%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<% int i = 1; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<!-- rpgstyle.cssを参照 -->
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>
<img src="http://localhost:8080/RPGGAME/image/End.gif" alt="end" class="pixel"/>

<div class="excel">


<a class="excel1">
<form action="MeasureServlet" method="POST">
<% if(gameClearTime != 0){ %> <!-- 一度クリアすると0になるので非表示とする -->
クリア時間：<%=gameClearTime%>&nbsp;&nbsp;
ユーザ名：<input type="text" name="userName" placeholder="ユーザー名を入力"/>
<input type="submit" value="登録する" >
<% } %></form></a>

</div>

	<% if(errorMsg != null){ %>
		<p class="clearlist"><%= errorMsg %></p>
	<% } %>
	<% for (Clear clear : clearList) { %>
		<p class="clearlist"><%=i %>位<%=clear.getName()%>：<%=(double)clear.getTime() / 1000%>秒<%i++; %></p>
	<% } %>
	<div class="startbutton456">
<input type="submit"    value="よくやった！！(終了する)" onclick="location.href='start.jsp'">
</div>
</body>
</html>