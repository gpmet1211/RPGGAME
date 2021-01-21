<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>

<img alt="prison" src="http://localhost:8080/RPGGAME/image/Devil_Castle3.jpg" alt="devilbattle" class="pixel">
<div class="rog">ボスを倒した！</div>

<div class="startbutton">
<form action="MeasureServlet" method="get">
<input type="hidden" value="end" name="endFlag">
<input type="submit" value="平和な世界が訪れた">
</form>
</div>
</body>
</html>