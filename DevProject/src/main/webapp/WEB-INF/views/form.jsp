<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>form home test</h1>
	<form action="/register" method="get">
		<input type="submit" value="register(get)">
	</form>
	
	<a href="/register/20">register(a get)</a>
	
	<form action="/register" method="post">
		<button type="submit">regist(post)</button>
		</form>
		
	<form action="/modify" method="get">
		<button type="submit">modify(get)</button>
		</form>
		
	<form action="/modify" method="post">
		<button type="submit">modify(post)</button>
		</form>
		
	<form action="/remove" method="post">
		<button type="submit">remove(post)</button>
		</form>
		
	<form action="/list" method="get">
		<button type="submit">list(get)</button>
		</form>

</body>
</html>