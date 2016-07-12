
<%--
  Created by IntelliJ IDEA.
  Zhoodar
  Date: 01.06.2016
--%>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
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
                <input type="submit" name="search" value="Найти" class="search-button">
            </form>
        </div>
        <a href="${pageContext.servletContext.contextPath}/index"><img src="${pageContext.request.contextPath}/resources/images/dom_white.png"></a>
    </div>


    <div class="control-panel">
        <div class="control-panel-header"></div>
        <div class="control-panel-buttons">
            <div>
                <form action="${pageContext.servletContext.contextPath}/clients/add" method="get">
                    <input type="submit"  value="добавить клиента" class="control-button">
                </form>
            </div>
            <div>
                <form action="" method="get">
                    <input type="submit"  value=" рекдактировать " class="control-button">
                </form>
            </div>
            <div>
                <form action="${pageContext.servletContext.contextPath}/clients/view" method="get">
                    <input type="submit"   value=" показать всех " class="control-button" >
                </form>
            </div>
        </div>
    </div>

    <div class="content">
        <c:if test="${content == true}">
            <h3>Результаты поиска: </h3>
            <c:choose>
                <c:when test="${error == true}">
                    <h5> Вы задали пустой запрос :( </h5>
                </c:when>
                <c:when test="${results.size() > 0}">
                    <table class="table-content">
                        <tr>
                            <td> <b>Ф.И. клиента</b> </td>
                            <td> <b>Кол-во животных</b> </td>
                            <td> Действие </td>
                        </tr>
                        <c:forEach items="${results}" var="result" varStatus="status">
                            <tr>
                                <td>${result.nameClient} </td>
                                <td >${result.getPets().size()}</td>
                                <td><a href="${pageContext.servletContext.contextPath}/clients/edit?id=${result.id}">edit</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h5>Клиент не найден!</h5>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${content == false}">
            <p> Добро пожаловать на webapp Клиника питомцов!</p>
        </c:if>
    </div>

</div>
</body>
</html>



