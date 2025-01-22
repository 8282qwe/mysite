<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script>
    window.addEventListener('load', function () {
        let anchors = document.querySelectorAll("#languages a");

        anchors.forEach(function (el) {
            el.addEventListener('click', function (e) {
                e.preventDefault();
                console.log(this.getAttribute('data-lang'));
                document.cookie = "lang=" + this.getAttribute('data-lang') + "; path=${pageContext.request.contextPath}; max-age=" + (30 * 24 * 60 * 60) + ";"
                location.href = '${pageContext.request.contextPath}';
            });
        });
    });
</script>
<div id="header">
    <%--    <h1><spring:eval expression="@siteService.siteVo.title"/></h1>--%>
    <h1>${sitevo.title}</h1>

    <div id="languages">
        <c:choose>
            <c:when test='${lang == "en"}'>
                <a href="" data-lang="ko">KO</a><a href="" class="active" data-lang="en">EN</a>
            </c:when>
            <c:when test='${lang == "ko"}'>
                <a href="" data-lang="ko" class="active">KO</a><a href="" data-lang="en">EN</a>
            </c:when>
        </c:choose>
    </div>
    <ul>
        <%--        <c:choose>--%>
        <%--        <c:when test="${sessionScope.get('authUser') != null}">--%>
        <%--            <li><a href="${pageContext.request.contextPath}/user/update"><spring:message--%>
        <%--                    code="header.gnb.settings"/></a>--%>
        <%--            <li>--%>
        <%--            <li><a href="${pageContext.request.contextPath}/user/logout"><spring:message code="header.gnb.logout"/></a>--%>
        <%--            <li>--%>
        <%--            <li><spring:message--%>
        <%--                    code="header.gnb.greeting"/>${sessionScope.get('authUser').name}<spring:message code="header.gnb.user.title"/></li>--%>
        <%--        </c:when>--%>
        <%--        <c:when test="${sessionScope.get('authUser') == null}">--%>
        <%--        <li><a href="${pageContext.request.contextPath}/user/login"><spring:message code="header.gnb.login"/></a>--%>
        <%--        <li>--%>
        <%--        <li><a href="${pageContext.request.contextPath}/user/join"><spring:message code="header.gnb.join"/></a>--%>
        <%--        <li>--%>
        <%--            </c:when>--%>
        <%--            </c:choose>--%>
        <sec:authorize access="!isAuthenticated()">
        <li><a href="${pageContext.request.contextPath}/user/login"><spring:message code="header.gnb.login"/></a>
        <li>
        <li><a href="${pageContext.request.contextPath}/user/join"><spring:message code="header.gnb.join"/></a>
        <li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal" var="authUser"/>
        <li><a href="${pageContext.request.contextPath}/user/update"><spring:message code="header.gnb.settings"/>
                <li>
                <li><a href="${pageContext.request.contextPath}/user/logout"><spring:message code="header.gnb.logout"/></a>
                <li>
                <li><spring:message code="header.gnb.greeting"/> ${authUser.name }<spring:message code="header.gnb.user.title"/></li>
                </sec:authorize>
            </ul>
        </div>