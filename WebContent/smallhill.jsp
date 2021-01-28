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
<div class="smallhill">
  <img src="http://localhost:8080/RPGGAME/image/hill.png" class="hill" alt="草が生い茂る小高い丘、モンスターが現れた" >
  <a class="monsterinfo">
    <!-- モンスターの情報<br> -->
	&nbsp;名前：<%=monster.getName()%>&nbsp;<br>
	&nbsp;HP:<%=monster.getHp()%>&nbsp;<br>
	&nbsp;MP:<%=monster.getMp()%>&nbsp;
  </a>
  <%if(encountTurn == 1) {%>
    <img src="http://localhost:8080/RPGGAME/image/<%=monster.getUrl()%>" class="monster69">
  <%} else {%>
	<img src="http://localhost:8080/RPGGAME/image/<%=monster.getUrl()%>" class="monster">
	<%}%>

</div>
<div class="rog">
<%if(encountTurn == 1) {%>
モンスターが現れた！！<br>
＊ボタンにカーソルを合わせると説明が表示されます。
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