<%--
  Created by IntelliJ IDEA.
  Zhoodar
  Date: 01.06.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактировать</title>
</head>
<body>
    <h2>Редактировать клиента</h2>
    <form action="${pageContext.servletContext.contextPath}/client/edit" method="POST">
        <input type="hidden" name="id" value="${client.id}">
        <table>
            <tr>
                <td> № </td>
                <td> Имя клиента </td>
                <td> Имя питомца </td>
                <td> Вид питомца </td>
            </tr>
            <tr>
                <td> ${client.id}  </td>
                <td> <input type="text" name="nameClient" value="${client.nameClient}"></td>
                <td> <input type="text" name="namePet" value="${client.pet.getName()}"></td>
                <td>
                    <select  name="typePet">
                        <option value="cat">Кошка</option><br>
                        <option value="dog">Собака</option><br>
                    </select>
                </td>
            </tr>

        </table>
        <input type="submit" value="Сохранить">
    </form>

</body>
</html>
