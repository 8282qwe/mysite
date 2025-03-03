<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form:form id="login-form" name="loginform" method="post" action="${pageContext.request.contextPath}/user/auth">
					<label class="block-label" for="email"><spring:message code="user.signin.label.email"/></label>
					<input id="email" name="email" type="text" value="${Optional.ofNullable(email).orElse("")}">
					<label class="block-label" ><spring:message code="user.signin.label.password"/></label>
					<input name="password" type="password" value="">
					<c:if test="${'fail' == result}">
						<p>
							<spring:message code="user.signin.fail"/>
						</p>
					</c:if>
					<spring:message code="user.signin.button.text" var="signin"/>
					<input type="submit" value="${signin}">
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>