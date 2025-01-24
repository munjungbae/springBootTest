<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/user.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/test.js"></script>
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="../common/Header.jsp" />
	<jsp:include page="../common/Menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="codedetail.header.read" />
		</h2>
		<form:form modelAttribute="codeDetail">
			<table class="user_table">
				<tr>
					<td><spring:message code="codedetail.groupCode" /></td>
					<td><form:select path="groupCode" items="${groupCodeList}" itemValue="value" itemLabel="label" readonly="true" /></td>
					<td><font color="red"><form:errors path="groupCode" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="codedetail.codeValue" /></td>
					<td><form:input path="codeValue" readonly="true" /></td>
					<td><font color="red"><form:errors path="codeValue" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="codedetail.codeName" /></td>
					<td><form:input path="codeName" readonly="true" /></td>
					<td><font color="red"><form:errors path="codeName" /></font></td>
				</tr>
			</table>
		</form:form>
		<div>
			<button type="submit" id="btnEdit">
				<spring:message code="action.edit" />
			</button>
			<button type="submit" id="btnRemove">
				<spring:message code="action.remove" />
			</button>
			<button type="submit" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>

	</main>
	<jsp:include page="../common/Footer.jsp" />

</body>
<script>
	$(document).ready(
			function() {
				let formObj = $("#codeDetail");
				$("#btnEdit").on(
						"click",
						function() {
							formObj.attr("action", "/codedetail/modify");
							formObj.attr("method", "get");
							formObj.submit();
							
							//이미 위에서 get방식으로 선언하였기 때문에 아래 self.location 보내기 위한 var 설정은 굳이 필요가 없음.
							//url에서 필요한 정보만 보여주게 하기 위해 해당처럼 변경 가능
							let groupCode = $("#groupCode");
							let groupCodeVal = groupCode.val();
							let codeValue = $("#codeValue");
							let codeValueVal = codeValue.val();
							self.location = "modify?groupCode=" + groupCodeVal
									+ "&" + "codeValue=" + codeValueVal;
						});
				$("#btnRemove").on("click", function() {
					formObj.attr("action", "remove");
					formObj.submit();
				});
				$("#btnList").on("click", function() {
					self.location = "list";
				});
			});
</script>
</html>