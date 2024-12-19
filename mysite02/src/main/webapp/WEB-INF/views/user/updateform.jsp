<%@ page import="java.util.Optional" %>
<%@ page import="mysite.vo.UserVo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
	UserVo vo = (UserVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="<%=request.getContextPath()%>/user">
					<input type="hidden" name="a" value="update">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<%=Optional.ofNullable(vo.getName()).orElse("")%>">

					<label class="block-label">이메일</label>
					<h4><%=Optional.ofNullable(vo.getEmail()).orElse("")%></h4>
					<input type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" <%="female".equalsIgnoreCase(vo.getGender())?"checked":""%>>
						<label>남</label> <input type="radio" name="gender" value="male" <%="male".equalsIgnoreCase(vo.getGender())?"checked":""%>>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>