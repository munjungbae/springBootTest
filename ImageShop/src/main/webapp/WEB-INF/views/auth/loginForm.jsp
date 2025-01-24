<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/auth.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Image Shop</title>
<script type="text/javascript" src="/js/test.js"></script>
</head>
<body>
	<jsp:include page="../common/Header.jsp" />
	<jsp:include page="../common/Menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="auth.header.login" />
		</h2>
		<h2>
			<c:out value="${error}" />
		</h2>
		<h2>
			<c:out value="${logout}" />
		</h2>
		<form method="post" action="/login">
			<table class="auth_table">
				<tr>
					<td><spring:message code="auth.header.id" /></td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td><spring:message code="auth.header.pw" /></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="checkbox" name="rememberme">
					<spring:message code="auth.rememberMe" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button>
							<spring:message code="action.login" />
						</button></td>
				</tr>
			</table>
			<sec:csrfInput />
		</form>
	</main>
	<P>${serverTime}</P>
	<jsp:include page="../common/Footer.jsp" />
</body>

</html>