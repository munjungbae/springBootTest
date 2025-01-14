<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis USER 등록</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#btnList").on("click", function() {
			self.location = "/user/list";
		});
	});
</script>
</head>
<body>
	<h3>${msg}</h3>
	<button type="button" id="btnList">List</button>
</body>
</html>