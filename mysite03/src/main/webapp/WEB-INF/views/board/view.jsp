<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
        <div id="board" class="board-form">
            <table class="tbl-ex">
                <tr>
                    <th colspan="2">글보기</th>
                </tr>
                <tr>
                    <td class="label">제목</td>
                    <td>${board.title}</td>
                </tr>
                <tr>
                    <td class="label">내용</td>
                    <td>
                        <div class="view-content">
                            ${fn:replace(board.contents, newline, "<br>")}
                        </div>
                    </td>
                </tr>
            </table>
            <div class="bottom">
                <a href="${pageContext.request.contextPath}/board?page=${currentPage}">글목록</a>
                <c:if test="${sessionScope.getOrDefault('authUser',null) != null && sessionScope.get('authUser').id == board.user_id}">
                    <a href="${pageContext.request.contextPath}/board/modify?id=${board.id}&currentPage=${currentPage}">글수정</a>
                </c:if>
                <c:if test="${sessionScope.get('authUser') != null}">
                    <a href="${pageContext.request.contextPath}/board/write?id=${board.id}">답글달기</a>
                </c:if>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/views/includes/navigation.jsp">
        <c:param name="menu" value="board"/>
    </c:import>
    <c:import url="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
</html>