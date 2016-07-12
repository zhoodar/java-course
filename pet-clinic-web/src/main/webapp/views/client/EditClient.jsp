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
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/css/mainstyle.css"/>
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


        <h3>Редактировать клиента</h3>
        <div class="edit-content">
            <form action="${pageContext.servletContext.contextPath}/clients/edit" method="POST">
                <input type="hidden" name="id" value="${client.id}">
                <table>
                    <tr>
                        <td> Ф.И клиента </td>
                        <td> ID клиента </td>
                    </tr>
                    <tr>
                        <td> <input type="text" name="nameClient" value="${client.nameClient}" class="content-text"></td>
                        <td> ${client.id}</td>
                    </tr>

                </table>
                <div>
                    <input type="submit" value="Изменить" class="content-button" name="save">
                </div>
            </form>
        </div>
        <div class="form-div">
            <b>Добавить нового животного к клиенту</b>
                <form action="${pageContext.servletContext.contextPath}/add/pet" method="POST">
                    <input type="hidden" name="id" value="${client.id}">
                    <div>
                        <label> Имя питомца: </label>
                        <input type="text" name="petName" class="content-text">
                    </div>
                    <div>
                        <label>Выберите питомца: </label>
                        <select  name="typePet" class="content-dropdown">
                            <option value="cat">Кошка</option>
                            <option value="dog">Собака</option>
                        </select>
                    </div>
                    <div>
                        <input type="submit" value="Добавить" name="addPet" class="content-button">
                    </div>
                </form>

            </div>
            <b>Список живтных:</b><br><br>
            <table class="table-content">
                <tr>
                    <td> <b>Имя животного </b> </td>
                    <td> <b>Тип </b> </td>
                    <td> <b>Цвет </b> </td>
                    <td> <b>Возрасть </b> </td>
                    <td> <div style="width: 120px;"> <b>Причина</b> </div> </td>
                </tr>
                    <c:forEach items="${pets}" var="pet" varStatus="status">
                    <tr>
                        <form action="${pageContext.servletContext.contextPath}/delete/pet" method="post">
                            <input type="hidden" name="id" value="${client.id}">
                        <td> ${pet.getName()}</td>
                        <td> ${pet.getClass().getSimpleName()}</td>
                        <input type="hidden" name="petName" value="${pet.getName()}">
                        <td> </td>
                        <td> </td>
                        <td> </td>
                        <td style="border:0"><input type="submit" value="x" class="little-button"></td>
                        </form>
                    </tr>
                    </c:forEach>
            </table>
    </div>
</div>
</body>
</html>
