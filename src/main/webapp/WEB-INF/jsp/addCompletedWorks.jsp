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
    <form method="post" action="/add/addCompletedWorks">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <select name="username">
            <c:forEach items="${users}" var="user">
                <option value="${user.username}"><c:out value="${user.firstName} ${user.lastName}" /></option>
            </c:forEach>
        </select><select name="address">
        <c:forEach items="${addresses}" var="address">
            <option value="${address.idAddress}"><c:out value="${address.city.name} ${address.street.name} ${address.houseNumber}" /></option>
        </c:forEach>
        <input type="text" name="numberCompletedEntrances" placeholder="кол-во сделанных подъездов">
        </select><select name="typeOfWorkPerformed">
        <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
            <option value="${typeOfWorkPerformed.name}"><c:out value="${typeOfWorkPerformed.name}" /></option>
        </c:forEach>
        <input type="text" name="comment" placeholder="комментарий">
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
                <th>Сотрудник</th>
                <th>Город</th>
                <th>Улица</th>
                <th>Дом</th>
                <th>Кол-во этажей в доме</th>
                <th>Кол-во выполненных подъездов</th>
                <th>Тип выполненной работы</th>
                <th>Комментарий</th>
                <th>Дата</th>
            </tr>
            <c:forEach  items="${completedWorksRepos}" var ="completedWorkRepos">
                <tr>
                    <td>${completedWorkRepos.idCompletedWork}</td>
                    <td>${completedWorkRepos.user.firstName} ${completedWorkRepos.user.lastName}</td>
                    <td>${completedWorkRepos.address.city.name}</td>
                    <td>${completedWorkRepos.address.street.name}</td>
                    <td>${completedWorkRepos.address.houseNumber}</td>
                    <td>${completedWorkRepos.address.numberFloors}</td>
                    <td>${completedWorkRepos.numberCompletedEntrances}</td>
                    <td>${completedWorkRepos.typeOfWorkPerformed.name}</td>
                    <td>${completedWorkRepos.comment}</td>
                    <td>${completedWorkRepos.timestamp_send}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</html>