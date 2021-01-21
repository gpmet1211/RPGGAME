<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<!-- rpgstyle.cssを参照 -->
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>


<img src="http://localhost:8080/RPGGAME/image/Forest.png" alt="草原" class="pixel"/>

<!-- ボタン押下でサーブレットを経由し、小高い丘smallhill.jspへ -->
<form action="FightServlet1" method="post">
<div class="startbutton">
<input type="submit" value="小高い丘" >
<input type="hidden" value="steppe" name="scene">
</div>
</form>

</body>
</html>