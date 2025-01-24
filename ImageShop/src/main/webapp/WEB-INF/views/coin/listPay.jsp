<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/coin.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/test.js"></script>
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="../common/Header.jsp" />
	<jsp:include page="../common/Menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="coin.header.listPay" />
		</h2>
		<table border="1">
			<tr>
				<th align="center" width="80"><spring:message code="coin.no" /></th>
				<th align="center" width="120"><spring:message code="coin.itemName" /></th>
				<th align="center" width="120"><spring:message code="coin.payAmount" /></th>
				<th align="center" width="180"><spring:message code="coin.regdate" /></th>
			</tr>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="4"><spring:message code="common.listEmpty" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="payCoin">

						<tr>
							<td align="center">${payCoin.historyNo}</td>
							<td align="left">${payCoin.itemName}</td>
							<td align="left">${payCoin.amount}</td>
							<td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${payCoin.regDate}" /></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</main>
	<jsp:include page="../common/Footer.jsp" />
</body>
</html>