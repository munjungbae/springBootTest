<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 시큐리티 - 웹 화면 접근 정책</title>
</head>
<body>
	<h3>게시글 등록 : 로그인한 회원만 접근 가능</h3>
	<form action="/logout" method="post">
		<sec:csrfInput />
	
		<div>
			<a href="/">HOME</a>
		</div>
		<button>로그아웃</button>
	</form>
</body>
</html>