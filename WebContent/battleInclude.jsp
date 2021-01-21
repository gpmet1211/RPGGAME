<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Hero,model.Sword,java.util.List"%>
<% List<Sword> swordList = (List<Sword>) session.getAttribute("swordList");%>
<% boolean statusCharge = (boolean) session.getAttribute("statusCharge"); %>
<% List<Hero> heroList = (List<Hero>) session.getAttribute("heroList");%>
<% int ikeStartTurn = (Integer) session.getAttribute("ikeStartTurn"); %>

<div class="square">
 <a>
	勇者の情報<br>
	<% for (Hero hero : heroList) { %>
		名前:<%=hero.getName()%>
		HP:<%=hero.getHp()%>
		MP:<%=hero.getMp()%>
		<%if(ikeStartTurn != 0) {%>　☆イケおじ中☆<% } %>
		<br>
	<% } %>
 <!-- battleIncludeより参照 -->

 <%if(!(statusCharge)) {%>
  <form action="ResultServlet" method="get"> <!-- 以下のvalue値はサーブレットに影響するため注意 -->
   <input type="submit" value="ためる" name="select" title="【消費MP０】ためるをクリックで秒数が計測開始される。画面内に表示される特定の秒数で放つボタンを押せ！">
   <input type="submit" value="坂東エイド" name="select" title="【消費MP４】HPを３０～１００ランダムに回復">
   <input type="submit" value="イケおじturbo" name="select" title="【消費MP９】５ターンの間攻撃力✕２">
   <input type="submit" value="怨念がおんねん" name="select" title="【消費MP３５】攻撃力の✕１０の大ダメージ！！">
   <input type="submit" value="武器生成" name="select" title="【消費MP１】ランダムで武器を生成する。５ターンが終わると生成された武器は壊れる。※イケおじ中は使用不可">
   <input type="submit" value="超武器生成" name="select" title="【消費MP７】右のセレクトボックスから武器を選んでからクリック！※イケおじ中は使用不可">
   <select name="swordId">
     <% for (Sword sword : swordList) { %>
		<option value="<%=sword.getId()%>"><%=sword.getName()%></option>
	<% } %>
   </select>
  </form>
 <%} %>
  <br>
  <form action="ResultServlet" method="get"> <!-- 以下のvalue値はサーブレットに影響するため注意 -->
   <input type="submit" value="はなつ" name="select"title="ためるボタンをクリック後表示された秒数で放つボタンを押そう！【とてもよい攻撃】で大ダメージ！？">
  </form>

  </a>
</div>