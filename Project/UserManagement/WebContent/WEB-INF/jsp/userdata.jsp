<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザ情報詳細参照</title>
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
            <a class="navbar-brand" href="LoginServlet">ユーザ管理システム</a>
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
<div align="center"><h2>ユーザ情報詳細参照</h2><br><br>
<%
// リクエストスコープからインスタンスを取得
User user = (User)request.getAttribute("userdata");
%>

<table width="300">
<tr>
<td>ログインID</td>
<td><%= user.getLoginId() %> </td>
</tr>
<tr>
<td>ユーザ名</td>
<td><%= user.getName() %> </td>
</tr>
<tr>
<td>生年月日</td>
<td><%= user.getBirthDate() %> </td>
</tr>
<tr>
<td>登録日時</td>
<td><%= user.getCreateDate() %> </td>
</tr>
<tr>
<td>更新日時</td>
<td><%= user.getUpdateDate() %> </td>
</tr></table>
</div><br><br>
<a href="UserListServlet">戻る</a>
</body>
</html>