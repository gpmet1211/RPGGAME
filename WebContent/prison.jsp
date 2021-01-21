<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- onclick="location.href='devilbattle.jsp'" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛の生えそろわないRPG</title>
<!-- rpgstyle.cssを参照 -->
<link rel="stylesheet" href="rpgstyle.css">
</head>
<body>

<div class="prison">
<img alt="prison" src="http://localhost:8080/RPGGAME/image/Prison.png" class="pixel2">
<img alt="Princess" src="http://localhost:8080/RPGGAME/image/Princess.PNG" class="princess">
</div>
<form action="PrincesServlet" method="post">
<div class="rog">姫：あれ？あなたは？まぁ！勇者なのね！じゃあ私の名前ぐらい知ってるわよね？
本当にあなたが勇者なら分かるでしょ！
早く私を助けなさいよ！<br>
あなたの名前は
<input type="text" size="30" name="princesname" placeholder="//姫の名前を入力" />&nbsp;姫</div>
<div class="startbutton">
<input type="submit" value="でしょ？" ></div></form>
</body>
</html>