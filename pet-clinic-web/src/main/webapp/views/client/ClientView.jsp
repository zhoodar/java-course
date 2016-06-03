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
</head>
<body>
<div>
    <div>
        <h2>Клиника домашных животных!</h2>
        <hr>
    </div>


    <div>
        <form action="${pageContext.servletContext.contextPath}/" method="post">
            <label>Найти клиента</label><br>
            <input type="text" name="name" >
            <input type="checkbox"  name="typeSearch" checked> <label>полное</label>
            <input type="submit" name="search" value="Найти">
        </form>
    </div>

    <div style="float: left;">
        <div>
            <form action="${pageContext.servletContext.contextPath}/client/create" method="get">
                <input type="submit"  value="добавить клиента" >
            </form>
        </div>
        <div>
            <form action="" method="get">
                <input type="submit"  value=" рекдактировать " >
            </form>
        </div>
        <div>
            <form action="${pageContext.servletContext.contextPath}/" method="get">
                <input type="submit"  value=" показать всех " >
            </form>
        </div>
    </div>

    <div>
        <p>Все клиенты</p>
        <table border="1">
            <tr>
                <td> № </td>
                <td> Имя клиента </td>
                <td> Имя питомца </td>
                <td> Вид питомца </td>
            </tr>
            <c:forEach items="${clients}" var="client" varStatus="status">
                <tr>
                    <td>${client.id} </td>
                    <td><a href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">${client.nameClient}</a> </td>
                    <td>${client.pet.getName()}</td>
                    <td>${client.pet.getClass().getSimpleName()}</td>
                    <td><a href="${pageContext.servletContext.contextPath}/client/delete?id=${client.id}"> x </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>


