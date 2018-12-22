<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Person List</title>
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
    <button class="b1" onclick="location.href='/'">Назад</button>
</div>
<h2>Список работ достпных для должностей</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Сотрудник</th>
                <th>Город</th>
                <th>Улица</th>
                <th>Дом</th>
                <th>Кол-во подъездов в доме</th>
                <th>Кол-во выполненных подъездов</th>
                <th>Тип выполненной работы</th>
                <th>Комментарий</th>
                <th>Дата</th>
            </tr>
            <c:forEach  items="${completedWorks}" var ="completedWork">
                <tr>
                    <td>${completedWork.idCompletedWork}</td>
                    <td>${completedWork.user.firstName} ${completedWork.user.lastName}</td>
                    <td>${completedWork.address.city.name}</td>
                    <td>${completedWork.address.street.name}</td>
                    <td>${completedWork.address.houseNumber}</td>
                    <td>${completedWork.address.numberEntrances}</td>
                    <td>${completedWork.numberCompletedEntrances}</td>
                    <td>${completedWork.typeOfWorkPerformed.name}</td>
                    <td>${completedWork.comment}</td>
                    <td>${completedWork.date}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</html>