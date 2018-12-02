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

<h2>Добавление региона</h2>
${message}
<div>
    <form method="post" action="/add/addRegion">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <input type="text" name="name" placeholder="Название региона">
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список регионов</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Наименование</th>
                <th>Удаление</th>
            </tr>
            <c:forEach  items="${regions}" var ="region">
                <tr>
                    <td>${region.idRegion}</td>
                    <td>${region.name}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</html>