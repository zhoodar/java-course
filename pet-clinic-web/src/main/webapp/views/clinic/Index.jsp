
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
        <h2>Клиника домашних животних!</h2>
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
            <form action="${pageContext.servletContext.contextPath}/client/view" method="post">
                <input type="submit"   value=" показать всех " >
            </form>
        </div>
    </div>

    <div>
        <c:if test="${content == true}">
            <h3>Результаты поиска: </h3>
            <c:choose>
                <c:when test="${results.size() > 0}">
                    <table border="1">
                        <tr>
                            <td> № </td>
                            <td> Имя клиента </td>
                            <td> Имя питомца </td>
                            <td> Вид питомца </td>
                            <td> Действие </td>
                        </tr>
                        <c:forEach items="${results}" var="result" varStatus="status">
                            <tr>
                                <td>${result.id} </td>
                                <td>${result.nameClient} </td>
                                <td>${result.pet.getName()}</td>
                                <td>${result.pet.getClass().getSimpleName()}</td>
                                <td><a href="#">edit</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h5>Клиенты не найдены!</h5>
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



