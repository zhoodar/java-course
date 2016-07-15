<%--
  Created by IntelliJ IDEA.
  Zhoodar
--%>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Access denied!</title>
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
                <form action="${pageContext.servletContext.contextPath}/add/client" method="get">
                    <input type="submit"  value="добавить клиента" class="control-button">
                </form>
            </div>
            <div>
                <form action="" method="get">
                    <input type="submit"  value=" рекдактировать " class="control-button">
                </form>
            </div>
            <div>
                <form action="${pageContext.servletContext.contextPath}/view/clients" method="get">
                    <input type="submit"   value=" показать всех " class="control-button" >
                </form>
            </div>
            <form action="${pageContext.servletContext.contextPath}/j_spring_security_logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Выйти" class="control-button">
            </form>
        </div>
    </div>

    <div class="content">
       <div>
          <span style="font-size:15px; color: #ff0000;"> ${errorMsg} </span>
       </div>
    </div>

</div>
</body>
</html>



