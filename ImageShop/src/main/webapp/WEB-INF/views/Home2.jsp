<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Image Shop</title>
<script type="text/javascript" src="/js/test.js"></script>
</head>
<body>
	<jsp:include page="./common/Header.jsp" />
	<jsp:include page="./common/Menu.jsp" />
	<main align="center">
		<h1>
			<spring:message code="common.homeWelcome" />
		</h1>
	</main>
	<jsp:include page="./common/Footer.jsp" />
</body>
</html>