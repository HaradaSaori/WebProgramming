<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザ情報更新</title>
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
<div align="right">ユーザ名さん  <a href="">ログアウト</a></div>
<div align="center"><h2>ユーザ情報更新</h2><br><br>
<%
// リクエストスコープからインスタンスを取得
User user = (User)request.getAttribute("userdata");
%>
<form action="UserF5" method="post">
<input type="hidden" value="<%= user.getLoginId() %>" name="loginid">
<table>
<tr>
<td>ログインID</td>
<td><%= user.getLoginId() %></td>
</tr>
<tr>
<td>パスワード</td>
<td><input type="password" size="20" name ="password"></td>

</tr>
<tr>
<td>パスワード(確認)</td>
<td><input type="password" size="20" name ="passwordcon"></td>
</tr>
<tr>
<td>ユーザ名</td>
<td><input type="text" name="name" value="<%= user.getName() %>"></td>
</tr>
<tr>
<td>生年月日</td>
<td><input type="text" name="birth_date" value="<%= user.getBirthDate() %>"></td></tr></table><br>
<input type="submit" value="更新"></form></div><br><br>
<a href="">戻る</a>
</body>
</html>