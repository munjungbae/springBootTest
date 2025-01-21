<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Shop</title>
<script src="https://kit.fontawesome.com/f5a3833180.js" defer crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/test.js"></script>
</head>
<body>
	<jsp:include page="./common/Header.jsp" />
	<aside>
		<div class="img_change">
			<i class="fa-solid fa-caret-left"></i> 
			<img src="./img/Zoo1.png" alt="" class="banner_change">
			<i class="fa-solid fa-caret-right"></i>
		</div>
	</aside>
	<jsp:include page="./common/Menu.jsp" />
	<div align="center">
		<h1>
			<spring:message code="common.homeWelcome" />
		</h1>
		<P>${serverTime}</P>
	</div>
	<jsp:include page="./common/Footer.jsp" />
</body>
</html>