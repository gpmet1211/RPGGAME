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


<img src="http://localhost:8080/RPGGAME/image/Top.png" class="pixel" alt="毛の生えそろわないRPG　２か月でjavaを学んできた" />

<form action="MeasureServlet" method="get">
<div class="titleform">
   <label><input type="radio" value="easy" name="mode">EASYモード</label>
   <label><input type="radio" value="normal" name="mode" checked>NORMALモード</label>
</div>
<div class="square2">

<img src="http://localhost:8080/RPGGAME/image/Slime_G.PNG" class="monster2">

<img src="http://localhost:8080/RPGGAME/image/Slime_G.PNG" class="monster3">

<img src="http://localhost:8080/RPGGAME/image/Slime_G.PNG" class="monster4">

<img src="http://localhost:8080/RPGGAME/image/Slime_G.PNG" class="monster5">

<img src="http://localhost:8080/RPGGAME/image/Slime_G.PNG" class="monster6">

<input type="image" src="http://localhost:8080/RPGGAME/image/Start.gif" class="pixel5" alt="スタートボタン" >
<img src="http://localhost:8080/RPGGAME/image/mukai.png" class="pixel6"/>
</div>
</form>
<!-- <a href="MeasureServlet"></a> -->

</body>
</html>