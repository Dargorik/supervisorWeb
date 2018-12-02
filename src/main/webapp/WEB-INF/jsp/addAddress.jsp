<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Person List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <style>
        .b1 {
            background: beige; /* Синий цвет фона */
            color: black; /* Белые буквы */
            font-size: 9pt; /* Размер шрифта в пунктах */
        }
    </style>
</head>
<body>
<div>
    <button class="b1" onclick="location.href='/addTable/'">Назад</button>
</div>
<h2>Добавление должности</h2>
${message}
<div>
    <form method="post" action="/add/addAddress">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <select name="city">
            <c:forEach items="${cities}" var="city">
                <option value="${city.name}"><c:out value="${city.name}" /></option>
            </c:forEach>
        </select>
        <select name="street">
            <c:forEach items="${streets}" var="street">
                <option value="${street.name}"><c:out value="${street.name}" /></option>
            </c:forEach>
        </select>
        <input type="text" name="houseNumber" placeholder="Номер дома">
        <input type="text" name="numberFloors" placeholder="Кол-во этажей">
        <input type="text" name="numberEntrances" placeholder="Кол-во подъездов">
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список должностей</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Город</th>
                <th>Улица</th>
                <th>Номер дома</th>
                <th>Кол-во этажей</th>
                <th>Кол-во подъездов</th>
            </tr>
            <c:forEach  items="${addresses}" var ="address">
                <tr>
                    <td>${address.idAddress}</td>
                    <td>${address.city.name}</td>
                    <td>${address.street.name}</td>
                    <td>${address.houseNumber}</td>
                    <td>${address.numberFloors}</td>
                    <td>${address.numberEntrances}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>