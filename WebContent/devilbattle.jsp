<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="model.Monster,java.util.List"%>

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
<div class="devilbattle">
 <img src="http://localhost:8080/RPGGAME/image/Devil_Castle3.jpg" alt="devilbattle" class="devil">
 <a class="monsterinfo2">
    敵の情報<br>
	名前：<%=monster.getName()%>
	HP:<%=monster.getHp()%>
	MP:<%=monster.getMp()%>
 </a>
 <img alt="prison" src="http://localhost:8080/RPGGAME/image/Devil.png" class="devilman">
</div>

<div class="rog">
	<%if(encountTurn == 1) {%>
	ガハハハハ！！！よく来たな勇者よ！！！わが力の前にひれ伏すがよい！！！
	<%} %>
	<%if(resultTime !=0) {%>
	誤差：<%=(double)resultTime / 1000 %> 秒
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
