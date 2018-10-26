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
<div align="right">ユーザ名さん  <a href="">ログアウト</a></div>
<div align="center"><h2>ユーザ削除確認</h2><br><br></div>
<%
// リクエストスコープからインスタンスを取得
User user = (User)request.getAttribute("userdata");
%>
<form action="Userdelete" method="post">
ログインID : <%= user.getLoginId() %><br>
を本当に削除してよろしいでしょうか。<br><br>
<input type="submit" value="OK"></form>
<input type="submit" value="キャンセル">
</body>
</html>