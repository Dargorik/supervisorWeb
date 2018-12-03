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
</body>
<div>
    <button class="b1" onclick="location.href='/addTable/'">Назад</button>
</div>

<h2>Добавление регионов сотрудникам</h2>
${message}
<div>
    <form method="post" action="/add/addUserRegions">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <select name="user">
        <c:forEach items="${users}" var="user">
            <option value="${user.username}"><c:out value="${user.firstName} ${user.lastName}" /></option>
        </c:forEach>
    </select><select name="region">
        <c:forEach items="${regions}" var="region">
            <option value="${region.name}"><c:out value="${region.name}" /></option>
        </c:forEach>
    </select>
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список регионов с сотрудниками</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Работник</th>
                <th>Название региона</th>
            </tr>
            <c:forEach  items="${usersRegions}" var ="userRegions">
                <tr>
                    <td>${userRegions.idUserRegions}</td>
                    <td>${userRegions.user.firstName} ${userRegions.user.lastName}</td>
                    <td>${userRegions.region.name}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</html>