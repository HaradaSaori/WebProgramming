<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザ削除確認</title>
    <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- オリジナルCSS読み込み -->
    <link href="css/original/common.css" rel="stylesheet">
    <!-- Jqeryの読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
    <!-- レイアウトカスタマイズ用個別CSS -->
</head>
<body>

    <!-- header -->
    <header>
      <nav class="navbar navbar-inverse">
      	<div class="container">
      		<div class="navbar-header">
            <a class="navbar-brand" href="userCreate.html">ユーザ管理システム</a>
      		</div>

          <ul class="nav navbar-nav navbar-right">
            <li class="navbar-text">${userInfo.name} さん </li>
  			<li class="dropdown">
  			  <a href="LogoutServlet" class="navbar-link logout-link">ログアウト</a>
            </li>
  		  </ul>
      	</div>
      </nav>
    </header>
    <!-- /header -->

<div align="center"><h2>ユーザ削除確認</h2><br><br></div>
<%
// リクエストスコープからインスタンスを取得
User user = (User)request.getAttribute("userdata");
%>
ログインID : <%= user.getLoginId() %><br>
を本当に削除してよろしいでしょうか。<br><br>
<table>
<tr>
<td width="120">
<form>
<input type="button" class="btn btn-light" name = "cancel" value="キャンセル" onclick="history.back()">
</form>
</td>
<td width="120">
<form action="Userdelete" method="post">
<input type="hidden" value="<%= user.getLoginId() %>" name="loginid">

<input type="submit" class="btn btn-info" name = "submit" value="OK">
</form>
</td></tr></table>
</body>
</html>