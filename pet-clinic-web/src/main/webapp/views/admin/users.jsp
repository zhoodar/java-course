<%--
  Created by IntelliJ IDEA.
  User: Zhoodar
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добро пожаловать!</title>
    <link rel="stylesheet" type="text/css"  href="<c:url value="/resources/css/mainstyle.css"/>"/>
</head>
<body class="back-site">
<div class="wrapper">

    <div class="header">
        <h2>Клиника домашних животных!</h2>
    </div>

    <div class="search-panel">
        <div>
            <form action="${pageContext.servletContext.contextPath}/search" method="post">
                <label>Поиск клиента </label>
                <input type="text" name="name" class="search-text" >
                <input type="checkbox"  name="typeSearch" value="off" class="search-checkbox"> <label>полное</label>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" name="search" value="Найти" class="search-button">
            </form>
        </div>
        <a href="${pageContext.servletContext.contextPath}/index"><img src="${pageContext.request.contextPath}/resources/images/dom_white.png"></a>
    </div>


    <div class="control-panel">
        <div class="control-panel-header"></div>
        <div class="control-panel-buttons">
            <div>
                <a href="${pageContext.servletContext.contextPath}/admin/add">add user</a>
            </div>
            <div>
                <a href="${pageContext.servletContext.contextPath}/admin/users">users</a>
            </div>
            <form action="${pageContext.servletContext.contextPath}/j_spring_security_logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Выйти" class="control-button">
            </form>
        </div>
    </div>

    <div class="content">
        <h3>Все пользователи</h3>
        <table  class="table-content"  >
            <tr>
                <td > <b>Id</b> </td>
                <td > <b>Логин</b> </td>
                <td > <b>Действие</b> </td>
            </tr>
            <c:forEach items="${users}" var="user" varStatus="status">
                <tr>
                    <td >${user.id} </td>
                    <td >${user.login} </td>
                    <td ><a href="${pageContext.servletContext.contextPath}/admin/edit/user?id=${user.id}"> edit </a> /
                         <a href="${pageContext.servletContext.contextPath}/admin/delete/user?id=${user.id}"> delete </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>
