<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Hero,model.Sword,model.Monster,java.util.List"%>
<% List<Hero> heroList = (List<Hero>) session.getAttribute("heroList");%>
<% Monster monster = (Monster) session.getAttribute("monster");%>
<% long resultTime = (long) session.getAttribute("resultTime"); %>
<% String chargeMessage = (String) session.getAttribute("chargeMessage"); %>
<% int encountTurn = (Integer) session.getAttribute("encountTurn"); %>
<% String battleMessage = (String) session.getAttribute("battleMessage"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>

<!-- 丘の背景画像を800px,400pxで貼り付け -->
<div class="smallhill">
  <img src="http://localhost:8080/RPGGAME/image/hill.png" alt="hill" class="hill">
  <a class="monsterinfo">
    <!-- モンスターの情報<br> -->
	&nbsp;名前：<%=monster.getName()%>&nbsp;<br>
	&nbsp;HP:<%=monster.getHp()%>&nbsp;<br>
	&nbsp;MP:<%=monster.getMp()%>&nbsp;
  </a>
  <img src="http://localhost:8080/RPGGAME/image/Slime_G.PNG" class="monster">
</div>
<div class="rog">
<%if(encountTurn == 1) {%>
モンスターが現れた！！<br>
＊ボタンにカーソルを合わせると説明が表示されます。
<%} %>
<%if(battleMessage != "") {%>
<%=battleMessage %>
<%} %>
<%if(chargeMessage != "") {%>
<%=chargeMessage %>
<%} %>
</div>

<jsp:include page="battleInclude.jsp" />

</body>
</html>