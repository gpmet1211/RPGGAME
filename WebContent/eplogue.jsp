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

<div class="kingcastle">
  <img src="http://localhost:8080/RPGGAME/image/Castle3.png" class="castle" alt="王様の城に戻ってきた勇者と姫。たくさんの風船やテープが飛ばされ王様も喜んでいる。" >
  <img src="http://localhost:8080/RPGGAME/image/King2.png" class="king2">
  <img alt="Princess" src="http://localhost:8080/RPGGAME/image/Princess2.png" class="princess2">
</div>
<div class="prologue">王様:よくやった勇者よ！！！１０億ドルをやろう！！<br>
どこに１０億ドルがあるかさがしてみぃ！！<br>
姫  :(ああ魔王様.......ステキだったわ......ポッ)<br>
勇者:当然のことをしたまでです。(よし.......あとで魔王にお金を振り込みにいこう......)<br><br>
ー定年まであと３年、わたしは勇者である。
</div>
<form action="MeasureServlet" method="get">
<input type="hidden" value="end" name="endFlag">
<input type="image" value="10億ドルを受け取る"  src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon10">
</form>


<img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon1">
<img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon2">
<img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon3">
<img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon5">
<img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon6">
<img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon7">
<img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon8">
<!-- <img alt="Princess" src="http://localhost:8080/RPGGAME/image/balloon.png" class="balloon10"> -->

</body>
</html>