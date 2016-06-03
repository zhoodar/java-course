<%--
  Created by IntelliJ IDEA.
  Zhoodar
  Date: 01.06.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавить клиент!</title>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}">в главную</a>
    <h2>Добавить клиента</h2>
    <form action="${pageContext.servletContext.contextPath}/client/create" method="post">
        <label> Имя клиента: </label>
        <input type="text" name="nameClient"> <br>
        <label>Выберите питомца: </label>
        <select  name="typePet">
            <option value="cat">Кошка</option><br>
            <option value="dog">Собака</option><br>
        </select><br>

        <label> Имя питомца: </label>
        <input type="text" name="namePet"><br>
        <input type="submit" name="add" value="Добавить">
    </form>
</body>
</html>
