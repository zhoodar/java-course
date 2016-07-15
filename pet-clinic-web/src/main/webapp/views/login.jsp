<%--
  Created by IntelliJ IDEA.
  User: Zhoodar
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"  href="<c:url value="/resources/css/mainstyle.css"/>"/>
</head>
<body class="back-site">
<div class="wrapper">
    <div class="error">
        <c:if test="${not empty error}">
            ${error}
        </c:if>
    </div>
    <div id="form_container">
        <c:url value="/j_spring_security_check"  var="loginUrl"/>
        <form name="form_login" action="${loginUrl}" method="post">
            <label> Login: </label>
            <input type="text" name="j_username" value="" />
            <label>Password:</label>
            <input type="password" name="j_password" />

            <div id="inner_form_container">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="checkbox"><label class="check">Remember me</label>
                <input type="submit" name="submit" value="submit" />
            </div>
        </form>
    </div>
</div>
</body>
</html>
