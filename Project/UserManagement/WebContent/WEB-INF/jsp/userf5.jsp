<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
    <style type="text/css">
    td {
width: 120px;
height: 30px;
}
</style>
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
    	<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>
<div align="center"><h2>ユーザ情報更新</h2><br><br>

<form action="UserF5" method="post">
<input type="hidden" value="${userdata.loginId}" name="loginid">
<table>
<tr>
<td>ログインID</td>
<td>${userdata.loginId}</td>
</tr>
<tr>
<td>パスワード</td>
<td><input type="password" size="20" name ="password" autofocus></td>

</tr>
<tr>
<td>パスワード(確認)</td>
<td><input type="password" size="20" name ="passwordcon"></td>
</tr>
<tr>
<td>ユーザ名</td>
<td><input type="text" name="name" value="${userdata.name}"></td>
</tr>
<tr>
<td>生年月日</td>
<td><input type="text" name="birth_date" value="${userdata.birthDateStr}"></td></tr></table><br>
<input type="submit" class="btn btn-info" value="更新"></form></div><br><br>
<a href="UserListServlet">戻る</a>
</body>
</html>