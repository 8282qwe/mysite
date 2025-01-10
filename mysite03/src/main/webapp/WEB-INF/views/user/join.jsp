<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
    <script>
        $(function() {
            $("#btn-check").click(() => {
                let email = $("#email").val();
                if (email === "") {
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/api/user/checkemail",
                    type: "GET",
                    data: {email: email},
                    dataType: "json",
                    success: function (response) {
                        if (response.exist) {
                            alert("이미 존재하는 이메일 입니다.");
                            $("#email").val("");
                            $("#email").focus();
                            return;
                        }
                        $("#img-check").show();
                        $("#btn-check").hide();
                    },
                    error: function (xhr, status, error) {
                        // 에러 발생 시.
                    }
                });
            });

            $("#email").keypress(function(){
                $("#img-check").hide();
                $("#btn-check").show();
            })
        });
    </script>
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
        <div id="user">

            <form:form id="join-form" name="joinForm" method="post"
                       action="${pageContext.request.contextPath}/user/join" modelAttribute="userVo">
                <form:label path="" for="name" cssClass="block-label"><spring:message
                        code="user.join.label.name"/></form:label>
                <form:input path="name"/>
                <p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
                    <form:errors path="name"/>
                </p>
                <%--					<input id="name" name="name" type="text" value="">--%>

                <form:label path="" for="email" cssClass="block-label"><spring:message
                        code="user.join.label.email"/></form:label>
                <form:input path="email"/>
                <spring:message code="user.join.label.email.check" var="emailCheck"/>
                <input id="btn-check" type="button" value="${emailCheck}" style="">
                <img id="img-check" src="${pageContext.request.contextPath}/assets/images/check.png"
                     style="width: 24px; height: 24px; vertical-align: bottom; display: none" alt=""/>
                <p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
                    <form:errors path="email"/>
                </p>
                <%--					<input id="email" name="email" type="text" value="">--%>

                <form:label path="" for="password" cssClass="block-label"><spring:message
                        code="user.join.label.password"/></form:label>
                <form:password path="password"/>
                <p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
                    <form:errors path="password"/>
                </p>
                <%--					<input name="password" type="password" value="">--%>

                <fieldset>
                    <legend><spring:message code="user.join.label.gender"/></legend>
                        <%--						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">--%>
                        <%--						<label>남</label> <input type="radio" name="gender" value="male">--%>
                    <spring:message code="user.join.label.gender.female" var="female"/>
                    <form:radiobutton path="gender" label='  ${female}' value="female" checked="checked"/>

                    <spring:message code="user.join.label.gender.male" var="male"/>
                    <form:radiobutton path="gender" label="  ${male}" value="male"/>
                </fieldset>

                <fieldset>
                    <legend><spring:message code="user.join.label.terms"/></legend>
                    <form:checkbox path="" id="agree-prov" name="agreeProv" value="y"/>
                        <%--						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">--%>
                    <label><spring:message code="user.join.label.terms.message"/></label>
                </fieldset>

                <spring:message code="user.join.button.signup" var="signup"/>
                <form:button>${signup}</form:button>
            </form:form>
        </div>
    </div>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"/>
    <c:import url="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
</html>