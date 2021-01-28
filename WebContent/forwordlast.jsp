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

<img src="http://localhost:8080/RPGGAME/image/Devil.png" class="pixel0" alt="姫をさらった魔王が現れた">
<form action="FightServlet1" method="post">
<div class="prologue">ゴゴゴゴゴゴゴゴゴッ.........!!!!!!<br>
お前は.......あの時の勇者の孫ではないか！！！！１００年の恨みッ！！
いまここで晴らしてくれるわぁぁぁぁぁ！！！
<!-- ボタン押下でサーブレットを経由し、魔王戦devilbattle.jspへ -->
<input type="submit"    value="たたかう" class="selectbutton">
<input type="hidden" value="last"  name="scene">
</div></form>
</body>
</html>