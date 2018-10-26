<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザ新規登録</title>
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
<div align="center"><h2>ユーザ新規登録</h2><br><br>
<form action="UserNew" method="post">
<table>
<tr>
<td>ログインID</td>
<td><input type="text" name="loginid"></td>
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
<td><input type="text" name="username"></td>
</tr>
<tr>
<td>生年月日</td>
<td><input type="text" name="birthday"></td></tr></table><br>
<input type="submit" value="登録"></form></div>
<br><br>
<a href="">戻る</a>
</body>
</html>