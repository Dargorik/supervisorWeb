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

<h2>Добавление типа работы</h2>
${message}
<div>
    <form method="post" action="/add/addTypeOfWork">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <input type="text" name="name" placeholder="Название типа работы">
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список типов работ</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Наименование</th>
            </tr>
            <c:forEach  items="${typesOfWork}" var ="typesfWork">
                <tr>
                    <td>${typesfWork.idTypeOfWork}</td>
                    <td>${typesfWork.name}</td>
                    <td>
                        <button class="b1" onclick="location.href='/add/Position/Delete/'"> Таблица должность</button>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</html>