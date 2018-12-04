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

<h2>Добавление работ работ доступных должностям</h2>
${message}
<div>
    <form method="post" action="/add/addPositionDuties">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <select name="position">
            <c:forEach items="${positions}" var="position">
                <option value="${position.name}"><c:out value="${position.name}" /></option>
            </c:forEach>
        </select><select name="typeOfWork">
        <c:forEach items="${typesOfWork}" var="typeOfWork">
            <option value="${typeOfWork.name}"><c:out value="${typeOfWork.name}" /></option>
        </c:forEach>
    </select>
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список работ достпных для должностей</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Должность</th>
                <th>Тип работы</th>
            </tr>
            <c:forEach  items="${positionsDuties}" var ="positionDuties">
                <tr>
                    <td>${positionDuties.idPositionDuties}</td>
                    <td>${positionDuties.position.name}</td>
                    <td>${positionDuties.typeOfWork.name}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</html>