<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Hero,model.Monster,java.util.List"%>
<% List<Hero> heroList = (List<Hero>) session.getAttribute("heroList");%>
<% Monster monster = (Monster) session.getAttribute("monster");%>
<% String chargeMessage = (String) session.getAttribute("chargeMessage"); %>
<% int encountTurn = (Integer) session.getAttribute("encountTurn"); %>

<% String battleMessage = (String) request.getAttribute("battleMessage"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>
<div class="devilbattle">
 <img src="http://localhost:8080/RPGGAME/image/Devil_Castle3.jpg" class="devil" alt="魔王城最深部の玉座、魔王との戦い" >
 <a class="monsterinfo">
    <!-- モンスターの情報<br> -->
	&nbsp;名前：<%=monster.getName()%>&nbsp;<br>
	&nbsp;HP:<%=monster.getHp()%>&nbsp;<br>
	&nbsp;MP:<%=monster.getMp()%>&nbsp;
 </a>
 <%if(encountTurn == 1) {%>
    <img alt="prison" src="http://localhost:8080/RPGGAME/image/<%=monster.getUrl()%>" class="devilman3">
  <%} else {%>
	<img alt="prison" src="http://localhost:8080/RPGGAME/image/<%=monster.getUrl()%>" class="devilman">
	<%}%>

</div>

<div class="rog">
<%if(encountTurn == 1) {%>
ガハハハハ！！！よく来たな勇者よ！！！わが力の前にひれ伏すがよい！！！
<%} %>
<%if(battleMessage != null) {%>
<%=battleMessage %>
<%} %>
<%if(chargeMessage != "") {%>
<%=chargeMessage %>
<%} %>
</div>

<jsp:include page="battleInclude.jsp" />

</body>
</html>
