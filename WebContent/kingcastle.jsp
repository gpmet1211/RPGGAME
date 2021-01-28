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

<!-- 王様の画像を800px,400pxで貼り付け -->
<div class="kingcastle">
  <img src="http://localhost:8080/RPGGAME/image/Castle2.jpg"  class="castle" alt="王様のお城の前、花畑がある広場で王様が慌てている。">
  <img src="http://localhost:8080/RPGGAME/image/King.png" class="king">
</div>

<!-- 王様との会話 "kingtalk1"-->
<div class="prologue2">王様:よく来た勇者よ！ワシの最愛の娘、ニョッフォリアミンテリッーヌコッテリーッヌ
姫が魔王にさらわれてしまったのじゃ！いますぐ姫を救い出し魔王をたおしてくるのじゃ！
<input type="submit"    value="冒険に出る"  onclick="location.href='steppe.jsp'" class="selectbutton">
</div>

<!-- ボタン押下で草原へ -->



</body>
</html>