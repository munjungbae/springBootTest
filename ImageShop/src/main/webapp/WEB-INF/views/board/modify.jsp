<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/board.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/test.js"></script>
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="../common/Header.jsp" />
	<jsp:include page="../common/Menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="board.header.modify" />
		</h2>
		<form:form modelAttribute="board" action="modify">
			<form:hidden path="boardNo" />
			<!-- 현재 페이지 번호와 페이징 크기 그리고 검색유형, 검색어를 숨겨진 필드 요소를
사용하여 전달한다. -->
			<input type="hidden" name="page" value="${pgrq.page}">
			<input type="hidden" name="sizePerPage" value="${pgrq.sizePerPage}">
			<table class="board_table">
				<tr>
					<td><spring:message code="board.title" /></td>
					<td><form:input path="title" /></td>
					<td><font color="red"><form:errors path="title" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="board.writer" /></td>
					<td><form:input path="writer" /></td>
					<td><font color="red"><form:errors path="writer" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="board.content" /></td>
					<td><form:textarea path="content" /></td>
					<td><font color="red"><form:errors path="content" /></font></td>
				</tr>
			</table>
		</form:form>
		<div>
			<sec:authentication property="principal" var="pinfo" />
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<button type="submit" id="btnModify">
					<spring:message code="action.modify" />
				</button>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_MEMBER')">
				<c:if test="${pinfo.username eq board.writer}">
					<button type="submit" id="btnModify">
						<spring:message code="action.modify" />
					</button>
				</c:if>
			</sec:authorize>
			<button type="submit" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>

	</main>
	<jsp:include page="../common/Footer.jsp" />
	<!-- 	<script>
		$(document).ready(function() {
			var formObj = $("#board");
			var pageObj = $("#page");
			var sizePerPageObj = $("#sizePerPage");
			var pageVal = pageObj.val();
			var sizePerPageVal = sizePerPageObj.val();
			$("#btnModify").on("click", function() {
				formObj.attr("action", "/board/modify");
				formObj.attr("method", "post");
				formObj.submit();
			});
			$("#btnList").on("click", function() {
				// 삭제 self.location = "list";
				// 페이징 관련 정보를 쿼리 파라미터로 전달한다.
				self.location = "list${pgrq.toUriString()}";
			});
		});
	</script>
 -->
	<script>
		$(document).ready(function() {
			var formObj = $("#board");
			$("#btnModify").on("click", function() {
				formObj.submit();
			});
			$("#btnList").on("click", function() {
				// 페이징 관련 정보를 쿼리 파라미터로 전달한다.
				self.location = "list${pgrq.toUriString()}";
			});
		});
	</script>
</body>
</html>