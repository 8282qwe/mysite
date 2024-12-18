<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div id="header">
    <h1>MySite</h1>
    <ul>
        <li><a href="<%=request.getContextPath()%>/user?a=loginForm">로그인</a><li>
        <li><a href="<%=request.getContextPath()%>/user?a=joinForm">회원가입</a><li>
        <li><a href="<%=request.getContextPath()%>/user?a=updateForm">회원정보수정</a><li>
        <li><a href="<%=request.getContextPath()%>/user?a=logoutForm">로그아웃</a><li>
        <li>님 안녕하세요 ^^;</li>
    </ul>
</div>