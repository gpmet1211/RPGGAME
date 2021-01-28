<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Clear,java.util.List"%>

<% long gameClearTime = (long) session.getAttribute("gameClearTime"); %>
<% List<Clear> clearList = (List<Clear>) request.getAttribute("clearList");%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<% long clearAvg = (long) request.getAttribute("clearAvg"); %>
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
<img src="http://localhost:8080/RPGGAME/image/End.gif"  class="pixel" alt="おしまい。あそんでくれてありがとう。エンディング画面"/>

<div class="excel">


<a class="excel1">
<form action="MeasureServlet" method="POST">
<% if(gameClearTime != 0){ %> <!-- 一度クリアすると0になるので非表示とする -->
クリア時間：<%=(double)gameClearTime / 1000%>秒&nbsp;&nbsp;
ユーザ名：<input type="text" name="userName" placeholder="ユーザー名を入力"/>
<input type="submit" value="登録する" >
<% } %></form></a>

</div>
	<% if(errorMsg != null){ %>
		<p class="clearlist"><%= errorMsg %></p>
	<% } %>
	<table >

	<% for (Clear clear : clearList) { %>
		<tr><td><%=i %>位</td> <td><%=clear.getName()%></td><td><%=(double)clear.getTime() / 1000%>秒</td></tr><%i++; %>
	<% } %>
	<tr><td></td><td>平均クリア時間</td><td><%=(double)clearAvg / 1000 %>秒</td></tr>
	</table>
	<br>
	<div class="startbutton456">
<input type="submit"    value="よくやった！！(終了する)" onclick="location.href='start.jsp'">
</div>
</body>
</html>