<%--
  Created by IntelliJ IDEA.
  Zhoodar
  Date: 01.06.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добро пожаловать!</title>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/mainstyle.css"/>
</head>
<body class="back-site">
<div class="wrapper">

    <div class="header">
        <h2>Клиника домашних животных!</h2>
    </div>

    <div class="search-panel">
        <div>
            <form action="${pageContext.servletContext.contextPath}/view" method="post">
                <label>Поиск клиента </label>
                <input type="text" name="name" class="search-text" >
                <input type="checkbox"  name="typeSearch" class="search-checkbox"> <label>полное</label>
                <input type="submit" name="search" value="Найти" class="search-button">
            </form>
        </div>
        <a href="${pageContext.servletContext.contextPath}/view"><img src="${pageContext.request.contextPath}/images/dom_white.png"></a>
    </div>


    <div class="control-panel">
        <div class="control-panel-header"></div>
        <div class="control-panel-buttons">
            <div>
                <form action="${pageContext.servletContext.contextPath}/client/create" method="get">
                    <input type="submit"  value="добавить клиента" class="control-button">
                </form>
            </div>
            <div>
                <form action="" method="get">
                    <input type="submit"  value=" рекдактировать " class="control-button">
                </form>
            </div>
            <div>
                <form action="${pageContext.servletContext.contextPath}/client/view" method="post">
                    <input type="submit"   value=" показать всех " class="control-button" >
                </form>
            </div>
        </div>
    </div>


    <div class="content">
        <h3>Все клиенты</h3>
        <table  class="table-content"  >
            <tr>
                <td > <b>№</b> </td>
                <td > <b>Имя клиента</b> </td>
                <td > <b>Имя питомца</b> </td>
                <td > <b>Вид питомца</b> </td>
            </tr>
            <c:forEach items="${clients}" var="client" varStatus="status">
                <tr>
                    <td >${client.id} </td>
                    <td ><a href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">${client.nameClient}</a> </td>
                    <td >${client.pet.getName()}</td>
                    <td >${client.pet.getClass().getSimpleName()}</td>
                    <td style="border:0"><a href="${pageContext.servletContext.contextPath}/client/delete?id=${client.id}"  about="delete" ><font style="color: red;">x</font>  </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>


