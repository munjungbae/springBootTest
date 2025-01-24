<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Shop</title>
<link rel="stylesheet" href="/css/home.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/f5a3833180.js" defer crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/test.js"></script>
</head>
<script>
	function change() {
		let banner_num = Math.floor(Math.random() * (12 - 1 + 1) + 1);
		$("#img_zoo").attr("src", "/image/zoo" + banner_num + ".png");
	}

	$(document).ready(function() {

		let intervalID = null;
		intervalID = setInterval(change, 2000);

		$("#img_zoo").on("mouseenter", function() {
			clearInterval(intervalID);
		});
		$("#img_zoo").on("mouseleave", function() {
			intervalID = setInterval(change, 2000);
		});
		$(".fa-caret-left").on("click", change);
		$(".fa-caret-left").on("mouseenter", function() {
			clearInterval(intervalID);
		});
		$(".fa-caret-left").on("mouseleave", function() {
			intervalID = setInterval(change, 2000);
		});
		$(".fa-caret-right").on("click", change);
		$(".fa-caret-right").on("mouseenter", function() {
			clearInterval(intervalID);
		});
		$(".fa-caret-right").on("mouseleave", function() {
			intervalID = setInterval(change, 2000);
		});
	});
</script>
<body>
		<jsp:include page="./common/Header.jsp" />
		<jsp:include page="./common/Menu.jsp" />
	<aside>
		<div class="img_change">
			<i class="fa-solid fa-caret-left"></i> <img alt="/" src="/image/zoo1.png" id="img_zoo"> <i class="fa-solid fa-caret-right"></i>
		</div>
	</aside>
	<div class="main">
		<h1>
			<spring:message code="common.homeWelcome" />
		</h1>
		<P>${serverTime}</P>
	</div>
	<jsp:include page="./common/Footer.jsp" />
</body>

</html>