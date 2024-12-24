<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
        <div id="board">
            <form id="search_form" action="${pageContext.request.contextPath}/board" method="post">
                <input type="text" id="kwd" name="kwd" value="${not empty param.kwd?param.kwd:""}">
                <input type="submit" value="찾기">
            </form>
            <table class="tbl-ex">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>&nbsp;</th>
                </tr>
                <c:forEach items="${boardList}" var="item" varStatus="index">
                    <tr>
                        <td>${pagingData.totalCount - ((pagingData.currentPage-1)*pagingData.perPage) - index.index}</td>
                        <td style="text-align: left; padding-left: ${item.depth*20}px">
                            <c:if test="${item.depth != 0}">
                                <img src="${pageContext.request.contextPath}/assets/images/reply.png"
                                     alt="${item.title}">
                            </c:if>
                            <a href="${pageContext.request.contextPath}/board?a=view&currentPage=${pagingData.currentPage}&id=${item.id}">${item.title}</a>
                        </td>
                        <td>${item.user_name}</td>
                        <td>${item.hit}</td>
                        <td>${item.reg_date}</td>
                        <c:if test="${sessionScope.get('authUser').id == item.user_id}">
                            <td><a href="${pageContext.request.contextPath}/board?a=delete&id=${item.id}"
                                   class="del">삭제</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <!-- pager 추가 -->
            <div class="pager">
                <ul>
                    <li>
                        <c:if test="${pagingData.prevPage >= 1}">
                        <a href="${pageContext.request.contextPath}/board?page=${pagingData.prevPage}${not empty param.kwd?'&kwd='.concat(param.kwd):""}">
                            </c:if>◀</a></li>
                    <c:forEach begin="${pagingData.prevPage+1}" end="${pagingData.prevPage+pagingData.perPage}" var="index">
                        <c:choose>
                            <c:when test="${index == pagingData.currentPage}">
                                <li class="selected">${index}</li>
                            </c:when>
                            <c:when test="${index != pagingData.currentPage && index <= pagingData.endPage}">
                                <li><a href="${pageContext.request.contextPath}/board?page=${index}${not empty param.kwd?'&kwd='.concat(param.kwd):""}">${index}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li>${index}</li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <li>
                        <c:if test="${pagingData.endPage+1 <= pagingData.totalPage}">
                        <a href="${pageContext.request.contextPath}/board?page=${pagingData.endPage+1}${not empty param.kwd?'&kwd='.concat(param.kwd):""}">
                            </c:if>▶</a></li>
                </ul>
            </div>
            <!-- pager 추가 -->
            <c:if test="${sessionScope.get('authUser') != null}">
                <div class="bottom">
                    <a href="${pageContext.request.contextPath}/board?a=writeform" id="new-book">글쓰기</a>
                </div>
            </c:if>
        </div>
    </div>
    <c:import url="/WEB-INF/views/includes/navigation.jsp">
        <c:param name="menu" value="board"/>
    </c:import>
    <c:import url="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
</html>