<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Board</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var formObj = $("#board");
		$("#btnRegister").on("click", function() {
			formObj.attr("action", "/board/register");
			formObj.attr("method", "post");
		});
		$("#btnList").on("click", function() {
			self.location = "/board/list";
		});
	});
</script>
<body>
	<h2>REGISTER</h2>
	<form:form modelAttribute="board"> <!-- 위의 formObj.attr("action", "/board/register"); 가 ACTION역할을 대신하기 때문에 해당 FORM에서는 ACTION형식만 지키고 값은 없어도된다. -->
		<table>
			<tr>
				<td>Title</td>
				<td><form:input path="title" /></td>
				<td><font color="red"><form:errors path="title" /></font></td>
			</tr>
			<tr>
				<td>Writer</td>
				<td><form:input path="writer" /></td>
				<td><font color="red"><form:errors path="writer" /></font></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><form:textarea path="content" /></td>
				<td><font color="red"><form:errors path="content" /></font></td>
			</tr>
		</table>
	</form:form>
	<div>
		<button type="submit" id="btnRegister">작성하기</button>
		<button type="submit" id="btnList">목록보기</button>
	</div>
</body>
</html>