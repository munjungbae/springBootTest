<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/notice.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/test.js"></script>
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="../common/Header.jsp" />
	<jsp:include page="../common/Menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="notice.header.register" />
		</h2>
		<form:form modelAttribute="notice" action="register">
			<table class="notice_table">
				<tr>
					<td><spring:message code="notice.title" /></td>
					<td><form:input path="title" /></td>
					<td><font color="red"><form:errors path="title" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="notice.content" /></td>
					<td><form:textarea path="content" /></td>
					<td><font color="red"><form:errors path="content" /></font></td>
				</tr>
			</table>
		</form:form>
		<div>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<button type="submit" id="btnRegister">
					<spring:message code="action.register" />
				</button>
			</sec:authorize>
			<button type="submit" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>
	</main>
	<jsp:include page="../common/Footer.jsp" />
	<script>
		$(document).ready(function() {
			var formObj = $("#notice");
			$("#btnRegister").on("click", function() {
				formObj.submit();
			});
			$("#btnList").on("click", function() {
				self.location = "list";
			});
		});
	</script>
</body>
</html>