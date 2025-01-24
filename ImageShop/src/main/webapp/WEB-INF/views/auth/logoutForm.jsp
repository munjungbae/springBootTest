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
			<spring:message code="auth.header.logout" />
		</h2>
		<form action="/auth/logout" method="post">
			<sec:csrfInput />
			<button>
				<spring:message code="action.logout" />
			</button>
		</form>
	</main>
	<P>${serverTime}</P>
	<jsp:include page="../common/Footer.jsp" />
</body>

</html>