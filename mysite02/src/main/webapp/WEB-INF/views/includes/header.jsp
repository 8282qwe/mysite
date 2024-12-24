<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="mysite.vo.UserVo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div id="header">
    <h1>MySite</h1>
    <ul>
        <c:choose>
            <c:when test="${sessionScope.get('authUser') != null}">
                <li><a href="${pageContext.request.contextPath}/user?a=updateform">회원정보수정</a>
                <li>
                <li><a href="${pageContext.request.contextPath}/user?a=logout">로그아웃</a>
                <li>
                <li>${sessionScope.get('authUser').name}님 안녕하세요 ^^;</li>
            </c:when>
            <c:when test="${sessionScope.get('authUser') == null}">
        <li><a href="${pageContext.request.contextPath}/user?a=loginform">로그인</a>
        <li>
        <li><a href="${pageContext.request.contextPath}/user?a=joinform">회원가입</a>
        <li>
            </c:when>
        </c:choose>
    </ul>
</div>