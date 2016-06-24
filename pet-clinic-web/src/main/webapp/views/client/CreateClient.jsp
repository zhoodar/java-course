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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript">
        function createClient() {
            if (validate()) {
                $('#nameClient').css('background-color', '');

            } else {
                alert("Введите Имя клиента!");
                return false;
            }
        }

        function validate() {
            var result = true;
            if ($('#nameClient').val() == '') {
                $('#nameClient').css('background-color', '#F0F0F0');
                result = false;
            }
            return result;
        }
    </script>
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

        <h3>Добавить клиента</h3>
        <div class="form-div">
        <form action="${pageContext.servletContext.contextPath}/client/create" method="post">
            <div>
                <label> Имя клиента: </label>
                <input type="text" name="nameClient" id="nameClient" class="content-text">
            </div>
            <div>
                <label> Имя питомца: </label>
                <input type="text" name="namePet" class="content-text">
            </div>
            <div>
                <label>Выберите вид питомца: </label>
                <select  name="typePet" class="content-dropdown">
                    <option value="cat">Кошка</option>
                    <option value="dog">Собака</option>
                </select>
            </div>
            <div  >
                <input type="submit" name="add" value="Добавить" class="content-button" onclick="return createClient();">
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>
