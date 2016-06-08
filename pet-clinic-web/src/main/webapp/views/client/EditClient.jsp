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

        <h3>Редактировать клиента</h3>
        <div class="edit-content">
            <form action="${pageContext.servletContext.contextPath}/client/edit" method="POST">
                <input type="hidden" name="id" value="${client.id}">
                <table>
                    <tr>
                        <td> Имя клиента </td>
                        <td> Имя питомца </td>
                        <td> Вид питомца </td>
                    </tr>
                    <tr>
                        <td> <input type="text" name="nameClient" value="${client.nameClient}" class="content-text"></td>
                        <td> <input type="text" name="namePet" value="${client.pet.getName()}" class="content-text"></td>
                        <td>
                            <select  name="typePet" class="content-dropdown">
                                <option value="cat">Кошка</option><br>
                                <option value="dog">Собака</option><br>
                            </select>
                        </td>
                    </tr>

                </table>
                <div>
                    <input type="submit" value="Сохранить" class="content-button">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
