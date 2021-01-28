<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Monster"%>
    <% Monster monster = (Monster) session.getAttribute("monster");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>
<div class="devilbattle">
<img alt="prison" src="http://localhost:8080/RPGGAME/image/Devil_Castle3.jpg" class="devil" alt="魔王の城の最深部、魔王を撃破した！" >
<img alt="prison" src="http://localhost:8080/RPGGAME/image/<%=monster.getUrl()%>"  class="devilman2">
</div>
<div class="rog">魔王を倒した！<br>
グフゥッ！！！おのれ！！！ウガアアアアアアアアアアア！！！！！！
ゆ、ゆうしゃよ......ワシは改心した........坂東エイドを使ってはくれぬか......????</div>

<div class="selectbutton3">
<div class="startbutton">
<form action="FightServlet1" method="post">
<input type="submit"    value="許してあげる" class="a">
<input type="hidden" value="last"  name="scene"></form>
<input type="submit" value="とどめを刺す" onclick="location.href='eplogue.jsp'" class="b">
</div>
</div>
</body>
</html>