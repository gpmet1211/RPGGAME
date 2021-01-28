<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<!-- rpgstyle.cssを参照 -->
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>

<img src="http://localhost:8080/RPGGAME/image/hill.png"  class="pixel" alt="草が生い茂る小高い丘、モンスターをやっつけた"/>

<div class="rog">モンスターを撃破した！！</div>

<div class="selectbutton3">

<form action="FightServlet1" method="post">
<input type="submit" value="←　小高い丘をまだ探索する" class="a">
</form>
<form action="FightServlet1" method="get">
<input type="submit"    value="す　す　む　→" class="b"></form>
</div>

</body>
</html>